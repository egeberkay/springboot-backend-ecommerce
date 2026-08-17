package kodlamaio.northwind.core.utilities;

import kodlamaio.northwind.core.dataAccess.RoleDao;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.dataAccess.abstracts.UserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import kodlamaio.northwind.core.entities.Role;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleSeeder implements CommandLineRunner {
    private final RoleDao roleDao;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public RoleSeeder(RoleDao roleDao, UserDao userDao, PasswordEncoder passwordEncoder) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleDao.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            adminRole = roleDao.save(adminRole);
        }

        Role userRole = roleDao.findByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("ROLE_USER");
            userRole = roleDao.save(userRole);
        }

        //Admin Kullanıcısı Oluşturma
        if (userDao.findByEmail("admin@northwind.com") == null) {
            User adminUser = new User();
            adminUser.setEmail("admin@northwind.com");
            adminUser.setPassword(passwordEncoder.encode("Admin123!"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            roles.add(userRole);
            adminUser.setRoles(roles);

            userDao.save(adminUser);
            System.out.println("Default ADMIN kullanıcısı oluşturuldu: admin@northwind.com");
        }
    }
}
