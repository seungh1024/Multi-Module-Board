package com.seungh1024.service;

import com.seungh1024.custom.InvalidMemberException;
import com.seungh1024.dto.CommentCreateBodyDto;
import com.seungh1024.dto.CommentUpdateBodyDto;
import com.seungh1024.entity.comment.Comment;
import com.seungh1024.repository.comment.CommentRepository;
import com.seungh1024.repository.comment.condition.CommentCondition;
import com.seungh1024.repository.comment.dto.CommentQueryDto;
import com.seungh1024.repository.comment.dto.CommentUpdateDto;
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
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    @Override
    public Page<CommentQueryDto> getCommentList(CommentCondition condition, Pageable pageable) {
        Page<CommentQueryDto> commentList = commentRepository.getCommentList(condition, pageable);
        if (commentList.isEmpty()){
            throw new NullPointerException();
        }
        return commentList;
    }

    @Override
    @Transactional
    public void modifyComment(Long memberId, CommentUpdateBodyDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getCommentId()).get();
        if(!comment.isOwner(memberId)){
            throw new InvalidMemberException("권한이 없는 사용자입니다.");
        }
//        if(comment.getMember().getMemberId() != memberId){
//            throw new InvalidMemberException("권한이 없는 사용자입니다.");
//        }
        CommentUpdateDto updateDto = commentDto.reqBodyToEntityDto();
        comment.updateComment(updateDto);
        commentRepository.save(comment);
    }

}
