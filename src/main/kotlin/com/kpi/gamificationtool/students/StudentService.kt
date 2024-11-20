package com.kpi.gamificationtool.students

import com.kpi.gamificationtool.points_system.PointSystem
import com.kpi.gamificationtool.points_system.PointSystemService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val groupRepository: GroupRepository,
    private val pointSystemService: PointSystemService,
    private val passwordEncoder: PasswordEncoder,
) {

    fun findAllByGroup(name: String): List<Student> {
        val group = groupRepository.findByName(name)
            ?: throw UsernameNotFoundException("Групу не знайдено")

        return studentRepository.findByGroup(group)
    }

    fun findById(id: Long): Student {
        return studentRepository.findById(id).orElse(null)
    }

    fun saveStudent(studentForm: StudentForm): Student {
        val group = groupRepository.findById(studentForm.groupId)
            .orElseThrow { IllegalArgumentException("Групу не знайдено") }

        val student = Student(
            name = studentForm.name,
            age = studentForm.age,
            login = studentForm.login,
            password = passwordEncoder.encode(studentForm.password),
            group = group,
            pointSystems = emptyList(),
        )

        val savedStudent = studentRepository.save(student)

        val testPointSystems = listOf(
            PointSystem(name = "Валюта", value = 0, student = savedStudent),
            PointSystem(name = "Технічні навички", value = 0, student = savedStudent),
            PointSystem(name = "Креативність", value = 0, student = savedStudent),
            PointSystem(name = "Командна робота", value = 0, student = savedStudent),
        )

        pointSystemService.addPointSystems(testPointSystems)
        return savedStudent
    }

    fun deleteStudentById(id: Long) {
        studentRepository.deleteById(id)
    }

    fun updateStudent(id: Long, studentForm: StudentForm): Student {
        val student = studentRepository.findById(id).orElseThrow { IllegalArgumentException("Student not found") }
        val newGroup = groupRepository.findById(studentForm.groupId).orElseThrow { IllegalArgumentException("Group not found") }

        var newPassword = ""

        if (studentForm.password.isNotBlank()) {
            newPassword = passwordEncoder.encode(studentForm.password)
        }
        return studentRepository.save(student.copy(
            name = studentForm.name,
            age = studentForm.age,
            login = studentForm.login,
            password = newPassword,
            group = newGroup,
        ))
    }
}