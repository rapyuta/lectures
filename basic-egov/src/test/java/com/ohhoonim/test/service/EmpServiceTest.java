package com.ohhoonim.test.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;

import com.ohhoonim.dao.EmpDao;
import com.ohhoonim.hr.service.EmpService;
import com.ohhoonim.hr.service.impl.EmpServiceImpl;
import com.ohhoonim.vo.EmpVo;

@RunWith(MockitoJUnitRunner.class)
public class EmpServiceTest {
	@InjectMocks
	private EmpService service = new EmpServiceImpl();
	
	@Mock
	EmpDao dao;
	
	@Test
	public void empListTest() {
		EmpVo vo = new EmpVo();
		
		List<EmpVo> daoList = dao.empList(vo);
		when(daoList).thenAnswer(new Answer<List<EmpVo>>() {
			@Override
			public List<EmpVo> answer(InvocationOnMock invocation) throws Throwable {
				List<EmpVo> list = new ArrayList<EmpVo>();
				
				EmpVo vo1 = new EmpVo();
				EmpVo vo2 = new EmpVo();
				list.add(vo1);
				list.add(vo2);
				
				return list;
			}
			
		});
		
		List<EmpVo> list = service.getEmpList(vo);
		int listsize = list.size();
		assertThat(listsize, is(2)); 
	}
	
	@Test
	public void empModifyTest() {
		EmpVo vo = new EmpVo();
		vo.setEmpno("1234");
		
		int daoResult = dao.empModify(vo);
		when(daoResult).thenReturn(1);
		
		int modifyCount = service.empModify(vo);
		
		assertThat(modifyCount, is(1));
	}
	
	@Test
	public void getEmpTest() {
		EmpVo vo = new EmpVo();
		vo.setEmpno("1234");
		
		Map<String, Object> resultVo = dao.getEmp(vo);
		
		when(resultVo).thenAnswer(new Answer<Map<String, Object>>() {
			@Override
			public Map<String, Object> answer(InvocationOnMock invocation) throws Throwable {
				Map<String, Object> returnVo = new HashMap<String, Object>();
				returnVo.put("empno" , "1234");
				returnVo.put("ename" , "ju");
				returnVo.put("sal"   , "1000");
				returnVo.put("deptno", "20");
				return returnVo;
			}
		});
		
		Map<String, Object> resultEmp = service.getEmp(vo);
		String resultEmpno = (String)resultEmp.get("empno"); // 1234
		assertThat(resultEmpno, is("1234"));
	}
	
	
	
}
