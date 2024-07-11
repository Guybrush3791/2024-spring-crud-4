package org.java.spring_crud4;

import java.util.List;
import java.util.Optional;

import org.java.spring_crud4.db.pojo.Employee;
import org.java.spring_crud4.db.pojo.Task;
import org.java.spring_crud4.db.serv.EmployeeService;
import org.java.spring_crud4.db.serv.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrud4Application implements CommandLineRunner {

	/**
	 * TODO:
	 * 
	 * Creare in nuovo progetto le seguenti entita':
	 * Employee:
	 * - id : INT
	 * - name : STRING
	 * - surname : STRING
	 * - salary : INT
	 * 
	 * Task
	 * - id : INT
	 * - title : STRING
	 * - description : STRING
	 * - level : INT (valore tra 1 e 5)
	 * 
	 * Dopo aver creato gli oggetti, salvarli in tabelle
	 * dedicate nel db. E testare il corretto funzionamento
	 * del codice.
	 * 
	 * Generare a questo punto la relazione di tipo
	 * molti-a-molti tra Employee e Task.
	 * 
	 * Decidere sensatamente quale delle due entita'
	 * dovrebbe essere quella principale.
	 * 
	 * Testare le 4 operazioni di CRUD su entrambe le
	 * tabelle tenendo in consideraizone le relazioni.
	 * 
	 */

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private TaskService taskService;

	public static void main(String[] args) {
		SpringApplication.run(SpringCrud4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Hello, World!");

		// employeeTest();
		// taskTest();

		employeeTaskRelationTest();

		System.out.println("The end");
	}

	public void employeeTest() {

		System.out.println("-----------------------------------------------------------");

		Employee e1 = new Employee("Guybrush", "Threepwood", 3000);
		Employee e2 = new Employee("Elaine", "Marley", 5000);
		Employee e3 = new Employee("LeChuck", "LeChuck", 10000);

		employeeService.save(e1);
		employeeService.save(e2);
		employeeService.save(e3);

		List<Employee> employees = employeeService.findAll();
		employees.forEach(System.out::println);
		System.out.println("-----------------------------------------------------------");

		Optional<Employee> optEmpId1 = employeeService.findById(1);

		if (optEmpId1.isEmpty()) {

			System.out.println("Employee not found");
			return;
		}

		Employee empId1 = optEmpId1.get();
		employeeService.delete(empId1);

		employees = employeeService.findAll();
		employees.forEach(System.out::println);
		System.out.println("-----------------------------------------------------------");
	}

	public void taskTest() {

		System.out.println("-----------------------------------------------------------");

		Task t1 = new Task("Task 1", "Description 1", 1);
		Task t2 = new Task("Task 2", "Description 2", 2);
		Task t3 = new Task("Task 3", "Description 3", 3);

		taskService.save(t1);
		taskService.save(t2);
		taskService.save(t3);

		List<Task> tasks = taskService.findAll();
		tasks.forEach(System.out::println);
		System.out.println("-----------------------------------------------------------");

		Optional<Task> optTaskId1 = taskService.findById(1);

		if (optTaskId1.isEmpty()) {

			System.out.println("Task not found");
			return;
		}

		Task taskId1 = optTaskId1.get();
		taskService.delete(taskId1);

		tasks = taskService.findAll();
		tasks.forEach(System.out::println);
		System.out.println("-----------------------------------------------------------");
	}

	public void employeeTaskRelationTest() {

		System.out.println("-----------------------------------------------------------");

		Employee e1 = new Employee("Guybrush", "Threepwood", 3000);
		Employee e2 = new Employee("Elaine", "Marley", 5000);
		Employee e3 = new Employee("LeChuck", "LeChuck", 10000);

		employeeService.save(e1);
		employeeService.save(e2);
		employeeService.save(e3);

		Task t1 = new Task("Task 1", "Description 1", 1);
		Task t2 = new Task("Task 2", "Description 2", 2);
		Task t3 = new Task("Task 3", "Description 3", 3);

		taskService.save(t1);
		taskService.save(t2);
		taskService.save(t3);

		List<Employee> employees = employeeService.findAllWTasks();

		e1 = employees.get(0);
		e2 = employees.get(1);

		e1.addTask(t1);
		e1.addTask(t2);

		e2.addTask(t1);
		e2.addTask(t2);
		e2.addTask(t3);

		employeeService.save(e1);
		employeeService.save(e2);

		employees = employeeService.findAllWTasks();
		employees.forEach(e -> {

			System.out.println(e);
			e.getTasks().forEach(System.out::println);

			System.out.println("");
		});

		System.out.println("-----------------------------------------------------------");

		List<Task> tasks = taskService.findAllWEmployees();
		tasks.forEach(t -> {

			System.out.println(t);
			t.getEmployees().forEach(System.out::println);

			System.out.println("");
		});

		System.out.println("-----------------------------------------------------------");

		Optional<Employee> optEmpId1 = employeeService.findByIdWTasks(1);

		if (optEmpId1.isEmpty()) {

			System.out.println("Employee not found");
			return;
		}

		Employee empId1 = optEmpId1.get();
		System.out.println(empId1);
		empId1.getTasks().forEach(System.out::println);

		employeeService.delete(empId1);

		System.out.println("-----------------------------------------------------------");

		Optional<Task> optTaskId1 = taskService.findByIdWEmployees(1);

		if (optTaskId1.isEmpty()) {

			System.out.println("Task not found");
			return;
		}

		Task taskId1 = optTaskId1.get();
		System.out.println(taskId1);
		taskId1.getEmployees().forEach(System.out::println);

		for (Employee e : taskId1.getEmployees()) {

			Employee tmpEmp = employeeService.findByIdWTasks(e.getId()).get();
			tmpEmp.removeTask(taskId1);
			employeeService.save(tmpEmp);
		}

		taskService.delete(taskId1);
	}
}
