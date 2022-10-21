package mx.edu.utez.hoteleria.service;

import mx.edu.utez.hoteleria.entity.Category;
import mx.edu.utez.hoteleria.entity.Facility;
import mx.edu.utez.hoteleria.repository.ICategoryRepository;
import mx.edu.utez.hoteleria.repository.IFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityService {
    @Autowired
    private IFacilityRepository facilityRepository;

    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }

    public Facility findById(long id) {
        return facilityRepository.findById(id);
    }

    public boolean save(Facility obj) {
        boolean flag = false;
        Facility tmp = facilityRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        Facility tmp = facilityRepository.findById(id);
        if (!tmp.equals(null)) {
            flag = true;
            facilityRepository.delete(tmp);
        }
        return flag;
    }

    public Facility findByName(String name) {
        return facilityRepository.findByName(name);
    }
}