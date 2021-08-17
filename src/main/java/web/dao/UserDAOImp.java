package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImp implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public int saveUser() {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);
        entityManager.persist(role1);
        entityManager.persist(role2);
        User user1 = new User("ADMIN", "Иванов", (byte) 29, "ivanov@mail.ru", "ADMIN", roles);
        entityManager.persist(user1);
        User user2 = new User("USER", "Лапина", (byte) 35, "lapina@mail.ru", "USER", roles);
        entityManager.persist(user2);
        return 1;
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
}
