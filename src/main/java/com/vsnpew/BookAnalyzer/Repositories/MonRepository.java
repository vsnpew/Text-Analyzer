package com.vsnpew.BookAnalyzer.Repositories;

import com.vsnpew.BookAnalyzer.Entities.BookChapter;
import com.vsnpew.BookAnalyzer.Entities.RawBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonRepository extends MongoRepository<RawBook, Integer> {
}
