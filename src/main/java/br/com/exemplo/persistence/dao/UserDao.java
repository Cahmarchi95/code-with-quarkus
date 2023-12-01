package br.com.exemplo.persistence.dao;
import br.com.exemplo.persistence.dto.UserDto;
import br.com.exemplo.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;


@ApplicationScoped
public class UserDao {

    @Inject
    EntityManager entityManager;

    @Transactional
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.name);
        user.setAge(userDto.age);
        entityManager.persist(user);
        return user;
    }
    public List<User> getAllUsers() {
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        return query.getResultList();
    }
}

