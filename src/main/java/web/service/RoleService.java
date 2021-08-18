package web.service;

import web.model.Role;

public interface RoleService {
    void createRole(Role role);
    Role getRoleByName(String name);
}
