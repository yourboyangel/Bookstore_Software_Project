package Permissions;

import Model.Role;

public class ManagerPermissions extends Permission{
    @Override
    protected String[] getPermissionRole(Role role){
        return new String[]{ADD_BOOK,CHECK_LIBRARIAN_PERFORMANCE,SEE_BOOK_STATISTICS};
    }
}
