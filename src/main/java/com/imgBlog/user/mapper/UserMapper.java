package com.imgBlog.user.mapper;

import com.imgBlog.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    UserVo login(UserVo userVo);

    void register(UserVo userVo);

    boolean IdDuplicateCheck(UserVo userVo);
}
