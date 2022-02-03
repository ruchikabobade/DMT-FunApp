package com.dmt.todo.dao;

import com.dmt.todo.entity.Todo;
import com.dmt.todo.model.ToDoItem;
import com.dmt.todo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class TodoDao {
    private Logger logger = LoggerFactory.getLogger(TodoDao.class);

    @Autowired
    private TodoRepository todoRepository;

    public Todo addTodo(ToDoItem toDoItem) throws ParseException {
        logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : addTodo() , action : adding todo , data : "+  toDoItem.toString() + "}");
        SimpleDateFormat formatter =new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        try{
            Date dueDate = formatter.parse(toDoItem.getDueDate());
            Date completionDate = formatter.parse(toDoItem.getCompletionDate());
            Todo todo = new Todo(toDoItem.getName(), toDoItem.getDescription(),
                    toDoItem.getPriority(), dueDate,
                    toDoItem.getCompletionStatus(), completionDate);
            return todoRepository.save(todo);
        } catch (Exception e){
            logger.error("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : addTodo() , message : error " + e.getMessage() + "}");
            throw e;
        }
    }

    public Todo updateTodo(ToDoItem toDoItem, Long todoId) throws ParseException {
        logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : updateTodo() , action : updating todo of id : " + todoId + " , data : "+  toDoItem.toString() + "}");
        SimpleDateFormat formatter =new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        try{
            Date dueDate = formatter.parse(toDoItem.getDueDate());
            Date completionDate = formatter.parse(toDoItem.getCompletionDate());
            Todo todo = new Todo();
            todo.setTodoId(todoId);
            todo.setName(toDoItem.getName());
            todo.setDescription(toDoItem.getDescription());
            todo.setPriority(toDoItem.getPriority());
            todo.setDueDate(dueDate);
            todo.setCompletionStatus(toDoItem.getCompletionStatus());
            todo.setCompletionDate(completionDate);

            return todoRepository.save(todo);
        } catch (Exception e){
            logger.error("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : updateTodo() , message : error " + e.getMessage() + "}");
            throw e;
        }
    }

    public List<Todo> viewTodo(String name, String description, Integer priority, String completionStatus) {
        logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewTodo() , action : view all todos " + "}");
        if(!name.equals("0") || !description.equals("0") || priority != 0 || !completionStatus.equals("0")){
            return todoRepository.findByNameOrDescriptionOrPriorityOrCompletionStatus(name, description, priority, completionStatus);
        }
        return todoRepository.findAll();
    }

    public Optional<Todo> viewTodoById(Long toDoId) {
        logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewTodoById() , action : view todo by id : " + toDoId + "}");
        return todoRepository.findById(toDoId);
    }

    public String deleteTodo(Long todoId) {
        logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : deleteTodo() , action : delete todo of id : "+  todoId + "}");
        todoRepository.deleteById(todoId);
        return "Success";
    }

}
