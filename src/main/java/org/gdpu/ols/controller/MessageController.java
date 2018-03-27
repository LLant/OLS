package org.gdpu.ols.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.gdpu.ols.bean.MyPageRequest;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.Message;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/OLS/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @ResponseBody
    @PostMapping("/read")
    public String setMessageIsRead(@RequestBody Message message){
        Message newMessage=this.messageService.findById(message.getId());
        newMessage.setIsRead("Y");
        this.messageService.update(newMessage);
        return "8888";
    }

    @ResponseBody
    @PostMapping("/getMessage")
    public ResponseBean getMessage(HttpSession session,@RequestBody MyPageRequest myPageRequest){
        ResponseBean responseBean=new ResponseBean();
        Student student= (Student) session.getAttribute(session.getId());

        PageHelper.startPage(Integer.parseInt(myPageRequest.getOffset()),
                Integer.parseInt(myPageRequest.getLimit()));

        Condition condition=new Condition(Message.class);
        condition.createCriteria().andCondition("target_user="+student.getId()+" and is_read='N'");
        condition.setOrderByClause("date desc");
        List<Message> list=this.messageService.findByCondition(condition);

        PageInfo pageInfo=new PageInfo(list);
        responseBean.setData(pageInfo);
        return responseBean;
    }
}
