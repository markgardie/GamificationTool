package com.kpi.gamificationtool.tasks

import com.kpi.gamificationtool.students.GroupService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/tasks")
class TaskController(
    private val taskService: TaskService,
    private val groupService: GroupService
) {
    @ModelAttribute("knowledge_areas")
    fun knowledgeAreas(): Array<KnowledgeArea> = KnowledgeArea.entries.toTypedArray()

    @GetMapping
    fun getTasks(@RequestParam groupId: Long, model: Model): String {
        val tasks = taskService.getTasksByGroup(groupId)
        val group = groupService.findById(groupId)

        model.addAttribute("tasks", tasks)
        model.addAttribute("groupId", groupId)
        model.addAttribute("groupName", group.name)
        return "tasks/tasks-list"
    }

    @GetMapping("/edit/{id}")
    fun editTask(
        @PathVariable id: Long,
        @RequestParam groupId: Long,
        model: Model
    ): String {
        val task = taskService.findById(id)
        val group = groupService.findById(groupId)

        model.addAttribute("task", task)
        model.addAttribute("groupId", groupId)
        model.addAttribute("groupName", group.name)
        return "tasks/edit-task"
    }

    @PostMapping("/add")
    fun addTask(
        @RequestParam groupId: Long,
        @ModelAttribute taskForm: TaskForm,
        redirectAttributes: RedirectAttributes
    ): String = handleTask(
        taskForm = taskForm,
        groupId = groupId,
        redirectAttributes = redirectAttributes,
        action = { taskService.addTask(it) }
    )

    @PostMapping("/delete/{id}")
    fun deleteTask(
        @PathVariable id: Long,
        @RequestParam groupId: Long,
        redirectAttributes: RedirectAttributes
    ): String {
        taskService.deleteTask(id)
        redirectAttributes.addAttribute("groupId", groupId)
        return "redirect:/tasks"
    }

    @PostMapping("/update/{id}")
    fun updateTask(
        @PathVariable id: Long,
        @RequestParam groupId: Long,
        @ModelAttribute taskForm: TaskForm,
        redirectAttributes: RedirectAttributes
    ): String = handleTask(
        taskForm = taskForm,
        groupId = groupId,
        redirectAttributes = redirectAttributes,
        action = { task ->
            taskService.updateTask(id, task)
        }
    )

    private fun handleTask(
        taskForm: TaskForm,
        groupId: Long,
        redirectAttributes: RedirectAttributes,
        action: (Task) -> Task
    ): String {
        val knowledgeArea = KnowledgeArea.entries.first { it.ukName == taskForm.knowledgeArea }
        val skill = Skill(name = taskForm.skillName)

        val task = Task(
            name = taskForm.name,
            description = taskForm.description,
            skill = skill,
            knowledgeArea = knowledgeArea,
            group = groupService.findById(groupId)
        )

        action(task)
        redirectAttributes.addAttribute("groupId", groupId)
        return "redirect:/tasks"
    }
}