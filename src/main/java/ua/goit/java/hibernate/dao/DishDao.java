package ua.goit.java.hibernate.dao;

import ua.goit.java.hibernate.model.Dish;

import java.util.List;

public interface DishDao {

    void save(Dish dish);
    void remove(Dish dish);

    List<Dish> findAll();
    Dish findByName(String name);
}
