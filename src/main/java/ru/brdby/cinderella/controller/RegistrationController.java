package ru.brdby.cinderella.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.brdby.cinderella.data.domain.User;
import ru.brdby.cinderella.data.form.RegistrationForm;
import ru.brdby.cinderella.data.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/register")
@Slf4j
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        User user = form.toUser(passwordEncoder);
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            userRepository.save(user);
            log.info("User added " + user);
        }
        return "redirect:/login";
    }
}
