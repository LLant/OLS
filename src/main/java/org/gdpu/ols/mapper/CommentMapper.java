package org.gdpu.ols.mapper;

import org.apache.ibatis.annotations.Param;
import org.gdpu.ols.model.Comment;

public interface CommentMapper extends org.gdpu.ols.core.Mapper<Comment>{

    int updateCommentAssessStatus(@Param("status") String status, @Param("id") Integer id);
}
