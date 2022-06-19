package com.tjoeun.vo;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component 
public class TRVO {
	
	private String uid; // 아이디
	private String pwd; // 비밀번호
	private String name; // 이름
	private String phone; // 전화번호
	private String address; // 주소
	private String root;	// 가입경로
	private String email;	// 이메일
	
	@Override
	public int hashCode() {
		return Objects.hash(this.uid);
	}

	@Override
	public boolean equals(Object obj) {
		TRVO u = (TRVO) obj;
		return this.uid.equals(u.getUid());
	}
	
	@Override
	public String toString() {
		
		return String.format("%s|%s|%s|%s|%s|%s|%s", uid,pwd,name,phone,address,root,email);
	}

	public String getLine() {
		return String.format("%s|%s|%s|%s|%s|%s|%s", uid,pwd,name,phone,address,root,email);
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
