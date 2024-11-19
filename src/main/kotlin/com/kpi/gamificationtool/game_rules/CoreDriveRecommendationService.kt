package com.kpi.gamificationtool.game_rules

import com.kpi.gamificationtool.tasks.KnowledgeArea
import org.springframework.stereotype.Service

@Service
class CoreDriveRecommendationService {


    fun getRecommendedDrives(knowledgeArea: KnowledgeArea): Set<CoreDrive> {
        return when (knowledgeArea) {

            KnowledgeArea.SOFTWARE_REQUIREMENTS -> setOf(
                CoreDrive.SCARCITY,
                CoreDrive.SOCIAL,
                CoreDrive.MEANING,
            )


            KnowledgeArea.SOFTWARE_DESIGN -> setOf(
                CoreDrive.CREATIVITY,
                CoreDrive.SCARCITY,
                CoreDrive.AVOIDANCE,
            )

            KnowledgeArea.SOFTWARE_CONSTRUCTION -> setOf(
                CoreDrive.ACCOMPLISHMENT,
                CoreDrive.SOCIAL,
                CoreDrive.UNPREDICTABILITY,
            )

            KnowledgeArea.SOFTWARE_TESTING -> setOf(
                CoreDrive.AVOIDANCE,
                CoreDrive.UNPREDICTABILITY,
                CoreDrive.ACCOMPLISHMENT,
            )


            KnowledgeArea.SOFTWARE_MAINTENANCE -> setOf(
                CoreDrive.AVOIDANCE,
                CoreDrive.UNPREDICTABILITY,
                CoreDrive.OWNERSHIP,
            )


            KnowledgeArea.SOFTWARE_CONFIGURATION_MANAGEMENT -> setOf(
                CoreDrive.AVOIDANCE,
                CoreDrive.UNPREDICTABILITY,
                CoreDrive.ACCOMPLISHMENT,
            )

            KnowledgeArea.SOFTWARE_ENGINEERING_MANAGEMENT -> setOf(
                CoreDrive.SOCIAL,
                CoreDrive.ACCOMPLISHMENT,
                CoreDrive.MEANING,
            )


            KnowledgeArea.SOFTWARE_QUALITY -> setOf(
                CoreDrive.AVOIDANCE,
                CoreDrive.UNPREDICTABILITY,
                CoreDrive.ACCOMPLISHMENT,
            )


            KnowledgeArea.SOFTWARE_ENGINEERING_PROFESSIONAL_PRACTICE -> setOf(
                CoreDrive.SOCIAL,
                CoreDrive.MEANING,
                CoreDrive.UNPREDICTABILITY,
            )
        }
    }


    fun isRecommendedDrive(knowledgeArea: KnowledgeArea, coreDrive: CoreDrive): Boolean {
        return getRecommendedDrives(knowledgeArea).contains(coreDrive)
    }

}