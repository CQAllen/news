package com.allenliu.news.mapper.base;

import com.github.pagehelper.Page;
import com.allenliu.news.domain.base.BaseModel;
import java.util.List;

/**
 * @Author: Allen Liu
 * @Date: 2020/7/4 10:39 上午
 */
public interface BaseMapper<T extends BaseModel> {

  /** 保存对象 */
  int insert(T obj);
  /** 批量保存 */
  int saveBatch(List<T> list);

  /** 通过主键查询对象*/
  T selectByPrimaryKey(Integer id);

  /** 通过对象属性作为查询条件进行查询 条件不设置查询为空**/
  List<T> selectBySelective(T param);

  /** 根据条件查询分页 */
  Page<T> selectByPage(T params);


  /** 通过主键更新记录(非null值) */
  int updateByPrimaryKeySelective(T record);

  /** 通过主键更新记录 */
  int updateByPrimaryKey(T record);

  /** 通过指定主键ID删除记录 */
  int deleteByPrimaryKey(Integer id);
}
