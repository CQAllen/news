package com.allenliu.news.domain.mdm.page;

import com.allenliu.news.domain.mdm.MdmUser;
import javax.validation.constraints.Max;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 2:26 下午
 */
public class MdmUserPage extends MdmUser {
  /** 分页大小 */
  @Max(value=500, message = "每页不允许超过500条！")
  private int pageSize = 10;
  /** 当前页码 */
  private int pageNum = 1;

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  @Override
  public String toString() {
    return "MdmUserPage{" +
        "pageSize=" + pageSize +
        ", pageNum=" + pageNum +
        '}';
  }
}
