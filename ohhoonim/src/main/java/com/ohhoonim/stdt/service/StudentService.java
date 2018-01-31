package com.ohhoonim.stdt.service;

import java.util.Map;

import com.ohhoonim.vo.StudentVo;

public interface StudentService {

	Map<String, Object> studentList(StudentVo vo);

	StudentVo studentDetail(StudentVo vo);

	int addStudent(StudentVo vo);

	int modifyStudent(StudentVo vo);

	int removeStudent(StudentVo vo);

}
