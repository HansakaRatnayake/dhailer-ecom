package com.eCom.dhailer.service;

import com.eCom.dhailer.dto.request.RequestUserDto;

public interface UserService {

    public void create(RequestUserDto dto);
    public void initializeAdmin();


}
