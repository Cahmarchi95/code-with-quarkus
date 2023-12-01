package br.com.exemplo.services;

import br.com.exemplo.persistence.dao.UserDao;
import br.com.exemplo.persistence.dto.UserDto;
import br.com.exemplo.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;


@ApplicationScoped
public class UserService {

    @Inject
    UserDao userDao;

    public User createUser(UserDto userdto) {
        return userDao.createUser(userdto);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}



