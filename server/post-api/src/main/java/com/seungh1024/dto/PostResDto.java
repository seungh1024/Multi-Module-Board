package com.seungh1024.dto;


import com.seungh1024.entity.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * 응답용 Post DTO
 *
 * @Author 강승훈
 * @Since 2023.06.27
 * */
@Getter
@Builder
@RequiredArgsConstructor
public class PostResDto {
    private final String postName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Integer postViews;
    private final String memberName;


    public List<PostResDto> entityToDto(List<Post> posts, String memberName){
        ArrayList<PostResDto> result = new ArrayList<>();
        for(Post post : posts){
            PostResDto postResDto = PostResDto.builder()
                    .postName(post.getPostName())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdatedAt())
                    .postViews(post.getPostViews())
                    .memberName(memberName)
                    .build();
            result.add(postResDto);
        }
        return result;
    }

}
