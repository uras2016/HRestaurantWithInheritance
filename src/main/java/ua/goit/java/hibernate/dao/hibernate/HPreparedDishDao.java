package ua.goit.java.hibernate.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.PreparedDishesDao;
import ua.goit.java.hibernate.model.PreparedDish;

import java.util.List;

public class HPreparedDishDao implements PreparedDishesDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void create(PreparedDish preparedDish) {
        sessionFactory.getCurrentSession().save(preparedDish);
    }

    @Override
    @Transactional
    public List<PreparedDish> findAll() {

        return sessionFactory.getCurrentSession().createQuery("select pd from PreparedDish pd").list();
    }
    @Override
    @Transactional
    public void removeAllPreparedDishes() {
        sessionFactory.getCurrentSession().createQuery("delete from PreparedDish").executeUpdate();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
