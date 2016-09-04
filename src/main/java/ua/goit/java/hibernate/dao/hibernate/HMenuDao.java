package ua.goit.java.hibernate.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.MenuDao;
import ua.goit.java.hibernate.model.Dish;
import ua.goit.java.hibernate.model.Menu;

import java.util.List;

public class HMenuDao implements MenuDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void add(Menu menu) {
        sessionFactory.getCurrentSession().save(menu);
    }

    @Override
    @Transactional
    public void remove(Menu menu) {
        sessionFactory.getCurrentSession().remove(menu);
    }

    @Override
    @Transactional
    public void addDish(Dish dish, Menu menu) {
        if (!menu.getDishes().contains(dish)){
            menu.getDishes().add(dish);
            sessionFactory.getCurrentSession().saveOrUpdate(menu);
        }
    }

    @Override
    @Transactional
    public void deleteDish(Dish dish, Menu menu) {
        menu.getDishes().remove(dish);
        sessionFactory.getCurrentSession().saveOrUpdate(menu);
    }

    @Override
    @Transactional
    public Menu getByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("select m from Menu m where m.name = :name");
        query.setParameter("name", name);
        return (Menu) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<Menu> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select m from Menu m").list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
