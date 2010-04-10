package com.mjs_svc.possibility.util;

import com.mjs_svc.possibility.models.User;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class UserContainer {
    private static User user;

    public static void setUser(User _user) {
        user = _user;
    }

    public static User getUser() {
        return user;
    }
}
