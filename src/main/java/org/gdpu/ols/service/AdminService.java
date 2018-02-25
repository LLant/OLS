package org.gdpu.ols.service;

import org.gdpu.ols.core.Service;
import org.gdpu.ols.model.Admin;

import java.util.List;

public interface AdminService extends Service<Admin> {

    Admin selectOne(String idStr);
    Integer addAdminBatch(List<Admin> admins);
}
