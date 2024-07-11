package com.kpi.gamificationtool.users

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AuthController(private val userService: UserService) {

    @GetMapping("/login")
    fun login(): String {
        return "users/login"
    }

    @GetMapping("/register")
    fun registerForm(): String {
        return "users/register"
    }

    @PostMapping("/register")
    fun registerSubmit(@ModelAttribute registrationForm: RegistrationForm, model: Model): String {
        if (registrationForm.password != registrationForm.confirmPassword) {
            model.addAttribute("error", "Паролі не співпадають")
            return "users/register"
        }

        try {
            userService.registerNewUser(registrationForm.name, registrationForm.username, registrationForm.password)
        } catch (e: RuntimeException) {
            model.addAttribute("error", e.message)
            return "users/register"
        }

        return "redirect:/login?registered"
    }
}
