package com.tjoeun.vo;

import java.util.ArrayList;
import java.util.List;


public class ReviwVO {
	
	private int num; // 게시글 번호
	private String title; // 제목
	private String author; // 작성자
	private String contents; // 내용
	private java.sql.Date bdate; // 날짜
	public List<AttachVO> attach = new ArrayList<>(); // 첨부파일
	
	@Override
	public String toString() {
		return num + " " + title + " " + author + " " + contents + " " + bdate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public java.sql.Date getBdate() {
		return bdate;
	}
	public void setBdate(java.sql.Date bdate) {
		this.bdate = bdate;
	}
	public List<AttachVO> getAttach() {
		return attach;
	}
	
}
