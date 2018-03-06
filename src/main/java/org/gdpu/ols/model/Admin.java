package org.gdpu.ols.model;

import org.apache.ibatis.type.Alias;
import org.gdpu.ols.common.BaseBean;

import javax.persistence.Id;
import java.util.Date;

@Alias("admin")
public class Admin extends BaseBean{

    @Id
    private Integer id;
    private String adminName;
    private transient String adminPassword;
    private Integer lastestUpdateUser;
    private Date lastestLoginDate;
    private Date lastestUpdateDate;

    public Admin(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Integer getLastestUpdateUser() {
        return lastestUpdateUser;
    }

    public void setLastestUpdateUser(Integer lastestUpdateUser) {
        this.lastestUpdateUser = lastestUpdateUser;
    }

    public Date getLastestLoginDate() {
        return lastestLoginDate;
    }

    public void setLastestLoginDate(Date lastestLoginDate) {
        this.lastestLoginDate = lastestLoginDate;
    }

    public Date getLastestUpdateDate() {
        return lastestUpdateDate;
    }

    public void setLastestUpdateDate(Date lastestUpdateDate) {
        this.lastestUpdateDate = lastestUpdateDate;
    }
}
