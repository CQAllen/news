package com.allenliu.news.domain.base;

import java.io.Serializable;
import java.util.Date;

/**
 *  @Author: Allen Liu
 *  @Date: 2020/7/4 10:41 上午
 */
public class BaseModel implements Serializable {
	/** ID */
	private Integer id;
	/** create id */
	private Integer createId;
	/** create time */
	private Date createTime;
	/** create user */
	private String createName;
	/** update time */
	private Integer updateId;
	/** update time */
	private Date updateTime;
	/** update user */
	private String updateName;
	/** deleted flag 0:none-deleted 1:deleted */
	private Integer deleted = 0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	@Override
	public String toString() {
		return "BaseModel{" +
				"id=" + id +
				", createId=" + createId +
				", createTime=" + createTime +
				", createName='" + createName + '\'' +
				", updateId=" + updateId +
				", updateTime=" + updateTime +
				", updateName='" + updateName + '\'' +
				", deleted=" + deleted +
				'}';
	}
}
