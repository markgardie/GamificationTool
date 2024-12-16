package com.kpi.gamificationtool.students

import com.kpi.gamificationtool.points_system.PointSystem
import jakarta.persistence.*
import java.time.LocalDate
import java.time.Period

@Entity
@Table(name = "students")
data class Student(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(name = "birth_date", nullable = false)
    val birthDate: LocalDate,

    @Column(unique = true, nullable = false)
    val login: String,

    @Column(nullable = false)
    val password: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    val group: Group,

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL], orphanRemoval = true)
    val pointSystems: List<PointSystem>,
) {
    fun calculateAge(): Int {
        return Period.between(birthDate, LocalDate.now()).years
    }
}
