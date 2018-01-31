package com.ohhoonim.dao;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.ohhoonim.vo.DeptVo;

@Repository("deptDao")
public class DeptDao extends Mapper {

	@Cacheable(value = "cacheMem", key = "#vo.dname")
	public List<DeptVo> deptList(DeptVo vo) {
		slowQuery(3000);
		return selectList("deptList", vo);
	}

	@CacheEvict(value = "cacheMem", key = "#vo.dname")
	public void refresh(DeptVo vo) {
	}

	public int deptAdd(DeptVo vo) {
		return insert("deptAdd", vo);
	}

	public int checkDeptno(DeptVo vo) {
		return selectOne("checkDeptno", vo);
	}

	public int removeDept(DeptVo vo) {
		return delete("removeDept", vo);
	}

	private void slowQuery(long seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}
