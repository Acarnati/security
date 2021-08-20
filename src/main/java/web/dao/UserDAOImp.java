package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        return getEntityManager()
                .createQuery("select u from User u where u.name = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.createQuery("delete from User where id=: id").setParameter("id", id).executeUpdate();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
