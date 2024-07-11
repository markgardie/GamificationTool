package com.kpi.gamificationtool.users

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class RegistrationController(
    private val userService: UserService
) {

    @GetMapping("/register")
    fun showRegistrationForm() = "users/register"

    @PostMapping("/register")
    fun registerUser(
        @RequestParam name: String,
        @RequestParam username: String,
        @RequestParam password: String
    ): String {

        userService.registerNewUser(name, username, password)
        return "redirect:/login"

    }
}