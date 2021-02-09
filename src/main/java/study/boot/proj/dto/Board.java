package study.boot.proj.dto;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_SEQ")
    private long seq;

    @NonNull
    @Column(name = "WRITER")
    private String nickname;

    @NonNull
    @Column(name = "TITLE")
    private String title;

    @NonNull
    @Column(name = "CONTENT")
    private String content;

    @NonNull
    @Column(name = "REGDATE")
    private Date date;

    @Column(name = "DELFLAGE")
    private String delflage;

    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_SEQ")
    private int groupseq;

    // 그룹 순서, 제목 탭, .... 추가해야함!!!!



}
