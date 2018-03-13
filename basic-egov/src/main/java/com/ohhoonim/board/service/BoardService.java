package com.ohhoonim.board.service;

import java.util.List;
import java.util.Map;

import com.ohhoonim.vo.DeptVo;
import com.ohhoonim.vo.EmpVo;

public interface BoardService {

	//List<EmpVo> boardList(EmpVo empvo);
	public final static String KEY_LIST = "list";
	public final static String KEY_TOTAL_CNT = "totalCount";
	
	//List<EmpVo> boardList(EmpVo empvo);
	//int boardListCount(EmpVo empVo);
	Map<String, Object> boardList(EmpVo empvo);
	
	EmpVo boardDetail(String empno);

	int boardRemove(String empno);

	int boardAdd(EmpVo vo);

	List<DeptVo> deptList();

	int boardModify(EmpVo vo);
	
//	int boardListCount(EmpVo empVo);
}
