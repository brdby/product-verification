package ru.brdby.cinderella.data.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Product {

    private final String UUID;
    private final String name;

}
