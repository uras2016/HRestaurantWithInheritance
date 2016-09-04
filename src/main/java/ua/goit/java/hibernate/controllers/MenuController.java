package ua.goit.java.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.DishDao;
import ua.goit.java.hibernate.dao.MenuDao;
import ua.goit.java.hibernate.model.Dish;
import ua.goit.java.hibernate.model.Menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuController {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private DishDao dishDao;

    public Menu prepareMenu(String name, List<Dish> dishes) {
        Menu menu = new Menu();
        menu.setName(name);
        menu.setDishes(dishes);
        return menu;
    }

    @Transactional
    public void createMenus(){
        Set<Menu> allMenus = new HashSet<>(menuDao.findAll());

        List<Dish> banketDishes = new ArrayList<>();
        banketDishes.add(dishDao.findByName("Plov"));
        banketDishes.add(dishDao.findByName("Salad"));

        Menu banket = prepareMenu("Banket", banketDishes);

        List<Dish> lunchDishes = new ArrayList<>();
        lunchDishes.add(dishDao.findByName("Milk"));
        lunchDishes.add(dishDao.findByName("Cake"));
        Menu lunch = prepareMenu("Lunch", lunchDishes);

        if (!allMenus.contains(banket)){
            menuDao.add(banket);
        }
        if (!allMenus.contains(lunch)){
            menuDao.add(lunch);
        }
    }
    @Transactional
    public void addNewMenu(Menu menu) {
        Set<Menu> allMenus = new HashSet<>(menuDao.findAll());
        if (!allMenus.contains(menu)) {    // если такого еще нет в БД, то добавляем
            menuDao.add(menu);
        }
    }

    @Transactional
    public void deleteMenu(Menu menu){
        menuDao.remove(menu);
    }

    @Transactional
    public void addDish(Dish dish, Menu menu){
        menuDao.addDish(dish, menu);
    }
    @Transactional
    public void deleteDish(Dish dish, Menu menu){
        menuDao.deleteDish(dish,menu);
    }
    @Transactional
    public Menu findMenuByName(String name){
        return menuDao.getByName(name);
    }
    @Transactional
    public List<Menu> findAllMenu(){
        return menuDao.findAll();
    }


    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
}
