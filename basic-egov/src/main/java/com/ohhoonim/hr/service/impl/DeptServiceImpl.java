package com.ohhoonim.hr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ohhoonim.dao.DeptDao;
import com.ohhoonim.hr.service.DeptService;
import com.ohhoonim.hr.service.EmpService;
import com.ohhoonim.vo.DeptVo;

@Service("deptService")
public class DeptServiceImpl implements DeptService{

	@Resource(name="deptDao")
	DeptDao deptDao;

	@Resource(name="empService")
	EmpService empService;
	
	@Override
	public List<DeptVo> deptList(DeptVo vo) {
		
		List<DeptVo> list = deptDao.deptList(vo);
		
		return list;
	}

	@Override
	public int deptAdd(DeptVo vo) {
		return deptDao.deptAdd(vo);
	}

	@Override
	public int checkDeptno(DeptVo vo) {
		return deptDao.checkDeptno(vo);
	}

	@Override
	public int removeDept(DeptVo vo) {
		return deptDao.removeDept(vo);
	}

	@Override
	public List<String> removeDept(String[] deptnos) {
		List<String> unremoved = new ArrayList<String>();
		for(String deptno: deptnos) {
			DeptVo vo = new DeptVo();
			vo.setDeptno(deptno);
			int cnt = empService.hasDept(deptno);
			if (cnt == 0) {
				removeDept(vo);
			} else {
				unremoved.add(deptno);
			}
		}
		return unremoved;
	}
	
	

}
