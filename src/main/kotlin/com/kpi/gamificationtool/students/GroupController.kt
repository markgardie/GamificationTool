package com.kpi.gamificationtool.students

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
class GroupController(
    private val groupService: GroupService
) {
    @GetMapping("/")
    fun showGroupList(model: Model): String {
        val groups: List<Group> = groupService.findAll()
        model.addAttribute("groups", groups)
        return "index"
    }

    @PostMapping("/delete_group")
    fun deleteGroup(@RequestParam groupId: Long?): String {
        if (groupId != null) {
            groupService.deleteGroupById(groupId)
        }
        return "redirect:/"
    }

    @GetMapping("/create_group")
    fun showCreateGroupForm(): String {
        return "students/create-group"
    }

    @PostMapping("/create_group")
    fun createGroup(@ModelAttribute groupForm: GroupForm, model: Model): String {
        if (groupForm.name == "") {
            model.addAttribute("error", "Немає назви")
            return "students/create-group"
        }
        try {
            groupService.upsertGroup(groupForm.name)
        } catch (e: RuntimeException) {
            model.addAttribute("error", e.message)
            return "students/create-group"
        }
        return "redirect:/"
    }
}