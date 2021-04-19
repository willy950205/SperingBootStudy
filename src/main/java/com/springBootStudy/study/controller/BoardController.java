package com.springBootStudy.study.controller;

import com.springBootStudy.study.model.Board;
import com.springBootStudy.study.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model){
        System.out.println("--------------CHECK----------------");
        List<Board> boards = boardRepository.findAll();
        System.out.println("--------------CHECK2----------------");

        model.addAttribute("boards",boards);
        return "board/list";
    }

}
