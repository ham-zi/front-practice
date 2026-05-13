package com.kh.market.member.model.dto;

public class PasswordDto {

	private String currentPwd;
	private String changePwd;
	private Long MemberNo;
	public PasswordDto(String currentPwd, String changePwd, Long memberNo) {
		super();
		this.currentPwd = currentPwd;
		this.changePwd = changePwd;
		MemberNo = memberNo;
	}
	public PasswordDto() {
		super();
	}
	public String getCurrentPwd() {
		return currentPwd;
	}
	public void setCurrentPwd(String currentPwd) {
		this.currentPwd = currentPwd;
	}
	public String getChangePwd() {
		return changePwd;
	}
	public void setChangePwd(String changePwd) {
		this.changePwd = changePwd;
	}
	public Long getMemberNo() {
		return MemberNo;
	}
	public void setMemberNo(Long memberNo) {
		MemberNo = memberNo;
	}
	@Override
	public String toString() {
		return "passwordDto [currentPwd=" + currentPwd + ", changePwd=" + changePwd + ", MemberNo=" + MemberNo + "]";
	}
	
}
