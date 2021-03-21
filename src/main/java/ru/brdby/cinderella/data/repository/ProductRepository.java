package ru.brdby.cinderella.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.brdby.cinderella.data.domain.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
