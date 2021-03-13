package ru.brdby.cinderella.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.brdby.cinderella.service.RSAService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class VerificationController {

    private final RSAService rsaService;

    @GetMapping("/verification")
    public String verification(@RequestParam String cipher, Model model) {
        model.addAttribute("decryptedCode", rsaService.decrypt(cipher.getBytes()));
        return "verification";
    }

}
