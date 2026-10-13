package ua.opu.projectmanager.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ua.opu.projectmanager.ui.AddProjectScreen
import ua.opu.projectmanager.ui.ProjectDetailsScreen
import ua.opu.projectmanager.ui.ProjectsListScreen
import ua.opu.projectmanager.viewmodel.ProjectsViewModel

object AppRoutes {
    const val PROJECTS = "projects"
    const val ADD_PROJECT = "add_project"
    const val DETAILS = "details/{projectId}"
    fun details(projectId: Int) = "details/$projectId"
}

@Composable
fun AppNavigation(
    viewModel: ProjectsViewModel,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = AppRoutes.PROJECTS) {
        composable(AppRoutes.PROJECTS) {
            val projects by viewModel.projects.collectAsState()
            ProjectsListScreen(
                projects = projects,
                onAddProject = {
                    viewModel.prepareNewProject()
                    navController.navigate(AppRoutes.ADD_PROJECT)
                },
                onProjectClick = { navController.navigate(AppRoutes.details(it)) },
            )
        }
        composable(AppRoutes.ADD_PROJECT) {
            val uiState by viewModel.addProjectUiState.collectAsState()
            AddProjectScreen(
                uiState = uiState,
                onNameChange = viewModel::updateName,
                onDescriptionChange = viewModel::updateDescription,
                onSave = { if (viewModel.saveProject()) navController.popBackStack() },
                onCancel = { navController.popBackStack() },
            )
        }
        composable(
            route = AppRoutes.DETAILS,
            arguments = listOf(navArgument("projectId") { type = NavType.IntType }),
        ) { entry ->
            val projects by viewModel.projects.collectAsState()
            val projectId = entry.arguments?.getInt("projectId")
            val project = projects.firstOrNull { it.id == projectId }
            ProjectDetailsScreen(
                project = project,
                onProgressChange = { progress -> if (projectId != null) viewModel.updateProgress(projectId, progress) },
                onDelete = {
                    if (projectId != null) viewModel.deleteProject(projectId)
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() },
            )
        }
    }
}
