package ru.brdby.cinderella.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.data.repository.ProductRepository;
import ru.brdby.cinderella.service.QRCodeService;
import ru.brdby.cinderella.service.UUIDService;

@Controller
@Slf4j
@RequiredArgsConstructor
//@RequestMapping("/business")
public class BusinessController {


    @Value("${urlPattern}")
    private String urlBase;

    private final UUIDService UUIDService;
    private final ProductRepository productRepository;
    private final QRCodeService qrCodeService;

    @GetMapping("/generate")
    public String generate(@RequestParam String name, Model model) {
        String uuid = UUIDService.generateRandomUUID();
        String url = String.format(urlBase, uuid);
        Product product = new Product(uuid, name);

        productRepository.save(product);
        log.info("Product created: " + product);

        model.addAttribute("image", qrCodeService.generateQRBase64URL(uuid));
        model.addAttribute("url", url);
        return "generator";
    }

}
