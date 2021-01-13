package com.pet.project.models;

public enum Permission {
    READ("user:read"),
    ADD("user:add"),
    EDIT("user:edit"),
    DELETE("user:delete"),
    CHANGE_STATUS("user:change_status"),
    CHANGE_ROLE("user:change_role");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
