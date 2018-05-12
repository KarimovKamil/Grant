package ru.itis.grant.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        User user = userDao.getUserByToken(token);
        String role = user.getRole();
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (role) {
            case "USER":
                authorities.add(new SimpleGrantedAuthority("USER"));
                break;
            case "EXPERT":
                authorities.add(new SimpleGrantedAuthority("EXPERT"));
                break;
            case "ORGANIZER":
                authorities.add(new SimpleGrantedAuthority("ORGANIZER"));
                break;
        }
        return new UserDetailsImpl(user.getFirstName(), user.getHashPassword(), authorities);
    }
}
