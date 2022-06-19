package com.tjoeun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tjoeun.vo.AttachVO;
import com.tjoeun.vo.ReviwVO;
import com.tjoeun.vo.CommentVO;
import com.tjoeun.vo.PagingVO;
import com.tjoeun.vo.TRVO;

@Mapper
public interface TRMapper {

//	로그인
	String login(TRVO uj);

//	아이디 찾기
	String forgot(TRVO uj);

//	유저 추가
	int insertUser(TRVO uj);

//	특정 아이디 찾기
	TRVO getUserById(String uid);

//	회원정보 수정
	int updateByMap(Map<String, String> map);

//	회원 삭제
	int deleteUser(TRVO uj);

//	=================================================================
//	게시판

//	글쓰기
	int addBoard(ReviwVO board);

//	파일 업로드
	int addFileInfo(AttachVO attach);

//	게시판 리스트
	List<Map<String, Object>> boardList();

//	게시판 상세보기
	List<Map<String, Object>> bdetail(int num);

//	특정 파일 불러오기
	String getFilename(int num);

//	특정 게시판 번호 불러오기
	ReviwVO getBoardByNum(int num);

//	글 수정
	int updateBoard(ReviwVO board);

//	글 삭제
	int deleteBoard(ReviwVO board);

//	파일 삭제
	int deleteFileInfo(int num);

//	게시글 전체 갯수
	public int countBoard();

//	게시판 페이지
	public List<ReviwVO> selectBoard(PagingVO vo);



//	=================================================================
//	댓글
//	특정글 댓글 리스트
	List<CommentVO> cList(int comment_num);

//	댓글 쓰기
	int cCreate(CommentVO comment);

//	댓글 삭제
	int cDelete(int cnum);

}
