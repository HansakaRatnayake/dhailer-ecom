package com.eCom.dhailer.service;

import com.eCom.dhailer.dto.request.RequestRoleDto;

public interface RoleService {

    public void create(RequestRoleDto dto);
    public void initializeUserRoles();
}
