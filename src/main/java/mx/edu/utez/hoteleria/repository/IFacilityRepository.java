package mx.edu.utez.hoteleria.repository;

import mx.edu.utez.hoteleria.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IFacilityRepository extends JpaRepository<Facility, Long>, PagingAndSortingRepository<Facility, Long> {

    public Facility findById(long id);

    Facility findByName(String name);

}
