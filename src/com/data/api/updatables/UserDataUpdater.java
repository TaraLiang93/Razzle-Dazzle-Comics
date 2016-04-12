package com.data.api.updatables;

import com.data.UserData;
import com.data.api.Updateable;
import com.google.appengine.api.users.User;

/**
 * Created by Zhenya on 4/8/16.
 */

/**
 * this class inherits the generic updateEntity method from Updateable and exists only specify the actual type
 * of the generic T
 */
public class UserDataUpdater extends Updateable<UserData> {
}
