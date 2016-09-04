package ua.goit.java.hibernate.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.PreparedDishesDAO;
import ua.goit.java.hibernate.model.PreparedDish;

import java.util.List;

public class HPreparedDishDao implements PreparedDishesDAO {

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

        return sessionFactory.getCurrentSession().createQuery("select pd from PrepareDish pd").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
