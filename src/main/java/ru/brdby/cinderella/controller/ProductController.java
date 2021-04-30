package ru.brdby.cinderella.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.data.domain.User;
import ru.brdby.cinderella.data.form.ProductForm;
import ru.brdby.cinderella.data.repository.ProductRepository;
import ru.brdby.cinderella.service.QRCodeService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manufacturer/product")
public class ProductController {


    @Value("${urlPattern}")
    private String urlBase;

    private final ru.brdby.cinderella.service.UUIDService UUIDService;
    private final ProductRepository productRepository;
    private final QRCodeService qrCodeService;

    @GetMapping("/create")
    public String generate() {
        return "create";
    }


    @PostMapping("/create")
    public String generate(ProductForm productForm, @AuthenticationPrincipal User user) {
        String name = productForm.getName();
        String uuid = UUIDService.generateRandomUUID();
        String url = String.format(urlBase, uuid);
        byte[] qrCode = qrCodeService.generateQRBase64URL(uuid);
        Product product = new Product(uuid, user, name, url, qrCode);
        productRepository.save(product);
        log.info("Product created: " + product);

        return "redirect:/manufacturer/products";
    }

}
