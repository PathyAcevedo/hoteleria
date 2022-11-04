package mx.edu.utez.hoteleria.repository;

import mx.edu.utez.hoteleria.entity.Category;
import mx.edu.utez.hoteleria.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservasRepository  extends JpaRepository<Rooms, Long> {
    public Rooms findById(long id);
    Rooms findByName(String name);
    List<Rooms> findByCategoryOrSize(Category category, Size size);
}
