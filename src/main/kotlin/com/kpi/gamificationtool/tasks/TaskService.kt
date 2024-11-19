package com.kpi.gamificationtool.tasks

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {
    fun getTasksByGroup(groupId: Long): List<Task> {
        return taskRepository.findByGroupId(groupId)
    }

    @Transactional
    fun addTask(task: Task): Task {
        return taskRepository.save(task)
    }

    @Transactional
    fun deleteTask(taskId: Long) {
        taskRepository.deleteById(taskId)
    }

    @Transactional
    fun updateTask(taskId: Long, updatedTask: Task): Task {
        val existingTask = taskRepository.findById(taskId).orElseThrow {
            throw IllegalArgumentException("Task with id $taskId not found")
        }
        val newTask = existingTask.copy(
            name = updatedTask.name,
            description = updatedTask.description,
            skill = updatedTask.skill,
            knowledgeArea = updatedTask.knowledgeArea,
            group = updatedTask.group
        )
        return taskRepository.save(newTask)
    }

    fun findById(id: Long): Task {
        return taskRepository.findById(id).orElseThrow {
            throw IllegalArgumentException("Task with id $id not found")
        }
    }
}