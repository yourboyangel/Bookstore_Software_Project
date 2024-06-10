package Permissions;

import Model.Role;

import java.io.Serializable;

public class AdministratorPermissions implements Permission, Serializable {
    @Override
    public String[] getPermissions() {
        return new String[]{CREATE_BILL, ADD_BOOK, CHECK_LIBRARIAN_PERFORMANCE, SEE_BOOK_STATISTICS, MANAGE_EMPLOYEES, TOTAL_COST};
    }

    @Override
    public boolean hasPermission(Role role, String permission) {
        return false;
    }
}
