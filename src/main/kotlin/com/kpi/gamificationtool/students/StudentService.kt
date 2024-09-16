package com.kpi.gamificationtool.students

import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val groupRepository: GroupRepository
) {

    fun findAllByGroup(name: String): List<Student> {
        val group = groupRepository.findByName(name)
            ?: throw UsernameNotFoundException("Групу не знайдено")

        return studentRepository.findByGroup(group)
    }

    fun saveStudent(studentName: String, age: Int, groupName: String): Student {
        val group = groupRepository.findByName(groupName)
            ?: throw UsernameNotFoundException("Групу не знайдено")

        val student = Student(
            name = studentName,
            age = age,
            group = group
        )
        return studentRepository.save(student)
    }

    fun deleteStudentById(id: Long) {
        studentRepository.deleteById(id)
    }
}