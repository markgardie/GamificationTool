package com.kpi.gamificationtool.students

import com.kpi.gamificationtool.points_system.PointSystemService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(path = ["/students"])
class StudentController(
    private val studentService: StudentService,
    private val pointSystemService: PointSystemService,
    private val groupService: GroupService,
) {

    @GetMapping
    fun showStudentList(@RequestParam groupName: String, model: Model): String {
        val students = studentService.findAllByGroup(groupName)
        val group = groupService.findByName(groupName)


        model.addAttribute("students", students)
        model.addAttribute("groupName", groupName)
        model.addAttribute("groupId", group.id)

        return "students/student-list"
    }

    @GetMapping("/details/{id}")
    fun showStudentDetails(@PathVariable id: Long, model: Model): String {
        val student = studentService.findById(id)
        model.addAttribute("student", student)
        return "students/student-details"

    }

    @GetMapping("/add")
    fun showAddStudentForm(@RequestParam groupName: String, model: Model): String {
        val group = groupService.findByName(groupName)
        model.addAttribute("group", group)
        return "students/add-student"
    }

    @PostMapping("/add")
    fun addStudent(@ModelAttribute studentForm: StudentForm): String {
        val newStudent = studentService.saveStudent(studentForm)
        return "redirect:/students?groupName=${newStudent.group.name}"
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
        val groups = groupService.getAllGroups()
        model.addAttribute("student", student)
        model.addAttribute("groups", groups)
        return "students/edit-student"
    }

    @PostMapping("/edit/{id}")
    fun updateStudent(@PathVariable id: Long, @ModelAttribute studentForm: StudentForm): String {
        studentService.updateStudent(id, studentForm)
        return "redirect:/students/details/$id"
    }

    @PostMapping("/{id}/points/update")
    fun updatePoints(
        @PathVariable id: Long,
        request: HttpServletRequest
    ): String {
        val points = request.parameterNames.asSequence()
            .filter { it.startsWith("value_") }
            .associate { paramName ->
                val pointName = paramName.removePrefix("value_")
                val value = request.getParameter(paramName).toIntOrNull() ?: 0
                pointName to value
            }

        pointSystemService.updatePoints(id, points)
        return "redirect:/students/details/$id"
    }

}