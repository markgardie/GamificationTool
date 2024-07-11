package com.kpi.gamificationtool.users

data class RegistrationForm(
    val name: String,
    val username: String,
    val password: String,
    val confirmPassword: String
)