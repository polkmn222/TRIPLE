package com.tjoeun.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.svc.TRService;
import com.tjoeun.vo.ReviwVO;
import com.tjoeun.vo.CommentVO;
import com.tjoeun.vo.PagingVO;
import com.tjoeun.vo.TRVO;

@RequestMapping("/triple")
@Controller
@SessionAttributes("uid")

public class TRController {

	@Autowired
	private TRService svc;

	@Autowired
	ResourceLoader resourceLoader;

//	홈
	@GetMapping("/home") // http://localhost/triple/home
	public String home() {

		return "triple/home";
	}


//	=================================================================
//	로그인
//	유저 추가
	@GetMapping("/add")
	public String form() {

		return "triple/add";
	}

	@PostMapping("/add")
	@ResponseBody
	public Map<String, Boolean> addUser(TRVO uj) {
		Map<String, Boolean> map = new HashMap<>();

		map.put("add", svc.insertUser(uj) > 0 ? true : false);
		return map;
	}

//	아이디 중복체크
	@PostMapping("/idcheck")
	@ResponseBody
	public Map<String, Boolean> idcheck(TRVO uj) {

		Map<String, Boolean> map = new HashMap<>();
		map.put("check", svc.idcheck(uj));

		return map;
	}

//	로그인
	@GetMapping("/login")
	public String login() {

		return "triple/login";
	}

	@PostMapping("/login")
	@ResponseBody
	public Map<String, Boolean> login(TRVO uj, Model m) {
		boolean ok = svc.login(uj);
		if (ok) {
			m.addAttribute("uid", uj.getUid());
		}
		Map<String, Boolean> map = new HashMap<>();
		map.put("ok", ok);
		return map;
	}

//	비밀번호 찾기
	@GetMapping("/forgot")
	public String forgot() {

		return "triple/forgot";
	}

	@PostMapping("/forgot")
	@ResponseBody
	public Map<String, Boolean> forgot(TRVO uj, Model m) {
		boolean ok = svc.forgot(uj);
		if (ok) {
			m.addAttribute("uid", uj.getUid());
		}
		Map<String, Boolean> map = new HashMap<>();
		map.put("ok", ok);
		return map;
	}

//	로그아웃
	@GetMapping("/logout")
	public String logout(SessionStatus status) {

		status.setComplete();
		return "redirect:/triple/home";
	}

//	회원정보 수정
	@GetMapping("/edit")
	public String edit(@SessionAttribute(name = "uid", required = false) String uerid, Model model) {
		TRVO user = svc.getUserById(uerid);
		model.addAttribute("user", user);
		return "triple/edit";
	}

	@PostMapping("/update")
	@ResponseBody
	public Map<String, Boolean> update(TRVO uj) {
		Map<String, String> pMap = new HashMap<>();
		pMap.put("uid", uj.getUid());
		pMap.put("pwd", uj.getPwd());
		pMap.put("phone", uj.getPhone());
		boolean updated = svc.updateByMap(pMap);

		Map<String, Boolean> map = new HashMap<>();
		map.put("updated", updated);
		return map;

	}

//	회원정보 삭제
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Boolean> deleteUser(TRVO uj) {
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", svc.deleteUser(uj) > 0 ? true : false);
		return map;
	}

//	=================================================================
//	게시판
//	글쓰기
	@GetMapping("/badd")
	public String badd(@SessionAttribute(name = "uid", required = false) String uerid, Model model) {
		TRVO user = svc.getUserById(uerid);
		model.addAttribute("user", user);
		return "board/addForm";
	}

	@PostMapping("/badd")
	@ResponseBody
	public Map<String, Boolean> badd(
			@SessionAttribute(name = "uid", required = false) @RequestParam(name = "mfiles", required = false) MultipartFile[] mfiles,
			HttpServletRequest request, ReviwVO board, Model model) {

		Map<String, Boolean> map = new HashMap<>();
		boolean added = svc.addBoard(request, board, mfiles);
		model.addAttribute("board", board);
		map.put("added", added);
		return map;

	}

//	게시판 리스트 + 페이지
	@GetMapping("/list")
	public String boardList(@SessionAttribute(name = "uid", required = false) String uid, PagingVO vo, Model model,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage) {

		if (uid == null) {
			return "redirect:/triple/login";
		} else {

			int total = svc.countBoard();
			if (nowPage == null && cntPerPage == null) {
				nowPage = "1";
				cntPerPage = "5";
			} else if (nowPage == null) {
				nowPage = "1";
			} else if (cntPerPage == null) {
				cntPerPage = "5";
			}
			vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			model.addAttribute("paging", vo);
			model.addAttribute("viewAll", svc.selectBoard(vo));
//			model.addAttribute("list", svc.boardList());

			return "board/list";
		}
	}



//	게시글 수정
	@GetMapping("/bedit")
	public String edit(@SessionAttribute(name = "uid", required = false) String uid, @RequestParam int num, Model model,
			ReviwVO board) {
		ReviwVO bbs = svc.getBoardByNum(num);
		model.addAttribute("bbs", bbs);
		model.addAttribute("uid", uid);

		return "board/editBoard";

	}

	@RequestMapping(value = "/bupdate")
	@ResponseBody
	public Map<String, Boolean> updateBoard(
			@SessionAttribute(name = "uid", required = false) @RequestParam(name = "mfiles", required = false) MultipartFile[] mfiles,
			HttpServletRequest request, ReviwVO board, Model model) {
		Map<String, Boolean> map = new HashMap<>();
		boolean updated = svc.updateBoard(request, board, mfiles);
		map.put("updated", updated);
		return map;
	}

//	파일 다운로드
	@GetMapping("/download/{filename}")
	public ResponseEntity<Resource> download(HttpServletRequest request, @PathVariable String filename) {
//		Resource resource = (Resource)resourceLoader.getResource("WEB-INF/upload/"+filename);
		Resource resource = (Resource) resourceLoader.getResource("/images/" + filename);
		System.out.println("파일명:" + resource.getFilename());
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping("/file/download/{num}")
	public ResponseEntity<Resource> fileDownload(@PathVariable int num, HttpServletRequest request) {
		// attach 테이블에서 att_num 번호를 이용하여 파일명을 구하여 위의 방법을 사용
		String filename = svc.getFilename(num);
//		Resource resource = (Resource)resourceLoader.getResource("WEB-INF/upload/"+filename);
		Resource resource = (Resource) resourceLoader.getResource("/images/" + filename);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

//	게시글 삭제
	@PostMapping("/bdelete")
	@ResponseBody
	public Map<String, Boolean> deleteBoard(ReviwVO board) {
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", svc.deleteBoard(board) > 0);
		return map;
	}

//	파일 삭제
	@PostMapping("/file/delete")
	@ResponseBody
	public Map<String, Boolean> deleteFileInfo(@RequestParam int num) {
		boolean deleted = svc.deleteFileInfo(num, resourceLoader);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", deleted);
		return map;
	}
	
//	=================================================================
//	댓글
//	게시글 상세보기, 댓글
	@GetMapping("/detail")
	public String detail(@SessionAttribute(name = "uid", required = false) String uid, @RequestParam int num,
			Model model,CommentVO comment) {
		TRVO user = svc.getUserById(uid);
		model.addAttribute("user", user);
		ReviwVO bbs = svc.bdetail(num);
		List<CommentVO> cList = svc.cList(num);
		
		model.addAttribute("cList", cList);
		model.addAttribute("bbs", bbs);
		model.addAttribute("check", uid != null && bbs.getAuthor().equals(uid) ? true : false);
		model.addAttribute("reply", comment);
		return "board/detail";
	}
	
//	댓글 등록
	@PostMapping("/cadd")
	@ResponseBody
	public Map<String, Boolean> cadd(@SessionAttribute(name = "uid", required = false)String uid, Model model, CommentVO comment) {

		Map<String, Boolean> map = new HashMap<>();
		boolean cadd = svc.cCreate(comment);
		map.put("cadd", cadd);
		return map;

	}
	
//	댓글 삭제
	@PostMapping("/cDeleted")
	@ResponseBody
	public Map<String, Boolean> cDeleted(@RequestParam int num) {
		boolean cDeleted = svc.cDelete(num);
		Map<String, Boolean> map = new HashMap<>();
		map.put("cDeleted", cDeleted);
		return map;
	}

}
