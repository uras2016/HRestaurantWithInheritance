package ua.goit.java.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.IngredientDao;
import ua.goit.java.hibernate.dao.hibernate.HIngredientDao;
import ua.goit.java.hibernate.model.Ingredient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IngredientController {

    @Autowired
    private IngredientDao ingredientDao;
    private HIngredientDao ingredientDAO;

    public Ingredient createIngredient(String name){
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
    return ingredient;
    }
    @Transactional
    public void createIngredients(){
        Ingredient water = createIngredient("water");
        Ingredient salt = createIngredient("salt");
        Ingredient flower = createIngredient("flower");
        Ingredient oil = createIngredient("oil");
        Ingredient tomato = createIngredient("tomato");
        Ingredient feta = createIngredient("feta");
        Ingredient rice = createIngredient("rice");

        Set<Ingredient> ingredients = new HashSet<>(ingredientDao.findAllIngredients());
        if (!ingredients.contains(water)) {
            ingredientDao.addIngredient(water);
        }
        if (!ingredients.contains(rice)) {
            ingredientDao.addIngredient(rice);
        }
        if (!ingredients.contains(oil)) {
            ingredientDao.addIngredient(oil);
        }
        if (!ingredients.contains(salt)) {
            ingredientDao.addIngredient(salt);
        }

        if (!ingredients.contains(flower)) {
            ingredientDao.addIngredient(flower);
        }

        if (!ingredients.contains(tomato)) {
            ingredientDao.addIngredient(tomato);
        }

        if (!ingredients.contains(feta)) {
            ingredientDao.addIngredient(feta);
        }

    }


    @Transactional
    public void addNewIngredient(Ingredient ingredient) {

        Set<Ingredient> ingredients = new HashSet<>(ingredientDao.findAllIngredients());

        if (!ingredients.contains(ingredient)) {
            ingredientDao.addIngredient(ingredient);
        }
    }

    @Transactional
    public void addIngredient(Ingredient ingredient){
        ingredientDao.addIngredient(ingredient);
    }
    @Transactional
    public void removeIngredient(Ingredient ingredient) {

        ingredientDao.removeIngredient(ingredient);
    }
    @Transactional
    public List<Ingredient> findAllIngredients() {
        return ingredientDao.findAllIngredients();
    }
    @Transactional
    public Ingredient findByName(String name) {
        return ingredientDao.findByName(name);
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public void setIngredientDAO(HIngredientDao ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    public HIngredientDao getIngredientDAO() {
        return ingredientDAO;
    }
}
