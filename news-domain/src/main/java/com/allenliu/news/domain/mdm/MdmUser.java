package com.allenliu.news.domain.mdm;

import com.allenliu.news.domain.base.BaseModel;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 1:20 下午
 */
public class MdmUser extends BaseModel {
  private String name;
  private String password;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "MdmUser{" +
        "name='" + name + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
