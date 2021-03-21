package ru.brdby.cinderella.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.brdby.cinderella.data.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
