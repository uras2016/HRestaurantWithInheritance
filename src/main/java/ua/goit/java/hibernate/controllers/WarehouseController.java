package ua.goit.java.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.WarehouseDao;
import ua.goit.java.hibernate.model.Warehouse;

import java.util.List;

public class WarehouseController {
    @Autowired
    public WarehouseDao warehouseDao;

    @Transactional
    public void create(Warehouse warehouse) {
        warehouseDao.create(warehouse);
    }


    @Transactional
    public void remove(Warehouse store) {
        warehouseDao.remove(store);
    }


    @Transactional
    public void changeQuantityOfIngredients(String ingredientName, Float quantity) {
        warehouseDao.changeQuantityOfIngredients(ingredientName, quantity);
    }


    @Transactional
    public Warehouse findByName(String name) {
        return warehouseDao.findByName(name);

    }


    @Transactional
    public List<Warehouse> findAll() {
        return warehouseDao.findAll();
    }


    @Transactional
    public List<Warehouse> findEndsIngredients() {
        return warehouseDao.findEndsIngredients();
    }


    public void setWarehouseDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }
}


