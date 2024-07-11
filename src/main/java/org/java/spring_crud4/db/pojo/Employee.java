package org.java.spring_crud4.db.pojo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 64)
    private String name;
    @Column(nullable = false, length = 64)
    private String lastname;

    @Column(nullable = false)
    private int salary;

    @ManyToMany(cascade = CascadeType.REFRESH)
    private List<Task> tasks;

    public Employee() {
    }

    public Employee(String name, String lastname, int salary) {

        setName(name);
        setLastname(lastname);
        setSalary(salary);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {

        tasks.add(task);
    }

    public void removeTask(Task task) {

        // List<Task> subTasks = new ArrayList<>();

        // for (Task t : tasks)
        // if (t.getId() != task.getId())
        // subTasks.add(t);

        // tasks = subTasks;

        tasks = tasks.stream()
                .filter(t -> t.getId() != task.getId())
                .toList();
    }

    @Override
    public String toString() {

        return "Employee{\n"
                + "id=" + id + ",\n"
                + "name=" + name + ",\n"
                + "lastname=" + lastname + ",\n"
                + "salary=" + salary + "\n}";
    }
}
