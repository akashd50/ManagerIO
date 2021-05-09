package com.greymatter.managerio;

import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.objects.AppUser;

public class AppServices {
    private static AppUser activeUser;

    public static AppUser getActiveUser() {
        if (activeUser == null) {
            activeUser = new AppUser();
            activeUser.setId(1);
            activeUser.setEmail("admin@admin.com");
            activeUser.setUsername("admin");
            int nextId = DBServices.getUsersDBHelper().getNextId();
            if(nextId == -1) {
                DBServices.getUsersDBHelper().insert(activeUser);
            }
        }
        return activeUser;
    }
}
