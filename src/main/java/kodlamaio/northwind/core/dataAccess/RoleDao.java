package kodlamaio.northwind.core.dataAccess;

import kodlamaio.northwind.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
