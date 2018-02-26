package com.ohhoonim.test.common;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ohhoonim.common.util.Utils;
import com.ohhoonim.vo.StudentVo;

public class UtilsTest {
	@Test
	public void checkParamIsEmptyTest() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("test", "");
		params.put("test2", "test2value");
				
		String[] keys = {"test", "test2"};
		boolean isEmpty = Utils.checkParamIsEmpty(params, keys);
		
		assertTrue(isEmpty);
		
	}
	
	@Test
	public void mappingReqparamToVoTest() throws Exception {
		Map<String, String> req = new HashMap<String, String>();
		req.put("memberId"    , "test");
		req.put("memberPw"    , "testpw");
		req.put("memberName"  , "testname");
		req.put("memberBirth" , "2017-10-12");
		req.put("memberGender", "testgender");
		req.put("memberEmail" , "testemail");
		
		StudentVo vo = Utils.mappingReqparamToVo(req, StudentVo.class);
		
		assertThat(vo.getMemberId(), is("test"));
	}
}
