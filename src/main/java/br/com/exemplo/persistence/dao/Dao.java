package br.com.exemplo.persistence.dao;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T>get(Long id);

    List<T> getAll();

    void create(T data);

    void update(T data);

    void delete(T data);

}
