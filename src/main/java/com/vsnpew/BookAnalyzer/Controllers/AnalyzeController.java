package com.vsnpew.BookAnalyzer.Controllers;

import com.vsnpew.BookAnalyzer.Entities.WrongSymbol;
import com.vsnpew.BookAnalyzer.Services.AnalyzeService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnalyzeController {


    private final AnalyzeService analyzeService;

    @Autowired
    public AnalyzeController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @GetMapping("/books/")  // из mongo в postgres

    public String findAll() {
        analyzeService.fromMongoToPostgres();
        return "ok";  // вернуть количество найденных глав (доделать)
    }

    @GetMapping("/count/{id}/{word}") // подсчет повторений слова
    public String wordCount(@PathVariable("id") Integer id, @PathVariable("word") String word) {
        return "слово: " + word + " повторяется в главе " + analyzeService.wordCount(id, word) + " раз";
    }

    @GetMapping("/check/")
    public void checkAllSymbols(){
        analyzeService.checkWrongSymbolsGlobal2();
    }


    @GetMapping("/wrong/")
    public List<WrongSymbol> findAllWrongSymbols() {
        return analyzeService.findAllWrongSymbols();
    }

}
