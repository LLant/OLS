package org.gdpu.ols.service.impl;

import org.gdpu.ols.core.AbstractService;
import org.gdpu.ols.mapper.RoleRelationshipMapper;
import org.gdpu.ols.model.RoleRelationship;
import org.gdpu.ols.service.RoleRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleRelationshipServiceImpl extends AbstractService<RoleRelationship> implements RoleRelationshipService{

    @Resource
    private RoleRelationshipMapper roleRelationshipMapper;

    @Override
    public Integer disFollow(Integer student, Integer teacher) {
        return this.roleRelationshipMapper.disFollow(student,teacher);
    }
}
