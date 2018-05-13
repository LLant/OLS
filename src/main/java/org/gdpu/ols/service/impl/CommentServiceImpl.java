package org.gdpu.ols.service.impl;

import org.gdpu.ols.core.AbstractService;
import org.gdpu.ols.mapper.CommentMapper;
import org.gdpu.ols.model.Comment;
import org.gdpu.ols.model.ViewNote;
import org.gdpu.ols.service.CommentService;
import org.gdpu.ols.service.ViewNoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl extends AbstractService<Comment> implements CommentService{

    @Resource
    private CommentMapper commentMapper;

    @Override
    public void updateCommentAssessStatus(String status, int id) {
        this.commentMapper.updateCommentAssessStatus(status,id);
    }
}
