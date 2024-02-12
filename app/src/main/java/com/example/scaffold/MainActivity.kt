package com.example.scaffold
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffold.ui.theme.ScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController){
    Scaffold (
        topBar = { MainTopBar("My App", navController)},
    ){ paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "Content for Main Screen")
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InfoScreen(navController: NavController){
    Scaffold (
        topBar = { ScreenTopBar("Info", navController)},
    ){ paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "Content for Info Screen")
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController){
    Scaffold (
        topBar = { ScreenTopBar("Settings", navController)},
    ){ paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "Content for Settings Screen")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title) },
        navigationIcon = { /* Menu icon here, onClick event empty */ },
        actions = {
            // More icon, for the menu
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More")
            }

            // Dropdown menu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem (
                    text = {Text("Info")}, onClick = { navController.navigate("Info")}
                )
                DropdownMenuItem (
                    text = {Text("Settings")}, onClick = { navController.navigate("Settings")}
                )
            }
        }
    )
}


@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") { MainScreen(navController) }
        composable("Info") { InfoScreen(navController) }
        composable("Settings") { SettingsScreen(navController) }
    }
}




@Preview(showBackground = true)
@Composable
fun ScaffoldAppPreview() {
    ScaffoldTheme {
        ScaffoldApp()
    }
}