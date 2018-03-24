package org.gdpu.ols.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.gdpu.ols.bean.MyPageRequest;
import org.gdpu.ols.common.BaseController;
import org.gdpu.ols.common.ResponseBean;
import org.gdpu.ols.model.Note;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.service.NoteService;
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

    @ResponseBody
    @PostMapping(value = "/getNote")
    public ResponseBean getNote(@RequestBody(required = false) MyPageRequest myPageRequest){

        PageHelper.startPage(Integer.parseInt(myPageRequest.getOffset()),
                Integer.parseInt(myPageRequest.getLimit()));

        List<Note> noteList=null;
        String coursewareId=null;
        if(!StringUtils.isEmpty(myPageRequest.getCondition()))
            coursewareId=myPageRequest.getCondition();

        Condition condition=new Condition(Note.class);
        condition.createCriteria().andCondition("courseware_id="+coursewareId);
        condition.setOrderByClause("note_data");
        noteList=this.noteService.findByCondition(condition);

        PageInfo pageInfo=new PageInfo(noteList);
        ResponseBean responseBean=new ResponseBean();
        responseBean.setResultCode(SUCCESS_CODE);
        responseBean.setData(pageInfo);

        return responseBean;
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

}
