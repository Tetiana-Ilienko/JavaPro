package de.aittr.g_31_2_security.services;

import de.aittr.g_31_2_security.domain.Role;
import de.aittr.g_31_2_security.domain.User;
import de.aittr.g_31_2_security.exeption_handling.UserAlreadyExistsException;
import de.aittr.g_31_2_security.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private UserRepository repository;
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }


// этот метод вызывается сасмим спрингом
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }

    //  метод регистрирующий нового пользователя в системе
    @Transactional
    public User register(User user) {
        User foundUser = repository.findByUsername(user.getUsername());
        if (foundUser != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует!");
        }
        user.setId(0);
        user.clearRoles();
        Role role = new Role(1,"ROLE_USER");
        user.addRole(role);

        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return repository.save(user);

    }
}
