package com.vsnpew.BookAnalyzer.Services;

import com.vsnpew.BookAnalyzer.Entities.BookChapter;
import com.vsnpew.BookAnalyzer.Entities.RawBook;
import com.vsnpew.BookAnalyzer.Entities.WrongSymbol;
import com.vsnpew.BookAnalyzer.Repositories.MonRepository;
import com.vsnpew.BookAnalyzer.Repositories.PostRepository;
import com.vsnpew.BookAnalyzer.Repositories.SymbolRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalyzeService {

    @Autowired
    private MonRepository monRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private SymbolRepository symRepo;

    /**
     * из Mongo в Postgres
     */

    public void fromMongoToPostgres() {

        List<RawBook> bookList = monRepo.findAll();
        RawBook fullBook = bookList.get(0);
        String[] chapters = fullBook.getBookText().split(","); //trim
        for (int i = 0; i < chapters.length; i++) {
            postRepo.save(new BookChapter(chapters[i]));
        }
    }


    public void findById(Integer id) {


    }

    /**
     * подсчет повторений слова
     */

    public Integer wordCount(Integer id, String word) {

        BookChapter chap = postRepo.getById(id); // заменить на findById с optional?
        String chapText = chap.getText();


        String text2 = chapText.toLowerCase();
        String[] splits = text2.split(word.toLowerCase());
        for (int i = 0; i < splits.length; i++) {
            System.out.println(splits[i]);
        }

        return (splits.length - 1);
    }

    /**
     * Поиск слова по всем главам
     */

    public Map<Integer, Integer> wordGlobalCount(String word) {

        List<BookChapter> allChapters = postRepo.findAll();
        Map<Integer, Integer> wordList = new HashMap<>();

        for (int i = 0; i < allChapters.size(); i++) {

            String chapText = allChapters.get(i).getText().toLowerCase();
            String[] splits = chapText.split(word.toLowerCase());
            wordList.put(i, splits.length - 1);

        }
        return wordList;
    }

    /**
     * Анализ главы на ошибки
     */

    public void checkWrondSymbolsChap(Integer chapterId) {
        if (symRepo.getById(chapterId) != null) {
            return;
        }

        Set<String> charSet = new HashSet<>();

        String chapText = postRepo.getById(chapterId).getText(); // получаю весь текст главы
        char[] chapSymbols = chapText.toCharArray(); // разбиваю весь текст на массив символов

        for (int i = 0; i < chapSymbols.length; i++) {                // проверяю какие символы явл. ошибками
            // записываю ошибки в Set
            String symbol = Character.toString(chapSymbols[i]);

            if (symbol.matches("\\W")) {
                charSet.add(symbol);
            }
        }

        String[] mistakes = charSet.toArray(new String[charSet.size()]); // перегоняю Set в массив строк

        for (int i = 0; i < mistakes.length; i++) {

            String[] chapSplits = chapText.split(mistakes[i]);

            WrongSymbol wrongSymbol = new WrongSymbol(chapterId, mistakes[i], chapSplits.length - 1);
            symRepo.save(wrongSymbol);

        }

    }


    /**
     * Анализ всего текста на ошибки - // как сделать чтобы повторно не записывались?
     */

    public void checkWrongSymbolsGlobal() {
        List<BookChapter> chapters = postRepo.findAll();
        for (int i = 0; i < chapters.size(); i++) {
            checkWrondSymbolsChap(i);
        }
    }


    /**
     * Анализ всего текста на ошибки. С разбиением на главы
     */

    public void checkWrongSymbolsGlobal2() {

        Set<String> charSet = new HashSet<>();
        List<BookChapter> chapters = postRepo.findAll();

        for (int i = 0; i < chapters.size(); i++) {

            String chapText = chapters.get(i).getText();  // получаю весь текст главы
            char[] chapSymbols = chapText.toCharArray(); // разбиваю текст главы на символы (в массив вношу)

            for (int j = 0; j < chapSymbols.length; j++) {                // проверяю какие символы явл. ошибками и записываю в Set
                String symbol = Character.toString(chapSymbols[j]);
                if (symbol.matches("\\W")) {
                    charSet.add(symbol);
                }
            }

            String[] mistakes = charSet.toArray(new String[charSet.size()]); // перегоняю Set в массив строк
            for (int k = 0; k < mistakes.length; k++) {
                String[] chapSplits = chapText.split(mistakes[k]);
                WrongSymbol wrongSymbol = new WrongSymbol(i, mistakes[k], chapSplits.length - 1);

                symRepo.save(wrongSymbol);
            }
        }
    }

    /** Получаем список всех ошибок */
    public List<WrongSymbol> findAllWrongSymbols (){

        return  symRepo.findAll();
    }

    /** Получаем список ошибок по номеру главы */
    public List<WrongSymbol> findWrongSymbolsByChapter (Integer chapterId){
//доделать
        return  null;
    }




}
