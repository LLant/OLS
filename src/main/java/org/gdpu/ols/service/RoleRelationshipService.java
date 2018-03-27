package org.gdpu.ols.service;

import org.gdpu.ols.core.Service;
import org.gdpu.ols.model.RoleRelationship;

public interface RoleRelationshipService extends Service<RoleRelationship> {
    Integer disFollow(Integer student,Integer teacher);
}
