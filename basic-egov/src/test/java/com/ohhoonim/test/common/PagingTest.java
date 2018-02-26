package com.ohhoonim.test.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.ohhoonim.common.util.Paging;

public class PagingTest {
	
	@Test
	public void makePagingTest() {
		int pageNo = 64; // 현재페이지
		int pageSize = 10; // 페이지당 데이타 수
		int totalCnt = 3134; // 전체 데이타 수
		
		Paging paging = new Paging();
		paging.setPageNo(pageNo);
		paging.setPageSize(pageSize);
		paging.setTotalCount(totalCnt);
		
		 // 첫 번째 페이지 번호 : 1 (단, 전체 데이타 수가 0 이면 페이징이 출력이 되면 안됨)
		assertThat(paging.getFirstPageNo(), is(1));
		
		// 이전 페이지 번호 : 현재페이지 - 1
		assertThat(paging.getPrevPageNo(), is(63)); 
		
		// 시작 페이지 (페이징 네비 기준) : ((현재페이지 - 1) / 10 ) * 10 + 1
		assertThat(paging.getStartPageNo(), is(61));
		
		// 끝 페이지 (페이징 네비 기준) : 시작페이지 + 10 - 1
		assertThat(paging.getEndPageNo(), is(70));   
		
		// 다음 페이지 번호 = 현재페이지 + 1
		assertThat(paging.getNextPageNo(), is(65));  
		
		// 마지막 페이지 번호 = (전체 데이타 수 / 페이지당 데이타 수) + 1
		assertThat(paging.getFinalPageNo(), is(314));  
	}
}











