package mx.edu.utez.hoteleria.entity;


import javax.persistence.*;

@Entity
@Table(name = "facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;
/*
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

*/
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

/*
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }*/
}

