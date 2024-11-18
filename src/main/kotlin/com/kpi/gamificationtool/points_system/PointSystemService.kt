package com.kpi.gamificationtool.points_system

import com.kpi.gamificationtool.students.StudentRepository
import org.springframework.stereotype.Service


@Service
class PointSystemService(
    private val pointSystemRepository: PointSystemRepository,
    private val studentRepository: StudentRepository
) {

    fun addPointSystems(systems: List<PointSystem>) {
        pointSystemRepository.saveAll(systems)
    }

    fun updatePoints(studentId: Long, points: Map<String, Int>) {
        val student = studentRepository.findById(studentId)
            .orElseThrow { IllegalArgumentException("Студента не знайдено") }

        for ((name, value) in points) {
            val pointSystem = student.pointSystems.find { it.name == name }
                ?: continue

            pointSystemRepository.save(pointSystem.copy(value = value))
        }
    }
}