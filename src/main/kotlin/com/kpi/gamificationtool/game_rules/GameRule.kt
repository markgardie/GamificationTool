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

    @Enumerated(EnumType.STRING)
    @Column(name = "game_element", nullable = false)
    val gameElement: GameElement,

    @Enumerated(EnumType.STRING)
    @Column(name = "core_drive", nullable = false)
    val coreDrive: CoreDrive,

    @Enumerated(EnumType.STRING)
    @Column(name = "motivation_type", nullable = false)
    val motivationType: MotivationType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    val group: Group,
)
