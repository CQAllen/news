package com.allenliu.news.service.base;

import com.allenliu.news.domain.base.BaseModel;
import com.allenliu.news.mapper.base.BaseMapper;
import com.github.pagehelper.Page;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

public abstract class BaseService<T extends BaseModel> implements IBaseService<T> {
    /***
     * 由子类决定实际的Mapper
     * @return
     */
    protected abstract BaseMapper getMapper();

    /** 通过主键获取对象 */
    @Override
    public T get(Integer id) {
        Assert.notNull(id,"主键ID参数不能为空");
        return (T) getMapper().selectByPrimaryKey(id);
    }

    /** 通过对象属性作为查询条件进行查询 需要咨询补充对应的实现Mapper.xml**/
    @Override
    public List<T> find(T param) {
        return getMapper().selectBySelective(param);
    }


    /**
     * 查询带分页功能 需要咨询补充对应的实现Mapper.xml
     * @param params
     * @return
     */
    @Override
    public Page<T> findByPage(T params) {
        return getMapper().selectByPage(params);
    }
    /** 保存新对象（非空值） */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int save(T obj) {
        return getMapper().insert(obj);
    }

    /** 批量保存新对象 */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int saveBatch(List<T> list) {
        return getMapper().saveBatch(list);
    }

    /** 更新对象 */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int updateById(T obj) {
        return getMapper().updateByPrimaryKey(obj);
    }

    /** 通过对象ID 更新对象中非空属性字段 */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int update(T obj) {
        return getMapper().updateByPrimaryKeySelective(obj);
    }

    /** 批量保存新对象 */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int updateBatch(List<T> list) {
        int returnNum = 0;
        for(T obj : list){
            this.update(obj);
            returnNum++;
        }
        return returnNum;
    }


    /**
     * 通过对象的主键删除
     * @param obj
     * @return
     */
    @Transactional
    @Override
    public int delete(T obj) {
        Assert.notNull(obj.getId(),"delete 主键ID参数不能为空");
        return getMapper().deleteByPrimaryKey(obj.getId());
    }

    /**
     * 通过对象的主键删除
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int deleteById(Integer id) {
        Assert.notNull(id,"deleteById ID参数不能为空");
        return getMapper().deleteByPrimaryKey(id);
    }
}