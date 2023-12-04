package br.com.exemplo.persistence.dao;
import br.com.exemplo.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class UserDao implements Dao<User> {

    @Inject
    EntityManager entityManager;


    @Override
    public Optional<User> get(Long id) {
        try {
            TypedQuery<User> query = this.entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
            query.setParameter("id", id);
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = this.entityManager.createQuery(jpql, User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void create(User user) {
        this.entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        this.entityManager.merge(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        this.entityManager.remove(user);
    }

}

