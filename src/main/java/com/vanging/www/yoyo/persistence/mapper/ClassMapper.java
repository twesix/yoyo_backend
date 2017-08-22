package com.vanging.www.yoyo.persistence.mapper;

import com.vanging.www.yoyo.persistence.entity.Class;
import org.apache.ibatis.annotations.Param;

public interface ClassMapper
{
    public Class selectById(@Param("class_id") String class_id);
    public void insert(Class new_class);
}
