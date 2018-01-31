package com.ohhoonim.test.controller;


import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ohhoonim.board.service.BoardService;
import com.ohhoonim.board.web.BoardController;
import com.ohhoonim.hr.service.DeptService;
import com.ohhoonim.hr.service.EmpService;
import com.ohhoonim.hr.web.DeptController;
import com.ohhoonim.hr.web.EmpController;
import com.ohhoonim.vo.BoardVo;
import com.ohhoonim.vo.DeptVo;
import com.ohhoonim.vo.EmpVo;

import oracle.jdbc.proxy.annotation.Post;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:**/applicationContext.xml")
@WebAppConfiguration
public class DeptControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private DeptService deptService;
	
	@InjectMocks
	private DeptController controller = new DeptController();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void deptListTest() throws Exception {
		
		List<DeptVo> deptList = new ArrayList<DeptVo>();
		when(deptService.deptList((DeptVo)anyObject())).thenReturn(deptList);
		
		mockMvc.perform(get("/hr/deptList.do", "test"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("dname"))
				.andExpect(model().attributeExists("deptList"))
				.andExpect(view().name("hr/deptList"));
	}

	@Test
	public void deptAddViewTest() throws Exception {
		mockMvc.perform(get("/hr/deptAddView.do"))
			.andExpect(status().isOk())
			.andExpect(view().name("hr/deptAdd"))
			;
	}
	
	@Test
	public void deptAddTest() throws Exception  {
		MockHttpServletRequestBuilder calledUrl = post("/hr/deptAdd.do")
				.param("DEPTNO", "test")
				.param("DNAME", "test")
				.param("LOC", "test")
				;
		mockMvc.perform(calledUrl)
				.andExpect(redirectedUrl("/hr/deptList.do")) ;
	}
	@Test
	public void deptAddTest2() throws Exception  {
		mockMvc.perform(get("/hr/deptAdd.do"))
		.andExpect(flash().attributeExists("rtnParam"))
		.andExpect(redirectedUrl("/hr/deptAddView.do")) ;
	}
	
}


