package ru.brdby.cinderella.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.data.repository.JdbcProductRepository;

@Controller
@Slf4j
@RequiredArgsConstructor
public class VerificationController {

    private final JdbcProductRepository jdbcProductRepository;

    @GetMapping("/verification")
    public String verification(@RequestParam String uuid, Model model) {
        Product product = jdbcProductRepository.findOne(uuid);
        model.addAttribute("name", product.getName());
        return "verification";
    }

}
