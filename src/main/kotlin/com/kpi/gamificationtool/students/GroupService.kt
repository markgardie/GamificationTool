package com.kpi.gamificationtool.students

import org.springframework.stereotype.Service

@Service
class GroupService(
    private val groupRepository: GroupRepository
) {

    fun findByName(name: String) : Group?  = groupRepository.findByName(name)

    fun findAll(): List<Group> = groupRepository.findAll()

    fun upsertGroup(name: String) : Group = groupRepository.save(Group(name = name))

    fun deleteGroupById(id: Long) = groupRepository.deleteById(id)
}