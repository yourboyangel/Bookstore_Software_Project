package Permissions;

import Model.Role;

import java.io.Serializable;

public interface Permission {
    String CREATE_BILL = "CREATE_BILL";
    String ADD_BOOK = "ADD_BOOK";
    String CHECK_LIBRARIAN_PERFORMANCE = "CHECK_LIBRARIAN_PERFORMANCE";
    String SEE_BOOK_STATISTICS = "SEE_BOOK_STATISTICS";
    String MANAGE_EMPLOYEES = "MANAGE_EMPLOYEES";
    String TOTAL_COST = "TOTAL_COST";

    String[] getPermissions();

    boolean hasPermission(Role role, String permission);
}
