package com.jang.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jang.biz.member.MemberVO;
import com.jang.biz.member.impl.MemberDAO;

@Controller
public class MemberController {

	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String index() {
		return "login.jsp"; // 그냥 사용하면 forward 방식
	}

	@RequestMapping(value="/login.do", method=RequestMethod.POST) 
	// value는 하나만 쓸 때는 생략가능 method를 POST방식으로 지정하면서 value써줘야함
	public String selectOneMember(MemberVO vo,MemberDAO dao, HttpSession session) {
		System.out.println("로그: Login메서드 실행");
		vo = dao.selectOneMember(vo);

		if(vo == null) {
			return "login.jsp"; 
		}
		else {
			session.setAttribute("member", vo); // 데이터를 저장해서 가야할 때는 MAV 사용
			return "redirect:main.do";
		}
	}

	@RequestMapping("/logout.do") 
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:login.do";
	}

	@RequestMapping("/delete.do") 
	public String deleteMember(MemberVO vo, MemberDAO dao) {
		dao.deleteMember(vo);
		return "redirect:logout.do";
	}

	@RequestMapping("/signUp.do") 
	public String insertMember(MemberVO vo,MemberDAO dao){
		dao.insertMember(vo);
		return "redirect:login.do";
	}
	
	@RequestMapping("/update.do") 
	public String handleRequest(MemberVO vo,MemberDAO dao) {
		dao.updateMember(vo);
		return "redirect:logout.do";
	}
}
