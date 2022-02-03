package com.dmt.todo.service;

import com.dmt.todo.dao.TodoDao;
import com.dmt.todo.entity.Todo;
import com.dmt.todo.model.ToDoItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TodoAppService {
    private Logger logger = LoggerFactory.getLogger(TodoAppService.class);

    @Autowired
    private TodoDao todoDao;

    public List<ToDoItem> getTodoItems(String name, String description, Integer priority, String completed){
        try {
            List<Todo> todoList = todoDao.viewTodo(name, description, priority, completed);
            List<ToDoItem> toDoItems = new ArrayList<>();

            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

            for(Todo todo: todoList){
                ToDoItem toDoItem = new ToDoItem();
                toDoItem.setID(todo.getTodoId());
                toDoItem.setName(todo.getName());
                toDoItem.setDescription(todo.getDescription());
                toDoItem.setCompletionStatus(todo.getCompletionStatus());
                toDoItem.setPriority(todo.getPriority());
                toDoItem.setDueDate(dateFormat.format(todo.getDueDate()));
                toDoItem.setCompletionDate(dateFormat.format(todo.getCompletionDate()));
                toDoItems.add(toDoItem);
            }

           return toDoItems;
        } catch (Exception e) {
            logger.error("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : getTodoItems() , message : error " + e.getMessage() + "}");
            throw e;
        }
    }

    public ToDoItem getTodoItemById(Long id){
        try{
            Optional<Todo> todo = todoDao.viewTodoById(id);
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

            ToDoItem toDoItem = new ToDoItem();
            toDoItem.setID(todo.get().getTodoId());
            toDoItem.setName(todo.get().getName());
            toDoItem.setDescription(todo.get().getDescription());
            toDoItem.setCompletionStatus(todo.get().getCompletionStatus());
            toDoItem.setPriority(todo.get().getPriority());
            toDoItem.setDueDate(dateFormat.format(todo.get().getDueDate()));
            toDoItem.setCompletionDate(dateFormat.format(todo.get().getCompletionDate()));

            return toDoItem;
        } catch (Exception e) {
            logger.error("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : getTodoItemById , message : error " + e.getMessage() + "}");
            throw e;
        }
    }

    public Long addTodoItem(ToDoItem toDoItem) throws ParseException {
        try {
            Todo todo = todoDao.addTodo(toDoItem);
            return todo.getTodoId();
        } catch (ParseException pe) {
            throw pe;
        } catch (Exception e) {
            throw e;
        }
    }

    public ToDoItem updateTodoItem(ToDoItem toDoItem, Long todoId) throws ParseException {
        try {
            Todo todo = todoDao.updateTodo(toDoItem, todoId);

            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

            ToDoItem item = new ToDoItem();
            item.setID(todoId);
            item.setName(todo.getName());
            item.setDescription(todo.getDescription());
            item.setCompletionStatus(todo.getCompletionStatus());
            item.setPriority(todo.getPriority());
            item.setDueDate(dateFormat.format(todo.getDueDate()));
            item.setCompletionDate(dateFormat.format(todo.getCompletionDate()));

            return item;
        } catch (ParseException pe) {
            throw pe;
        } catch (Exception e) {
            throw e;
        }
    }

    public String deleteTodoItem(Long todoId){
        try{
            String resp = todoDao.deleteTodo(todoId);
            return resp;
        } catch (Exception e) {
            logger.error("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : deleteTodoItem() , message : error " + e.getMessage() + "}");
            throw e;
        }
    }


}
