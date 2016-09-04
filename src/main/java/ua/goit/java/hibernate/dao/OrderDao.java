package ua.goit.java.hibernate.dao;

import ua.goit.java.hibernate.model.Dish;
import ua.goit.java.hibernate.model.Orders;

import java.util.List;

public interface OrderDao {

    void save(Orders order);
    void deleteOrder(Orders orders);
    Orders findById(Long id);
    void addDishToOrder(Dish dish, Orders orders);
    void deleteDishFromOrder(Dish dish, Orders orders);
    void closeOrder(Orders orders);
    List<Orders> findAllOrders();
    List<Orders> findAllOpenedOrders();
    List<Orders> findAllClosedOrders();

}
