package com.example.spring.springboot.springboot_rest.dao;


import com.example.spring.springboot.springboot_rest.entity.*;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOImplementation  implements EmployeeDAO{
    // dependency injection с помощью поля класса
    @Autowired
//    private SessionFactory sessionFactory;
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
//        Session session = entityManager.unwrap(Session.class);//sessionFactory.getCurrentSession();
//        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class)
//                .getResultList();
// аналог записи выше
//        Query<Employee> query = session.createQuery("from Employee", Employee.class);
//        query.getResultList();


        Query query = entityManager.createQuery("from Employee");
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
//        System.out.println(employee);
//        Session session  = entityManager.unwrap(Session.class);//sessionFactory.getCurrentSession();
//        session.saveOrUpdate(employee);
        Employee newEmployee = entityManager.merge(employee); // +-== saveOrUpdate
        employee.setId(newEmployee.getId()); // response did send wrong id

    }

    @Override
    public Employee getEmployee(int id) {
//        Session session  = entityManager.unwrap(Session.class);//sessionFactory.getCurrentSession();
//        return session.get(Employee.class, id);


        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
//        Session session  = entityManager.unwrap(Session.class);//sessionFactory.getCurrentSession();
//        Query<Employee> query = session.createQuery("delete from Employee " +
//                "where id =:employeeId");
//        query.setParameter("employeeId", id);
//        query.executeUpdate();
        Query query = entityManager.createQuery("delete from Employee " +
                "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
