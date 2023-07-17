package com.seungh1024.application;

import com.seungh1024.dto.CommentCreateBodyDto;
import com.seungh1024.dto.CommentUpdateBodyDto;
import com.seungh1024.entity.comment.Comment;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.post.Post;
import com.seungh1024.repository.comment.CommentRepository;
import com.seungh1024.repository.comment.condition.CommentCondition;
import com.seungh1024.repository.comment.dto.CommentQueryDto;
import com.seungh1024.repository.comment.dto.MyCommentQueryDto;
import com.seungh1024.repository.member.MemberRepository;
import com.seungh1024.repository.post.PostRepository;
import com.seungh1024.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/*
 * Comment Application 구현 클래스
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Service
@RequiredArgsConstructor
public class CommentApplicationImpl implements CommentApplication {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @Override
    public Page<CommentQueryDto> getCommentList(CommentCondition condition, Pageable pageable) {
        return commentService.getCommentList(condition, pageable);
    }

    @Override
    public void createComment(Long memberId, CommentCreateBodyDto commentDto) {
        Comment comment = commentDto.dtoToEntity();
        Member member = memberRepository.getReferenceById(memberId);
        Post post = postRepository.getReferenceById(commentDto.getPostId());
        comment.addFk(member,post);
        commentRepository.save(comment);
    }

    @Override
    public void modifyComment(Long memberId, CommentUpdateBodyDto commentDto) {
        commentService.modifyComment(memberId, commentDto);
    }

    @Override
    public Page<MyCommentQueryDto> getMyCommentList(Long memberId, Pageable pageable) {
        return commentService.getMyCommentList(memberId,pageable);
    }
}
