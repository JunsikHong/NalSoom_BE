package com.pick.nalsoom.dto;

import com.pick.nalsoom.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private Long postProperNum;
    private String postTitle;
    private String postContent;
    private Timestamp postWriteTime;
    private Long postView;
    private Long userProperNum;
    private Long shelterProperNum;

    public Post toEntity() {
        return Post.builder()
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
