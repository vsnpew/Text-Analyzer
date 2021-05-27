package com.vsnpew.BookAnalyzer.Services;

import com.vsnpew.BookAnalyzer.Entities.BookChapter;
import com.vsnpew.BookAnalyzer.Entities.RawBook;
import com.vsnpew.BookAnalyzer.Repositories.MonRepository;
import com.vsnpew.BookAnalyzer.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyzeService {

    @Autowired
    private MonRepository monRepo;
    @Autowired
    private PostRepository postRepo;



    public void findAll() {
        // переделать потом

//        return monRepo.findAll();

        List<RawBook> bookList = monRepo.findAll();
        RawBook fullBook = bookList.get(0);
        String[] chapters = fullBook.getBookText().split(", ");
        for (int i = 0; i < chapters.length; i++) {
            postRepo.save(new BookChapter(chapters[i]));
        }

    }
}
