package com.allenliu.news.domain.busi.page;

import com.allenliu.news.domain.busi.NewsInfo;
import javax.validation.constraints.Max;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 10:40 下午
 */
public class NewsInfoPage extends NewsInfo {
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

  @Override
  public String toString() {
    return "NewsInfoPage{" +
        "pageSize=" + pageSize +
        ", pageNum=" + pageNum +
        '}';
  }
}
