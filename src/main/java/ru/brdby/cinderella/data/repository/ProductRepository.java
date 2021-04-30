package ru.brdby.cinderella.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.data.domain.User;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {

	List<Product> getProductsByUser(User user);

}
