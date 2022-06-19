package com.tjoeun.vo;

import org.springframework.stereotype.Component;

@Component 
public class CommentVO {

	private int cnum; // 댓글 번호
	private int comment_num; // 글번호
	private String comment_writer; // 작성자
	private String comment; // 내용
	private java.sql.Date comment_date; // 날짜
	
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getComment_writer() {
		return comment_writer;
	}
	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public java.sql.Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(java.sql.Date comment_date) {
		this.comment_date = comment_date;
	}
	
	
	

}
