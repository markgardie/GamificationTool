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

    fun deletePointSystem(systemId: Long) {
        pointSystemRepository.deleteById(systemId)
    }

    fun increasePoints(studentId: Long, pointSystemName: String, amount: Int) {
        val pointSystem = pointSystemRepository.findByStudentIdAndName(studentId, pointSystemName)
            ?: throw IllegalArgumentException("Point system not found")
        pointSystem.value += amount
        pointSystemRepository.save(pointSystem)
    }

    fun decreasePoints(studentId: Long, pointSystemName: String, amount: Int) {
        val pointSystem = pointSystemRepository.findByStudentIdAndName(studentId, pointSystemName)
            ?: throw IllegalArgumentException("Point system not found")
        pointSystem.value = (pointSystem.value - amount).coerceAtLeast(0)
        pointSystemRepository.save(pointSystem)
    }

    fun getPointSystems(studentId: Long): List<PointSystem> {
        return pointSystemRepository.findByStudentId(studentId)
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