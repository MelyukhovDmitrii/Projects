package me.melyukhov.SpringProject.Service;

import org.springframework.security.core.authority.AuthorityUtils;

import me.melyukhov.SpringProject.DataBase.User;

public class MyLogin extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private User user;

    public MyLogin(User user) {
        super(user.getLogin(), user.getHash(), AuthorityUtils.createAuthorityList("ALL"));
        this.user = user;
    }
}