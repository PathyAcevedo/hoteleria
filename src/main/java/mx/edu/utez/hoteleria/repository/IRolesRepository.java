package mx.edu.utez.hoteleria.repository;

import mx.edu.utez.hoteleria.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolesRepository extends JpaRepository<Roles, Long> {
    public Roles findById(long id);
    Roles findByAuthority(String authority);
}
