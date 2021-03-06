package ru.brdby.cinderella.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.data.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
