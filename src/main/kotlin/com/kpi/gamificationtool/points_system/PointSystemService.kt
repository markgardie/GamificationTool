package com.kpi.gamificationtool.points_system

import com.kpi.gamificationtool.students.Student
import com.kpi.gamificationtool.students.StudentRepository
import org.springframework.stereotype.Service


@Service
class PointSystemService(
    private val pointSystemRepository: PointSystemRepository,
    private val studentRepository: StudentRepository
) {

    fun addPointSystem(name: String, value: Int, studentId: Long) {

        val student = studentRepository.findById(studentId).orElseThrow()

        val pointSystem = PointSystem(
            name = name,
            value = value,
            student = student,
        )

        pointSystemRepository.save(pointSystem)
    }

    fun addTestPointSystems(systems: List<PointSystem>) {
        pointSystemRepository.saveAll(systems)
    }

    fun deletePointSystem(systemId: Long) {
        pointSystemRepository.deleteById(systemId)
    }
}