package org.gdpu.ols.service.impl;

import org.gdpu.ols.core.AbstractService;
import org.gdpu.ols.model.ViewTeacherDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ViewTeacherDetailServiceImpl extends AbstractService<ViewTeacherDetail> implements ViewTeacherDetailService{

}
