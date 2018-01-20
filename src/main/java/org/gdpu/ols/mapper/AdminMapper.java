package org.gdpu.ols.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.gdpu.ols.model.Admin;

import java.util.List;

@Mapper
public interface AdminMapper {

    Admin selectOne(Integer id);
    int addAdminBatch(List<Admin> admins);
}
