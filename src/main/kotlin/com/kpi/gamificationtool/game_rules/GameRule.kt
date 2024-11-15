package com.kpi.gamificationtool.game_rules

import com.kpi.gamificationtool.students.Group
import jakarta.persistence.*

@Entity
@Table(name = "game_rules")
data class GameRule(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    val stimuli: String,
    val task: String,
    val gameElement: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    val group: Group,
)
