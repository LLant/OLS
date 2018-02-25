package org.gdpu.ols.service.impl;

import org.gdpu.ols.core.AbstractService;
import org.gdpu.ols.mapper.FileMapper;
import org.gdpu.ols.model.File;
import org.gdpu.ols.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class FileServiceImpl extends AbstractService<File> implements FileService{

    @Resource
    private FileMapper fileMapper;

    @Override
    public Boolean addFile(List<File> fileList) {
        int i=0;
        i=this.fileMapper.addFileBatch(fileList);
        if (i==0) {
            return false;
        }else {
            return true;
        }
    }
}
