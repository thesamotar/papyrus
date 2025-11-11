package com.example.papyrus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.Group

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import com.example.papyrus.ui.screens.DiscoverScreen
import com.example.papyrus.ui.screens.HomeScreen
import com.example.papyrus.ui.library.LibraryScreen
import com.example.papyrus.ui.library.LibraryViewModel
import com.example.papyrus.ui.screens.ProfileScreen
import com.example.papyrus.ui.screens.CommunityScreen
import com.example.papyrus.ui.screens.SearchScreen
import com.example.papyrus.ui.theme.PapyrusTheme

import com.example.papyrus.ui.components.SearchTopBar
import com.example.papyrus.ui.search.SearchViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PapyrusTheme {
                PapyrusApp()
            }
        }
    }
}

sealed class NavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : NavItem("Home", Icons.Filled.Home)
    object Library : NavItem("Library", Icons.Filled.Book)
    object Discover : NavItem("Discover", Icons.Filled.Search)
    object Community : NavItem("Community", Icons.Filled.Group)
    object Profile : NavItem("Profile", Icons.Filled.Person)
}

val navigationItems = listOf(
    NavItem.Home,
    NavItem.Library,
    NavItem.Discover,
    NavItem.Community,
    NavItem.Profile
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PapyrusApp() {
    var searchActive by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var notificationsOpen by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableIntStateOf(0) }

    val searchViewModel = remember { SearchViewModel() }
    val libraryViewModel = remember { LibraryViewModel() }

    // This function will be used to navigate to a shelf from the home screen
    // For simplicity, let's use shelf label (could use ID/enum for more robust navigation)
    fun onShelfClick(shelfLabel: String) {
        selectedTab = 1 // Go to Library tab
        libraryViewModel.shelves.value.firstOrNull { it.name == shelfLabel }?.let { shelf ->
            libraryViewModel.selectShelf(shelf.id)
        }
    }

    Scaffold(
        topBar = {
            SearchTopBar(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                onSearchActivated = { searchActive = true },
                onNotificationsClicked = { notificationsOpen = true }
            )
        },
        bottomBar = {
            NavigationBar {
                navigationItems.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        icon = { Icon(navItem.icon, contentDescription = navItem.label) },
                        label = { Text(navItem.label) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        alwaysShowLabel = true
                    )
                }
            }
        }
    ) { innerPadding ->
        if (searchActive) {
            SearchScreen(
                query = searchText,
                onQueryChange = { searchText = it },
                onSearchExit = { searchActive = false; searchText = "" },
                viewModel = searchViewModel
            )
        } else {
            when (selectedTab) {
                // Pass the click function and ensure padding is applied so content starts below search bar
                0 -> HomeScreen(modifier = Modifier.padding(innerPadding))
                1 -> LibraryScreen(viewModel = libraryViewModel)
                2 -> DiscoverScreen(Modifier.padding(innerPadding))
                3 -> CommunityScreen(Modifier.padding(innerPadding))
                4 -> ProfileScreen(Modifier.padding(innerPadding))
            }
        }
    }
}