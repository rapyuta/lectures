package com.ohhoonim.vo;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class StudentVo extends CommonVo {
	private String memberId      ;
	private String memberPw      ;
	private String memberName    ;
	private Date   memberBirth   ;
	private String memberGender  ;
	private String memberEmail   ;

	public void init() throws Exception{
		this.memberId    = "";
		this.memberPw    = "";
		this.memberName  = "";
		java.util.Date today = new java.util.Date();
		this.memberBirth = new java.sql.Date(today.getTime());
		this.memberGender= "";
		this.memberEmail = "";
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

}
