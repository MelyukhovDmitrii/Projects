package me.melyukhov.SpringProject.DataBase;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByLogin(String login);
}

