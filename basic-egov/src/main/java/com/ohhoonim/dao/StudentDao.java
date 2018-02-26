package com.ohhoonim.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ohhoonim.vo.StudentVo;

@Repository("studentDao")
public class StudentDao extends Mapper {

	public List<StudentVo> studentList(StudentVo vo) {
		return selectList("studentList", vo);
	}

	public int studentListCount(StudentVo vo) {
		return selectOne("studentListCount", vo);
	}

	public StudentVo studentDetail(StudentVo vo) {
		return selectOne("studentDetail", vo);
	}

	public int addStudent(StudentVo vo) {
		return insert("addStudent", vo);
	}

	public int modifyStudent(StudentVo vo) {
		return update("modifyStudent", vo);
	}

	public int removeStudent(StudentVo vo) {
		return delete("removeStudent", vo);
	}

	
}
