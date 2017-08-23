package com.vanging.www.yoyo.persistence.mapper;

import com.vanging.www.yoyo.persistence.entity.Class;
import com.vanging.www.yoyo.persistence.entity.Profile;
import org.apache.ibatis.annotations.Param;

public interface ProfileMapper
{
    public String selectLocation(String user_id);
    public void insertLocation(Profile profile);
    public void updateLocation(Profile profile);
}
