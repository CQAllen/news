package com.allenliu.news.domain.mdm;

import com.allenliu.news.domain.base.BaseModel;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 1:20 下午
 */
public class MdmUser extends BaseModel {
  private String username;
  private String password;
  private String role;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "MdmUser{" +
        "name='" + username + '\'' +
        ", password='" + password + '\'' +
        ", role='" + role + '\'' +
        '}';
  }
}
