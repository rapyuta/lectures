package com.ohhoonim.stdt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ohhoonim.common.service.FilesService;
import com.ohhoonim.dao.StudentDao;
import com.ohhoonim.stdt.service.StudentService;
import com.ohhoonim.vo.StudentVo;

import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
	@Resource(name="studentDao")
	StudentDao dao;
	@Resource(name="filesService")
	FilesService fileService;
	
	@Override
	public Map<String, Object> studentList(StudentVo vo) {
		List<StudentVo> list = dao.studentList(vo);
		int listCount = dao.studentListCount(vo);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("listCount", listCount);
				
		return resultMap;		
	}

	@Override
	public StudentVo studentDetail(StudentVo vo) {
		StudentVo resultVo = dao.studentDetail(vo);
		return resultVo;
	}

	@Override
	public int addStudent(StudentVo vo) {
		int insertCnt = dao.addStudent(vo);
		return insertCnt;
	}

	@Override
	//public int addStudent(StudentVo vo) {
	public int addStudent(StudentVo vo, MultipartFile file, String contextPath) throws Exception {
			String fileId = fileService.addFiles(file, contextPath);
			vo.setFilesId(fileId);
		int insertCnt = dao.addStudent(vo);
		return insertCnt;
	}

	@Override
	public int modifyStudent(StudentVo vo) {
		int updatedCnt = dao.modifyStudent(vo);
		return updatedCnt;
	}

	@Override
	public int removeStudent(StudentVo vo) {
		int deletedCnt = dao.removeStudent(vo);
		return deletedCnt;
	}
}
