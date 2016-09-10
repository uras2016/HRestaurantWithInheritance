package ua.goit.java.hibernate.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.OrderDao;
import ua.goit.java.hibernate.dao.WarehouseDao;
import ua.goit.java.hibernate.model.*;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

public class HOrderDao implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private WarehouseDao warehouseDao;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(Orders order) {
        order.setOpenStatus(true);
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteOrder(Orders order) {
        if (order.isOpen()) {

            sessionFactory.getCurrentSession().remove(order);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Orders findById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select o from Orders o where o.id = :id");
        query.setParameter("id", id);
        return (Orders) query.uniqueResult();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addDishToOrder(Dish dish, Orders orders) {
        if (!orders.getDishes().contains(dish)){
            orders.getDishes().add(dish);
            sessionFactory.getCurrentSession().saveOrUpdate(orders);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteDishFromOrder(Dish dish, Orders orders) {
        if (orders.getDishes().contains(dish)){
            orders.getDishes().remove(dish);
            sessionFactory.getCurrentSession().saveOrUpdate(orders);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void closeOrder(Orders order) {
        if (order.isOpen()) {
            order.setOpenStatus(false);

            List<PreparedDish> allPreparedDishes = new ArrayList<>();

            for (Dish dish : order.getDishes()) {
                checkIngredientsInWarehouse(dish); // validation
                PreparedDish preparedDish = new PreparedDish();
                preparedDish.setDish(dish);
                preparedDish.setDate(order.getOrderDate());
                preparedDish.setCooker(dish.getCooker());


                allPreparedDishes.add(preparedDish);
            }


            order.setPreparedDishes(allPreparedDishes);
            sessionFactory.getCurrentSession().saveOrUpdate(order);
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void checkIngredientsInWarehouse(Dish dish){

        List<Ingredient> ingredientsInDish  = dish.getIngredients();
        for (Ingredient ingredient : ingredientsInDish) {

            Warehouse warehouse = warehouseDao.getWarehouseIngredientById(ingredient.getId());
            Float warehouseIngredientQuantity = warehouse.getQuantity();

            Float newQuantity = warehouseIngredientQuantity - 1F;


            if (newQuantity > 0 || newQuantity == 0){
                warehouseDao.changeQuantityOfIngredients(ingredient.getId(), newQuantity);

            }else {
                System.out.println("Ingredient quantity = " + warehouseIngredientQuantity +
                        "." + "Please prepare order to deliver " + warehouse.getIngredient().getName());

            }

        }
    }


    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Orders> findAllOpenedOrders() {
        return sessionFactory.getCurrentSession().createQuery("select o from Orders o where o.status = :true").list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Orders> findAllClosedOrders() {
        return sessionFactory.getCurrentSession().createQuery("select o from Orders o where o.status = :false").list();

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Orders> findAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("select o from Orders o").list();
    }

    @Override
    @Transactional
    public void removeAllOrders() {
        sessionFactory.getCurrentSession().createQuery("delete from Orders").executeUpdate();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
