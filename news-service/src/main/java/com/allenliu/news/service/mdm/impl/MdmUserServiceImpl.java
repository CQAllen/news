package com.allenliu.news.service.mdm.impl;

import com.allenliu.news.domain.mdm.MdmUser;
import com.allenliu.news.domain.mdm.dto.JwtUser;
import com.allenliu.news.mapper.base.BaseMapper;
import com.allenliu.news.mapper.mdm.MdmUserMapper;
import com.allenliu.news.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 1:32 下午
 */
@Service("mdmUserServiceImpl")
public class MdmUserServiceImpl extends BaseService implements UserDetailsService {
  @Autowired
  private MdmUserMapper mdmUserMapper;
  @Override
  protected BaseMapper getMapper() {
    return mdmUserMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    MdmUser user = mdmUserMapper.selectByName(s);
    return new JwtUser(user);
  }
}
