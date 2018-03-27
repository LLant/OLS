package org.gdpu.ols.service.impl;

import org.gdpu.ols.core.AbstractService;
import org.gdpu.ols.model.ViewNote;
import org.gdpu.ols.service.ViewNoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ViewNoteServiceImpl extends AbstractService<ViewNote> implements ViewNoteService {
}
