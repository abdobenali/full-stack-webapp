package com.springtodoapi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springtodoapi.app.model.Todo;

@Repository

public interface Todorepository  extends JpaRepository<Todo, Long> {

}
