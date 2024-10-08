package com.kpi.gamificationtool.students

import com.kpi.gamificationtool.points_system.PointSystemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(path = ["/students"])
class StudentController(
    private val studentService: StudentService,
    private val pointSystemService: PointSystemService,
) {

    @GetMapping
    fun showStudentList(@RequestParam groupName: String, model: Model): String {
        val students = studentService.findAllByGroup(groupName)
        model.addAttribute("students", students)
        model.addAttribute("groupName", groupName)
        return "students/student-list"
    }

    @GetMapping("/add")
    fun showAddStudentForm(@RequestParam groupName: String, model: Model): String {
        model.addAttribute("groupName", groupName)
        return "students/add_student"
    }

    @GetMapping("/details/{id}")
    fun showStudentDetails(@PathVariable id: Long, model: Model): String {
        val student = studentService.findById(id)
        model.addAttribute("student", student)
        return "students/student-details"

    }

    @PostMapping("/add")
    fun addStudent(@ModelAttribute studentForm: StudentForm, @RequestParam groupName: String): String {
        studentService.saveStudent(studentForm.name, studentForm.age, groupName)
        return "redirect:/students?groupName=$groupName"
    }

    @PostMapping("/delete")
    fun deleteStudent(@RequestParam studentId: Long, @RequestParam groupName: String): String {
        studentService.deleteStudentById(studentId)
        return "redirect:/students?groupName=$groupName"
    }

    @PostMapping("/{id}/points/increase")
    fun increasePoints(@PathVariable id: Long, @RequestParam name: String, @RequestParam amount: Int): String {
        pointSystemService.increasePoints(id, name, amount)
        return "redirect:/students/details/$id"
    }

    @PostMapping("/{id}/points/decrease")
    fun decreasePoints(@PathVariable id: Long, @RequestParam name: String, @RequestParam amount: Int): String {
        pointSystemService.decreasePoints(id, name, amount)
        return "redirect:/students/details/$id"
    }

    @GetMapping("/edit/{id}")
    fun showEditForm(@PathVariable id: Long, model: Model): String {
        val student = studentService.findById(id)
        model.addAttribute("student", student)
        return "students/edit-student"
    }

    @PostMapping("/edit/{id}")
    fun updateStudent(@PathVariable id: Long, @ModelAttribute studentForm: StudentForm): String {
        studentService.updateStudent(id, studentForm.name, studentForm.age)
        return "redirect:/students/details/$id"
    }
}