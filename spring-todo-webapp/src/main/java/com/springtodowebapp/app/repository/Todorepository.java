package com.springtodowebapp.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.springtodowebapp.app.CustomProperties;
import com.springtodowebapp.app.model.Todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public class Todorepository {

	@Autowired
	private CustomProperties props ;


	public List<Todo> getTodos(){
		String baseApiUrl = props.getApiUrl();
		String getTodosUri = "/todos";
		//spring webClient
		WebClient.Builder builder = WebClient.builder();
		Flux<Todo> todoFlux = builder
				.baseUrl(baseApiUrl)
				.build()
				.get()
				.uri(getTodosUri)
				.retrieve()
				.bodyToFlux(Todo.class);

		
		List<Todo> todoList = todoFlux.collectList().block(); 

		return todoList;

	}
	public Todo saveTodo(Todo todo) {
		String baseApiUrl = props.getApiUrl();
		String getTodosUri = "/todo";
		WebClient.Builder builder = WebClient.builder();
		Todo savedTodo = builder
        .baseUrl(baseApiUrl) 
        .build()
        .post()
        .uri(getTodosUri) 
        .body(Mono.just(todo), Todo.class)
        .retrieve()
        .bodyToMono(Todo.class)
        .block();
		return savedTodo;
	}
	public void deleteTodo(final Long id) {
		String baseApiUrl = props.getApiUrl();
		String getTodosUri = "/todo/{id}";
		WebClient.Builder builder = WebClient.builder();
		
		builder
        .baseUrl(baseApiUrl) 
        .build()
        .delete()
        .uri(getTodosUri, id) 
        .retrieve()
        .bodyToMono(Void.class);
	}
	public Optional<Todo> getTodo(final Long id) {
		String baseApiUrl = props.getApiUrl();
		String getTodosUri = "/todo/{id}";
		WebClient.Builder builder = WebClient.builder();
		
		Mono<Todo> todo = builder
        .baseUrl(baseApiUrl) // Remplacez par l'URL de l'API
        .build()
        .get()
        .uri(getTodosUri, id) // Remplacez par l'API endpoint réel pour obtenir un "Todo" par ID
        .retrieve()
        .bodyToMono(Todo.class);
		return todo.blockOptional();
	}

}
