package org.gdpu.ols.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.gdpu.ols.bean.MyPageRequest;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.*;
import org.gdpu.ols.service.CommentService;
import org.gdpu.ols.service.MessageService;
import org.gdpu.ols.service.ViewCommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/OLS/comment")
public class CommentController extends BaseController{

    private static final String ERROR_CODE="9999";
    private static final String SUCCESS_CODE="8888";
    private static final String ADMINREAD="Y";

    @Resource
    private CommentService commentService;
    @Resource
    private ViewCommentService viewCommentService;
    @Resource
    private MessageService messageService;

    @PostMapping("/reply")
    @ResponseBody
    public String reply(@RequestBody Comment comment){
        Comment newComment=this.commentService.findById(comment.getId());
        newComment.setReplyContent(comment.getReplyContent());
        newComment.setIsRead("Y");
        this.commentService.update(newComment);

        this.setMessage(newComment.getCommentAuthor(),"<strong>[你的评论]</strong>"+
                newComment.getCommentContent()+"</br>"+
                "<span style=\"font-color:blue;\">"+newComment.getReplyContent()+"</span>");
        return "8888";
    }

    @PostMapping("/ignore")
    @ResponseBody
    public String ignore(@RequestBody Comment comment){
        Comment newComment=this.commentService.findById(comment.getId());
        newComment.setIsRead("Y");
        this.commentService.update(newComment);

        return "8888";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseBean addComment(@RequestBody Comment comment, HttpSession session){

        Student student=null;
        if (session.getAttribute(session.getId())!=null){
            student= (Student) session.getAttribute(session.getId());
        }

        ResponseBean responseBean=new ResponseBean();
        comment.setCommentAuthor(student.getId());
        comment.setCommentDate(new Date());
        comment.setIsRead("N");
        comment.setAdminRead("N");
        try {
            this.commentService.save(comment);
            responseBean.setResultCode(SUCCESS_CODE);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setResultCode(ERROR_CODE);
        }finally {
            return responseBean;
        }

    }


    @ResponseBody
    @GetMapping("/read/{commentId:\\d{1,11}}")
    public String readComment(@PathVariable int commentId){
        this.commentService.updateCommentAssessStatus(ADMINREAD,commentId);
        return SUCCESS;
    }

    @ResponseBody
    @PostMapping(value = "/getAssessComment")
    public ResponseBean getAssessComment(@RequestBody MyPageRequest myPageRequest){
        return this.pageQuery(myPageRequest,"admin_read='N'");
    }


    @ResponseBody
    @PostMapping(value = "/getComment")
    public ResponseBean getComment(@RequestBody(required = false) MyPageRequest myPageRequest){
        return this.pageQuery(myPageRequest,"courseware_id="+myPageRequest.getCondition());
    }

    @ResponseBody
    @PostMapping(value = "/getUnreadComment")
    public ResponseBean getUnreadComment(@RequestBody(required = false) MyPageRequest myPageRequest,HttpSession session){
        Student student= (Student) session.getAttribute(session.getId());
        String query="is_read='N' and author="+student.getTeacherId();
        return this.pageQuery(myPageRequest,query);
    }

    private ResponseBean pageQuery(MyPageRequest myPageRequest, String str){
        List<ViewComment> list=null;
        PageHelper.startPage(Integer.parseInt(myPageRequest.getOffset()),
                Integer.parseInt(myPageRequest.getLimit()));

        Condition condition=new Condition(ViewComment.class);
        condition.createCriteria().andCondition(str);
        condition.setOrderByClause("comment_date desc");
        list=this.viewCommentService.findByCondition(condition);

        PageInfo pageInfo=new PageInfo(list);
        ResponseBean responseBean=new ResponseBean();
        responseBean.setData(pageInfo);
        return responseBean;
    }

    private void setMessage(Integer targetUser,String content){
        Message message=new Message();
        message.setContent(content);
        message.setDate(new Date());
        message.setIsRead("N");
        message.setTargetUser(targetUser);
        this.messageService.save(message);
    }
}
