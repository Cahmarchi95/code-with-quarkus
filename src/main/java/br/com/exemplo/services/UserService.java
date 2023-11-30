package br.com.exemplo.services;

import br.com.exemplo.persistence.dao.UserRepository;
import br.com.exemplo.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;

@ApplicationScoped
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> getUsers() {
        try {
            return userRepository.listAll();
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao buscar usuários", 500);
        }
    }

    @Transactional
    public void addUser(User user) {
        try {
            userRepository.persist(user);
        } catch (Exception e) {
            throw new WebApplicationException("Erro ao adicionar usuário", 500);
        }
    }
}



