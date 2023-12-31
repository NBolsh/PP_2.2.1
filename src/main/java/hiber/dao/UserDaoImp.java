package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {

      sessionFactory.getCurrentSession().save(user);
      if (user.getCar() != null) {
         sessionFactory.getCurrentSession().save(user.getCar());
      }
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(Car car) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series");
      query.setParameter("model", car.getModel());
      query.setParameter("series", car.getSeries());
      List<User> users = query.getResultList();
      if (!users.isEmpty()) {
         return users.get(0);
      } else {
         return null;
      }
   }
}
