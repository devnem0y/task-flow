// Информация о приложении
GET("http://localhost:8080/api/v1/info") {
    header("Accept", "application/json")
}

// Получить задачи проекта
GET("http://localhost:8080/api/v1/tasks?projectId=1&page=0&size=10") {
    header("Accept", "application/json")
}

// Создать задачу
POST("http://localhost:8080/api/v1/tasks") {
    header("Content-Type", "application/json")
    body(
        """
        {
          "title": "Написать тесты",
          "description": "Покрыть сервисный слой юнит-тестами",
          "projectId": 1,
          "priority": "HIGH"
        }
        """.trimIndent()
    )
}

// Получить задачу по id
GET("http://localhost:8080/api/v1/tasks/1") {
    header("Accept", "application/json")
}

// Удалить задачу
DELETE("http://localhost:8080/api/v1/tasks/1")
