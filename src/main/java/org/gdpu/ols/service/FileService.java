package org.gdpu.ols.service;

import org.gdpu.ols.core.Service;
import org.gdpu.ols.model.File;

import java.util.List;

public interface FileService extends Service<File> {
    Boolean addFile(List<File> fileList);

}
