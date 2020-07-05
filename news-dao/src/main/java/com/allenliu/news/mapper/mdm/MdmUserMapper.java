package com.allenliu.news.mapper.mdm;

import com.allenliu.news.domain.mdm.MdmUser;
import com.allenliu.news.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 1:22 下午
 */
@Mapper
@Repository
public interface MdmUserMapper extends BaseMapper {
  MdmUser selectByName(String name);
}
