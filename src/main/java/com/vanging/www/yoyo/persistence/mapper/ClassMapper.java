package com.vanging.www.yoyo.persistence.mapper;

import com.vanging.www.yoyo.persistence.entity.Class;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper
{
    public Class selectById(@Param("class_id") String class_id);
    public List<Class> selectByKeyWord(@Param("key_word") String key_word);
    public void insert(Class new_class);
}
