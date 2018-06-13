package com.teamc.service;

import com.teamc.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void save(Role role);

    Role findById(Long id);

    Role findByName(String roleName);

}

