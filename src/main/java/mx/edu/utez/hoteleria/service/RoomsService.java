package mx.edu.utez.hoteleria.service;


import mx.edu.utez.hoteleria.entity.Rooms;
import mx.edu.utez.hoteleria.entity.Users;
import mx.edu.utez.hoteleria.repository.IRoomsRepository;
import mx.edu.utez.hoteleria.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.List;

@Service
public class RoomsService {

    @Autowired
    private IRoomsRepository roomsRepository;

    public List<Rooms> findAll() {
        return roomsRepository.findAll();
    }

    public Rooms findById(long id) {
        return roomsRepository.findById(id);
    }

    public Page<Rooms> listarPaginacion(PageRequest page) {
        return roomsRepository.findAll((org.springframework.data.domain.Pageable) page);
    }

    public boolean save(Rooms obj) {
        boolean flag = false;
        Rooms tmp = roomsRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        Rooms tmp = roomsRepository.findById(id);
        if (!tmp.equals(null)) {
            roomsRepository.delete(tmp);
            flag = true;
        }
        return flag;
    }

    public Rooms findByName(String name) {
        return roomsRepository.findByName(name);
    }

    public boolean guardar(Rooms room) {
        try {
            roomsRepository.save(room);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}