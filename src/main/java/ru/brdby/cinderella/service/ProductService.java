package ru.brdby.cinderella.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.data.domain.User;
import ru.brdby.cinderella.data.form.ProductForm;
import ru.brdby.cinderella.data.repository.ProductRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${urlPattern}")
    private String urlBase;

    private final QRCodeService qrCodeService;
    private final UUIDService uuidService;
    private final ProductRepository productRepository;

    public Product createProduct(User user, ProductForm productForm) {
        String uuid = uuidService.generateRandomUUID();
        String url = String.format(urlBase, uuid);
        byte[] qrCode = qrCodeService.generateQRBase64URL(uuid);
        Product product = productForm.toProduct(uuid, user, url, qrCode);
        productRepository.save(product);
        log.info("Product created: " + product);
        return product;
    }

    public void updateProductIfPresent(String uuid, ProductForm productForm) {
        productRepository.findById(uuid).ifPresent(value -> {
            value.replaceChangedFields(productForm);
            productRepository.save(value);
        });
    }

    public Optional<Product> findProductById(String uuid) {
        return productRepository.findById(uuid);
    }

    public Optional<Product> verificateProductById(String uuid) {
        Optional<Product> product = productRepository.findById(uuid);
        product.ifPresent(value -> {
            if (value.isOneTimeProduct()) productRepository.delete(value);
        });
        return product;
    }

}
