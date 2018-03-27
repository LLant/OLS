package org.gdpu.ols.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.gdpu.ols.bean.MyPageRequest;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.Note;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.model.ViewNote;
import org.gdpu.ols.service.NoteService;
import org.gdpu.ols.service.ViewNoteService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/OLS/note")
public class NoteController extends BaseController{

    private static final String ERROR_CODE="9999";
    private static final String SUCCESS_CODE="8888";

    @Resource
    private NoteService noteService;
    @Resource
    private ViewNoteService viewNoteService;

    @ResponseBody
    @PostMapping(value = "/getNote")
    public ResponseBean getNote(@RequestBody(required = false) MyPageRequest myPageRequest){
        return this.pageQuery(myPageRequest,"courseware_id",myPageRequest.getCondition());
    }

    @ResponseBody
    @PostMapping("/addNote")
    public Map<String,String> addNote(@RequestBody Note note, HttpSession session){
        Map<String,String> map=null;
        Student student=null;
        if (session.getAttribute(session.getId())!=null){
            student= (Student) session.getAttribute(session.getId());

        }
        Date date=new Date();
        note.setNoteDate(date);
        note.setUser(student.getId());
        try {
            this.noteService.save(note);

        }catch (Exception e){
            e.printStackTrace();
            return map;
        }
        map=new HashMap<String, String>();
        map.put("name",student.getStudentName());
        map.put("photoLocation",student.getPhotoStorageLocation());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        map.put("date",simpleDateFormat.format(date));

        map.put("note",note.getNoteContent());

        return map;
    }


    @ResponseBody
    @PostMapping("/getPersonalNote")
    public ResponseBean getPersonNote(@RequestBody(required = false) MyPageRequest myPageRequest,
                                      HttpSession session){

        Student student= (Student) session.getAttribute(session.getId());
        return this.pageQuery(myPageRequest,"user",student.getId().toString());
    }

    private ResponseBean pageQuery(MyPageRequest myPageRequest,String str,String value){
        List<ViewNote> list=null;
        PageHelper.startPage(Integer.parseInt(myPageRequest.getOffset()),
                Integer.parseInt(myPageRequest.getLimit()));

        Condition condition=new Condition(ViewNote.class);
        condition.createCriteria().andCondition(str+"="+value);
        condition.setOrderByClause("note_date desc");
        list=this.viewNoteService.findByCondition(condition);

        PageInfo pageInfo=new PageInfo(list);
        ResponseBean responseBean=new ResponseBean();
        responseBean.setData(pageInfo);
        return responseBean;
    }
}
