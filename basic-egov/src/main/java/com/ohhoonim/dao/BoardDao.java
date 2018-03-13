package com.ohhoonim.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ohhoonim.vo.DeptVo;
import com.ohhoonim.vo.EmpVo;

@Repository("boardDao")
public class BoardDao extends Mapper {
	public List<EmpVo> boardList(EmpVo empvo) {
		return selectList("boardList", empvo);
	}

	public EmpVo boardDetail(String empno) {
		return selectOne("boardDetail", empno);
	}

	public int boardRemove(String empno) {
		return delete("boardRemove", empno);
	}

	public int boardAdd(EmpVo vo) {
		return insert("boardAdd", vo);
	}

	public List<DeptVo> deptList() {
		return selectList("Board.deptList", null);
	}

	public int boardModify(EmpVo vo) {		
		return update("boardModify", vo);
	}

	public int boardListCount(EmpVo empVo) {
		return selectOne("boardListCount2", empVo);
	}
}
