package org.gdpu.ols.service.impl;

import org.gdpu.ols.core.AbstractService;
import org.gdpu.ols.mapper.FileMapper;
import org.gdpu.ols.model.File;
import org.gdpu.ols.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl extends AbstractService<File> implements FileService{

    @Resource
    private FileMapper fileMapper;

    @Override
    public Integer deleteByCourseware(Integer coursewareId) {
        return this.fileMapper.deleteByCourseware(coursewareId);
    }
}
