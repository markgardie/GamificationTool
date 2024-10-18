package com.kpi.gamificationtool.users

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found")
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            ArrayList() // Here you can add user roles if needed
        )
    }

    fun registerNewUser(name: String, username: String, password: String): User {
        if (userRepository.findByUsername(username) != null) {
            throw RuntimeException("User already exists")
        }
        val encodedPassword = passwordEncoder.encode(password)
        val user = User(name = name, username = username, password = encodedPassword)
        return userRepository.save(user)
    }
}