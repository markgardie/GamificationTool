package com.kpi.gamificationtool.game_rules

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRuleRepository : JpaRepository<GameRule, Long> {
    fun findByGroupId(groupId: Long): List<GameRule>
}