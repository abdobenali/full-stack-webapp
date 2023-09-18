package com.springtodowebapp.app.controller;


import java.sql.Date;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.springtodowebapp.app.model.Todo;
import com.springtodowebapp.app.service.Todoservice;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Controller
public class WebappController {

	@Autowired
	private Todoservice todoservice ;
	
	@GetMapping("/todos")
	public String getTodos(Model model) {
		
		model.addAttribute("todos",todoservice.getTodos());
		return "home";
	}
	@GetMapping("/updatetodo/{id}")
	public String updateTodo(@PathVariable("id") final Long id, Model model) {
		model.addAttribute("todo",todoservice.getTodo(id));
		
		/*Optional<Todo> t = todoservice.getTodo(id);
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
		}*/
		return"update";
	}
	
	@GetMapping("/todo/{id}")
	public Todo getTodo(@PathVariable("id") final Long id) {
		Optional<Todo> todo = todoservice.getTodo(id);
		if(todo.isPresent()) {
			return todo.get();
		} else {
			return null;
		}
		
	}
	@PostMapping("/todo")
	public String createTodo(@ModelAttribute Todo todo,Model model) {
		todoservice.saveTodo(todo);
		model.addAttribute("todos",todoservice.getTodos());
		return "home";
	}
	@PostMapping("/savetodo")
	public ModelAndView saveEmployee(@ModelAttribute Todo todo) {
		System.out.println(todo.getId());
		todoservice.updateTodo(todo);
		return new ModelAndView("redirect:/todos");	
	}
	
	@GetMapping("/deletetodo/{id}")
	public ModelAndView deleteTodo(@PathVariable("id") final Long id) {
		todoservice.deleteTodo(id);
		
		return new ModelAndView("redirect:/todos");	
	}
	
}
