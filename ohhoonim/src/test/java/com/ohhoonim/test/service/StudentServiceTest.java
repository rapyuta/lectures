package com.ohhoonim.test.service;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doAnswer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.ohhoonim.board.service.BoardService;
import com.ohhoonim.board.service.impl.BoardServiceImpl;
import com.ohhoonim.dao.BoardDao;
import com.ohhoonim.dao.StudentDao;
import com.ohhoonim.stdt.service.StudentService;
import com.ohhoonim.stdt.service.impl.StudentServiceImpl;
import com.ohhoonim.vo.BoardVo;
import com.ohhoonim.vo.StudentVo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;


@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
	
	@InjectMocks
	private StudentService service = new StudentServiceImpl();
	
	@Mock
	StudentDao dao;
	
	StudentVo vo;
	
	@Before
	public void setUp() {
		vo = new StudentVo();
	}
	/*
	@Test
	public void studentListTest() {
		vo.setMemberName("test");
		
		List<StudentVo> studentList = dao.studentList(vo);
		when(studentList).thenAnswer(new Answer<List<StudentVo>>() {
			@Override
			public List<StudentVo> answer(InvocationOnMock invocation) throws Throwable {
				List<StudentVo> list = new ArrayList<StudentVo>();
				StudentVo stdVo = new StudentVo();
				stdVo.setMemberName("test");
				stdVo.setMemberId("1000");
				list.add(stdVo);
				return list;
			}
		});
		int listCount = dao.studentListCount(vo);
		when(listCount).thenReturn(1);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", studentList);
		resultMap.put("listCount", listCount);
		
		Map<String, Object> expectedMap = service.studentList(vo);
		
		List<StudentVo> expectedList = (List<StudentVo>)expectedMap.get("list");
		int expectedListCount = (int)expectedMap.get("listCount");
		assertThat(expectedList.get(0).getMemberId(), is("1000"));
		assertThat(expectedListCount, is(1));
	}
	*/
	@Test
	public void studentDetailTest() {
		StudentVo resultVo = dao.studentDetail(vo);
		when(resultVo).thenAnswer(new Answer<StudentVo>() {
			@Override
			public StudentVo answer(InvocationOnMock invocation) throws Throwable {
				StudentVo vo = new StudentVo();
				vo.setMemberId("1000");
				vo.setMemberName("test");
				return vo;
			}
		});
		
		StudentVo stdVo = service.studentDetail(vo);
		assertThat(stdVo.getMemberId(), is("1000"));
	}
	@Test
	public void addStudentTest() {
		int insertCnt = dao.addStudent(vo);
		when(insertCnt).thenReturn(1);
		int expectedCnt = service.addStudent(vo);
		assertThat(expectedCnt, is(1));
	}
	@Test
	public void modifyStudentTest() {
		int updatedCnt = dao.modifyStudent(vo);
		when(updatedCnt).thenReturn(1);
		int expectedCnt = service.modifyStudent(vo);
		assertThat(expectedCnt, is(1));
	}
	@Test
	public void removeStudentTest() {
		int deletedCnt = dao.removeStudent(vo);
		when(deletedCnt).thenReturn(1);
		int expectedCnt = service.removeStudent(vo);
		assertThat(expectedCnt, is(1));
		
	}
	
}


