package ru.brdby.cinderella.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.data.domain.User;
import ru.brdby.cinderella.data.form.ProductForm;
import ru.brdby.cinderella.service.ProductService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manufacturer/products")
public class ProductController {


    private final ProductService productService;

    @GetMapping("/create")
    public String generate(Product product) {
        return "create";
    }


    @PostMapping("/create")
    public String generate(ProductForm productForm, @AuthenticationPrincipal User user) {
        productService.createProduct(user, productForm);
        return "redirect:/manufacturer/products";
    }

    @GetMapping("/{uuid}")
    public String productPage(Model model, @PathVariable String uuid) {
        productService.findProductById(uuid).ifPresent(value -> model.addAttribute("product", value));
        return "productPage";
    }

    @PostMapping("/{uuid}")
    public String saveProduct(ProductForm productForm, @PathVariable String uuid) {
        productService.updateProductIfPresent(uuid, productForm);
        return "redirect:/manufacturer/products";
    }

}
