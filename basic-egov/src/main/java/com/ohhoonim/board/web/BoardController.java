package com.ohhoonim.board.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ohhoonim.board.service.BoardService;
import com.ohhoonim.common.util.Paging;
import com.ohhoonim.common.util.Utils;
import com.ohhoonim.vo.DeptVo;
import com.ohhoonim.vo.EmpVo;

@Controller
public class BoardController {
	@Resource(name="boardService") //impl 에 있는 Servie 명과 동일해야한다. 
	BoardService boardService;
	
	// http://localhost:8080/[contextroot]/board/boardList.do
	@RequestMapping("/board/boardList.do")
	public String boardList(@RequestParam Map<String, String> req, ModelMap model ) {
		
		String searchType = Utils.toEmptyBlank(req.get("searchType")); //empno, ename
		String searchWord = Utils.toEmptyBlank(req.get("searchWord"));
		String pageNo = Utils.toEmptyBlank(req.get("pageNo"));
		String pageSize = Utils.toEmptyBlank(req.get("pageSize"));

		EmpVo empVo = new EmpVo();
		
		//searchType == empno && searchWord == "" 일 경우 searchType을 ename으로 변경시켜 준다.  
		//searchType = searchType.equals("empno") && searchWord.equals("") ? "ename" : searchType;
		
		empVo.setSearchType(searchType);
		empVo.setSearchWord(searchWord);		
		
//		List<EmpVo> list = boardService.boardList(empVo);
// 		int totalCount = boardService.boardListCount(empVo);
		
		Map<String, Object> listResult = boardService.boardList(empVo);
		
		List<EmpVo> list = (List<EmpVo>)listResult.get(BoardService.KEY_LIST);
		int totalCount   = (int)listResult.get(BoardService.KEY_TOTAL_CNT);

		model.addAttribute("list", list);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchWord", searchWord);
		
		Paging paging = new Paging();
		//paging 처리시에는 3가지 정보만 세팅해주면 된다.
		paging.setPageNo(Integer.parseInt(pageNo));
		paging.setPageSize(Integer.parseInt(pageSize));		
		// TODO : DB에서 전체게시글수를 가져오는 쿼리로 처리를 해줘야한다.		
		// paging.setTotalCount(totalCount);
		
		model.addAttribute("paging", paging);
		
		//\src\main\webapp\WEB-INF\jsp\board\boardList.jsp <- 실제경로
		return "board/boardList";
	}
	
	@RequestMapping("/board/boardDetail.do")
	public String boardDetail(@RequestParam Map<String, String> req, ModelMap model ) {
		String empno = req.get("empno");
				
		EmpVo empvo = boardService.boardDetail(empno);
		//jsp 에서 el 문법 사용시 empvo 사용가능 		
		model.addAttribute("empvo", empvo);
		
		//\src\main\webapp\WEB-INF\jsp\board\boardView.jsp <- 실제경로
		return "board/boardView";
	}
	
	
	@RequestMapping("/board/boardRemove.do")
	public String boardRemove(@RequestParam Map<String, String> req, ModelMap model ) {
		String empno = req.get("empno");
		
		int resultCnt = boardService.boardRemove(empno);
		// insert, update, delete 쿼리문에 대한 결과값은
		// int 형이다
		// ==> x 행이 삭제되었습니다.
		
		//redirect 가 붙어있으면 jsp가 아니라 url을 호출하는 거임.
		return "redirect:/board/boardList.do";
	}
	
	@RequestMapping("/board/boardAddView.do")
	public String boardAddView(@RequestParam Map<String, String> req, ModelMap model ) {
		
		List<DeptVo> deptList =  boardService.deptList();
		
		model.addAttribute("deptList", deptList);
		
		//\src\main\webapp\WEB-INF\jsp\board\boardAddView.jsp <- 실제경로
		return "board/boardAdd";
	}
	
	@RequestMapping("/board/boardAdd.do")
	public String boardAdd(@RequestParam Map<String, String> req, RedirectAttributes reAttr) {
		
		String returnStr = "redirect:/board/boardList.do";

		String empno = Utils.toEmptyBlank(req.get("empno")); 
		String ename = Utils.toEmptyBlank(req.get("ename"));
		String sal = Utils.toEmptyBlank(req.get("sal"));
		String manager = Utils.toEmptyBlank(req.get("manager"));
		String deptno = Utils.toEmptyBlank(req.get("deptno"));
		String hiredate = Utils.dateFommatter(req.get("hiredate"));
		String age = Utils.toEmptyBlank(req.get("age"));
		String comm = Utils.toEmptyBlank(req.get("comm"));
		
		if( empno == null || empno.length() < 1 
			|| ename == null || ename.length() < 1
			|| age == null || age.length() < 1)	{
			
			req.put("errorMsg", "필수입력값 중 누락된 항목이 있습니다.");
			
			reAttr.addFlashAttribute("rtnParams", req);
			returnStr = "redirect:/board/boardAddView.do";				
		}				
		else
		{		
			EmpVo vo = new EmpVo();
			vo.setEmpno(empno);
			vo.setEname(ename);
			vo.setSal(sal);
			vo.setManager(manager);
			vo.setDeptno(deptno);
			vo.setHiredate(hiredate);
			vo.setAge(age);
			vo.setComm(comm);
			
			int resultCnt = boardService.boardAdd(vo);
		}
		
		return returnStr;
	}
	
	@RequestMapping("/board/boardModifyView.do")
	public String boardModifyView(@RequestParam Map<String, String> req, ModelMap model ) {
				
		String empno = req.get("empno");		
		
		EmpVo empvo = boardService.boardDetail(empno);
		//select 태그의 dept 데이터 넘기기
		List<DeptVo> deptList =  boardService.deptList();	
		
		//jsp 에서 el 문법 사용시 empvo 사용가능 		
		model.addAttribute("empvo", empvo);	
		model.addAttribute("deptList", deptList);
		
		//\src\main\webapp\WEB-INF\jsp\board\boardModifyView.jsp <- 실제경로
		return "board/boardModify";
	}
	
	@RequestMapping("/board/boardModify.do")
	public String boardModify(@RequestParam Map<String, String> req, RedirectAttributes reAttr) {
		
		String empno = Utils.toEmptyBlank(req.get("empno"));
		
		String returnStr = "redirect:/board/boardDetail.do?empno=" +empno;
		
		String ename = Utils.toEmptyBlank(req.get("ename"));
		String sal = Utils.toEmptyBlank(req.get("sal"));
		String manager = Utils.toEmptyBlank(req.get("manager"));
		String deptno = Utils.toEmptyBlank(req.get("deptno"));
		String hiredate = Utils.dateFommatter(req.get("hiredate"));
		String age = Utils.toEmptyBlank(req.get("age"));
		String comm = Utils.toEmptyBlank(req.get("comm"));
		
		if( empno == null || empno.length() < 1 
			|| ename == null || ename.length() < 1
			|| age == null || age.length() < 1)	{
			
			req.put("errorMsg", "필수입력값 중 누락된 항목이 있습니다.");
			
			reAttr.addFlashAttribute("rtnParams", req);
			returnStr = "redirect:/board/boardModifyView.do";				
		}	
		else 
		{		
			EmpVo vo = new EmpVo();
			vo.setEmpno(empno);
			vo.setEname(ename);
			vo.setSal(sal);
			vo.setManager(manager);
			vo.setDeptno(deptno);
			vo.setHiredate(hiredate);
			vo.setAge(age);
			vo.setComm(comm);
			
			int resultCnt = boardService.boardModify(vo);
		}
		
		return returnStr;
	}

}
