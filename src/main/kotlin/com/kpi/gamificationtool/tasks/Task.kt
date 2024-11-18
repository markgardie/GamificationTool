package com.kpi.gamificationtool.tasks

import com.kpi.gamificationtool.game_rules.GameRule
import com.kpi.gamificationtool.students.Group
import jakarta.persistence.*

@Entity
@Table(name = "tasks")
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    val description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competency_id", nullable = false)
    val skill: Skill,

    @Enumerated(EnumType.STRING)
    @Column(name = "knowledge_area", nullable = false)
    val knowledgeArea: KnowledgeArea,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    val group: Group,

    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL])
    val gameRules: List<GameRule> = emptyList()
)