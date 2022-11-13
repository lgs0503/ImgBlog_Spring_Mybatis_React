package com.user.service;

import com.user.vo.UserVo;

public interface UserService {
    boolean login(UserVo userVo);

    void register(UserVo userVo);
}
