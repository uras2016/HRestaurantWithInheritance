package ua.goit.java.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.IngredientDao;
import ua.goit.java.hibernate.dao.WarehouseDao;
import ua.goit.java.hibernate.model.Measures;
import ua.goit.java.hibernate.model.Warehouse;

import java.util.List;

public class WarehouseController {
    @Autowired
    public WarehouseDao warehouseDao;
    @Autowired
    private IngredientDao ingredientDao;

    @Transactional
    public void create(Warehouse warehouse) {
        warehouseDao.create(warehouse);
    }


    @Transactional
    public void remove(Warehouse store) {
        warehouseDao.remove(store);
    }


    @Transactional
    public void changeQuantityOfIngredients(Long id, Float newQuantity) {
        warehouseDao.changeQuantityOfIngredients(id, newQuantity);
    }


    @Transactional
    public Warehouse findByName(String name) {
        return warehouseDao.findByName(name);

    }
    @Transactional
    public void initWarehouseIngredients() {
        Warehouse water = new Warehouse();
        water.setIngredient(ingredientDao.findByName("water"));
        water.setQuantity(1000F);
        water.setMeasure(Measures.LITER);

        Warehouse rice = new Warehouse();
        rice.setIngredient(ingredientDao.findByName("rice"));
        rice.setQuantity(1000F);
        rice.setMeasure(Measures.KG);

        Warehouse oil = new Warehouse();
        oil.setIngredient(ingredientDao.findByName("oil"));
        oil.setQuantity(1000F);
        oil.setMeasure(Measures.LITER);

        Warehouse salt = new Warehouse();
        salt.setIngredient(ingredientDao.findByName("salt"));
        salt.setQuantity(1000F);
        salt.setMeasure(Measures.KG);

        Warehouse flower = new Warehouse();
        flower.setIngredient(ingredientDao.findByName("flower"));
        flower.setQuantity(1000F);
        flower.setMeasure(Measures.PIECE);

        Warehouse tomato = new Warehouse();
        tomato.setIngredient(ingredientDao.findByName("tomato"));
        tomato.setQuantity(1000F);
        tomato.setMeasure(Measures.KG);

        Warehouse feta = new Warehouse();
        feta.setIngredient(ingredientDao.findByName("feta"));
        feta.setQuantity(1000F);
        feta.setMeasure(Measures.KG);



        warehouseDao.create(water);
        warehouseDao.create(rice);
        warehouseDao.create(oil);
        warehouseDao.create(flower);
//        warehouseDao.create(sugar);
        warehouseDao.create(feta);
        warehouseDao.create(salt);
        warehouseDao.create(tomato);

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

    @Transactional
    public void removeAllWarehouse() {
        warehouseDao.removeAllWarehouse();

    }
}


