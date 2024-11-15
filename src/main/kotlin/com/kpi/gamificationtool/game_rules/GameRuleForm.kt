package com.kpi.gamificationtool.game_rules

data class GameRuleForm(
    val name: String,
    val stimuli: String,
    val task: String,
    val gameElement: String,
    val coreDrive: CoreDrive
)
