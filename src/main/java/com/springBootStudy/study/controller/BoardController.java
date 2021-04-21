package com.springBootStudy.study.controller;

import com.springBootStudy.study.model.Board;
import com.springBootStudy.study.repository.BoardRepository;
import com.springBootStudy.study.validation.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){
        System.out.println("--------------CHECK----------------");
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        System.out.println("--------------CHECK2----------------");

        int startPage = Math.max(1, boards.getPageable().getPageNumber() -4 );
        int endPage = Math.min(boards.getPageable().getPageNumber() + 4 , boards.getTotalPages()) ;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards",boards);
        return "board/list";
    }

    @GetMapping("/write")
    public String write(Model model, @RequestParam(required = false) Long id){
        if(id==null){
            model.addAttribute("board",new Board());
        }else{
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }


        System.out.println("---------write CHeck-------");
        return "board/write";
    }

    @PostMapping("/write")
    public String write(@Valid Board board, BindingResult bindingResult){
        System.out.println("---------post------write CHeck-------");
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/write";
        }
        boardRepository.save(board);

        return "redirect:/board/list";
    }



}
