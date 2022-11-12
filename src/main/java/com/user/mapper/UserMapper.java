package com.user.mapper;

import com.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    public int login(UserVo userVo);
}
