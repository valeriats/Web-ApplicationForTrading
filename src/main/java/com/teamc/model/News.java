package com.teamc.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
//@EqualsAndHashCode(exclude = {"date", "title", "text", "link", "img"})
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "newsGenerator")
    @SequenceGenerator(name="newsGenerator", sequenceName = "newsSeq")
    @Column(name = "newsid", updatable = false, nullable = false)
    private Long newsID;

    @Column(name = "datenews")
    String date;
    String title;
    String text;
    String img;
    String fullText;
    String fullImg;
    String tesis;

    public News(String date, String title, String text, String img, String fullText, String fullImg, String tesis) {
        this.date = date;
        this.title = title;
        this.text = text;
        this.img = img;
        this.fullText = fullText;
        this.fullImg = fullImg;
        this.tesis = tesis;
    }

}
