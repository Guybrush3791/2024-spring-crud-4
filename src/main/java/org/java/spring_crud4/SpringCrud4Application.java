package org.java.spring_crud4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrud4Application {

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

	public static void main(String[] args) {
		SpringApplication.run(SpringCrud4Application.class, args);
	}

}
