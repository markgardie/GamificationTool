package com.kpi.gamificationtool.tasks

import jakarta.persistence.*

@Entity
@Table(name = "skills")
data class Skill(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String
)