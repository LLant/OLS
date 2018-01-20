package org.gdpu.ols.service.impl;

import org.gdpu.ols.mapper.AdminMapper;
import org.gdpu.ols.model.Admin;
import org.gdpu.ols.service.AdminService;
import org.gdpu.ols.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin selectOne(String idStr) {
        Admin admin=null;
        if (idStr!=null && !"".equals(idStr)){
            Integer id=Integer.parseInt(idStr);
            admin=this.adminMapper.selectOne(id);
        }
        return admin;
    }

    @Override
    public Integer addAdminBatch(List<Admin> admins) {
        Integer result=0;

        if(admins!=null && admins.size()!=0){
            Date date=null;
            List<Admin> newAdminList=new ArrayList<Admin>();
            for(Admin item:admins){
                date=new Date();
                item.setLastestLoginDate(date);
                item.setLastestUpdateDate(date);
                item.setAdminPassword(MD5Util.MD5Encode(item.getAdminPassword(),""));
                newAdminList.add(item);
            }
            result=this.adminMapper.addAdminBatch(newAdminList);
        }

        return result;
    }
}
