package Permissions;

import Model.Role;

import java.io.Serializable;

public class ManagerPermissions implements Permission, Serializable {
    @Override
    public String[] getPermissions() {
        return new String[]{ADD_BOOK, CHECK_LIBRARIAN_PERFORMANCE, SEE_BOOK_STATISTICS};
    }

    @Override
    public boolean hasPermission(Role role, String permission) {
        return false;
    }
}
