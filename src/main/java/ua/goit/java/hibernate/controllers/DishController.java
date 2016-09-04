package ua.goit.java.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.DishDao;
import ua.goit.java.hibernate.dao.IngredientDao;
import ua.goit.java.hibernate.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DishController {

    @Autowired
    private DishDao dishDao;
    @Autowired
    private IngredientDao ingredientDao;

    public Dish prepareDish(String name, DishCategory category, Float price, Float weight, Measures measure, List<Ingredient> ingredients) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setCategory(category);
        dish.setPrice(price);
        dish.setWeight(weight);
        dish.setMeasure(measure);
        dish.setIngredients(ingredients);
        return dish;
    }

    @Transactional
    public void createDishes() {
        Set<Dish> allDishes= new HashSet<>(dishDao.findAll());

        List<Ingredient> plovIngredients = new ArrayList<>();
        plovIngredients.add(ingredientDao.findByName("water"));
        plovIngredients.add(ingredientDao.findByName("rice"));
        plovIngredients.add(ingredientDao.findByName("salt"));
       Dish plov = prepareDish("Plov", DishCategory.MAIN, 5.00F, 0.250F, Measures.KG, plovIngredients);
        List<Ingredient> milkIngredients = new ArrayList<>();
        milkIngredients.add(ingredientDao.findByName("water"));
        milkIngredients.add(ingredientDao.findByName("flower"));
       Dish milk = prepareDish("Milk", DishCategory.MAIN, 1.00F, 0.150F, Measures.LITER, milkIngredients);
        List<Ingredient> saladIngredients = new ArrayList<>();
        saladIngredients.add(ingredientDao.findByName("oil"));
        saladIngredients.add(ingredientDao.findByName("tomato"));
        saladIngredients.add(ingredientDao.findByName("feta"));
        Dish salad = prepareDish("Salad", DishCategory.SALAD, 2.00F, 0.200F, Measures.KG, saladIngredients);

        if (!allDishes.contains(plov)){
            addNewDish(plov);
        }
        if (!allDishes.contains(milk)){
            dishDao.save(milk);
        }
        if (!allDishes.contains(salad)){
            dishDao.save(salad);
        }


    }

    @Transactional
    public void addNewDish(Dish dish) {
        Set<Dish> allDishes = new HashSet<>(dishDao.findAll());
        if (!allDishes.contains(dish)) {
            // если такого еще нет в БД, то добавляем
            dishDao.save(dish);
        }
    }

    @Transactional
    public void removeDish(Dish dish) {
        dishDao.remove(dish);
    }
    @Transactional
    public List<Dish> getAllDishes() {
        return dishDao.findAll();
    }

    @Transactional
    public Dish getDishByName(String name) {
        return dishDao.findByName(name);
    }



    private List<Ingredient> createIngredients(List<String> ingredients) {

        List<Ingredient> result = new ArrayList<>();
        for (String ingredientName : ingredients) {
            result.add(ingredientDao.findByName(ingredientName));
        }
        return result;
    }


    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
}
