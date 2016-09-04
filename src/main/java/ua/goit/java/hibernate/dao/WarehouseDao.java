package ua.goit.java.hibernate.dao;

import ua.goit.java.hibernate.model.Warehouse;

import java.util.List;

public interface WarehouseDao {

    void create(Warehouse warehouse);
    void remove(Warehouse warehouse);
    void changeQuantityOfIngredients(String ingredientName, Float quantity);
    Warehouse findByName(String name);
    List<Warehouse> findAll();
    List<Warehouse> findEndsIngredients();
}
