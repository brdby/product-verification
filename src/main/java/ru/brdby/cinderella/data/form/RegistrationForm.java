package ru.brdby.cinderella.data.form;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.brdby.cinderella.data.domain.User;

@Data
public class RegistrationForm {

    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password));
    }
}
