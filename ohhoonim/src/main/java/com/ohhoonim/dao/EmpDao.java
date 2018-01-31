package com.ohhoonim.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ohhoonim.vo.EmpVo;

@Repository("empDao")
public class EmpDao extends Mapper {
	
	public List<EmpVo> empList(EmpVo vo) {
		return selectList("empList", vo);
	}
	
	public Map<String, Object> getEmp(EmpVo vo) {
		return selectOne("getEmp", vo);
	}

	public int hasDept(String deptno) {
		return selectOne("hasDept", deptno);
	}

	public int empAdd(EmpVo vo) {
		return insert("empAdd", vo);
	}

	public int checkEmpno(EmpVo vo) {
		return selectOne("checkEmpno", vo);
	}

	public int empModify(EmpVo vo) {
		return update("empModify", vo);
	}

	public List<EmpVo> noDeptEmpList(EmpVo empVo) {
		return selectList("containsNoDeptEmpList", empVo);
	}

	public int empRemove(EmpVo empVo) {
		return delete("empRemove", empVo);
	}
}
