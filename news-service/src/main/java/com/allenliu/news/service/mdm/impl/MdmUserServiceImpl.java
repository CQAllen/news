package com.allenliu.news.service.mdm.impl;

import com.allenliu.news.mapper.base.BaseMapper;
import com.allenliu.news.mapper.mdm.MdmUserMapper;
import com.allenliu.news.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 1:32 下午
 */
@Service
public class MdmUserServiceImpl extends BaseService {
  @Autowired
  private MdmUserMapper mdmUserMapper;
  @Override
  protected BaseMapper getMapper() {
    return mdmUserMapper;
  }
}
