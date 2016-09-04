package ua.goit.java.hibernate.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.dao.EmployeeDao;
import ua.goit.java.hibernate.model.Employee;

import java.util.List;

public class HEmployeeDao implements EmployeeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    @Override
    @Transactional
    public void remove(Employee employee) {
        sessionFactory.getCurrentSession().delete(employee);
    }

    @Override
    @Transactional
    public Employee findByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.name like :name"); // :name - параметр, переданный в запрос
        query.setParameter("name", name); // "name" должен совпадать с параметром в квери - :name
        return (Employee) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Employee e").list();   // select* from employee

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    //    @Override
//    @Transactional
//    public Employee load(Long id) {
//        return null;
//    }
}
