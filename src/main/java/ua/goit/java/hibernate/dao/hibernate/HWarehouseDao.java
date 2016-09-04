package ua.goit.java.hibernate.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.WarehouseDao;
import ua.goit.java.hibernate.model.Ingredient;
import ua.goit.java.hibernate.model.Warehouse;

import java.util.List;

public class HWarehouseDao implements WarehouseDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public void create(Warehouse warehouse) {
        if (!findAll().contains(warehouse))
        sessionFactory.getCurrentSession().save(warehouse);
    }

    @Override
    @Transactional
    public void remove(Warehouse warehouse) {
        sessionFactory.getCurrentSession().remove(warehouse);
    }

    @Override
    @Transactional
    public void changeQuantityOfIngredients(String ingredientName, Float quantity) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "update Warehouse set quantity = :quantity where ingredient.name = :ingredientName");
        query.setParameter("quantity", quantity);
        query.setParameter("ingredientName", ingredientName);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public Warehouse findByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("select w from Warehouse w where w.ingredient.name = :name");
        query.setParameter("name", name);
        return (Warehouse) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<Warehouse> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select w from Warehouse w").list();
    }

    @Override
    @Transactional
    public List<Warehouse> findEndsIngredients() {
        return sessionFactory.getCurrentSession().createQuery("select w from Warehouse w where w.quantity < 10").list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
