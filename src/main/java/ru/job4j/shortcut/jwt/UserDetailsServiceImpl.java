package ru.job4j.shortcut.jwt;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.entity.Site;
import ru.job4j.shortcut.repositories.SiteRepository;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SiteRepository users;

    public UserDetailsServiceImpl(SiteRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Site user = users.findByLogin(name);
        if (user == null) {
            throw new UsernameNotFoundException(name);
        }
        return new User(user.getLogin(), user.getPassword(), emptyList());
    }
}