package com.dmt.todo.repository;

import com.dmt.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    public List<Todo> findByNameOrDescriptionOrPriorityOrCompletionStatus(
            @Param("name") String name,
            @Param("description") String description,
            @Param("priority") int priority,
            @Param("completion_status") String completionStatus);
}
