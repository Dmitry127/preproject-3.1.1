package ru.dmitry.seleznev.dao;

import org.springframework.stereotype.Repository;
import ru.dmitry.seleznev.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUser(String login) {
        return entityManager.createQuery("select u from  User u join fetch u.roles where u.login = :login", User.class)
                .setParameter("login", login).getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void deleteUser(String login) {
        entityManager.remove(getUser(login));
    }
}
