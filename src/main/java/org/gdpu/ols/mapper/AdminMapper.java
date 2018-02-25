package org.gdpu.ols.mapper;

import org.gdpu.ols.model.Admin;

import java.util.List;

public interface AdminMapper extends org.gdpu.ols.core.Mapper<Admin>{

    Admin selectOneAdmin(Integer id);
    int addAdminBatch(List<Admin> admins);
}
