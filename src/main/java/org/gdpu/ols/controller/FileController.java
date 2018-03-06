package org.gdpu.ols.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.gdpu.ols.model.File;
import org.gdpu.ols.model.Student;
import org.gdpu.ols.service.FileService;
import org.gdpu.ols.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/")
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileService fileService;

    public String saveFile(@RequestBody File file, HttpServletRequest request, HttpSession session) {
        String teacherId = "";
        if (session.getAttribute(session.getId()) != null) {
            teacherId = ((Student) session.getAttribute(session.getId())).getId().toString();
        }
        try {
            String storageLocation=this.saveFile(teacherId,request);
            file.setStorageLocation(storageLocation);
            List<File> fileList=new ArrayList<File>();
            fileList.add(file);
            this.fileService.addFile(fileList);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    private String saveFile(String teacherId, HttpServletRequest request) throws Exception {
        //创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //通过工厂创建解析器
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //设置upload的编码
        fileUpload.setHeaderEncoding("UTF-8");
        //判断上传表单的类型
        if (!fileUpload.isMultipartContent(request)) {
            return "";
        }
        String realPath="";
        //解析request对象，得到List【装载着上传的全部内容】
        List<FileItem> list = fileUpload.parseRequest(request);
        for (FileItem fileItem : list) {
            //如果是普通输入项
            if (fileItem.isFormField()) {
                //得到输入项的名称和值
                String name = fileItem.getFieldName();
                String value = fileItem.getString();
                logger.info(name + ":" + value);
            } else {
                //如果是上传文件
                //得到上传名称【包括路径名】
                String fileName = fileItem.getName();
                //截取文件名
                fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                //生成独一无二的文件名
                fileName = FileUtil.makeFileName(fileName);

                InputStream inputStream = fileItem.getInputStream();

                //得到项目的路径，把上传文件写到项目中
                String path = request.getServletContext().getRealPath("/resources/static/upload");
                logger.info("path:", path);

                //得到分散后的目录路径
                realPath = FileUtil.makeDirPath(teacherId, fileName, path);

                FileOutputStream outputStream = new FileOutputStream(realPath + "\\" + fileName);

                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, len);
                }
                inputStream.close();
                outputStream.close();

                //删除临时文件的数据
                fileItem.delete();
            }
        }
        return realPath;
    }
}
