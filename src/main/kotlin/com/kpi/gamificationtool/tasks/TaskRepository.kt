package com.kpi.gamificationtool.tasks

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
    fun findByGroupId(groupId: Long): List<Task>
}