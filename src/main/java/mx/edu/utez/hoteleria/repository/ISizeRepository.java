package mx.edu.utez.hoteleria.repository;

import mx.edu.utez.hoteleria.entity.Category;
import mx.edu.utez.hoteleria.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISizeRepository extends JpaRepository<Size, Long> {
    public Size findById(long id);
    Size findByName(String name);
}
