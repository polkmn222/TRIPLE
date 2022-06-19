package com.tjoeun.svc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.dao.TRDAO;
import com.tjoeun.vo.AttachVO;
import com.tjoeun.vo.ReviwVO;
import com.tjoeun.vo.CommentVO;
import com.tjoeun.vo.PagingVO;
import com.tjoeun.vo.TRVO;

@Service
public class TRService {

	@Autowired
	TRDAO dao;
//	=================================================================
//	로그인
//	회원가입
	public int insertUser(TRVO uj) {

		return dao.insertUser(uj);
	}

//	아이디 중복 체크
	public boolean idcheck(TRVO uj) {

		return dao.idcheck(uj);
	}

//	로그인
	public boolean login(TRVO uj) {

		return dao.login(uj);
	}

//	아이디 찾기
	public boolean forgot(TRVO uj) {

		return dao.forgot(uj);
	}

//	특정 아이디 찾기
	public TRVO getUserById(String uid) {

		return dao.getUserById(uid);
	}

//	회원정보 수정
	public boolean updateByMap(Map<String, String> map) {
		return dao.updateByMap(map);
	}

//	회원 탈퇴
	public int deleteUser(TRVO uj) {
		return dao.deleteUser(uj);
	}
//	=================================================================
//	게시판
//	게시글 쓰기
	public boolean addBoard(ReviwVO board) {

		return dao.addBoard(board);
	}

	public boolean addBoard(HttpServletRequest request, ReviwVO board, MultipartFile[] mfiles) {
		boolean saved = addBoard(board); // 글 저장
		int board_num = board.getNum(); // 글 저장시 자동증가 필드
		if (!saved) {
			System.out.println("글 저장 실패");
			return false;
		}

		ServletContext context = request.getServletContext();

//		      String savePath = context.getRealPath("/WEB-INF/upload");
		String savePath = context.getRealPath("/images");

		int fileCnt = mfiles.length;
		int saveCnt = 0;
		boolean fSaved = false;

		if (!mfiles[0].isEmpty()) {
			try {
				for (int i = 0; i < mfiles.length; i++) {
					String filename = mfiles[i].getOriginalFilename();
					mfiles[i].transferTo(new File(savePath + "/" + System.currentTimeMillis() / 100000 + filename)); // 서버측 디스크
																														
					AttachVO attach = new AttachVO();
					attach.setBoard_num(board_num);
					attach.setFilename(System.currentTimeMillis() / 100000 + filename);
					attach.setFilesize(mfiles[i].getSize());
					fSaved = dao.addFileInfo(attach); // attach 테이블에 파일정보 저장
					if (fSaved)
						saveCnt++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return fileCnt == saveCnt ? true : false;

		}
		return saved;
	}

//	게시판 리스트
	public List<ReviwVO> boardList() {
		List<Map<String, Object>> list = dao.boardList();
		List<ReviwVO> list2 = new ArrayList<>();

		int prev_num = 0;
		for (int i = 0; i < list.size(); i++) {
			int bnum = (int) list.get(i).get("num");
			if (bnum == prev_num) { // 첨부파일이 다수개라서 중복되는 행이 있다면...
				ReviwVO _board = list2.get(list2.size() - 1);
				AttachVO att = new AttachVO();
				att.setFilename((String) list.get(i).get("filename"));
				att.setFilesize((int) list.get(i).get("filesize"));
				_board.attach.add(att);
				continue;
			}

			// 첨부파일이 없거나 한개인 게시글이라면...
			Map<String, Object> m = list.get(i);
			ReviwVO board = new ReviwVO();
			board.setNum(bnum);
			board.setTitle((String) m.get("title"));
			board.setAuthor((String) m.get("author"));
			board.setBdate((java.sql.Date) m.get("bdate"));

			if (m.get("filename") != null) { // 첨부파일을 가진 글이라면...

				AttachVO att = new AttachVO();
				att.setNum((int) list.get(i).get("att_num"));
				att.setFilename((String) list.get(i).get("filename"));
				att.setFilesize((int) list.get(i).get("filesize"));
				board.attach.add(att);
			}
			list2.add(board);
			prev_num = bnum; // 중복되는 행인지 확인하기 위함
		} 
		return list2;
	}

//	게시글 상세보기
	public ReviwVO bdetail(int num) {

		List<Map<String, Object>> list = dao.bdetail(num);
		ReviwVO board = new ReviwVO();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Map<String, Object> map = list.get(i);
			if (i == 0) {
				board.setNum((int) map.get("num"));
				board.setTitle((String) map.get("title"));
				board.setAuthor((String) map.get("author"));
				board.setBdate((java.sql.Date) map.get("bdate"));
				board.setContents((String) map.get("contents"));
			}
			Object obj = map.get("filename");
			if (obj != null) {
				AttachVO att = new AttachVO();
				att.setNum((int) map.get("att_num"));
				att.setFilename((String) map.get("filename"));
				att.setFilesize((int) map.get("filesize"));
				board.attach.add(att);
			}
		}
		return board;
	}

//	특정 파일 불러오기
	public String getFilename(int num) {
		return dao.getFilename(num);
	}

//	특정 게시글 불러오기
	public ReviwVO getBoardByNum(int num) {

		return dao.getBoardByNum(num);
	}

//	게시글 수정
	public boolean updateBoard(ReviwVO board) {

		return dao.updateBoard(board);
	}

	public boolean updateBoard(HttpServletRequest request, ReviwVO board, MultipartFile[] mfiles) {
		boolean saved = updateBoard(board); // 글 저장
		int board_num = board.getNum(); // 글 저장시 자동증가 필드
		if (!saved) {
			System.out.println("글 저장 실패");
			return false;
		}

		ServletContext context = request.getServletContext();
//			      String savePath = context.getRealPath("/WEB-INF/upload");
		String savePath = context.getRealPath("/images");
		int fileCnt = mfiles.length;
		int saveCnt = 0;
		boolean fSaved = false;

		if (!mfiles[0].isEmpty()) {
			try {
				for (int i = 0; i < mfiles.length; i++) {
					String filename = mfiles[i].getOriginalFilename();
					mfiles[i].transferTo(new File(savePath + "/" + System.currentTimeMillis() / 100000 + filename)); // 서버측 디스크
																														
					AttachVO attach = new AttachVO();
					attach.setBoard_num(board_num);
					attach.setFilename(System.currentTimeMillis() / 100000 + filename);
					attach.setFilesize(mfiles[i].getSize());
					fSaved = dao.addFileInfo(attach); // attach 테이블에 파일정보 저장
					if (fSaved)
						saveCnt++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return fileCnt == saveCnt ? true : false;

		}
		return saved;
	}

//	게시글 삭제
	public int deleteBoard(ReviwVO board) {

		return dao.deleteBoard(board);
	}

//	파일 삭제
	public boolean deleteFileInfo(int num, ResourceLoader resourceLoader) {

		String filename = getFilename(num);
//		Resource resource = (Resource)resourceLoader.getResource("WEB-INF/upload/"+filename);
		Resource resource = (Resource) resourceLoader.getResource("/images/" + filename);

		boolean deleted = false;
		try {
			String abPath = resource.getFile().getAbsolutePath();
			File fileDel = new File(abPath);
			deleted = fileDel.exists() ? fileDel.delete() : false;
		} catch (Exception e) {

		}
		if (deleted) {
			return dao.deleteFileInfo(num);
		}
		return false;
	}

//	게시글 총 수
	public int countBoard() {
		return dao.countBoard();
	}

//	게시글 페이지
	public List<ReviwVO> selectBoard(PagingVO vo) {
		return dao.selectBoard(vo);
	}


//	=================================================================
//	댓글
//	특정글 댓글 리스트
	public List<CommentVO> cList(int comment_num) {

		return dao.cList(comment_num);
	}
//	댓글 쓰기
	public boolean cCreate(CommentVO comment) {

		return dao.cCreate(comment);
	}
	
//	댓글 삭제
	public boolean cDelete(int cnum) {

		return dao.cDelete(cnum);
	}

}
