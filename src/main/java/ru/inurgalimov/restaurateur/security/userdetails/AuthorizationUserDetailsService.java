package ru.inurgalimov.restaurateur.security.userdetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.inurgalimov.restaurateur.entity.Role;
import ru.inurgalimov.restaurateur.service.JwtTokenService;
import ru.inurgalimov.restaurateur.utils.HttpRequestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorizationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final JwtTokenService jwtTokenService;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) {
        String authorizationHeader = preAuthenticatedAuthenticationToken.getPrincipal().toString();
        log.info("Loading user for Authorization header: {}", authorizationHeader);
        if (!authorizationHeader.startsWith("Bearer")) {
            throw new RuntimeException("Invalid authentication scheme found in Authorization header");
        }
        String token = HttpRequestUtil.getTokenFromAuthorizationHeader(authorizationHeader);
        if (token == null) {
            throw new RuntimeException("Authorization header token is empty");
        }
        return loadUserDetails(token);
    }

    private UserDetails loadUserDetails(String token) {
        try {
            return Optional.ofNullable(jwtTokenService.userInfoByToken(token))
                    .map(account -> {
                        List<SimpleGrantedAuthority> authorities = getAuthorities(account.getRole());
                        return UserAccount.builder()
                                .uuid(account.getUuid())
                                .login(account.getLogin())
                                .fullName(account.getFullName())
                                .createDate(account.getCreateDate())
                                .authorities(authorities)
                                .isAccountNonExpired(true)
                                .isCredentialsNonExpired(true)
                                .isAccountNonLocked(true)
                                .token(token)
                                .build();
                    })
                    .orElseThrow(() -> new UsernameNotFoundException("Unknown user by token " + token));
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private List<SimpleGrantedAuthority> getAuthorities(Role role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE" + "_" + role.name()));
        return authorities;
    }
}