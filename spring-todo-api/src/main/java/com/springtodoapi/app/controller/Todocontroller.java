package com.springtodoapi.app.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springtodoapi.app.model.Todo;
import com.springtodoapi.app.service.Todoservice;



@RestController
public class Todocontroller {
	@Autowired 
	private Todoservice todoservice;

	@GetMapping("/todos")
	public List<Todo> getTodos() {
		return (List<Todo>) todoservice.getTodos();
	}
	
	@GetMapping("/todo/{id}")
	public Todo getTodo(@PathVariable("id") final Long id) {
		Optional<Todo> todo = todoservice.findTodo(id);
		if(todo.isPresent()) {
			return todo.get();
		} else {
			return null;
		}
	}
	@PostMapping("/todo")
	public Todo createTodo(@RequestBody Todo todo) {
		return todoservice.saveTodo(todo);
	}
	
	@PutMapping("/todo/{id}")
	public Todo updateTodo(@PathVariable("id") final Long id, @RequestBody Todo todo) {
		Optional<Todo> t = todoservice.findTodo(id);
		if(t.isPresent()) {
			Todo currentTodo = t.get();
			
			String firstName = todo.getFirstName();
			if(firstName != null) {
				currentTodo.setFirstName(firstName);
			}
			String lastName = todo.getLastName();
			if(lastName != null) {
				currentTodo.setLastName(lastName);;
			}
			Date begindate = todo.getBegindate();
			if(begindate != null) {
				currentTodo.setBegindate(begindate);
			}
			Date enddate = todo.getEnddate();
			if(enddate != null) {
				currentTodo.setEnddate(enddate);;
			}
			String todocontext = todo.getTodo();
			if(todocontext != null) {
				currentTodo.setTodo(todocontext);
			}
			todoservice.saveTodo(currentTodo);
			return currentTodo;
		} else {
			return null;
		}
	}
	@DeleteMapping("/todo/{id}")
	public void deleteTodo(@PathVariable("id") final Long id) {
		todoservice.deleteTodo(id);
	}


	
	
	

	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	
}
