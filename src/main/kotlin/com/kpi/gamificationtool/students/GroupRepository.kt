package com.kpi.gamificationtool.students

import com.kpi.gamificationtool.users.User
import org.springframework.data.jpa.repository.JpaRepository

interface GroupRepository: JpaRepository<Group, Long> {

    fun findByUser(user: User): List<Group>
    fun findByNameAndUser(name: String, user: User): Group?
}