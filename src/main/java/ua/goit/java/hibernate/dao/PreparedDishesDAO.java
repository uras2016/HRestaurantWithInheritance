package ua.goit.java.hibernate.dao;

import ua.goit.java.hibernate.model.PreparedDish;

import java.util.List;

public interface PreparedDishesDAO {

    void create(PreparedDish preparedDish);
    List<PreparedDish> findAll();

}
