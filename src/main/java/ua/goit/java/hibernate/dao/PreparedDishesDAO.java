package ua.goit.java.hibernate.dao;

import ua.goit.java.hibernate.model.PreparedDish;

import java.util.List;

public interface PreparedDishesDao {

    void create(PreparedDish preparedDish);
    List<PreparedDish> findAll();
    void removeAllPreparedDishes();

}
