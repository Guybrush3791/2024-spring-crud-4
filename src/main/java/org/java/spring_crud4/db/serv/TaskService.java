package org.java.spring_crud4.db.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_crud4.db.pojo.Task;
import org.java.spring_crud4.db.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<Task> findAll() {

        return taskRepo.findAll();
    }

    @Transactional
    public List<Task> findAllWEmployees() {

        List<Task> tasks = findAll();

        for (Task task : tasks) {

            Hibernate.initialize(task.getEmployees());
        }

        return tasks;
    }

    public Optional<Task> findById(int id) {

        return taskRepo.findById(id);
    }

    @Transactional
    public Optional<Task> findByIdWEmployees(int id) {

        Optional<Task> task = findById(id);

        if (task.isEmpty())
            return Optional.empty();

        Hibernate.initialize(task.get().getEmployees());

        return task;
    }

    public void save(Task task) {

        taskRepo.save(task);
    }

    public void delete(Task task) {

        taskRepo.delete(task);
    }
}
