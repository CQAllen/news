package com.allenliu.news.domain.busi;

import com.allenliu.news.domain.base.BaseModel;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 9:36 下午
 */
public class NewsInfo extends BaseModel {
 private String newsTitle;
 private String newsContent;

  public String getNewsTitle() {
    return newsTitle;
  }

  public void setNewsTitle(String newsTitle) {
    this.newsTitle = newsTitle;
  }

  public String getNewsContent() {
    return newsContent;
  }

  public void setNewsContent(String newsContent) {
    this.newsContent = newsContent;
  }

  @Override
  public String toString() {
    return "NewsInfo{" +
        "newsTitle='" + newsTitle + '\'' +
        ", newsContent='" + newsContent + '\'' +
        '}';
  }
}
