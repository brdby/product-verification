package ru.brdby.cinderella.data.repository;

import ru.brdby.cinderella.data.domain.Product;

public interface ProductRepository {

    Product findOne(String uuid);
    void save(Product product);

}
