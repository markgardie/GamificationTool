package com.kpi.gamificationtool.game_rules


enum class CoreDrive(
    val ukName: String,
    val motivationTypes: Set<MotivationType>,
    val gameElements: Set<GameElement>
) {
    ACCOMPLISHMENT(
        ukName = "Досягнення",
        motivationTypes = setOf(MotivationType.POSITIVE, MotivationType.LOGICAL),
        gameElements = setOf(
            GameElement.DEVELOPER_LEVELS,
            GameElement.SKILL_TREE,
            GameElement.BADGES,
            GameElement.PERSONAL_STATISTIC,
            GameElement.QUIZ,
            GameElement.TECHNICAL_DEMO,
            GameElement.PORTFOLIO,
            GameElement.CERTIFICATE,
            GameElement.CHALLENGE,
            GameElement.PROGRESS_BAR,
            GameElement.BURNDOWN_DIAGRAM,
            GameElement.KANBAN_BOARD,
            GameElement.LEADERBOARD,
        )
    ),
    MEANING(
        ukName = "Сенс",
        motivationTypes = setOf(MotivationType.POSITIVE, MotivationType.LOGICAL, MotivationType.EMOTIONAL),
        gameElements = setOf(
            GameElement.NARRATIVE,
            GameElement.REAL_PROJECTS,
            GameElement.CHARITY_PROJECTS,
            GameElement.MENTORSHIP,
            GameElement.CRISIS_SIMULATION,
        )
    ),
    CREATIVITY(
        ukName = "Креативність",
        motivationTypes = setOf(MotivationType.POSITIVE, MotivationType.EMOTIONAL),
        gameElements = setOf(
            GameElement.OWN_PROJECTS,
            GameElement.OWN_SOLUTIONS,
            GameElement.RESEARCH,
            GameElement.CUSTOMIZATION,
            GameElement.NONLINEAR_STORY,
            GameElement.INNOVATION_HACKATHON,
            GameElement.IDEAS_FORUM,
            GameElement.BRAINSTORM,
            GameElement.SIX_HATS,
            GameElement.FOUR_STEP_SKETCH,
            GameElement.DOT_VOTING,
            GameElement.CRAZY_EIGHTS,
        )
    ),
    OWNERSHIP(
        ukName = "Володіння",
        motivationTypes = setOf(MotivationType.POSITIVE, MotivationType.NEGATIVE, MotivationType.LOGICAL),
        gameElements = setOf(
            GameElement.CURRENCY,
            GameElement.STORE,
            GameElement.COLLECTION_SET,
            GameElement.VIRTUAL_ECONOMICS,
        )
    ),
    SOCIAL(
        ukName = "Соціальний вплив",
        motivationTypes = setOf(MotivationType.POSITIVE, MotivationType.NEGATIVE, MotivationType.EMOTIONAL),
        gameElements = setOf(
            GameElement.ROLE_PLAY,
            GameElement.TEAM_PROJECTS,
            GameElement.TEAM_HACKATHONS,
            GameElement.SOCIAL_NETWORK,
        )
    ),
    SCARCITY(
        ukName = "Дефіцит",
        motivationTypes = setOf(MotivationType.NEGATIVE, MotivationType.LOGICAL),
        gameElements = setOf(
            GameElement.LIMITED_COURSES,
            GameElement.DEADLINES,
            GameElement.LIMITED_RESOURCES,
            GameElement.FUNCTIONALITY_PRIORITIZATION,
            GameElement.REQUIREMENTS_PRIORITIZATION,
            GameElement.TIME_LIMITED_INTERVIEWS,
            GameElement.TIME_LIMITED_HACKATHONS,
            GameElement.POKER_PLANNING,
        )
    ),
    UNPREDICTABILITY(
        ukName = "Непередбачуваність",
        motivationTypes = setOf(MotivationType.NEGATIVE, MotivationType.EMOTIONAL),
        gameElements = setOf(
            GameElement.CHANGING_REQUIREMENTS,
            GameElement.RANDOM_TEAMS,
            GameElement.RANDOM_BUGS,
            GameElement.UNPREDICTABLE_STORY,
            GameElement.EASTER_EGGS,
            GameElement.STORY_POINT_ESTIMATION,
            GameElement.RANDOM_TECHNOLOGIES,
        )
    ),
    AVOIDANCE(
        ukName = "Уникнення",
        motivationTypes = setOf(MotivationType.NEGATIVE, MotivationType.LOGICAL, MotivationType.EMOTIONAL),
        gameElements = setOf(
            GameElement.NEGATIVE_ENDING,
            GameElement.STARTUP_FAILURE,
            GameElement.CYBERATTACK,
            GameElement.PROJECTS_ATTACK_COMPETITION,
            GameElement.PENALTY_POINTS,
            GameElement.LOSING_STREAK,

            )
    );

    // Допоміжний метод для отримання ігрових елементів драйвера
    fun getAvailableGameElements(): Set<GameElement> = gameElements
}