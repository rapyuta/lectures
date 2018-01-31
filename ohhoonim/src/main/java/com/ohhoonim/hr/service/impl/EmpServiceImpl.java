package com.ohhoonim.hr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ohhoonim.dao.EmpDao;
import com.ohhoonim.hr.service.EmpService;
import com.ohhoonim.vo.DeptVo;
import com.ohhoonim.vo.EmpVo;

@Service("empService")
public class EmpServiceImpl implements EmpService {
	
	@Resource(name="empDao")
	EmpDao dao;
	
	@Override
	public List<EmpVo> getEmpList(EmpVo vo) {
		List<EmpVo> list = dao.empList(vo);
		return list;
	}

	@Override
	public List<EmpVo> deptEmpList(EmpVo vo) {
		List<EmpVo> list = dao.empList(vo);
		return list;
	};
	
	@Override
	public Map<String, Object> getEmp(EmpVo vo) {
		
		Map<String, Object> resultVo = dao.getEmp(vo);
		
		return resultVo;
	}

	@Override
	public int hasDept(String deptno) {
		return dao.hasDept(deptno);
	}

	@Override
	public int empAdd(EmpVo vo) {
		return dao.empAdd(vo);
	}

	@Override
	public int checkEmpno(EmpVo vo) {
		return dao.checkEmpno(vo);
	}

	@Override
	public int empModify(EmpVo vo) {
		return dao.empModify(vo);
	}

	@Override
	public List<EmpVo> noDeptEmpList(EmpVo empVo) {
		List<EmpVo> list = dao.noDeptEmpList(empVo);
		return list;
	}

	@Override
	public int empRemove(EmpVo empVo) {
		return dao.empRemove(empVo);
	}

}





