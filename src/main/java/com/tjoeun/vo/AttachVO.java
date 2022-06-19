package com.tjoeun.vo;

public class AttachVO {
	
	private int num; // 첨부파일 번호
	private int board_num; // 게시글 번호
	private String filename; // 파일 이름
	private long filesize; // 파일크기
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	
}
