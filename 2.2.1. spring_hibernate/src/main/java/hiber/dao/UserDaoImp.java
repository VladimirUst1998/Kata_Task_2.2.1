package hiber.dao;


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
   }


   @Override
   public List<User> getUserByModelAndSeries(String model, int series) {
       String hqlQuery = "FROM User u LEFT OUTER JOIN FETCH u.car WHERE u.car.model=:model and u.car.series=:series";
       TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hqlQuery);
       return query.setParameter("model", model).setParameter("series", series).getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
