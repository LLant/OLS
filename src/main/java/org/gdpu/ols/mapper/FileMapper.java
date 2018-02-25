package org.gdpu.ols.mapper;

import org.gdpu.ols.model.File;

import java.util.List;

public interface FileMapper extends org.gdpu.ols.core.Mapper<File> {

    Integer addFileBatch(List<File> fileList);
}
