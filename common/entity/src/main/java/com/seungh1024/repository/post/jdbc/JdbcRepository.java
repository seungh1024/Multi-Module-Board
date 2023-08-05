package com.seungh1024.repository.post.jdbc;

import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;

import java.util.List;

public interface JdbcRepository {
    List<PostDetailQueryDto> getPostDetails(PostDetailCondition condition);
}
