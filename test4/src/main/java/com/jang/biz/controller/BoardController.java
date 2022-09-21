package com.jang.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jang.biz.board.BoardVO;
import com.jang.biz.board.impl.BoardDAO;

@Controller
public class BoardController {
	
	@RequestMapping("/main.do")
	public ModelAndView selectAllBoard(BoardVO vo, BoardDAO dao, ModelAndView mav) {
		mav.addObject("bDatas", dao.selectAllBoard(vo));
		mav.setViewName("main.jsp"); // 같은 서비스 이기때문에 forward방식
		return mav;
	}
	@RequestMapping("/board.do")
	public ModelAndView selectOneBoard(BoardVO vo, BoardDAO dao, ModelAndView mav) {
		
		mav.addObject("bData", dao.selectOneBoard(vo));
		mav.setViewName("board.jsp"); // viewResolver를 통해 경로가 지정됨
		return mav;
	}
}
