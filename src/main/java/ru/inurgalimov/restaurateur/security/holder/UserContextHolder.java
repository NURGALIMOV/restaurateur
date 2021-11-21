package ru.inurgalimov.restaurateur.security.holder;


import ru.inurgalimov.restaurateur.security.userdetails.UserAccount;

public interface UserContextHolder {

    UserAccount getUserAccountFromSecurityContext();
}
