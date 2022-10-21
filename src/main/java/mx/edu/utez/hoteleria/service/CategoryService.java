package mx.edu.utez.hoteleria.service;


import mx.edu.utez.hoteleria.entity.Category;
import mx.edu.utez.hoteleria.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(long id) {
        return categoryRepository.findById(id);
    }

    public boolean save(Category obj) {
        boolean flag = false;
        Category tmp = categoryRepository.save(obj);
        if (!tmp.equals(null)) {
            flag = true;
        }
        return flag;
    }

    public boolean delete(long id) {
        boolean flag = false;
        Category tmp = categoryRepository.findById(id);
        if (!tmp.equals(null)) {
            flag = true;
            categoryRepository.delete(tmp);
        }
        return flag;
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
