package com.ohhoonim.stdt.service;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

import com.ohhoonim.vo.StudentVo;

public interface StudentService {

	Map<String, Object> studentList(StudentVo vo);

	StudentVo studentDetail(StudentVo vo);
	
	int addStudent(StudentVo vo);

	int addStudent(StudentVo vo, MultipartFile file, String contextPath) throws Exception;

	int modifyStudent(StudentVo vo);

	int removeStudent(StudentVo vo);

}
