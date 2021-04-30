package ru.brdby.cinderella.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.brdby.cinderella.data.domain.User;
import ru.brdby.cinderella.data.repository.ProductRepository;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ProductRepository productRepository;

    @GetMapping("/products")
    public String generate(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("products", productRepository.getProductsByUser(user));
        return "products";
    }

}
