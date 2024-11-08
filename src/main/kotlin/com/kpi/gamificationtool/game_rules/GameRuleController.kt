package com.kpi.gamificationtool.game_rules

import com.kpi.gamificationtool.students.GroupService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/game-rules")
class GameRuleController(
    private val gameRuleService: GameRuleService,
    private val groupService: GroupService,
) {

    @GetMapping
    fun getGameRules(@RequestParam groupId: Long, model: Model): String {
        val gameRules = gameRuleService.getGameRulesByGroup(groupId)
        model.addAttribute("gameRules", gameRules)
        model.addAttribute("groupId", groupId)
        return "game_rules/rules-list"
    }

    @GetMapping("/edit/{id}")
    fun editGameRule(
        @PathVariable id: Long,
        @RequestParam groupId: Long,
        model: Model
    ): String {
        val gameRule = gameRuleService.findById(id)
        model.addAttribute("gameRule", gameRule)
        model.addAttribute("groupId", groupId)
        return "game_rules/edit-rule"
    }

    @PostMapping("/add")
    fun addGameRule(
        @RequestParam groupId: Long,
        @ModelAttribute gameRuleForm: GameRuleForm,
        redirectAttributes: RedirectAttributes
    ): String {
        val newRule = GameRule(
            name = gameRuleForm.name,
            stimuli = gameRuleForm.stimuli,
            task = gameRuleForm.task,
            motivator = gameRuleForm.motivator,
            group = groupService.findById(groupId)
        )
        gameRuleService.addGameRule(newRule)
        redirectAttributes.addAttribute("groupId", groupId)
        return "redirect:/game-rules"
    }

    @PostMapping("/delete/{id}")
    fun deleteGameRule(
        @PathVariable id: Long,
        @RequestParam groupId: Long,
        redirectAttributes: RedirectAttributes
    ): String {
        gameRuleService.deleteGameRule(id)
        redirectAttributes.addAttribute("groupId", groupId)
        return "redirect:/game-rules"
    }

    @PostMapping("/update/{id}")
    fun updateGameRule(
        @PathVariable id: Long,
        @RequestParam groupId: Long,
        @ModelAttribute gameRuleForm: GameRuleForm,
        redirectAttributes: RedirectAttributes
    ): String {
        val updatedRule = gameRuleService.findById(id).copy(
            name = gameRuleForm.name,
            stimuli = gameRuleForm.stimuli,
            task = gameRuleForm.task,
            motivator = gameRuleForm.motivator,
        )
        gameRuleService.updateGameRule(id, updatedRule)
        redirectAttributes.addAttribute("groupId", groupId)
        return "redirect:/game-rules"
    }
}