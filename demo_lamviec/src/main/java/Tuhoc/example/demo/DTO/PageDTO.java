package Tuhoc.example.demo.DTO;

import java.util.List;

public class PageDTO<T> {
	private int totalPages;
	private long totalElements;
	private List<T> contents;
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public List<T> getContents() {
		return contents;
	}
	public void setContents(List<T> contents) {
		this.contents = contents;
	}
	
}
