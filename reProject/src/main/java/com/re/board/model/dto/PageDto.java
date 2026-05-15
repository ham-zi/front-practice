package com.re.board.model.dto;

public class PageDto {
	private int listCount;
	private int pageLimit;
	private int boardLimit;
	private int currentPage;
	private int maxPage;
	private int startPage;
	private int endPage;
	private int offset;
	public PageDto(int listCount, int pageLimit, int boardLimit, int currentPage,
			int maxPage, int startPage, int endPage, int offset) {
		super();
		this.listCount = listCount;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		this.currentPage = currentPage;
		this.startPage = startPage;
		this.maxPage = maxPage;
		this.endPage = endPage;
		this.offset = offset;
	}
	public PageDto() {
		super();
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getPageLimit() {
		return pageLimit;
	}
	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}
	public int getBoardLimit() {
		return boardLimit;
	}
	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	@Override
	public String toString() {
		return "PageDto [listCount=" + listCount + ", pageLimit=" + pageLimit + ", boardLimit=" + boardLimit
				+ ", currentPage=" + currentPage + ", startPage=" + startPage + ", endPage=" + endPage + ", maxPage="
				+ maxPage + ", offset=" + offset + "]";
	}
	
	
	
}
