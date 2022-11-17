package com.imgBlog.user.mapper;

import com.imgBlog.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    public UserVo login(UserVo userVo);

    public void register(UserVo userVo);
}
