package ua.opu.projectmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.opu.projectmanager.navigation.AppNavigation
import ua.opu.projectmanager.ui.theme.ProjectManagerTheme
import ua.opu.projectmanager.viewmodel.ProjectsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectManagerTheme {
                AppNavigation(viewModel = viewModel<ProjectsViewModel>())
            }
        }
    }
}
