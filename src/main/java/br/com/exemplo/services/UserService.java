package br.com.exemplo.services;

import br.com.exemplo.persistence.dao.UserDao;
import br.com.exemplo.persistence.dto.UserDto;
import br.com.exemplo.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class UserService {

    @Inject
    UserDao userDao;

    public void createUser(UserDto userdto) {
        User user = new User();
        user.setName(userdto.getName());
        user.setAge(userdto.getAge());
        this.userDao.create(user);
    }

    public List<User> getAllUsers() {
        return this.userDao.getAll();
    }

    public Optional<User> getUser(Long id){
        return this.userDao.get(id);
    }

    public void updateUser(UserDto userDto) {
        User user = userDao.get(userDto.getId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        userDao.update(user);
    }

    public void deleteUser(Long id) {
        User user = userDao.get(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        userDao.delete(user);
    }
}



