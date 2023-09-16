package com.springtodowebapp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtodowebapp.app.model.Todo;
import com.springtodowebapp.app.repository.Todorepository;

@Service
public class Todoservice {
	@Autowired
	private Todorepository todorepository;
	
	public List<Todo> getTodos(){
		return todorepository.getTodos();
	}
	public Optional<Todo> getTodo(final Long id){
		return todorepository.getTodo(id);
	}
	public Todo saveTodo(Todo todo) {
		Todo savedTodo = todorepository.saveTodo(todo);
		return(savedTodo);
	}
	
	public void deleteTodo(final Long id) {
		todorepository.deleteTodo(id);
	}
	
	
}
