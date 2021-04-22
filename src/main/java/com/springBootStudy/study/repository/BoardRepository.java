package com.springBootStudy.study.repository;

import com.springBootStudy.study.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    public List<Board> findByTitle(String title);

    public List<Board> findByTitleOrContent(String title, String content);

    public Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
