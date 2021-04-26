package ru.brdby.cinderella.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.data.repository.ProductRepository;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class VerificationController {

    private final ProductRepository productRepository;

    @GetMapping("/verificate")
    public String verification(@RequestParam String uuid, Model model) {
        Optional<Product> product = productRepository.findById(uuid);
        product.ifPresentOrElse(
                value -> model.addAttribute("verificationResult", value.getName() + " successfully verificated!"),
                () -> model.addAttribute("verificationResult", "Product is not verificated!"));
        return "verificate";
    }

}
