package com.re.board.model.dto;

import java.sql.Date;

public class BoardDto {
	private Long boardNo;
	private Long userNo;
	private String boardTitle;
	private String boardContent;
	private Long viewCount;
	private Date writenDate;
	private Date modifyDate;
	private String status;
	private String userName;
	
	
	
	
	public Long getViewCount() {
		return viewCount;
	}
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public BoardDto(Long boardNo, Long userNo, String boardTitle, String boardContent, Date writenDate, Date modifyDate,
			String status) {
		super();
		this.boardNo = boardNo;
		this.userNo = userNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.writenDate = writenDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}
	public BoardDto() {
		super();
	}
	public Long getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Date getWritenDate() {
		return writenDate;
	}
	public void setWritenDate(Date writenDate) {
		this.writenDate = writenDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BoardDto [boardNo=" + boardNo + ", userNo=" + userNo + ", boardTitle=" + boardTitle + ", boardContent="
				+ boardContent + ", viewCount=" + viewCount + ", writenDate=" + writenDate + ", modifyDate="
				+ modifyDate + ", status=" + status + ", userName=" + userName + "]";
	}


	
}
