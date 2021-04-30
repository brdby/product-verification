package ru.brdby.cinderella.data.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Base64;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Product {

    @Id
    private final String UUID;

    @ManyToOne
    private final User user;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private final String name;

    private final String url;

    @Lob
    @Basic
    private final byte[] qrCode;

    public String getBase64QrCode(){
        return Base64.getEncoder().encodeToString(qrCode);
    }

}
