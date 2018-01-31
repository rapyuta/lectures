package com.ohhoonim.test.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ohhoonim.common.util.Utils;
import com.ohhoonim.dao.StudentDao;
import com.ohhoonim.vo.StudentVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:**/applicationContext.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional(value = "txManager")
public class StudentDaoTest {
	@Resource(name = "studentDao")
	private StudentDao dao;

	private StudentVo vo;
	private static final String memberName = "학생1";
	private static final String memberId = "test99";

	@Before
	public void setUp() {
		vo = new StudentVo();
	}

	@Test
	public void listAllTest()  throws Exception{
		add();
		List<StudentVo> list = dao.studentList(vo);
//		assertThat(list.get(0).getMemberName(), is(memberName));
		assertThat(list.get(0).getMemberId(), is(memberId));
	}

	@Test
	public void listSearchTest()  throws Exception{
		add();
		vo.setMemberName(memberName);
		List<StudentVo> list = dao.studentList(vo);
		assertThat(list.get(0).getMemberId(), is(memberId));
	}

	@Test
	public void listCountTest()  throws Exception{
		add();
		vo.setMemberName(memberName);
		int cnt = dao.studentListCount(vo);
		assertThat(cnt, is(1));
	}

	@Test
	public void detailTest()  throws Exception{
		add();
		vo.setMemberId(memberId);
		StudentVo resultVo = dao.studentDetail(vo);
		assertThat(resultVo.getMemberName(), is(memberName));
	}

	@Test
	public void addTest() throws Exception {
		int cnt = add();
		assertThat(cnt, is(1));
	}

	private int add() throws Exception{
		Map<String, String> req = new HashMap<String, String>();
		req.put("memberId", memberId);
		req.put("memberPw", "test");
		req.put("memberName", memberName);
		req.put("memberBirth", "2017-10-21");
		req.put("memberGender", "M");
		req.put("memberEmail", "test@naver.com");
		
		StudentVo vo = Utils.mappingReqparamToVo(req, StudentVo.class);
		
		int cnt = dao.addStudent(vo);
		return cnt;
	}

	@Test
	public void modifyTest() throws Exception {
		add();
		vo.setMemberId(memberId);
		vo.setMemberPw("testtest");
		vo.setMemberEmail("testtest@naver.com");
		int cnt = dao.modifyStudent(vo);
		assertThat(cnt, is(1));
	}

	@Test
	public void removeTest() throws Exception {
		add();
		vo.setMemberId(memberId);
		int cnt = dao.removeStudent(vo);
		assertThat(cnt, is(1));
	}

}
