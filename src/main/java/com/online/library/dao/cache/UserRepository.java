package com.online.library.dao.cache;

import com.online.library.dao.entity.UserProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private static UserRepository ourInstance = new UserRepository();
    private Map<String, UserProfile> loginToProfile;
    private Map<String, UserProfile> sessionIdToProfile;

    public static UserRepository getInstance() {
        return ourInstance;
    }

    private UserRepository() {
        loginToProfile = new HashMap<String, UserProfile>();
        sessionIdToProfile = new HashMap<String, UserProfile>();

        //Add default testUser admin userProfile
        UserProfile admin = new UserProfile("admin","123","admin@support.com");
        loginToProfile.put("admin", admin);
    }

    public void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }

    public void deleteUser(String user) {
        loginToProfile.remove(user);
    }

    public void updateUser(UserProfile userProfile) {
        for (Map.Entry<String, UserProfile> entry : loginToProfile.entrySet()) {
            String key = entry.getKey();
            if (key.equals(userProfile.getLogin())) {
                loginToProfile.replace(key, userProfile);
            }
        }
        for (Map.Entry<String, UserProfile> entry : sessionIdToProfile.entrySet()) {
            String key = entry.getKey();
            if (key.equals(userProfile.getLogin())) {
                sessionIdToProfile.replace(key, userProfile);
            }
        }
    }

    public void initializeUsers(List<UserProfile> userProfileList) {
        for (int i = 0; i < userProfileList.size(); i++) {
            UserProfile profile = userProfileList.get(i);
            loginToProfile.put(profile.getLogin(), profile);
        }
    }
}
