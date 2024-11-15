package com.kpi.gamificationtool.game_rules

data class GameRuleForm(
    val name: String,
    val stimuli: String,
    val task: String,
    val motivationType: MotivationType,
    val coreDrive: CoreDrive,
    val gameElement: GameElement
)
