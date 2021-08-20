package web.service;

import web.model.Role;

import java.util.List;

public interface RoleService {
    void createRole(Role role);
    List<Role> getAllRole();
    Role getRoleByName(String name);
}
