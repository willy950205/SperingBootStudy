package com.springBootStudy.study.repository;

import com.springBootStudy.study.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
