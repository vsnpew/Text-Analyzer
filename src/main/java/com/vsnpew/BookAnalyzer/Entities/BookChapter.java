package com.vsnpew.BookAnalyzer.Entities;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

import javax.persistence.*;

@Entity
@Data
@Table(name = "books")
public class BookChapter {
    public BookChapter(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_chapter")
    private Integer id;
    private String text;

}
