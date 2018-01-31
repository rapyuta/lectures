package test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import common.Util;
import exception.DateNumberFormatException;

public class UtilTest {

	@Test
	public void emptyBlankTest() {
		assertThat(Util.emptyBlank("Test String"), is("Test String"));
		assertThat(Util.emptyBlank(null)         , is(""));
		assertThat(Util.emptyBlank(1)         , is("1"));
		assertThat(Util.emptyBlank(0)         , is(""));
	}
	
	@Test
	public void splitTest() {
		String str = "boo:and:foo";
		
		String[] result = str.split(":");
		assertThat(result[0],     is("boo"));
				
		assertThat(result[1],     is("and"));
		assertThat(result[2],     is("foo"));
		assertThat(result.length, is(3));
	}

	@Test
	public void splitTest1() {
		String str = "boo:and:foo";
		
		String[] result = str.split(":and:");
		assertThat(result.length,     is(2));	
	}
	
	@Test
	public void equalsTest() {
		String a = "hello";
		String b = new String("hello");
		
		assertThat(a.equals(b), is(true));		
	}
	@Test
	public void equalsTest2() {
		String a = "hello";
		String b = new String("hello");
		
		assertThat(a == b, is(false));		
	}
	@Test
	public void equalsTest3() {
		String a = "hello";
		String b = "hello";
		
		assertThat(a == b, is(true));		
	}
	
	@Test
	public void dateFormatTest() {
		  
		// given
		// db에는 8자리로 날짜가 관리됨.
		String createDate = "20181206";
		
		// when
		String viewDate = "";
		// 문자열을 4자, 2자, 2자로 자른 다음에 '-'를 붙여주는방법
		String yyyy = createDate.substring(0, 4);
		String mm = createDate.substring(4, 6);
		String dd = createDate.substring(6, 8);
		
		viewDate = yyyy + "-" + mm + "-" + dd;
		
		// then
		// 화면에는 YYYY-MM-DD 와 같은 형식으로 보여줘야함.
		assertThat(viewDate, is("2018-12-06"));
		
	}
	
	@Test
	public void dateFormatTest1() throws Exception{
		String createDate = "20181206";
		String viewDate = Util.dateFormat(createDate);
		assertThat(viewDate, is("2018-12-06"));
		
	}
	
	@Test
	public void substringTest() {
		String str = "20180102";
		
		assertThat(str.substring(0, 4), is("2018"));
		assertThat(str.substring(4, 6), is("01"));
		assertThat(str.substring(6, 8), is("02"));		
	}
	
	@Test
	public void dateFormatTest2() throws Exception {
		String createDate = null;
		String result = Util.dateFormat(createDate);
		assertThat(result, is("1970-01-01"));
	}
	
	@Test(expected=DateNumberFormatException.class)	
	public void dateFormatTest3() throws Exception {
		String createDate = "080106";
		String result = Util.dateFormat(createDate);
		
	}
	
}
















































