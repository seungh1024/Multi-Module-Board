package com.seungh1024.service;

import com.seungh1024.custom.InvalidMemberException;
import com.seungh1024.dto.CommentCreateBodyDto;
import com.seungh1024.dto.CommentUpdateBodyDto;
import com.seungh1024.entity.comment.Comment;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.post.Post;
import com.seungh1024.exception.custom.CommentNotFoundException;
import com.seungh1024.repository.comment.CommentRepository;
import com.seungh1024.repository.comment.condition.CommentCondition;
import com.seungh1024.repository.comment.dto.CommentQueryDto;
import com.seungh1024.repository.comment.dto.CommentUpdateDto;
import com.seungh1024.repository.comment.dto.MyCommentQueryDto;
import com.seungh1024.repository.member.MemberRepository;
import com.seungh1024.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Comment Service 구현 클래스
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService{
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public Page<CommentQueryDto> getCommentList(CommentCondition condition, Pageable pageable) {
        Page<CommentQueryDto> commentList = commentRepository.getCommentList(condition, pageable);
        if (commentList.isEmpty()){
            throw new CommentNotFoundException();
        }
        return commentList;
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
    @Transactional(readOnly = false)
    public void modifyComment(Long memberId, CommentUpdateBodyDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getCommentId()).orElseGet(()->null);
        if(comment == null){
            throw new CommentNotFoundException();
        }
        if(!comment.isOwner(memberId)){
            throw new InvalidMemberException("권한이 없는 사용자입니다.");
        }
        CommentUpdateDto updateDto = commentDto.toUpdateDto();
        comment.updateComment(updateDto);
        commentRepository.save(comment);
    }

    @Override
    public Page<MyCommentQueryDto> getMyCommentList(Long memberId, Pageable pageable) {
        return commentRepository.getMyCommentList(memberId, pageable);
    }

}
