package com.allenliu.news.service.base;

import com.allenliu.news.domain.base.BaseModel;
import com.github.pagehelper.Page;
import java.util.List;

public interface IBaseService<T extends BaseModel> {

    /**
     *  通过主键获取对象
     * @param id
     * @return
     */
    T get(Integer id);

    /**
     * 通过对象属性作为查询条件进行查询  需要咨询补充对应的实现Mapper.xml
     * @param param
     * @return
     */
    List<T> find(T param);

    /**
     * 根据条件查询分页
     *
     * @param obj 查询条件实体
     * @return
     */
    Page<T> findByPage(T obj);

    /**
     * 保存新对象
     * @param obj
     * @return 生成的ID
     */
    int save(T obj);

    /**
     * 根据List批量保存对象
     * @param list
     * @return
     */
    int saveBatch(List<T> list);

    /**
     * 根据List批量保存对象
     * @param list
     * @return
     */
    int updateBatch(List<T> list);

    /**
     * 通过对象ID 更新整个对象
     * @param obj
     * @return 生成的ID
     */
    int updateById(T obj);

    /**
     * 通过对象ID 更新对象中非空属性字段
     * @param obj
     * @return
     */
    int update(T obj);

    /**
     * 通过对象主健删除
     *
     * @param obj
     * @return
     */
    int delete(T obj);

    /**
     * 通过指定主键ID进行删除
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

}