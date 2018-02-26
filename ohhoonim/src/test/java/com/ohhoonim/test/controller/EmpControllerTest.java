package com.ohhoonim.test.controller;


import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ohhoonim.hr.service.DeptService;
import com.ohhoonim.hr.service.EmpService;
import com.ohhoonim.hr.web.EmpController;
import com.ohhoonim.vo.EmpVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:**/applicationContext.xml")
@WebAppConfiguration
public class EmpControllerTest {
	@InjectMocks private EmpController controller = new EmpController();
	@Mock private EmpService empService;
	@Mock private DeptService deptService;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void empListTest() throws Exception{
		List<EmpVo> list = empService.getEmpList((EmpVo)anyObject());
		when(list).thenAnswer(new Answer<List<EmpVo>>() {

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
		
		mockMvc.perform(post("/hr/empList.do").param("searchName", "test")) 
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("emplist"))
			.andExpect(model().attributeExists("searchName"))
			.andExpect(view().name("hr/empList"));
	}
	
	@Test
	public void empDetailTest() throws Exception {
		mockMvc.perform(post("/hr/empDetail.do"))
		.andExpect(status().isOk())
		
		.andExpect(view().name("hr/empDetail"));
	}
	
	@Test
	public void empAddView() throws Exception {
		mockMvc.perform(post("/hr/empAddView.do"))
			.andExpect(status().isOk())
			.andExpect(view().name("hr/empAdd"));
	}
	
	@Test
	public void empModifyView() throws Exception {
		mockMvc.perform(post("/hr/empModifyView.do"))
			.andExpect(status().isOk())
			.andExpect(view().name("hr/empModifyView"));
	}

	@Test
	public void empAdd1() throws Exception {
		mockMvc.perform(post("/hr/empAdd.do"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/hr/empAddView.do"));
	}
	
	@Test
	public void empAdd2() throws Exception {
		mockMvc.perform( 
					post("/hr/empAdd.do")
						.param("EMPNO" , "123")   
						.param("ENAME" , "kim")  
						.param("AGE"   , "12")   
				)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/hr/empList.do"));
	}
	
	@Test
	public void empListAjaxTest() throws Exception {
		mockMvc.perform( 
					post("/hr/empListAjax.do")
						.param("name" , "ju")   
				)
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.name").value("ju hyoung"))
		;
	}
}

