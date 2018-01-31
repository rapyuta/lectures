package com.ohhoonim.test.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ohhoonim.dao.EmpDao;
import com.ohhoonim.vo.EmpVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:**/applicationContext.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional(value = "txManager")
public class EmpDaoTest {
	@Resource(name="empDao")
	public EmpDao dao;
	
	@Test
	public void empListTest() {
		EmpVo vo = new EmpVo();
		vo.setEname("J");
		List<EmpVo> list = dao.empList(vo);
		
		for (EmpVo row: list) {
			System.out.println(row.getEname());
		}
	}
	
	@Test
	public void getEmpTest() {
		EmpVo vo = new EmpVo();
		vo.setEmpno("1234");
		Map<String, Object> result = dao.getEmp(vo);
		
		System.out.println(result.get("EMPNO"));
	}
	
	@Test
	public void hasDeptTest() {
		String deptno = "10";
		int result = dao.hasDept(deptno);
		System.out.println(result);
	}
	
	
}





















