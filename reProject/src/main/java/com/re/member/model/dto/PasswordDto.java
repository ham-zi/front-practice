package com.re.member.model.dto;

public class PasswordDto {
	private Long userNo;
	private String currPwd;
	private String newPwd;
	public PasswordDto(Long userNo, String currPwd, String newPwd) {
		super();
		this.userNo = userNo;
		this.currPwd = currPwd;
		this.newPwd = newPwd;
	}
	public PasswordDto() {
		super();
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getCurrPwd() {
		return currPwd;
	}
	public void setCurrPwd(String currPwd) {
		this.currPwd = currPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	@Override
	public String toString() {
		return "PasswordDto [userNo=" + userNo + ", currPwd=" + currPwd + ", newPwd=" + newPwd + "]";
	}
	
	
}
