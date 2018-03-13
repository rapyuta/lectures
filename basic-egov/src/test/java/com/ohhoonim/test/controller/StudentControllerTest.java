package com.ohhoonim.test.controller;


import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import com.ohhoonim.stdt.service.StudentService;
import com.ohhoonim.stdt.web.StudentController;
import com.ohhoonim.vo.StudentVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:**/applicationContext.xml")
@WebAppConfiguration
public class StudentControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private StudentService studentService;
	
	@InjectMocks
	private StudentController controller = new StudentController();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void listTest() throws Exception {
		Map<String, Object> listMap = studentService.studentList((StudentVo)anyObject());
		when(listMap).thenAnswer(new Answer<Map<String, Object>>(){
			@Override
			public Map<String, Object> answer(InvocationOnMock invocation) throws Throwable {
				List<StudentVo> list = new ArrayList<StudentVo>();
				for(int i = 0; i < 10; i++) {
					StudentVo stdtVo = new StudentVo();
					stdtVo.setMemberName("name" + i);
					list.add(stdtVo);
				}
						
				int listCount = list.size();
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("list", list);
				resultMap.put("listCount", listCount);
				
				return resultMap;
			}
		});
		
		mockMvc.perform(get("/stdt/studentList.do"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists(StudentController.KEY_LIST))
				//.andExpect(model().attribute(StudentController.KEY_LIST_COUNT, 10))
				.andExpect(model().attributeExists("memberName"))
				.andExpect(view().name("stdt/studentList"))
				;
		
		
	}
	@Test
	public void detailTest()  throws Exception {
		MockHttpServletRequestBuilder calledUrl = post("/stdt/studentDetail.do") 
				.param("memberId", "1000") 
				;
		
		StudentVo stdtVo = studentService.studentDetail((StudentVo)anyObject());
		when(stdtVo).thenReturn(new StudentVo());
		
		mockMvc.perform(calledUrl)
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("stdt"))
				.andExpect(view().name("stdt/studentDetail"))
		;
	}
	
	@Test
	public void addViewTest()  throws Exception{
		mockMvc.perform(get("/stdt/studentAddView.do"))
				.andExpect(status().isOk())
				.andExpect(view().name("stdt/studentAddView"))
		;
	}

	@Test
	public void addTest()  throws Exception{
		MockHttpServletRequestBuilder calledUrl = post("/stdt/studentAdd.do")
				.param("memberId","test")
				.param("memberPw","test")
				.param("memberName","test")
				.param("memberBirth","2017-01-25")
				.param("memberGender","m")
				.param("memberEmail","t")
				;

		int addedCnt = studentService.addStudent((StudentVo)anyObject());
		
		when(addedCnt).thenReturn(1);		
		
		mockMvc.perform(calledUrl)
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/stdt/studentList.do"))
		;
	}
	@Test
	public void addTest2()  throws Exception{
		//id,pw,name 누락시 값을 다시 돌려줌
		MockHttpServletRequestBuilder calledUrl = post("/stdt/studentAdd.do")
				.param("memberId","")
				.param("memberPw","test")
				.param("memberName","test")
				.param("memberBirth","2017-01-25")
				.param("memberGender","m")
				.param("memberEmail","t")
				;
		
		mockMvc.perform(calledUrl)
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("redirectParam"))
		.andExpect(redirectedUrl("/stdt/studentAddView.do"))
		;
	}
	@Test
	public void modifyViewTest()  throws Exception{
		MockHttpServletRequestBuilder calledUrl = post("/stdt/studentModifyView.do") 
				.param("memberId", "1000") 
				;
		
		StudentVo stdtVo = studentService.studentDetail((StudentVo)anyObject());
		when(stdtVo).thenReturn(new StudentVo());
		
		mockMvc.perform(calledUrl)
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("stdt"))
				.andExpect(view().name("stdt/studentModifyView"))
		;
	}
	@Test
	public void modifyTest()  throws Exception{
		MockHttpServletRequestBuilder calledUrl = post("/stdt/studentModify.do")
				.param("memberId","test")
				.param("memberPw","test")
				.param("memberName","test")
				.param("memberBirth","2017-01-25")
				.param("memberGender","m")
				.param("memberEmail","t")
				;
		
		int addedCnt = studentService.modifyStudent((StudentVo)anyObject());
		when(addedCnt).thenReturn(1);		
		
		mockMvc.perform(calledUrl)
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/stdt/studentDetail.do"))
		;
	}
	@Test
	public void modifyTest2()  throws Exception{
		//id,pw,name 누락시 값을 다시 돌려줌
		MockHttpServletRequestBuilder calledUrl = post("/stdt/studentModify.do")
				.param("memberId","")
				.param("memberPw","test")
				.param("memberName","test")
				.param("memberBirth","2017-01-25")
				.param("memberGender","m")
				.param("memberEmail","t")
				;
		
		mockMvc.perform(calledUrl)
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("redirectParam"))
		.andExpect(redirectedUrl("/stdt/studentModifyView.do"))
		;
	}
	@Test
	public void removeTest()  throws Exception{
		MockHttpServletRequestBuilder calledUrl = post("/stdt/studentRemove.do")
				.param("memberId","test")
				;
		
		mockMvc.perform(calledUrl)
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/stdt/studentList.do"))
		;
	}
}