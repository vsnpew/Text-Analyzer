package com.vsnpew.BookAnalyzer.Services;

import com.vsnpew.BookAnalyzer.Entities.BookChapter;
import com.vsnpew.BookAnalyzer.Entities.RawBook;
import com.vsnpew.BookAnalyzer.Repositories.MonRepository;
import com.vsnpew.BookAnalyzer.Repositories.PostRepository;
import org.hibernate.boot.archive.scan.internal.NoopEntryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.spi.CharsetProvider;
import java.util.List;
import java.util.Optional;

@Service
public class AnalyzeService {

    @Autowired
    private MonRepository monRepo;
    @Autowired
    private PostRepository postRepo;


    public void findAll() { // from mongo to postgres

        List<RawBook> bookList = monRepo.findAll();
        RawBook fullBook = bookList.get(0);
        String[] chapters = fullBook.getBookText().split(", ");
        for (int i = 0; i < chapters.length; i++) {
            postRepo.save(new BookChapter(chapters[i]));
        }
    }


    public void findById(Integer id) {


    }

    public Integer wordsCount(Integer id, String word) { // подсчет повторений слова

        BookChapter chap = postRepo.getById(id); // заменить на findById с optional?
        String chapText = chap.getText();


        String text2 = chapText.toLowerCase();
        String[] splits = text2.split(word.toLowerCase());
        for (int i = 0; i < splits.length; i++) {
            System.out.println(splits[i]);
        }

        return (splits.length - 1);
    }


}
