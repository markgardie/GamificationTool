package com.kpi.gamificationtool.students

import com.kpi.gamificationtool.points_system.PointSystem
import com.kpi.gamificationtool.points_system.PointSystemService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val groupRepository: GroupRepository,
    private val pointSystemService: PointSystemService,
) {

    fun findAllByGroup(name: String): List<Student> {
        val group = groupRepository.findByName(name)
            ?: throw UsernameNotFoundException("Групу не знайдено")

        return studentRepository.findByGroup(group)
    }

    fun findById(id: Long): Student {
        return studentRepository.findById(id).orElse(null)
    }

    fun saveStudent(studentName: String, age: Int, groupName: String): Student {
        val group = groupRepository.findByName(groupName)
            ?: throw UsernameNotFoundException("Групу не знайдено")

        val student = Student(
            name = studentName,
            age = age,
            group = group,
            pointSystems = emptyList(),
        )

        val testPointSystems = listOf(
            PointSystem(name = "Логіки", value = 0, student = student),
            PointSystem(name = "Технічні навички", value = 0, student = student),
            PointSystem(name = "Креативність", value = 0, student = student),
            PointSystem(name = "Командна робота", value = 0, student = student),
        )

        val res = studentRepository.save(student)

        pointSystemService.addPointSystems(testPointSystems)
        return res
    }

    fun deleteStudentById(id: Long) {
        studentRepository.deleteById(id)
    }
}