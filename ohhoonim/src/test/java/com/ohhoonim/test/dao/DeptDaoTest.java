package com.ohhoonim.test.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ohhoonim.dao.DeptDao;
import com.ohhoonim.vo.BoardVo;
import com.ohhoonim.vo.DeptVo;
import com.ohhoonim.vo.StudentVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:**/applicationContext.xml")
@TransactionConfiguration(defaultRollback=true)
@Transactional(value="txManager")
public class DeptDaoTest {
	@Resource(name = "deptDao")
	private DeptDao dao;
	
	private DeptVo vo;
	
	@Before
	public void setUp() {
		vo = new DeptVo();
	}
	
	@Test
	public void studentListTest() {
		vo.setDname("");
		List<DeptVo> list = dao.deptList(vo);
	}
		
		
	

}
