package Permissions;

import Model.Role;
import Model.User;

import java.util.Arrays;

public abstract class Permission {
    public static final String CREATE_BILL="CREATE_BILL";
    public static final String ADD_BOOK="ADD_BOOK";
    public static final String CHECK_LIBRARIAN_PERFORMANCE="CHECK_LIBRARIAN_PERFORMANCE";
    public static final String SEE_BOOK_STATISTICS="SEE_BOOK_STATISTICS";
    public static final String MANAGE_EMPLOYEES="MANAGE_EMPLOYEES";
    public static final String TOTAL_COST="TOTAL_COST";

    protected abstract String[] getPermissionRole(Role role);

    public boolean hasPermission(User user, String permission){
        String[] permissionRole = getPermissionRole(user.getRole());
        return Arrays.asList(permissionRole).contains(permission);
    }

}
