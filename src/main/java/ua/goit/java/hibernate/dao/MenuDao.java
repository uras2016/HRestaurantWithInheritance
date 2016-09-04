package ua.goit.java.hibernate.dao;

import ua.goit.java.hibernate.model.Dish;
import ua.goit.java.hibernate.model.Menu;

import java.util.List;

public interface MenuDao {

    void add(Menu menu);
    void remove(Menu menu);
    void addDish(Dish dish, Menu menu/*List<Dish>*/);
    void deleteDish(Dish dish, Menu menu/*TODO:List<Dish>*/);
    Menu getByName(String name);
    List<Menu> findAll();
}
