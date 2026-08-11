package kodlamaio.northwind.core.utilities;

import kodlamaio.northwind.core.dataAccess.RoleDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import kodlamaio.northwind.core.entities.Role;

@Component
public class RoleSeeder implements CommandLineRunner {
    private final RoleDao roleDao;

    public RoleSeeder(RoleDao roleDao){
        this.roleDao = roleDao;
    }


    @Override
    public void run(String... args) throws Exception {
        //Check the database for the USER_ROLE
        if (roleDao.findByName("ROLE_USER") == null){
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleDao.save(userRole);
        }

        if (roleDao.findByName("ROLE_ADMIN") == null){
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleDao.save(adminRole);
        }
    }
}
