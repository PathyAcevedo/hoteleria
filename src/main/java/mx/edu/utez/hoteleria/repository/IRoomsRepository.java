package mx.edu.utez.hoteleria.repository;

import mx.edu.utez.hoteleria.entity.Rooms;
import mx.edu.utez.hoteleria.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface IRoomsRepository  extends JpaRepository<Rooms, Long>, PagingAndSortingRepository<Rooms, Long> {

    public Rooms findById(long id);

    Rooms findByName(String name);

}
