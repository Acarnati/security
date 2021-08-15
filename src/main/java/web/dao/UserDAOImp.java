package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int saveUser() {
        User user1 = new User("Иван", "Иванов", (byte) 29, "ivanov@mail.ru");
        entityManager.persist(user1);
        User user2 = new User("Ирина", "Лапина", (byte) 35, "lapina@mail.ru");
        entityManager.persist(user2);
        return 1;
    }

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("from User").getResultList();
    }
}
