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
    private String fileTheme;               //文件主题（属于哪一类科目文件）
    private Integer previousFile;           //上一个文件
    private Integer nextFile;               //下一个文件
    private Integer mainFile;               //若为附属文件，附属主文件

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

    public String getFileTheme() {
        return fileTheme;
    }

    public void setFileTheme(String fileTheme) {
        this.fileTheme = fileTheme;
    }

    public Integer getPreviousFile() {
        return previousFile;
    }

    public void setPreviousFile(Integer previousFile) {
        this.previousFile = previousFile;
    }

    public Integer getNextFile() {
        return nextFile;
    }

    public void setNextFile(Integer nextFile) {
        this.nextFile = nextFile;
    }

    public Integer getMainFile() {
        return mainFile;
    }

    public void setMainFile(Integer mainFile) {
        this.mainFile = mainFile;
    }
}
