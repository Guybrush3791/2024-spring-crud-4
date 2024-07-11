package org.java.spring_crud4.db.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_crud4.db.pojo.Employee;
import org.java.spring_crud4.db.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> findAll() {

        return employeeRepo.findAll();
    }

    @Transactional
    public List<Employee> findAllWTasks() {

        List<Employee> emps = findAll();

        for (Employee emp : emps) {

            Hibernate.initialize(emp.getTasks());
        }

        return emps;
    }

    public Optional<Employee> findById(int id) {

        return employeeRepo.findById(id);
    }

    @Transactional
    public Optional<Employee> findByIdWTasks(int id) {

        Optional<Employee> emp = findById(id);

        if (emp.isEmpty())
            return Optional.empty();

        Hibernate.initialize(emp.get().getTasks());

        return emp;

    }

    public void save(Employee employee) {

        employeeRepo.save(employee);
    }

    public void delete(Employee employee) {

        employeeRepo.delete(employee);
    }
}
