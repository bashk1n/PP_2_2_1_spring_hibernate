package hiber.dao;

import hiber.model.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return  query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("FROM User user LEFT JOIN FETCH user.car WHERE user.car.model = :modelName and user.car.series = :seriesNumber", User.class);
      query.setParameter("modelName", model).setParameter("seriesNumber", series);
      return (User) query.getSingleResult();
   }
}
