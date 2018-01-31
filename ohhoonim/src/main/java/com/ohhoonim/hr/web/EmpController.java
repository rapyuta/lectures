package com.ohhoonim.hr.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ohhoonim.common.util.Utils;
import com.ohhoonim.hr.service.DeptService;
import com.ohhoonim.hr.service.EmpService;
import com.ohhoonim.vo.DeptVo;
import com.ohhoonim.vo.EmpVo;

@Controller
public class EmpController {
	
	@Resource(name="empService")
	EmpService empService ;

	@Resource(name="deptService")
	DeptService deptService ;
	
	@RequestMapping("/hr/empList.do")
	public String empList(@RequestParam HashMap<String, String> req, ModelMap model) {
		
		String searchName = req.get("searchName") == null ? "": req.get("searchName");
		
		EmpVo vo = new EmpVo();
		vo.setEname(searchName);
		
		List<EmpVo> list = empService.getEmpList(vo);
		model.addAttribute("emplist", list);
		
		model.addAttribute("searchName", searchName);
		
		return "hr/empList";
	}
	
	@RequestMapping("/hr/empListAjax.do")
	@ResponseBody
	public Object empListAjax(@RequestParam HashMap<String, String> req) {
		
		String name = req.get("name");
		name += " hyoung";
		
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("name", name);
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		json.put("list", list);
		
		
		return json;
	}
	
	@RequestMapping("/hr/empXls.do")
	public ModelAndView empListXls(@RequestParam HashMap<String, String> req) throws Exception {
	 
		String searchName = req.get("searchName") == null ? "": req.get("searchName");
		
		EmpVo vo = new EmpVo();
		vo.setEname(searchName);
		
		List<EmpVo> lists = empService.getEmpList(vo);
		
	 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empList", lists);
	 
		return new ModelAndView("simplePOIExcelView", "empMap", map);
	}
	
	@RequestMapping("/hr/empDetail.do")
	public String empDetail(@RequestParam Map<String, String> req, ModelMap model) {
		String empno = req.get("empno") == null? "": req.get("empno");

		EmpVo vo = new EmpVo();
		vo.setEmpno(empno);
		Map<String, Object> resultEmp = empService.getEmp(vo);
		
		model.addAttribute("empno", empno);
		model.addAttribute("emp", resultEmp);
		
		return "hr/empDetail";
	}
	
	@RequestMapping("/hr/empAddView.do")
	public String empAddView(ModelMap model) {
//		
//		EmpVo vo = new EmpVo();
//		List<EmpVo> list = empService.getEmpList(vo);
//		model.addAttribute("empList", list);
//		
//		DeptVo dvo = new DeptVo();
//		dvo.setDname("");
//		List<DeptVo> dlist = deptService.deptList(dvo);
//		model.addAttribute("deptList", dlist);
//		
		return "hr/empAdd";
	}
	@RequestMapping("/hr/empAdd.do")
	public String empAdd(@RequestParam Map<String, String> req, RedirectAttributes reAttr) {
		String rtnUrl = "redirect:/hr/empList.do";
		
		String empno = Utils.toEmptyBlank(req.get("EMPNO"));
		String ename = Utils.toEmptyBlank(req.get("ENAME"));
		String age   = Utils.toEmptyBlank(req.get("AGE"));
		
		if (empno.equals("") || ename.equals("") || age.equals("")) {
			req.put("msg", "empno, ename and age value is required.");
			reAttr.addFlashAttribute("rtnParams", req);
			rtnUrl = "redirect:/hr/empAddView.do";
		} else {
			EmpVo vo = new EmpVo();
			vo.setEmpno(empno);
			vo.setEname(ename);
			vo.setAge(age);
			vo.setSal(Utils.toEmptyBlank(req.get("SAL")));
			vo.setManager(Utils.toEmptyBlank(req.get("MANAGER")));
			vo.setDeptno(Utils.toEmptyBlank(req.get("DEPTNO")));
			vo.setHiredate(Utils.toEmptyBlank(req.get("HIREDATE")));
			vo.setComm(Utils.toEmptyBlank(req.get("COMM")));

			int cnt = empService.empAdd(vo);
		}
				
		return rtnUrl;
	}
	
	@RequestMapping("/hr/checkDupEmpno.do")
	@ResponseBody
	public Object checkDupEmpno(@RequestParam Map<String, String> req) {
		Map<String, Object> json = new HashMap<String, Object>();
		
		String empno = Utils.toEmptyBlank(req.get("empno"));
		
		EmpVo vo = new EmpVo();
		vo.setEmpno(empno);
		int resultCount = empService.checkEmpno(vo);
		
		if (resultCount > 0) {
			json.put("isDup", true);
		} else {
			json.put("isDup", false);
		}
		// => {isDup: true}
		return json;
	}
	
	@RequestMapping("/hr/empModifyView.do")
	public String empModifyView(@RequestParam Map<String, String> req, ModelMap model) {
		
		String empno = req.get("empno") == null? "": req.get("empno");

		EmpVo empVo = new EmpVo();
		empVo.setEmpno(empno);
		Map<String, Object> resultEmp = empService.getEmp(empVo);
		///////////////////////////////////////////////////////////////////
		EmpVo vo = new EmpVo();
		List<EmpVo> list = empService.getEmpList(vo);
		model.addAttribute("empList", list);
		
		DeptVo dvo = new DeptVo();
		dvo.setDname("");
		List<DeptVo> dlist = deptService.deptList(dvo);
		model.addAttribute("deptList", dlist);
		//////////////////////////////////////////////////////////////////
		
		model.addAttribute("emp", resultEmp);
		
		return "hr/empModifyView";
	}
	@RequestMapping("/hr/empModify.do")
	public String empModify(@RequestParam Map<String, String> req, RedirectAttributes reAttr) {
		String rtnUrl = "redirect:/hr/empDetail.do?empno=" + Utils.toEmptyBlank(req.get("EMPNO"));
		
		String empno = Utils.toEmptyBlank(req.get("EMPNO"));
		String ename = Utils.toEmptyBlank(req.get("ENAME"));
		String age   = Utils.toEmptyBlank(req.get("AGE"));
		
		if (empno.equals("") || ename.equals("") || age.equals("")) {
			req.put("msg", "empno, ename and age value is required.");
			reAttr.addFlashAttribute("rtnParams", req);
			rtnUrl = "redirect:/hr/empModify.do";
		} else {
			EmpVo vo = new EmpVo();
			vo.setEmpno(empno);
			vo.setEname(ename);
			vo.setAge(age);
			vo.setSal(Utils.toEmptyBlank(req.get("SAL")));
			vo.setManager(Utils.toEmptyBlank(req.get("MANAGER")));
			vo.setDeptno(Utils.toEmptyBlank(req.get("DEPTNO")));
			vo.setHiredate(Utils.toEmptyBlank(req.get("HIREDATE")));
			vo.setComm(Utils.toEmptyBlank(req.get("COMM")));

			int cnt = empService.empModify(vo);
			
		}
				
		return rtnUrl;
	}
	
	@RequestMapping("/hr/empRemove.do")
	public String empRemove(@RequestParam Map<String, String> req ) {
		String empno = req.get("empno");
		
		EmpVo empVo = new EmpVo();
		empVo.setEmpno(empno);
		int resultCnt = empService.empRemove(empVo);
		
		return "redirect:/hr/empList.do";
	}
}















