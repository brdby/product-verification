package ru.brdby.cinderella.data.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import ru.brdby.cinderella.data.form.ProductForm;

import javax.persistence.*;
import java.util.Base64;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Product {

    @Id
    private final String UUID;

    @ManyToOne
    private User user;

    @NotNull
    private String name;
    private String description;

    private String storeName;
    private String storeAddress;

    private boolean oneTimeProduct;

    private final String url;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private final byte[] qrCode;

    public String getBase64QrCode() {
        return Base64.getEncoder().encodeToString(qrCode);
    }

    public void replaceChangedFields(ProductForm productForm) {
        BeanUtils.copyProperties(productForm, this);
    }

}
