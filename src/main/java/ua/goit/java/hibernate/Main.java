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
    @Autowired
    private KitchenHistoryController kitchenHistoryController;


    private boolean reInit;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context-annotation.xml", "hibernate-context.xml");
            Main main = applicationContext.getBean(Main.class); // регистрируем меин метод
            main.start();

    }

    public void init(){    // будет запускаться каждый раз
        if (reInit) {
            orderController.removeAllOrders();
            dishController.removeAllDishes();
            ingredientController.removeAllIngredients();
            menuController.removeAllMenus();
            warehouseController.removeAllWarehouse();
            employeeController.removeAllEmployees();
            kitchenHistoryController.removeAllPreparedDishes();

            employeeController.initEmployees();
            ingredientController.initIngredients();
            dishController.initDishes();
            menuController.initMenus();
            orderController.initOrders();

        }
    }



    private void start() {
        System.out.println("--------------------Employee-----------------------------");


//        employeeController.initEmployees();
        employeeController.addNewEmployee(employeeController.bornEmployee("Sasha","Beliy", 7777777, Position.MANAGER, 99999999.0F, "2001-10-01"));
        System.out.println("Find by name 'Sasha' : " + employeeController.getEmployeesByName("Sasha"));
        System.out.println("All employees : ");employeeController.getAllEmployees().forEach(System.out::println);
//        employeeController.removeEmployee(employeeController.getEmployeesByName("Sasha"));


        System.out.println("Print employee #1:" );employeeController.printEmployee(1L);

        System.out.println("--------------------Ingredients-------------------------------");

//        ingredientController.initIngredients();
        ingredientController.addNewIngredient(ingredientController.createIngredient("sugar"));
//        ingredientController.removeIngredient(ingredientController.findByName("sugar"));
        System.out.println("All ingredients : "); ingredientController.findAllIngredients().forEach(System.out::println);
        System.out.println("--------------------Dishes-------------------------------");

//        dishController.initDishes();
        List<Ingredient> cakeIngredients = new ArrayList<>();
        cakeIngredients.add(ingredientController.findByName("water"));
        cakeIngredients.add(ingredientController.findByName("sugar"));
        cakeIngredients.add(ingredientController.findByName("salt"));
        Dish cake = dishController.prepareDish("Cake", DishCategory.DESSERT, 8.00F, 1.0F, Measures.PIECE, cakeIngredients, employeeController.getEmployeesByName("John"));
        dishController.addNewDish(cake);
//        System.out.println("Find by name 'Cake': " + dishController.getDishByName("Cake"));

        System.out.println("All dishes : ");dishController.getAllDishes().forEach(System.out::println);
//        dishController.removeDish(dishController.getDishByName("Cake"));

        System.out.println("--------------------Menu---------------------------------");
//
//        menuController.createMenus();
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

//        orderController.addOrders();
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

//        orderController.findOpenedOrders();
//        orderController.findClosedOrders();
        System.out.println("--------------------With inheritance-------------------------------");


        Employee waiter = new Waiter();
        waiter.setName("Hector");
        waiter.setSurname("Monatic");
        waiter.setPosition(Position.WAITER);
        waiter.setTelephone(556815311);
        waiter.setSalary(5555.5F);
        waiter.setBirthday("05-08-2012");
        employeeController.addNewEmployee(waiter);
        System.out.println("Waiter Hector was employed");

        Employee cooker = new Cook();
        cooker.setName("Vova");
        cooker.setSurname("Winner");
        cooker.setPosition(Position.COOKER);
        cooker.setTelephone(551311);
        cooker.setSalary(55555F);
        cooker.setBirthday("05-05-2014");
        employeeController.addNewEmployee(cooker);
        System.out.println("Cooker Vova was employed");

        List<Ingredient> difloppeIngredients = new ArrayList<>();
        cakeIngredients.add(ingredientController.findByName("tomato"));
        cakeIngredients.add(ingredientController.findByName("feta"));
        cakeIngredients.add(ingredientController.findByName("oil"));

        System.out.println("Ingredients were added");
//        employeeController.getEmployeesByName("Vova");
        Dish difloppe = dishController.prepareDish("Difloppe", DishCategory.MAIN, 190.00F, 0.05F, Measures.KG, difloppeIngredients, employeeController.getEmployeesByName("Vova"));
        System.out.println("DISH");
        dishController.addNewDish(difloppe);
        System.out.println("Dish created");

        List<String> dishesForHector = new ArrayList<>();
        dishesForHector.add("Difloppe");

        orderController.addNewOrder(orderController.createOrder("Hector",dishesForHector,10));
        System.out.println("Order created");
//
//        orderController.closeOrder(orderController.findById(5L));

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

    public boolean isReInit() {
        return reInit;
    }

    public void setReInit(boolean reInit) {
        this.reInit = reInit;
    }

    public void setKitchenHistoryController(KitchenHistoryController kitchenHistoryController) {
        this.kitchenHistoryController = kitchenHistoryController;
    }
}

