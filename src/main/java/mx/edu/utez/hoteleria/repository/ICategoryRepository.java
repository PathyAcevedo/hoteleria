package mx.edu.utez.hoteleria.repository;

import mx.edu.utez.hoteleria.entity.Category;
import mx.edu.utez.hoteleria.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository  extends JpaRepository<Category, Long> {
    public Category findById(long id);
    Category findByName(String name);
}
