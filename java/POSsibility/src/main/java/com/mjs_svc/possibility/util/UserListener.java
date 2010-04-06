package com.mjs_svc.possibility.util;

import com.mjs_svc.possibility.models.User;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public interface UserListener {

    /**
     * Listen for a user object
     * @param user
     */
    public void setUser(User user);
}
