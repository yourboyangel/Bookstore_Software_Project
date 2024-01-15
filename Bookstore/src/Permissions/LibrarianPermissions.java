package Permissions;

import Model.Role;

import java.io.Serializable;

public class LibrarianPermissions implements Permission, Serializable {
    @Override
    public String[] getPermissions() {
        return new String[]{CREATE_BILL};
    }

    @Override
    public boolean hasPermission(Role role, String permission) {
        return false; // Implement as needed
    }
}
