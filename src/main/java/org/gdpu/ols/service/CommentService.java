package org.gdpu.ols.service;

import org.gdpu.ols.core.Service;
import org.gdpu.ols.model.Comment;

public interface CommentService extends Service<Comment> {

    void updateCommentAssessStatus(String status,int id);
}
