package com.kpi.gamificationtool.students

import com.kpi.gamificationtool.users.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class GroupService(
    private val groupRepository: GroupRepository,
    private val userRepository: UserRepository
) {
    fun findAllByUser(username: String): List<Group> {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("Користувача не знайдено")
        return groupRepository.findByUser(user)
    }

    fun upsertGroup(name: String, username: String): Group {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("Користувача не знайдено")
        val existingGroup = groupRepository.findByNameAndUser(name, user)
        return existingGroup ?: groupRepository.save(
            Group(
                name = name,
                user = user,
                students = emptyList()
            )
        )
    }

    fun deleteGroupById(id: Long, username: String) {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("Користувача не знайдено")
        val group = groupRepository.findById(id).orElseThrow { NoSuchElementException("Групи не знайдено") }
        if (group.user.id != user.id) {
            throw RuntimeException("У вас немає прав на видалення даної групи")
        }
        groupRepository.deleteById(id)
    }

    fun getAllGroups(): List<Group> {
        return groupRepository.findAll()
    }

    fun findByName(name: String): Group {
        return groupRepository.findByName(name) ?: throw IllegalArgumentException("Групу не знайдено")
    }
}