package com.seungh1024.repository.post.jdbc;

import com.seungh1024.repository.post.condition.PostDetailCondition;
import com.seungh1024.repository.post.dto.PostDetailQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class JdbcRepositoryImpl implements JdbcRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<PostDetailQueryDto> getPostDetails(PostDetailCondition condition) {
//        String sql = "select p.post_id, p.post_name, p.post_content, p.created_at as post_created_at,p.post_views," +
//                "pm.member_id as post_member_id ,pm.member_name as post_member_name," +
//                "c.comment_id, c.comment_content, c.created_at as comment_created_at," +
//                "cm.member_id comment_member_id, cm.member_name as comment_member_name " +
//                "from post p " +
//                "join member pm " +
//                "   on p.member_id = pm.member_id" +
//                "   and p.post_id =:postId " +
//                "left join comment c " +
//                "join member cm " +
//                "   on c.member_id = cm.member_id " +
//                "   and c.comment_id in (select *\n" +
//                "                         from (select cc.comment_id\n" +
//                "                               from comment cc\n" +
//                "                               where (cc.post_id = :postId)\n" +
//                "                               limit 0,10) as cl)\n" +
//                "    on p.post_id =:postId";
        String sql = "select p.post_id, p.post_name, p.post_content, p.created_at as post_created_at,p.post_views, " +
                "       pm.member_id as post_member_id ,pm.member_name as post_member_name, " +
                "       c.comment_id, c.comment_content, c.created_at as comment_created_at, " +
                "       cm.member_id as comment_member_id, cm.member_name as comment_member_name " +
                "\n" +
                "from post p " +
                "         join member pm " +
                "              on p.member_id = pm.member_id " +
                "                  and p.post_id = :postId " +
                "         left join comment c " +
                "         join member cm " +
                "              on c.member_id = cm.member_id " +
                "        on c.post_id = :postId " +
                "       order by  comment_created_at asc " +
                "limit 0,10";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(condition);
        List<PostDetailQueryDto> result = jdbcTemplate.query(sql, param,
//             BeanPropertyRowMapper.newInstance(PostDetailQueryDto.class)
                postDetailRowMapper
        );

        return result;
    }

    private final RowMapper<PostDetailQueryDto> postDetailRowMapper = (resultSet, rowNum) -> {
        return new PostDetailQueryDto(
                resultSet.getLong("post_id"),
                resultSet.getString("post_name"),
                resultSet.getString("post_content"),
                resultSet.getTimestamp("post_created_at"),
                resultSet.getInt("post_views"),
                resultSet.getLong("post_member_id"),
                resultSet.getString("post_member_name"),
                resultSet.getLong("comment_id"),
                resultSet.getString("comment_content"),
                resultSet.getTimestamp("comment_created_at"),
                resultSet.getLong("comment_member_id"),
                resultSet.getString("comment_member_name")
        );
    };
}
