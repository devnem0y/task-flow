
// POST — создать задачу
// POST http://localhost:8080/api/v1/tasks
// Content-Type: application/json
//
// {
//   "title": "Написать тесты для TaskService",
//   "description": "Покрыть юнит-тестами все методы сервиса",
//   "projectId": 1,
//   "priority": "HIGH"
// }
// Ожидаем: 201 Created + Location: /api/v1/tasks/1

POST("http://localhost:8080/api/v1/tasks") {
    header("Content-Type", "application/json")
    body("""
        {
            "title": "Написать тесты для TaskService",
            "description": "Покрыть юнит-тестами все методы сервиса",
            "projectId": 1,
            "priority": "HIGH"
        }
    """.trimIndent())
}

// GET — получить задачу
// GET http://localhost:8080/api/v1/tasks/1
// Ожидаем: 200 OK + JSON задачи

GET("http://localhost:8080/api/v1/tasks/1")

// GET — список задач проекта
// GET http://localhost:8080/api/v1/tasks?projectId=1&page=0&size=10
// Ожидаем: 200 OK + Page с content и метаданными

GET("http://localhost:8080/api/v1/tasks?projectId=1&page=0&size=10")

// GET — с фильтром по статусу
// GET http://localhost:8080/api/v1/tasks?projectId=1&status=TODO&sort=priority,desc
// Ожидаем: только задачи со статусом TODO

GET("http://localhost:8080/api/v1/tasks?projectId=1&status=TODO&sort=priority,desc")

// PATCH — обновить задачу
// PATCH http://localhost:8080/api/v1/tasks/1
// Content-Type: application/json
//
// { "title": "Написать тесты (обновлено)", "priority": "CRITICAL" }
// Ожидаем: 200 OK + обновлённая задача

PATCH("http://localhost:8080/api/v1/tasks/1") {
    header("Content-Type", "application/json")
    body("""
        {
            "title": "Написать тесты (обновлено)",
            "priority": "CRITICAL"
        }
    """.trimIndent())
}

// PATCH — сменить статус
// PATCH http://localhost:8080/api/v1/tasks/1/status
// Content-Type: application/json
//
// { "status": "IN_PROGRESS" }
// Ожидаем: 200 OK + задача со статусом IN_PROGRESS

PATCH("http://localhost:8080/api/v1/tasks/1/status") {
    header("Content-Type", "application/json")
    body("""
        {
            "status": "IN_PROGRESS"
        }
    """.trimIndent())
}

// DELETE — удалить задачу
// DELETE http://localhost:8080/api/v1/tasks/1
// Ожидаем: 204 No Content

DELETE("http://localhost:8080/api/v1/tasks/1")

// GET после удаления
// GET http://localhost:8080/api/v1/tasks/1
// Ожидаем: 404 Not Found + ProblemDetail

GET("http://localhost:8080/api/v1/tasks/1")