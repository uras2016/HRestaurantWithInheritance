package ua.goit.java.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.PreparedDishesDao;
import ua.goit.java.hibernate.model.PreparedDish;

import java.util.List;

public class KitchenHistoryController {
    @Autowired
    private PreparedDishesDao preparedDishesDAO;

    @Transactional
    public void create(PreparedDish preparedDish) {
        preparedDishesDAO.create(preparedDish);
    }
    @Transactional
    public List<PreparedDish> findAll() {
        return preparedDishesDAO.findAll();
    }

    public void setPreparedDishesDAO(PreparedDishesDao preparedDishesDAO) {
        this.preparedDishesDAO = preparedDishesDAO;
    }
    @Transactional
    public void removeAllPreparedDishes() {
        preparedDishesDAO.removeAllPreparedDishes();
    }
}
