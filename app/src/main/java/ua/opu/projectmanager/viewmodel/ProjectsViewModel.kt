package ua.opu.projectmanager.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ua.opu.projectmanager.data.Project

data class AddProjectUiState(
    val name: String = "",
    val description: String = "",
)

class ProjectsViewModel : ViewModel() {
    private val _projects = MutableStateFlow(
        listOf(
            Project(1, "Мобільний застосунок", "Розробка інтерфейсу для навчального проєкту", 35f),
            Project(2, "Презентація", "Підготовка матеріалів до захисту лабораторної роботи", 70f),
        )
    )
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    private val _addProjectUiState = MutableStateFlow(AddProjectUiState())
    val addProjectUiState: StateFlow<AddProjectUiState> = _addProjectUiState.asStateFlow()

    fun prepareNewProject() { _addProjectUiState.value = AddProjectUiState() }
    fun updateName(name: String) { _addProjectUiState.update { it.copy(name = name) } }
    fun updateDescription(description: String) { _addProjectUiState.update { it.copy(description = description) } }

    fun saveProject(): Boolean {
        val state = _addProjectUiState.value
        if (state.name.isBlank() || state.description.isBlank()) return false
        _projects.update { list ->
            list + Project(
                id = (list.maxOfOrNull { it.id } ?: 0) + 1,
                name = state.name.trim(),
                description = state.description.trim(),
            )
        }
        return true
    }

    fun updateProgress(projectId: Int, progress: Float) {
        _projects.update { list -> list.map { if (it.id == projectId) it.copy(progress = progress) else it } }
    }

    fun deleteProject(projectId: Int) {
        _projects.update { list -> list.filterNot { it.id == projectId } }
    }
}
