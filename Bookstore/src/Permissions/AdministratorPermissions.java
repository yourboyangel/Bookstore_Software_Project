package Permissions;

import Model.Role;

public class AdministratorPermissions extends Permission{
    @Override
    protected String[] getPermissionRole(Role role){
        return new String[]{CREATE_BILL,ADD_BOOK,CHECK_LIBRARIAN_PERFORMANCE,SEE_BOOK_STATISTICS,MANAGE_EMPLOYEES,TOTAL_COST};
    }
}
