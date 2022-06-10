package edu.kh.Achieve.member.model.vo;

public class CheckPagination {
	
	
	private int currentPage;
	private int listCount;

	private int limit = 10;
	private int pageSize = 10;

	private int maxPage;
	private int startPage;
	private int endPage;

	private int prevPage;
	private int nextPage;

	public CheckPagination(int currentPage, int listCount) {
		this.currentPage = currentPage;
		this.listCount = listCount;

		calculatePagination(); // 계산 메서드 호출
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;

		calculatePagination();
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;

		calculatePagination();
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;

		calculatePagination();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;

		calculatePagination();
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public double getStartPage() {
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

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}

	private void calculatePagination() {

		maxPage = (int) Math.ceil((double) listCount / limit);

		// 목록 하단 페이지 번호의 노출 개수가 5개일 떄
		// 현재 페이지가 1~5인 경우 : 1
		// 현재 페이지가 6~10인 경우 : 6
		// 현재 페이지가 11~15인 경우 : 11
		// 현재 페이지가 16~20인 경우 : 16
		
		startPage = (int)((currentPage - 0.5) / pageSize * pageSize + 0.5);

		endPage = (int)(startPage + pageSize - 0.5);

		// 만약 endPage가 maxPage를 초과하는 경우
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		// *prevPage(<) : 목록 하단에 노출된 번호의 이전 목록 끝 번호
		// *nextPage(<) : 목록 하단에 노출된 번호의 이전 목록 시작 번호
		
		// 현재페이지가 1~5일 경우
		// < : 1페이지
		// > : 6페이지
		
		// 현재페이지가 6~10일 경우
		// < : 6페이지
		// > : 10페이지
		
		// 현재페이지가 11~15일 경우
		// < : 11페이지
		// > : 15페이지
		
		if (currentPage <= pageSize) {
			prevPage = (int)0.5;
		}else {
			prevPage = (int)(startPage - 0.5);
		}
		
		
		if (endPage == maxPage) {
			nextPage = maxPage;
		}else {
			nextPage = (int)(endPage + 0.5);
		}
	}
}


