package mx.edu.utez.hoteleria.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Rooms {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    @NotBlank(message="El nombre no puede estar vacio")
    private String name;

    @Column(name = "description", nullable = false, length = 150)
    @NotBlank(message="La descripcion no puede estar vacio")
    private String description;

    @Column(name = "price", nullable = false)
    private double price;


    @Column(name = "enabled", nullable = false)
    private int enabled;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;
/*
    @OneToMany(mappedBy = "name")
    private List<Facility> facilities;
*/
    @ManyToOne
    @JoinColumn(name = "size", nullable = false)
    private Size size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
