package org.gdpu.ols.model;

import org.gdpu.ols.common.BaseBean;

import javax.persistence.Id;
import java.util.Date;

public class File extends BaseBean{

    @Id
    private Integer id;
    private String fileName;
    private String storageLocation;         //存储位置
    private Date publishDate;               //发布日期
    private String fileType;                //文件格式类型
    private Integer author;                 //作者
    private Integer mainFile;               //若为附属文件，附属主文件 0附属
    private Integer courseware;

    public File(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getCourseware() {
        return courseware;
    }

    public void setCourseware(Integer courseware) {
        this.courseware = courseware;
    }

    public Integer getMainFile() {
        return mainFile;
    }

    public void setMainFile(Integer mainFile) {
        this.mainFile = mainFile;
    }
}
