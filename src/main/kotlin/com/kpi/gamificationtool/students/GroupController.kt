package com.kpi.gamificationtool.students

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.security.Principal


@Controller
class GroupController(
    private val groupService: GroupService
) {
    @GetMapping("/")
    fun listGroups(model: Model, principal: Principal): String {
        val groups = groupService.findAllByUser(principal.name)
        model.addAttribute("groups", groups)
        return "index"
    }

    @PostMapping("/delete_group")
    fun deleteGroup(@RequestParam groupId: Long, principal: Principal): String {
        groupService.deleteGroupById(groupId, principal.name)
        return "redirect:/"
    }

    @GetMapping("/create_group")
    fun showCreateGroupForm(): String {
        return "students/create-group"
    }

    @PostMapping("/create_group")
    fun createGroup(@ModelAttribute groupForm: GroupForm, principal: Principal): String {
        groupService.upsertGroup(groupForm.name, principal.name)
        return "redirect:/"
    }
}