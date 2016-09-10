package ua.goit.java.hibernate.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.WarehouseDao;
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
    public void changeQuantityOfIngredients(Long id, Float newQuantity) {
        Warehouse warehouseIngredient = getWarehouseIngredientById(id);
        warehouseIngredient.setQuantity(newQuantity);

//        if (newQuantity == 0) remove(getWarehouseIngredientById(id));

        sessionFactory.getCurrentSession().update(warehouseIngredient);

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

    @Override
    @Transactional
    public void removeAllWarehouse() {
        sessionFactory.getCurrentSession().createQuery("delete from Warehouse").executeUpdate();
    }
@Transactional
    public Warehouse getWarehouseIngredientById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Warehouse i where i.ingredient.id = :id");
        query.setParameter("id", id);
        return (Warehouse) query.uniqueResult();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
