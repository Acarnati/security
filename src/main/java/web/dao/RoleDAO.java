package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDAO {
    void createRole(Role role);
    Role getRoleByName(String name);
}
