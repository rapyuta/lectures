package com.ohhoonim.hr.service;

import java.util.List;
import java.util.Map;

import com.ohhoonim.vo.EmpVo;

public interface EmpService {
	public List<EmpVo> getEmpList(EmpVo vo);
	public List<EmpVo> deptEmpList(EmpVo empVo);
	public Map<String, Object> getEmp(EmpVo vo);
	public int hasDept(String deptno);
	public int empAdd(EmpVo vo);
	public int checkEmpno(EmpVo vo);
	public int empModify(EmpVo vo);
	public List<EmpVo> noDeptEmpList(EmpVo empVo);
	public int empRemove(EmpVo empVo);
}
