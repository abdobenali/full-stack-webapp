package com.springtodowebapp.app.model;

import java.sql.Date;


public class Todo {
	 	private Long id;
	    private String firstName; 
	    private String lastName;    
	    private Date begindate;
	    private Date enddate;
	   
	    private String todo;
		

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Date getBegindate() {
			return begindate;
		}
		public void setBegindate(Date begindate) {
			this.begindate = begindate;
		}
		public Date getEnddate() {
			return enddate;
		}
		public void setEnddate(Date enddate) {
			this.enddate = enddate;
		}
		public String getTodo() {
			return todo;
		}
		public void setTodo(String todo) {
			this.todo = todo;
		}
		
}
