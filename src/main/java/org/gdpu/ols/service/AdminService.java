package org.gdpu.ols.service;

import org.gdpu.ols.model.Admin;

import java.util.List;

public interface AdminService {

    Admin selectOne(String idStr);
    Integer addAdminBatch(List<Admin> admins);
}
