# Управління проєктами

Android-застосунок для лабораторної роботи 3 з програмування Android-застосунків. Варіант 1.

## Екрани

- `ProjectsListScreen` — список проєктів із назвою та прогресом;
- `AddProjectScreen` — створення нового проєкту через форму назви й опису;
- `ProjectDetailsScreen` — деталі, слайдер прогресу та видалення проєкту.

## Навігація

Навігація реалізована за допомогою Navigation Compose. Екран деталей приймає тільки ідентифікатор:

```kotlin
const val DETAILS = "details/{projectId}"
navController.navigate(AppRoutes.details(projectId))
```

Дані зберігаються у `ProjectsViewModel` в `MutableStateFlow<List<Project>>`. На екрані деталей об'єкт знаходиться за отриманим `projectId`; весь об'єкт між екранами не передається.

## Запуск

Відкрийте проєкт у Android Studio, дочекайтеся Gradle Sync та запустіть модуль `app` на емуляторі або пристрої з Android 6.0 (API 23) чи новішим.
