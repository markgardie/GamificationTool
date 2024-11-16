package com.kpi.gamificationtool.points_system

import org.springframework.data.jpa.repository.JpaRepository

interface PointSystemRepository : JpaRepository<PointSystem, Long> {
    fun findByStudentId(studentId: Long): List<PointSystem>
    fun findByStudentIdAndName(studentId: Long, name: String): PointSystem?

}