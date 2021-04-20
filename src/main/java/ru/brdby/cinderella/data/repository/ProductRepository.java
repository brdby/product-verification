package ru.brdby.cinderella.data.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.brdby.cinderella.data.domain.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

	List<Product> getProductsByUsername(String username);

}
