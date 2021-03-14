package ru.brdby.cinderella.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.data.repository.JdbcProductRepository;
import ru.brdby.cinderella.service.UUIDService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BusinessController {

    @Value("${urlPattern}")
    private String urlBase;

    private final UUIDService UUIDService;
    private final JdbcProductRepository jdbcProductRepository;

    @GetMapping("/generateURL")
    public String generateURL(@RequestParam String name, Model model) {
        Product product = new Product(UUIDService.generateRandomUUID(), name);
        jdbcProductRepository.save(product);
        log.info("Product created: " + product);
        model.addAttribute("generatedURL", String.format(urlBase, product.getUUID()));
        return "generator";
    }

}
