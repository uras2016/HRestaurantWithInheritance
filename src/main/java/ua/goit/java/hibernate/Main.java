package ua.goit.java.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.goit.java.hibernate.controllers.*;
import ua.goit.java.hibernate.model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private DishController dishController;
    @Autowired
    private OrderController orderController;
    @Autowired
    private MenuController menuController;
    @Autowired
    private IngredientController ingredientController;
    @Autowired
    private WarehouseController warehouseController;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context-annotation.xml", "hibernate-context.xml");
            Main main = applicationContext.getBean(Main.class); // регистрируем меин метод
            main.start();

    }

    private void start() {

        System.out.println("--------------------Employee-----------------------------");

        employeeController.createEmployees();
        employeeController.addNewEmployee(employeeController.bornEmployee("Sasha","Beliy", 7777777, Position.MANAGER, 99999999.0F, "2001-10-01"));
//        System.out.println("Find by name 'Sasha': " + employeeController.getEmployeesByName("Sasha"));
        System.out.println("All employees : ");employeeController.getAllEmployees().forEach(System.out::println);
//        employeeController.removeEmployee(employeeController.getEmployeesByName("Sasha"));

        System.out.println("--------------------Ingredients-------------------------------");

        ingredientController.createIngredients();
        ingredientController.addNewIngredient(ingredientController.createIngredient("sugar"));
//        ingredientController.removeIngredient(ingredientController.findByName("sugar"));
        System.out.println("All ingredients : "); ingredientController.findAllIngredients().forEach(System.out::println);
        System.out.println("--------------------Dishes-------------------------------");

        dishController.createDishes();
        List<Ingredient> cakeIngredients = new ArrayList<>();
        cakeIngredients.add(ingredientController.findByName("water"));
        cakeIngredients.add(ingredientController.findByName("sugar"));
        cakeIngredients.add(ingredientController.findByName("salt"));
        Dish cake = dishController.prepareDish("Cake", DishCategory.DESSERT, 8.00F, 1.0F, Measures.PIECE, cakeIngredients);
        dishController.addNewDish(cake);
//        System.out.println("Find by name 'Cake': " + dishController.getDishByName("Cake"));

        System.out.println("All dishes : ");dishController.getAllDishes().forEach(System.out::println);
//        dishController.removeDish(dishController.getDishByName("Cake"));

        System.out.println("--------------------Menu---------------------------------");
//
        menuController.createMenus();
        List<Dish> desertDishes = new ArrayList<>();
        desertDishes.add(dishController.getDishByName("Cake"));
        menuController.addNewMenu(menuController.prepareMenu("Deserts", desertDishes));
//
        menuController.addDish(dishController.getDishByName("Milk"), menuController.findMenuByName("Deserts"));
////        System.out.println(menuController.findMenuByName("Deserts"));
        menuController.findAllMenu().forEach(System.out::println);
////        menuController.deleteDish(dishController.getDishByName("Milk"), menuController.findMenuByName("Deserts"));
////        menuController.deleteMenu(menuController.findMenuByName("Deserts"));

        System.out.println("--------------------Orders-------------------------------");

        orderController.addOrders();
        List<String> dishesForSasha = new ArrayList<>();
        dishesForSasha.add("Salad");
        orderController.addNewOrder(orderController.createOrder("Sasha",dishesForSasha,1));
        orderController.findAllOrders().forEach(System.out::println);
        orderController.addDishToOrder(dishController.getDishByName("Milk"), orderController.findById(3L));
//        orderController.deleteDishFromOrder(dishController.getDishByName("Milk"), orderController.findById(3L));
//        orderController.removeOrder(orderController.findById(3L));
//        orderController.closeOrder(orderController.findById(3L));
//        System.out.println(orderController.findById(1L));
        orderController.findAllOrders().forEach(System.out::println);
//        orderController.findOpenedOrders().forEach(System.out::println);
//        orderController.findClosedOrders().forEach(System.out::println);

        System.out.println("--------------------Warehouse-------------------------------");

        Warehouse warehouse = new Warehouse();
        warehouse.setIngredient(ingredientController.findByName("water"));
        warehouse.setQuantity(150F);
        warehouse.setMeasure(Measures.LITER);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setIngredient(ingredientController.findByName("rice"));
        warehouse1.setQuantity(3F);
        warehouse1.setMeasure(Measures.KG);

        warehouseController.create(warehouse);
        warehouseController.create(warehouse1);
        System.out.println("All ingredients in warehouse : ");warehouseController.findAll().forEach(System.out::println);
//        warehouseController.changeQuantityOfIngredients("water", 200F);
        System.out.println(warehouseController.findByName("rice"));
        System.out.println(warehouseController.findEndsIngredients());
//        warehouseController.remove(warehouseController.findByName("rice"));

    }


    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setIngredientController(IngredientController ingredientController) {
        this.ingredientController = ingredientController;
    }

    public void setWarehouseController(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
    }
}

