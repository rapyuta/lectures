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
import com.ohhoonim.dao.FilesDao;
import com.ohhoonim.dao.StudentDao;
import com.ohhoonim.vo.FilesVo;
import com.ohhoonim.vo.StudentVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:**/applicationContext.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional(value = "txManager")
public class FilesDaoTest {
	@Resource(name = "filesDao")
	private FilesDao dao;

	private FilesVo vo;
	
	@Before
	public void setUp() {
		vo = new FilesVo();
		
	}

	@Test
	public void selecteFilesTest()  throws Exception{
		vo.setFilesId("");
		dao.selectFiles(vo);
	}
	
	@Test
	public void addFilesTest() throws Exception {
		vo.setFilesNm("test");
		vo.setFilesUfn("test111");
		vo.setFilesSize("100");
		vo.setFilesType("jpg");
		vo.setFilesDl("htpp://");
		dao.addFiles(vo);
	}

	@Test
	public void updateFilesTest() throws Exception {
		vo.setFilesId("123");
		vo.setFilesNm("test");
		vo.setFilesUfn("test111");
		vo.setFilesSize("100");
		vo.setFilesType("jpg");
		vo.setFilesDl("htpp://");
		dao.addFiles(vo);
	}
	
	@Test
	public void deleteFilesTest() throws Exception{
		vo.setFilesId("123");
		dao.deleteFiles(vo);
	}
	

}