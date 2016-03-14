package com.online.library.service;

import com.online.library.dao.cache.UserRepository;
import com.online.library.dao.entity.UserProfile;

public class AccountService {

    public UserProfile getUserByLogin(String login) {
        return UserRepository.getInstance().getUserByLogin(login);
    }

    public UserProfile getUserBySessionId(String requestedSessionId) {
        return UserRepository.getInstance().getUserBySessionId(requestedSessionId);
    }

    public void addNewUser(UserProfile newUser) {
        UserRepository.getInstance().addNewUser(newUser);
    }

    public void addSession(String requestedSessionId, UserProfile newUser) {
        UserRepository.getInstance().addSession(requestedSessionId, newUser);
    }


    public void updateUser(UserProfile userBySessionId) {
        UserRepository.getInstance().updateUser(userBySessionId);
    }

    public void deleteSession(String sessionId) {
        UserRepository.getInstance().deleteSession(sessionId);
    }

    public void deleteUser(String user) {
        UserRepository.getInstance().deleteUser(user);
    }
}