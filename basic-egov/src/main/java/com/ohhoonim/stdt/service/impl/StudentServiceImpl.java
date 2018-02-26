package com.ohhoonim.stdt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ohhoonim.dao.StudentDao;
import com.ohhoonim.stdt.service.StudentService;
import com.ohhoonim.vo.StudentVo;

import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
	@Resource(name="studentDao")
	StudentDao dao;
	
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
