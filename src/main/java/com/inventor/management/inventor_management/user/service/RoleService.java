package com.inventor.management.inventor_management.user.service;

public interface RoleService {
    void assignRole(String userId, String roleName);
    void deleteRoleFromUser(String userId, String roleName);
}
