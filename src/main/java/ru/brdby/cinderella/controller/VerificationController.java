package ru.brdby.cinderella.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.brdby.cinderella.service.ProductService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class VerificationController {

    private final ProductService productService;

    @GetMapping("/verificate")
    public String verification(@RequestParam String uuid, Model model) {
        productService.verificateProductById(uuid).ifPresent(model::addAttribute);
        return "verificate";
    }

}
