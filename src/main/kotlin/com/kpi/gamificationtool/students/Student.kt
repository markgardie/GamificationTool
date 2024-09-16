package com.kpi.gamificationtool.students

import jakarta.persistence.*

@Entity
@Table(name = "students")
data class Student(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    val age: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    val group: Group,
)