package com.ohhoonim.hr.service;

import java.util.List;

import com.ohhoonim.vo.DeptVo;

public interface DeptService {

	List<DeptVo> deptList(DeptVo vo);

	int deptAdd(DeptVo vo);

	int checkDeptno(DeptVo vo);

	int removeDept(DeptVo vo);

	List<String> removeDept(String[] deptnos);
	
}
