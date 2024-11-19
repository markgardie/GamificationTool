package com.kpi.gamificationtool.game_rules

data class GameRuleForm(
    val name: String,
    val stimuli: String,
    val taskId: Long,  // Змінено з task: String на taskId: Long
    val motivationType: String,
    val coreDrive: String,
    val gameElement: String
)