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
            GameElement.POINTS,
            GameElement.BADGES,
            GameElement.LEADERBOARDS,
            GameElement.LEVELS
        )
    ),
    MEANING(
        ukName = "Сенс",
        motivationTypes = setOf(MotivationType.POSITIVE, MotivationType.LOGICAL, MotivationType.EMOTIONAL),
        gameElements = setOf() // Додати відповідні елементи
    ),
    CREATIVITY(
        ukName = "Креативність",
        motivationTypes = setOf(MotivationType.POSITIVE, MotivationType.EMOTIONAL),
        gameElements = setOf() // Додати відповідні елементи
    ),
    OWNERSHIP(
        ukName = "Володіння",
        motivationTypes = setOf(MotivationType.POSITIVE, MotivationType.NEGATIVE, MotivationType.LOGICAL),
        gameElements = setOf() // Додати відповідні елементи
    ),
    SOCIAL(
        ukName = "Соціальний вплив",
        motivationTypes = setOf(MotivationType.POSITIVE, MotivationType.NEGATIVE, MotivationType.EMOTIONAL),
        gameElements = setOf() // Додати відповідні елементи
    ),
    SCARCITY(
        ukName = "Дефіцит",
        motivationTypes = setOf(MotivationType.NEGATIVE, MotivationType.LOGICAL),
        gameElements = setOf() // Додати відповідні елементи
    ),
    UNPREDICTABILITY(
        ukName = "Непередбачуваність",
        motivationTypes = setOf(MotivationType.NEGATIVE, MotivationType.EMOTIONAL),
        gameElements = setOf() // Додати відповідні елементи
    ),
    AVOIDANCE(
        ukName = "Уникнення",
        motivationTypes = setOf(MotivationType.NEGATIVE, MotivationType.LOGICAL, MotivationType.EMOTIONAL),
        gameElements = setOf() // Додати відповідні елементи
    );

    // Допоміжний метод для отримання ігрових елементів драйвера
    fun getAvailableGameElements(): Set<GameElement> = gameElements
}