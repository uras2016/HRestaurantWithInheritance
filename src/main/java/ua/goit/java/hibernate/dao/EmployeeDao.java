package ua.goit.java.hibernate.dao;

import ua.goit.java.hibernate.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void save(Employee employee);
    void remove(Employee employee);
    Employee findByName(String name);
    List<Employee> findAll();

    //    Employee load(Long id);

}
