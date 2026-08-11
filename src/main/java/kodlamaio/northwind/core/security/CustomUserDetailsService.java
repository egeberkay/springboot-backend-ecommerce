package kodlamaio.northwind.core.security;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.dataAccess.abstracts.UserDao;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userDao.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Kullanici Bulunamadi|" + email);
        }

        Collection<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
          user.getEmail(),
          user.getPassword(),
                authorities
        );

    }
}
