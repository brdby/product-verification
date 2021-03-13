package ru.brdby.cinderella.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.brdby.cinderella.service.RSAService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BusinessController {

    @Value("${urlBase}")
    private String urlBase;

    private final RSAService rsaService;

    @GetMapping("/generateURL")
    public String generateURL(@RequestParam String code, Model model) {
        model.addAttribute("generatedURL", String.format(urlBase, new String(rsaService.encrypt(code.getBytes()))));
        return "generator";
    }

}
