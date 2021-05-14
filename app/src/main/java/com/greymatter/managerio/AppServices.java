package com.greymatter.managerio;

import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.objects.AppUser;
import com.greymatter.managerio.objects.IDS;

import java.util.List;

public class AppServices {
    private static AppUser activeUser;

    public static void setActiveUser(AppUser user) {
        if (!DBServices.getUsersDBHelper().contains(user)) {
            DBServices.getUsersDBHelper().insert(user);
        }

        List<AppUser> users = DBServices.getUsersDBHelper().getWithEmail(user.getEmail());
        if (users.size() > 0) {
            activeUser = users.get(0);
        }
    }

    public static void updateActiveUser() {
        DBServices.getUsersDBHelper().update(activeUser);
    }

    public static AppUser getActiveUser() {
        return activeUser;
    }
}
