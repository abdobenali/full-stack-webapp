package com.springtodoapi.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtodoapi.app.model.Todo;
import com.springtodoapi.app.repository.Todorepository;

import lombok.Data;



@Data
@Service
public class Todoservice {
	
	@Autowired
	private Todorepository todorepository;
	
	public Todo saveTodo(Todo todo) {
		Todo savedTodo = todorepository.save(todo);
		return(savedTodo);
	}
	//delete
	public void deleteTodo(final Long id) {
		todorepository.deleteById(id);
	}
	//findbyid
	public Optional<Todo> findTodo(final Long id) {
		return todorepository.findById(id);
	}
	//findall
	public List<Todo> getTodos() {
		
        return (List<Todo>) todorepository.findAll();
    }
}
