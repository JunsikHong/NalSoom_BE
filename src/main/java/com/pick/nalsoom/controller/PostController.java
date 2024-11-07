package com.pick.nalsoom.controller;

import com.pick.nalsoom.dto.PostDto;
import com.pick.nalsoom.service.PostService;
import com.pick.nalsoom.utils.NoSuchShelterException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getPost(
            @RequestParam(name = "searchShelterType", defaultValue = "normal") String searchShelterType,
            @RequestParam(name = "searchSortBy", defaultValue = "good") String searchSortBy,
            @RequestParam(name = "shelterProperNum", required = false) List<String> shelterProperNum,
            @RequestParam(name = "searchPaging", defaultValue = "0") int searchPaging,
            @RequestParam(name = "searchSize", defaultValue = "10") int searchSize
    ) {
        List<PostDto> postDtoList = null;

        return ResponseEntity.ok().body(postDtoList);
    }

    @GetMapping("my-post")
    public ResponseEntity<List<PostDto>> getMyPost() {
        List<PostDto> postDtoList = null;

        return ResponseEntity.ok().body(postDtoList);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost() {
        PostDto postDto = null;

        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }

    @PutMapping
    public ResponseEntity<PostDto> modifyPost() {
        PostDto postDto = null;

        return ResponseEntity.ok().body(postDto);
    }

    @DeleteMapping
    public void deletePost() {

    }
}
