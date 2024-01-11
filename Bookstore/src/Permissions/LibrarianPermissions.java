package Permissions;

import Model.Role;

public class LibrarianPermissions extends Permission{
    @Override
    protected String[] getPermissionRole(Role role){
        return new String[]{CREATE_BILL};
    }
}
