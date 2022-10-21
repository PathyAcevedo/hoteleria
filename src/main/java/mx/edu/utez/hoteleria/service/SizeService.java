package mx.edu.utez.hoteleria.service;

import mx.edu.utez.hoteleria.entity.Category;
import mx.edu.utez.hoteleria.entity.Size;
import mx.edu.utez.hoteleria.repository.ICategoryRepository;
import mx.edu.utez.hoteleria.repository.ISizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    private ISizeRepository sizeRepository;

    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    public Size findById(long id) {
        return sizeRepository.findById(id);
    }

    public boolean save(Size obj) {
        boolean flag = false;
        Size tmp = sizeRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        Size tmp = sizeRepository.findById(id);
        if (!tmp.equals(null)) {
            flag = true;
            sizeRepository.delete(tmp);
        }
        return flag;
    }

    public Size findByName(String name) {
        return sizeRepository.findByName(name);
    }
}
