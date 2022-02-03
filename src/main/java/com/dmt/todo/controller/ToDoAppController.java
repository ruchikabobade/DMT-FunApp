package com.dmt.todo.controller;

import com.dmt.todo.exception.InvalidInputException;
import com.dmt.todo.exception.NoDataFoundException;
import com.dmt.todo.model.PostResponse;
import com.dmt.todo.model.ToDoItem;
import com.dmt.todo.service.TodoAppService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class ToDoAppController {
    private Logger logger = LoggerFactory.getLogger(ToDoAppController.class);

    @Autowired
    private TodoAppService service;

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public ResponseEntity<List<ToDoItem>> getTodoItems(@RequestParam(required = false, value = "name", defaultValue = "0") String name ,
                                                       @RequestParam(required = false, value = "description", defaultValue = "0") String description,
                                                       @RequestParam(required = false, value = "priority", defaultValue = "0") Integer priority,
                                                       @RequestParam(required = false, value = "completed", defaultValue = "0") String completed) throws InvalidInputException {
        if(name == null || description == null || priority == null || completed == null) {
            throw new InvalidInputException("invalid query params passed");
        }
        List<ToDoItem> toDoItems = service.getTodoItems(name, description, priority, completed);
        return new ResponseEntity<>(toDoItems, HttpStatus.OK);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public ResponseEntity<ToDoItem> getTodoItemById(@PathVariable Long id) throws InvalidInputException, NoDataFoundException {
        if(id == null || id == 0L) {
            throw new InvalidInputException("invalid id passed");
        }
        try{
            ToDoItem toDoItem = service.getTodoItemById(id);
            return new ResponseEntity<>(toDoItem, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            throw  new NoDataFoundException(e.getMessage());
        }
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public ResponseEntity<PostResponse> addTodoItem(@RequestBody ToDoItem toDoItem ) throws Exception {
        try {
            Long id = service.addTodoItem(toDoItem);
            PostResponse resp = new PostResponse();
            resp.setID(id);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (Exception e){
            if(e instanceof ParseException){
                throw new ParseException("invalid input data", 0);
            } else {
                throw new Exception();
            }
        }
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ToDoItem> updateTodoItem(@PathVariable Long id, @RequestBody ToDoItem toDoItem ) throws Exception {
        try {
            ToDoItem todo = service.updateTodoItem(toDoItem, id);
            return new ResponseEntity<>(todo, HttpStatus.OK);
         } catch (Exception e){
        if(e instanceof ParseException){
            throw new ParseException("invalid input data", 0);
        } else {
            throw new Exception();
        }
    }
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTodoItem(@PathVariable Long id) throws NoDataFoundException {

        try {
            String resp = service.deleteTodoItem(id);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            throw  new NoDataFoundException(e.getMessage());
        }
    }
}
