package mx.edu.utez.hoteleria.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    @NotBlank(message="El nombre no puede estar vacio")
    private String name;

    @Column(name = "lastname", nullable = false, length = 150)
    @NotBlank(message="El apellido no puede estar vacio")
    private String lastName;

    @Column(name = "surname", nullable = true, length = 150)
    @NotBlank(message="El apellido no puede estar vacio")
    private String surname;

    @Column(name = "username", nullable = false, length = 150, unique = true)
    @NotBlank(message="El nombre de usuario no puede estar vacio")
    private String username;

    @Column(name = "phone", nullable = false, length = 20)
    @NotBlank(message="El número no puede estar vacio")
    private String phone;

    @Column(name = "password", nullable = false, length = 255)
    @NotBlank(message="La contraseña no puede estar vacia")
    private String password;

    @Column(name = "enabled", nullable = false)
    private int enabled;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    @NotBlank(message="El Correo electronico no puede estar vacio")
    @Email(message="El Correo electronico no es valido")
    private String email;

    @Column(name = "registered_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDate;

    @Column(name = "login_date",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;

    @Column(name = "logout_date",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutDate;

    @ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "role"))
	private Set<Roles> roles;

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }

    // Metodo para agregar roles
    public void agregarRol(Roles role) {
        if (roles == null) {
            roles = new HashSet<Roles>();
        }
        roles.add(role);
    }

    
}
