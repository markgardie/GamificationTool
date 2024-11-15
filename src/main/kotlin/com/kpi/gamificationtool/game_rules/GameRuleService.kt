package com.kpi.gamificationtool.game_rules

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class GameRuleService(
    private val gameRuleRepository: GameRuleRepository
) {
    fun getGameRulesByGroup(groupId: Long): List<GameRule> {
        return gameRuleRepository.findByGroupId(groupId)
    }

    @Transactional
    fun addGameRule(gameRule: GameRule): GameRule {
        return gameRuleRepository.save(gameRule)
    }

    @Transactional
    fun deleteGameRule(ruleId: Long) {
        gameRuleRepository.deleteById(ruleId)
    }

    @Transactional
    fun updateGameRule(ruleId: Long, updatedGameRule: GameRule): GameRule {
        val existingRule = gameRuleRepository.findById(ruleId).orElseThrow {
            throw IllegalArgumentException("GameRule with id $ruleId not found")
        }
        val newRule = existingRule.copy(
            name = updatedGameRule.name,
            stimuli = updatedGameRule.stimuli,
            task = updatedGameRule.task,
            gameElement = updatedGameRule.gameElement,
            coreDrive = updatedGameRule.coreDrive,
            group = updatedGameRule.group
        )
        return gameRuleRepository.save(newRule)
    }

    fun findById(id: Long): GameRule {
        return gameRuleRepository.findById(id).orElse(null)
    }
}