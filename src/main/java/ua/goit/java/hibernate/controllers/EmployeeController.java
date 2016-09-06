package ua.goit.java.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.EmployeeDao;
import ua.goit.java.hibernate.model.Employee;
import ua.goit.java.hibernate.model.Position;
import ua.goit.java.hibernate.model.Waiter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDAO;

    @Transactional
    public Employee bornEmployee(String name, String surname, int telephone, Position position, Float salary, String birthday) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setTelephone(telephone);
        employee.setPosition(position);
        employee.setSalary(salary);
        employee.setBirthday(birthday);
        return employee;
    }


    @Transactional
    public void initEmployees() {
        Set<Employee> allEmployees = new HashSet<>(employeeDAO.findAll());
        Waiter employee = new Waiter();
        employee.setName("Ivan");
        employee.setSurname("Smith");
        employee.setTelephone(123456789);
        employee.setPosition(Position.WAITER);
        employee.setSalary(5000.0F);
        employee.setBirthday("1988-05-01");
//                bornEmployee(, "Smith", 123456789, Position.WAITER, 5000.0F, "1988-05-01");

        if (!allEmployees.contains(employee)) {    // если такого еще нет в БД, то добавляем

            employeeDAO.save(employee);
        }
        Employee employee2 = bornEmployee("Peter", "Carrot", 555184, Position.COOKER, 999999.0F, "1988-05-01");
        if (!allEmployees.contains(employee2)) {    // если такого еще нет в БД, то добавляем

            employeeDAO.save(employee2);
        }
    }

    @Transactional
    public void addNewEmployee(Employee employee) {
        Set<Employee> allEmployees = new HashSet<>(employeeDAO.findAll());

        if (!allEmployees.contains(employee)) {    // если такого еще нет в БД, то добавляем

            employeeDAO.save(employee);
        }
    }

    @Transactional
    public void removeEmployee(Employee employee) {
        employeeDAO.remove(employee);
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

    @Transactional
    public void removeAllEmployees() {
        employeeDAO.removeAllEmployees();
    }


    @Transactional
    public Employee getEmployeesByName(String name) {
        return employeeDAO.findByName(name);
    }


    @Transactional
    public void printEmployee(Long id){
        System.out.println(employeeDAO.load(id));
    }

    public void setEmployeeDAO(EmployeeDao employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}
