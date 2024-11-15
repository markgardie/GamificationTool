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

    @ModelAttribute("motivation_types")
    fun motivationTypes(): Array<MotivationType> = MotivationType.entries.toTypedArray()

    @GetMapping("/core-drives")
    @ResponseBody
    fun getCoreDrives(@RequestParam motivationType: MotivationType): List<String> {
        return gameRuleService.getAvailableCoreDrives(motivationType)
            .map { it.ukName }
    }

    @GetMapping("/game-elements")
    @ResponseBody
    fun getGameElements(@RequestParam coreDriveName: String): List<String> {
        val coreDrive = CoreDrive.entries.first { it.ukName == coreDriveName }
        return gameRuleService.getAvailableGameElements(coreDrive)
            .map { it.ukName }
    }

    @GetMapping
    fun getGameRules(@RequestParam groupId: Long, model: Model): String {
        val gameRules = gameRuleService.getGameRulesByGroup(groupId)
        val group = groupService.findById(groupId)

        model.addAttribute("gameRules", gameRules)
        model.addAttribute("groupId", groupId)
        model.addAttribute("groupName", group.name)
        return "game_rules/rules-list"
    }

    @GetMapping("/edit/{id}")
    fun editGameRule(
        @PathVariable id: Long,
        @RequestParam groupId: Long,
        model: Model
    ): String {
        val gameRule = gameRuleService.findById(id)
        val group = groupService.findById(groupId)

        model.addAttribute("gameRule", gameRule)
        model.addAttribute("groupId", groupId)
        model.addAttribute("groupName", group.name)
        return "game_rules/edit-rule"
    }

    @PostMapping("/add")
    fun addGameRule(
        @RequestParam groupId: Long,
        @ModelAttribute gameRuleForm: GameRuleForm,
        redirectAttributes: RedirectAttributes
    ): String = handleGameRule(
        gameRuleForm = gameRuleForm,
        groupId = groupId,
        redirectAttributes = redirectAttributes,
        action = { gameRuleService.addGameRule(it) }
    )

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
    ): String = handleGameRule(
        gameRuleForm = gameRuleForm,
        groupId = groupId,
        redirectAttributes = redirectAttributes,
        action = { rule ->
            val updatedRule = gameRuleService.findById(id).copy(
                name = rule.name,
                stimuli = rule.stimuli,
                task = rule.task,
                motivationType = rule.motivationType,
                gameElement = rule.gameElement,
                coreDrive = rule.coreDrive
            )
            gameRuleService.updateGameRule(id, updatedRule)
        }
    )

    private fun GameRuleForm.toEnums() = Triple(
        MotivationType.entries.first { it.ukName == motivationType },
        CoreDrive.entries.first { it.ukName == coreDrive },
        GameElement.entries.first { it.ukName == gameElement }
    )

    private fun handleGameRule(
        gameRuleForm: GameRuleForm,
        groupId: Long,
        redirectAttributes: RedirectAttributes,
        action: (GameRule) -> GameRule
    ): String {
        val (motivationType, coreDrive, gameElement) = gameRuleForm.toEnums()

        val rule = GameRule(
            name = gameRuleForm.name,
            stimuli = gameRuleForm.stimuli,
            task = gameRuleForm.task,
            motivationType = motivationType,
            coreDrive = coreDrive,
            gameElement = gameElement,
            group = groupService.findById(groupId)
        )

        action(rule)
        redirectAttributes.addAttribute("groupId", groupId)
        return "redirect:/game-rules"
    }
}