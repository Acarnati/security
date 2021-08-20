package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDAO {
    void createRole(Role role);
    List<Role> getAllRole();
    Role getRoleByName(String name);
}
