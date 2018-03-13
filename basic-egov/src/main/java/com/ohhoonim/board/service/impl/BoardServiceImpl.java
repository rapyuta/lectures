package com.ohhoonim.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.ohhoonim.hr.service.DeptService;

import com.ohhoonim.board.service.BoardService;
import com.ohhoonim.dao.BoardDao;
import com.ohhoonim.vo.DeptVo;
import com.ohhoonim.vo.EmpVo;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Resource(name="boardDao")
	BoardDao boardDao;
	
	@Resource(name="deptService")
	DeptService deptService;
	
	@Override
	//public List<EmpVo> boardList(EmpVo empvo) {
	public Map<String, Object> boardList(EmpVo empvo){
		List<EmpVo> list = boardDao.boardList(empvo);
		//return list;
		int totalCount = boardDao.boardListCount(empvo);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(KEY_LIST, list);
		resultMap.put(KEY_TOTAL_CNT, totalCount);

		return resultMap;
	}

	@Override
	public EmpVo boardDetail(String empno) {
		EmpVo empvo = boardDao.boardDetail(empno);
		return empvo;
	}

	@Override
	public int boardRemove(String empno) {
		int resultCnt = boardDao.boardRemove(empno);
		return resultCnt;
	}

	@Override
	public int boardAdd(EmpVo vo) {
		int resultCnt = boardDao.boardAdd(vo);
		return resultCnt;
	}

	@Override
	public List<DeptVo> deptList() {
		//List<DeptVo> deptList = boardDao.deptList();
		DeptVo vo = new DeptVo();
		vo.setDname("");
		List<DeptVo> deptList = deptService.deptList(vo);
		//List<DeptVo> deptList = boardDao.deptList();
		return deptList;
	}

	@Override
	public int boardModify(EmpVo vo) {
		int resultCnt = boardDao.boardModify(vo);
		return resultCnt;
	}

//	@Override
//	public int boardListCount(EmpVo empVo) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	
	
}
