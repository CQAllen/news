package com.allenliu.news.service.busi.impl;

import com.allenliu.news.mapper.base.BaseMapper;
import com.allenliu.news.mapper.busi.NewsInfoMapper;
import com.allenliu.news.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 9:46 下午
 */
@Service
public class NewsInfoServiceImpl extends BaseService {
  @Autowired
  private NewsInfoMapper mapper;

  @Override
  protected BaseMapper getMapper() {
    return mapper;
  }
}
