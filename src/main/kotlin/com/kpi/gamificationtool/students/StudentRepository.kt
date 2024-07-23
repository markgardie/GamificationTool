package com.kpi.gamificationtool.students

import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Long> {

    fun findByGroup(group: Group): List<Student>
}