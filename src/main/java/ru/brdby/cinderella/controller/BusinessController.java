package ru.brdby.cinderella.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.data.domain.User;
import ru.brdby.cinderella.data.form.ProductForm;
import ru.brdby.cinderella.data.repository.ProductRepository;
import ru.brdby.cinderella.service.QRCodeService;
import ru.brdby.cinderella.service.UUIDService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/business")
public class BusinessController {


    @Value("${urlPattern}")
    private String urlBase;

    private final UUIDService UUIDService;
    private final ProductRepository productRepository;
    private final QRCodeService qrCodeService;

    @GetMapping("/generate")
    public String generate() {
        return "generate";
    }


    @PostMapping("/generate")
    public String generate(ProductForm productForm, @AuthenticationPrincipal User user) {
        String name = productForm.getName();
        String uuid = UUIDService.generateRandomUUID();
        String url = String.format(urlBase, uuid);
        String username = user.getUsername();
        byte[] qrCode = qrCodeService.generateQRBase64URL(uuid);
        Product product = new Product(uuid, name, username, url, qrCode);

        productRepository.save(product);
        log.info("Product created: " + product);

        return "redirect:userpage";
    }

    @GetMapping("/userpage")
    public String generate(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("products", productRepository.getProductsByUsername(user.getUsername()));
        return "userpage";
    }

}
