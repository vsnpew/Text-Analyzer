package com.vsnpew.BookAnalyzer.Repositories;

import com.vsnpew.BookAnalyzer.Entities.BookChapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<BookChapter, Integer> {
}
