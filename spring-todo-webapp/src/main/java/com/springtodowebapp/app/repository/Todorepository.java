package com.springtodowebapp.app.repository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
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
		try {
            String POST_URL = "http://localhost:9000/todo";
            URI postURI = new URI(POST_URL);
            ObjectMapper objectMapper = new ObjectMapper();
            String postBody = objectMapper.writeValueAsString(todo);
            
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest httpRequestPost = HttpRequest.newBuilder()
                    .uri(postURI)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(postBody))
                    .build();

            HttpResponse<String> postResponse = httpClient.send(httpRequestPost, HttpResponse.BodyHandlers.ofString());
            System.out.println(postResponse.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
		return todo;
	}
	public Todo updateTodo(Todo todo) {
		try {
            String PUT_URL = "http://localhost:9000/todo/"+todo.getId();
            URI putURI = new URI(PUT_URL);
            ObjectMapper objectMapper = new ObjectMapper();
            String postBody = objectMapper.writeValueAsString(todo);
            
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest httpRequestPost = HttpRequest.newBuilder()
                    .uri(putURI)
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(postBody))
                    .build();

            HttpResponse<String> postResponse = httpClient.send(httpRequestPost, HttpResponse.BodyHandlers.ofString());
            System.out.println(postResponse.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
		return todo;
	}
	public void deleteTodo(final Long id) {
		try {
            String DELETE_URL = "http://localhost:9000/todo/"+id;
            URI deleteURI = new URI(DELETE_URL);
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest httpRequestPost = HttpRequest.newBuilder()
                    .uri(deleteURI)
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> postResponse = httpClient.send(httpRequestPost, HttpResponse.BodyHandlers.ofString());
            System.out.println(postResponse.body());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
	}
	public Optional<Todo> getTodo(final Long id) {
		String baseApiUrl = props.getApiUrl();
		String getTodosUri = "/todo/{id}";
		WebClient.Builder builder = WebClient.builder();
		
		Mono<Todo> todo = builder
        .baseUrl(baseApiUrl) 
        .build()
        .get()
        .uri(getTodosUri, id) 
        .retrieve()
        .bodyToMono(Todo.class);
		
		return todo.blockOptional();
	}

}
