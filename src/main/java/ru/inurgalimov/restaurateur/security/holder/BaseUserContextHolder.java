package ru.inurgalimov.restaurateur.security.holder;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import ru.inurgalimov.restaurateur.security.userdetails.UserAccount;

@Component
public class BaseUserContextHolder implements UserContextHolder {

    public UserAccount getUserAccountFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof PreAuthenticatedAuthenticationToken) {
            PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken
                    = (PreAuthenticatedAuthenticationToken) authentication;
            Object principal = preAuthenticatedAuthenticationToken.getPrincipal();
            if (principal instanceof UserAccount) {
                return (UserAccount) principal;
            }
        }

        throw new UsernameNotFoundException("Not found user context");
    }
}
