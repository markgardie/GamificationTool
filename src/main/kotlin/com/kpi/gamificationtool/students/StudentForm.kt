package com.kpi.gamificationtool.students

import java.time.LocalDate

data class StudentForm(
    val name: String,
    val birthDate: LocalDate,
    val login: String,
    val password: String,
    val groupId: Long,
)
