package com.example.papyrus

// Android imports for base activity and Compose system
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// Material3 UI and icons
import androidx.compose.material.icons.Icons // Gives you access to ready-to-use icons (free from Google)
import androidx.compose.material.icons.filled.* // Loads all the "filled" style icons (Home, Book, Person, etc)
import androidx.compose.material3.* // Material3 UI toolkit: modern, beautiful components for your app
import androidx.compose.material.icons.filled.Group

// Compose state-and-layout tools
import androidx.compose.runtime.* // Lets you use Compose's @Composable and remember (for UI state)

// Modifier and layout for adjusting spacing, padding, etc.
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import com.example.papyrus.ui.screens.DiscoverScreen
import com.example.papyrus.ui.screens.HomeScreen
import com.example.papyrus.ui.screens.LibraryScreen
import com.example.papyrus.ui.screens.ProfileScreen
import com.example.papyrus.ui.screens.CommunityScreen
import com.example.papyrus.ui.screens.SearchScreen
import com.example.papyrus.ui.theme.PapyrusTheme

// importing UI components
import com.example.papyrus.ui.components.SearchTopBar
import com.example.papyrus.ui.search.SearchViewModel

class MainActivity : ComponentActivity() {
    // This is the entry point for your app - like "main" in other languages
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Compose UI starts here!
        setContent {
            PapyrusTheme {
                PapyrusApp() // All your UI starts from this function below
            }
        }
    }
}

// This sealed class defines all the bottom navigation tabs ("items")
// Each tab gets a label and icon - easy to add/edit in one spot!
sealed class NavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : NavItem("Home", Icons.Filled.Home) // Home tab with house icon
    object Library : NavItem("Library", Icons.Filled.Book) // Library tab with book icon
    object Discover : NavItem("Discover", Icons.Filled.Search) // Discover tab with search icon
    object Community : NavItem("Community", Icons.Filled.Group) // Community tab with people icon
    object Profile : NavItem("Profile", Icons.Filled.Person) // Profile tab with person icon
}

// List of all navigation items - controls the order of tabs shown in bottom bar
val navigationItems = listOf(
    NavItem.Home,
    NavItem.Library,
    NavItem.Discover,
    NavItem.Community,
    NavItem.Profile
)


@OptIn(ExperimentalMaterial3Api::class) // Required for TopAppBar API (safe to use, just alerts you it's new)
@Composable
fun PapyrusApp() {
    // Search UI start
    var searchActive by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    // Notifications drawer state
    var notificationsOpen by remember { mutableStateOf(false) }

    // This variable stores which tab you are on (0 = Home, 1 = Library, etc.)
    var selectedTab by remember { mutableIntStateOf(0) }
    // "remember" makes Compose keep the value until you switch tabs
    val searchViewModel = remember { SearchViewModel() }

    // Scaffold provides Material3 "structure": top app bar, bottom nav bar, and main content in the middle
    Scaffold(
        topBar = {
            // --- USE NEW TOP BAR IN EVERY SCREEN ---
            SearchTopBar(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                onSearchActivated = { searchActive = true },
                onNotificationsClicked = { notificationsOpen = true }
            )
        },
        bottomBar = {
            // NavigationBar is the beautiful Material3 bottom navigation tab bar
            NavigationBar {
                // For each tab (NavItem) in order...
                navigationItems.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        icon = { Icon(navItem.icon, contentDescription = navItem.label) }, // Icon for the tab
                        label = { Text(navItem.label) }, // Tab label
                        selected = selectedTab == index, // Highlights current tab
                        onClick = { selectedTab = index }, // Switch to the tab on click
                        alwaysShowLabel = true // Always show the label (good for accessibility and Play Store style)
                    )
                }
            }
        }
    ) { innerPadding ->
        if (searchActive) {
            // Overlay blank background and input UI when search is active
            SearchScreen(
                query = searchText,
                onQueryChange = { searchText = it },
                onSearchExit = { searchActive = false; searchText = " " },
                viewModel = searchViewModel
            )
        } else {
            // Show a different screen depending on which tab is selected (like switching pages)
            when (selectedTab) {
                0 -> HomeScreen(Modifier.padding(innerPadding)) // Show Home tab content
                1 -> LibraryScreen(Modifier.padding(innerPadding)) // Show Library tab content
                2 -> DiscoverScreen(Modifier.padding(innerPadding)) // Show Discover tab content
                3 -> CommunityScreen(Modifier.padding(innerPadding)) // Show Community tab content
                4 -> ProfileScreen(Modifier.padding(innerPadding)) // Show Profile tab content
            }
        }
    }
}