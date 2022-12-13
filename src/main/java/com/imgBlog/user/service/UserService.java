package com.imgBlog.user.service;

import com.imgBlog.user.vo.UserVo;

public interface UserService {
    boolean login(UserVo userVo);

    void register(UserVo userVo);

    boolean IdDuplicateCheck(UserVo userVo);
}
