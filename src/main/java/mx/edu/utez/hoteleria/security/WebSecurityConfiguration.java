package mx.edu.utez.hoteleria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT u.username, r.authority FROM user_role AS ur "
                        + "INNER JOIN users AS u ON u.id = ur.user "
                        + "INNER JOIN roles AS r ON r.id = ur.role WHERE u.username = ?");
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers(
                        // Los recursos estaticos no requieren autenticacion
                        "/css/**", "/js/**", "/image/**", "/error/**", "/images/**", "/imagenes/**", "/docs/**").permitAll()
                // Las URL publicas no requieren autenticacion
                .antMatchers("/", "/signup","forgotPassword", "/encriptar/**").permitAll()

                // Asignar permisos a las URL de acuerdo a los roles
                .antMatchers("/users/**").hasAnyAuthority("ROL_ADMINISTRADOR")
                .antMatchers("/client/**").hasAnyAuthority("ROL_EMPLEADO")
                //.antMatchers("/category/**").hasAnyAuthority("ROL_ADMINISTRADOR")
                //.antMatchers("/requests/**").hasAnyAuthority("ROL_ENLACE")
                //.antMatchers("/suburb/**").hasAnyAuthority("ROL_ENLACE")
                //.antMatchers("/president/**").hasAnyAuthority("ROL_PRESIDENTE")

                // Las demas URL requieren autenticacion
                .anyRequest().authenticated()

                // Formulario de inicio de sesion no requiere autenticacion
                .and().formLogin().successHandler(successHandler).loginPage("/login").permitAll();
 }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
