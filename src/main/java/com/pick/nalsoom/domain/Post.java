package com.pick.nalsoom.domain;

import com.pick.nalsoom.dto.PostDto;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_PROPER_NUM")
    private Long postProperNum;

    @Column(name = "POST_TITLE")
    private String postTitle;

    @Column(name = "POST_CONTENT")
    private String postContent;

    @Column(name = "POST_WRITE_TIME")
    private Timestamp postWriteTime;

    @Column(name = "POST_VIEW")
    private Long postView;

    @Column(name = "USER_PROPER_NUM")
    private Long userProperNum;

    @Column(name = "SHELTER_PROPER_NUM")
    private Long shelterProperNum;

    public PostDto toDto () {
        return PostDto.builder()
                .postProperNum(postProperNum)
                .postTitle(postTitle)
                .postContent(postContent)
                .postWriteTime(postWriteTime)
                .postView(postView)
                .userProperNum(userProperNum)
                .shelterProperNum(shelterProperNum)
                .build();
    }

}
