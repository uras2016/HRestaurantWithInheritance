package ua.goit.java.hibernate.dao;

import ua.goit.java.hibernate.model.Ingredient;

import java.util.List;

public interface IngredientDao {
    void addIngredient(Ingredient ingredient);
    void removeIngredient(Ingredient ingredient);

    List<Ingredient> findAllIngredients();
    Ingredient findByName(String name);
}
