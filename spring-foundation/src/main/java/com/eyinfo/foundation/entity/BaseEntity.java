package com.eyinfo.foundation.entity;

import java.io.Serializable;

/**
 * @Author lijinghuan
 * @Email:ljh0576123@163.com
 * @CreateTime:2018/4/5
 * @Description:
 * @Modifier:
 * @ModifyContent:
 */
public class BaseEntity implements Serializable {

    //id数据
    private Long id;

    //创建时间
    private Long createTime;

    //修改时间
    private Long modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }
}
