package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;
import com.mysql.cj.Session;

// @RequestMapping(value = "/member/*")
// => /member/로 시작되는 모든주소를 처리하는 컨트롤러

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {

	// 서비스 객체주입
	@Inject
	private MemberService mService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// http://localhost:8088/web/member/memberjoin
	// 회원가입(입력)=>GET방식 (정보입력,조회,출력)
	@RequestMapping(value = "/memberjoin1",method = RequestMethod.GET)
	public void memberJoinGET() {
		logger.debug(" memberJoinGET() 실행 - 회원정보 입력 ");
		logger.debug(" 연결된 뷰페이지(/WEB-INF/views/member/memberjoin.jsp)로 이동  ");		
	}
	
	
	// 회원가입(처리)=>POST방식 (처리,DB사용) 
	// ./memberjoinAction -> " "
	@RequestMapping(value = "/memberjoin1",method = RequestMethod.POST)
	public String memberJoinPOST(/* @ModelAttribute("memberVO") */ MemberVO vo) {
		logger.debug(" memberJoinPOST() 실행 - 회원정보 처리");
		
		// 0. 한글처리 인코딩 => web.xml에 필터사용 처리
		// 1. 전달정보 저장(submit)
		logger.debug(" 전달정보 vo : "+vo);
		// 2. 서비스 -> DB에 전달정보 저장
		mService.memberJoin(vo);
		logger.debug(" 회원가입 성공! ");
		
		// 3. 페이지 이동 (로그인 페이지GET)
		return "redirect:/member/login";
	}
	
	// http://localhost:8088/web/member/login (x)
	// http://localhost:8088/member/login (o)
	// 로그인 GET
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public void memberLoginGET() {
		logger.debug(" /member/login -> memberLoginGET() 호출 ");
		logger.debug(" /member/login.jsp 페이지 연결 ");
	}
	
	// 로그인 POST
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String memberLoginPOST(MemberVO vo, HttpSession session) {
		logger.debug(" login.jsp ->memberLoginPOST() 호출 ");
		// 1. 전달정보 저장(+한글처리 인코딩)
		logger.debug(" 로그인 정보 vo : "+vo);
		// 2. 서비스 ->  DB에 로그인 처리 동작 호출
		MemberVO resultVO = mService.memberLogin(vo);
		
		String addr ="";
		if(resultVO == null) {
			logger.debug(" 로그인 실패! -> 다시 로그인 ");

			addr = "/member/login";
		}else {
			logger.debug(" 로그인 성공!! -> 메인페이지 ");
			
			// 로그인 성공한 사용자의 아이디 정보를 세션에 저장
			session.setAttribute("id", resultVO.getUserid());
			

			addr = "/member/main";
		}		
		
		return "redirect:"+addr;
	}
	
	// http://localhost:8088/member/main
	// 메인페이지 GET
	@GetMapping(value = "/main")
	public String memberMainGET() {
		logger.debug(" /member/main -> memberMainGET()실행 ");
		logger.debug(" /member/main.jsp 페이지 이동");		
		
		// String 형태로 리턴시에는 컨트롤러주소를 포함한 
		// 페이지 이름을 작성(리턴) / void경우 자동으로 처리
		return "/member/main";
	}
	
	// 로그아웃 GET 
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String memberLogoutGET(HttpSession session) {
		logger.debug(" /member/logout -> memberLogoutGET() 실행 ");
		
		// 세션정보 초기화
		session.invalidate();
		logger.debug(" 세션객체 초기화(로그아웃) ");
		
		// 다시 페이지 이동(메인페이지/로그인페이지)		
		return "redirect:/member/main";
	}
	
	
	// 회원정보 조회 GET
	@RequestMapping(value = "/info",method = RequestMethod.GET)
	public void memberInfoGET(HttpSession session,Model model) {
		logger.debug("/member/info -> memberInfoGET() 실행");
		
		// 사용자 정보(id) 저장
		String id = (String) session.getAttribute("id");
		logger.debug(" id : "+id);
		
		// 서비스 - DB에서 id해당하는 모든정보를 가져오기
		MemberVO resultVO = mService.memberInfo(id);
		// 정보를 view페이지로 전달(Model)
		model.addAttribute("resultVO", resultVO);
		
		// 연결된 view페이지로 이동 (void -> /member/info.jsp)
	}
	
	
	// http://localhost:8088/member/update
	// 회원정보 수정-GET
	@RequestMapping(value = "/update",method = RequestMethod.GET)
	public void memberUpdateGET(HttpSession session, Model model) {
		logger.debug(" memberUpdateGET() 실행 ! ");
		
		// 아이디 정보
		String id = (String) session.getAttribute("id");
		
		// 회원의 아이디,이름,이메일 주소 확인 / 비밀번호 입력창 사용
		// => 서비스 -> DAO : 아이디에 해당하는 회원정보 조회
		MemberVO resultVO = mService.memberInfo(id);
		logger.debug(" resultVO :  "+resultVO);
		
		
		
		// 전달받은 정보를 연결된 뷰페이지로 전달 (Model)
		model.addAttribute("resultVO", resultVO);
		
		
		
		// 연결된 뷰페이지 생성후 출력
		
	}
	
	// 회원정보 수정-POST
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String memberUpdatePOST(MemberVO vo) {
		logger.debug(" memberUpdatePOST() 호출 ");
		
		// 한글처리(필터)
		// 전달받은 정보 저장(수정할 정보)
		logger.debug(" 수정할 정보 : "+vo);
		
		// 서비스 -> DAO 회원정보 수정동작 호출
		int result = mService.memberUpdate(vo);
		logger.debug(" 결과 : "+result);
		
		// 정상 수정 : main페이지 이동
		if( result == 1 ) {
			return "redirect:/member/main";
		}
		
		// 비정상 수정 : update 페이지 이동
		// result == 0
		
		return "redirect:/member/update";
	}
	
	// 회원정보 삭제-GET
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public String memberDeleteGET() {
		logger.debug(" memberDeleteGET() 호출 ");
		
		// 삭제를 위한 비밀번호 입력하는 페이지로 이동
		return "/member/delete";		
	}
	
	// 회원정보 삭제-POST
	//@RequestMapping
	@PostMapping(value = "/delete")
	public String memberDeletePOST(MemberVO vo,HttpSession session) {
		logger.debug(" memberDeletePOST()  호출 ");
		
		// 한글처리 인코딩 (생략)
		// 전달정보 저장
		logger.debug(" vo(삭제회원정보) : "+vo);
		
		// 서비스 - DAO 회원탈퇴 기능	
		int result = mService.memberDelete(vo);
		
		// 결과에 따른 페이지 이동
		if(result == 1) {
			//로그인한 사용자의 세션초기화 
			session.invalidate(); 
			// 정상 삭제 처리 (메인페이지로 )
			return "redirect:/member/main";
		}
		
		// 비밀번호 오류
		return "redirect:/member/delete";
	}
	
	
	// 회원 목록(관리자기능) - GET
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String memberListGET(HttpSession session, Model model) {
		logger.debug(" memberListGET() ");
		
		// 관리자 로그인여부 체크(메인페이지/로그인페이지 이동)
		String id = (String) session.getAttribute("id");
		
		if( id == null || !id.equals("admin")) {
			return "redirect:/member/login";
		}
		
		// 서비스 - 회원정보 리스트를 DAO에서 가져오기
		List<MemberVO> memberList = mService.memberList();
		// 가져온정보를 Model 객체에 저장해서
		model.addAttribute("memberList", memberList);
		
		// 연결된 뷰페이지에 출력	    	
		return  "/member/list";
	}
	
	
	
	
	
	
	
	
	
	
	
}// controller
