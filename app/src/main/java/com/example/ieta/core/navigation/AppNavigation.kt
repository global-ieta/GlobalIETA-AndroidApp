package com.example.ieta.core.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ieta.core.components.GlobalIetaBottomBar
import com.example.ieta.core.components.GlobalIetaTopBar
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaMotion
import com.example.ieta.feature.about.AboutScreen
import com.example.ieta.feature.aero.AeroScreen
import com.example.ieta.feature.arin.ArinScreen
import com.example.ieta.feature.aura.AuraScreen
import com.example.ieta.feature.auth.AuthScreen
import com.example.ieta.feature.billing.BillingScreen
import com.example.ieta.feature.campus.CampusScreen
import com.example.ieta.feature.company.CompanyScreen
import com.example.ieta.feature.connector.ConnectorScreen
import com.example.ieta.feature.earlyaccess.EarlyAccessScreen
import com.example.ieta.feature.gaming.GamingScreen
import com.example.ieta.feature.home.HomeScreen
import com.example.ieta.feature.industries.IndustriesScreen
import com.example.ieta.feature.legal.PrivacyScreen
import com.example.ieta.feature.legal.TermsScreen
import com.example.ieta.feature.marketplace.MarketplaceScreen
import com.example.ieta.feature.menu.MenuScreen
import com.example.ieta.feature.notifications.NotificationsScreen
import com.example.ieta.feature.profile.ProfileScreen
import com.example.ieta.feature.resources.ResourcesScreen
import com.example.ieta.feature.rideos.RideOSScreen
import com.example.ieta.feature.search.SearchScreen
import com.example.ieta.feature.settings.SettingsScreen
import com.example.ieta.feature.splash.SplashScreen
import com.example.ieta.feature.support.SupportScreen
import com.example.ieta.feature.workspace.WorkspaceScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    /*
     * ------------------------------------------------------------
     * CURRENT NAVIGATION STATE
     * ------------------------------------------------------------
     *
     * currentBackStackEntryAsState() is the Compose-friendly way
     * to observe the current Navigation destination.
     *
     * The NavBackStackEntry itself can temporarily be null during
     * navigation/recomposition, so everything is handled safely.
     */
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry?.destination?.route
            ?: Screen.Home.route

    val isSplash =
        currentRoute == Screen.Splash.route

    /*
     * ------------------------------------------------------------
     * BACK NAVIGATION
     * ------------------------------------------------------------
     *
     * Home is the root destination.
     * Splash is never shown with the normal app navigation chrome.
     */
    val canNavigateBack =
        navController.previousBackStackEntry != null &&
                !isSplash &&
                currentRoute != Screen.Home.route

    /*
     * ------------------------------------------------------------
     * SAFE SCREEN LOOKUP
     * ------------------------------------------------------------
     *
     * IMPORTANT FIX:
     *
     * The previous implementation contained:
     *
     * Screen.allScreens.find { it.route == currentRoute }
     *
     * If Screen.allScreens contains a null entry, accessing
     * it.route causes:
     *
     * NullPointerException:
     * Screen.getRoute() on a null object reference
     *
     * Therefore we explicitly filter null entries BEFORE accessing
     * route or title.
     */
    val currentScreen =
        Screen.allScreens
            .asSequence()
            .filterNotNull()
            .firstOrNull { screen ->
                screen.route == currentRoute
            }

    val currentScreenTitle =
        currentScreen?.title

    /*
     * ------------------------------------------------------------
     * BOTTOM NAVIGATION ROUTES
     * ------------------------------------------------------------
     *
     * These are the five primary mobile navigation destinations.
     */
    val isBottomTabRoute: (String) -> Boolean = { route ->
        route == Screen.Home.route ||
                route == Screen.Industries.route ||
                route == Screen.Marketplace.route ||
                route == Screen.Profile.route ||
                route == Screen.Aura.route
    }

    /*
     * ------------------------------------------------------------
     * BOTTOM TAB NAVIGATION
     * ------------------------------------------------------------
     *
     * Uses singleTop + saved state + restored state so switching
     * between the main tabs does not continuously create duplicate
     * destinations.
     *
     * This follows the standard Navigation Compose pattern.
     */
    val navigateBottomTab: (String) -> Unit = { route ->

        if (route.isNotBlank()) {
            navController.navigate(route) {
                popUpTo(Screen.Home.route) {
                    saveState = true
                }

                launchSingleTop = true
                restoreState = true
            }
        }
    }

    /*
     * ------------------------------------------------------------
     * GENERAL ROUTE NAVIGATION
     * ------------------------------------------------------------
     *
     * Used by Home, Industries, Resources, Search and Menu.
     *
     * Main bottom-tab destinations use the bottom-tab navigation
     * behavior.
     *
     * Other destinations use normal navigation.
     */
    val navigateToRoute: (String) -> Unit = { route ->

        if (route.isNotBlank()) {
            if (isBottomTabRoute(route)) {
                navigateBottomTab(route)
            } else {
                navController.navigate(route) {
                    launchSingleTop = true
                }
            }
        }
    }

    /*
     * ------------------------------------------------------------
     * MAIN SCAFFOLD
     * ------------------------------------------------------------
     */
    Scaffold(

        /*
         * --------------------------------------------------------
         * TOP BAR
         * --------------------------------------------------------
         */
        topBar = {

            if (!isSplash) {

                GlobalIetaTopBar(

                    title =
                        if (currentRoute == Screen.Home.route) {
                            null
                        } else {
                            /*
                             * SAFE:
                             *
                             * currentScreen can be null if the route
                             * is not represented in Screen.allScreens.
                             *
                             * In that case title simply becomes null.
                             *
                             * Most importantly, we never access
                             * .route or .title on a null Screen.
                             */
                            currentScreenTitle
                        },

                    canNavigateBack = canNavigateBack,

                    onNavigateBack = {
                        if (canNavigateBack) {
                            navController.popBackStack()
                        }
                    },

                    onSearchClick = {
                        navController.navigate(Screen.Search.route) {
                            launchSingleTop = true
                        }
                    },

                    onNotificationClick = {
                        navController.navigate(Screen.Notifications.route) {
                            launchSingleTop = true
                        }
                    },

                    onMenuClick = {
                        navController.navigate(Screen.Menu.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        },

        /*
         * --------------------------------------------------------
         * BOTTOM BAR
         * --------------------------------------------------------
         */
        bottomBar = {

            if (!isSplash) {

                GlobalIetaBottomBar(

                    currentRoute = currentRoute,

                    onNavigate = navigateBottomTab,

                    onAuraClick = {
                        navigateBottomTab(Screen.Aura.route)
                    }
                )
            }
        },

        /*
         * --------------------------------------------------------
         * GLOBAL BACKGROUND
         * --------------------------------------------------------
         */
        containerColor = GlobalIETAColor.PrimaryBg

    ) { innerPadding ->

        /*
         * --------------------------------------------------------
         * NAVIGATION HOST
         * --------------------------------------------------------
         */
        NavHost(

            navController = navController,

            startDestination = Screen.Splash.route,

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            /*
             * ----------------------------------------------------
             * FORWARD ENTER
             * ----------------------------------------------------
             */
            enterTransition = {

                fadeIn(
                    animationSpec =
                        GlobalIetaMotion.smoothTweenSpec()
                ) +
                        slideInHorizontally(
                            animationSpec =
                                GlobalIetaMotion.smoothTweenSpec(),

                            initialOffsetX = { fullWidth ->
                                fullWidth / 4
                            }
                        )
            },

            /*
             * ----------------------------------------------------
             * FORWARD EXIT
             * ----------------------------------------------------
             */
            exitTransition = {

                fadeOut(
                    animationSpec =
                        GlobalIetaMotion.smoothTweenSpec()
                ) +
                        slideOutHorizontally(
                            animationSpec =
                                GlobalIetaMotion.smoothTweenSpec(),

                            targetOffsetX = { fullWidth ->
                                -fullWidth / 4
                            }
                        )
            },

            /*
             * ----------------------------------------------------
             * BACK ENTER
             * ----------------------------------------------------
             */
            popEnterTransition = {

                fadeIn(
                    animationSpec =
                        GlobalIetaMotion.smoothTweenSpec()
                ) +
                        slideInHorizontally(
                            animationSpec =
                                GlobalIetaMotion.smoothTweenSpec(),

                            initialOffsetX = { fullWidth ->
                                -fullWidth / 4
                            }
                        )
            },

            /*
             * ----------------------------------------------------
             * BACK EXIT
             * ----------------------------------------------------
             */
            popExitTransition = {

                fadeOut(
                    animationSpec =
                        GlobalIetaMotion.smoothTweenSpec()
                ) +
                        slideOutHorizontally(
                            animationSpec =
                                GlobalIetaMotion.smoothTweenSpec(),

                            targetOffsetX = { fullWidth ->
                                fullWidth / 4
                            }
                        )
            }
        ) {

            /*
             * ====================================================
             * SPLASH
             * ====================================================
             */
            composable(
                route = Screen.Splash.route
            ) {

                SplashScreen(

                    onSplashFinished = {

                        navController.navigate(
                            Screen.Home.route
                        ) {

                            popUpTo(
                                Screen.Splash.route
                            ) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                )
            }

            /*
             * ====================================================
             * HOME
             * ====================================================
             */
            composable(
                route = Screen.Home.route
            ) {

                HomeScreen(

                    onNavigateToRoute = { route ->
                        navigateToRoute(route)
                    }
                )
            }

            /*
             * ====================================================
             * AURA
             * ====================================================
             *
             * AURA keeps its dedicated vertical transition.
             */
            composable(

                route = Screen.Aura.route,

                enterTransition = {

                    fadeIn(
                        animationSpec =
                            GlobalIetaMotion.smoothTweenSpec()
                    ) +

                            slideInVertically(
                                animationSpec =
                                    GlobalIetaMotion.smoothTweenSpec(),

                                initialOffsetY = { fullHeight ->
                                    fullHeight / 3
                                }
                            )
                },

                exitTransition = {

                    fadeOut(
                        animationSpec =
                            GlobalIetaMotion.smoothTweenSpec()
                    ) +

                            slideOutVertically(
                                animationSpec =
                                    GlobalIetaMotion.smoothTweenSpec(),

                                targetOffsetY = { fullHeight ->
                                    fullHeight / 3
                                }
                            )
                },

                popEnterTransition = {

                    fadeIn(
                        animationSpec =
                            GlobalIetaMotion.smoothTweenSpec()
                    ) +

                            slideInVertically(
                                animationSpec =
                                    GlobalIetaMotion.smoothTweenSpec(),

                                initialOffsetY = { fullHeight ->
                                    fullHeight / 3
                                }
                            )
                },

                popExitTransition = {

                    fadeOut(
                        animationSpec =
                            GlobalIetaMotion.smoothTweenSpec()
                    ) +

                            slideOutVertically(
                                animationSpec =
                                    GlobalIetaMotion.smoothTweenSpec(),

                                targetOffsetY = { fullHeight ->
                                    fullHeight / 4
                                }
                            )
                }

            ) {

                AuraScreen()
            }

            /*
             * ====================================================
             * ARIN
             * ====================================================
             */
            composable(
                route = Screen.Arin.route
            ) {

                ArinScreen(

                    onNavigateToAero = {

                        navController.navigate(
                            Screen.Aero.route
                        ) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            /*
             * ====================================================
             * AERO
             * ====================================================
             */
            composable(
                route = Screen.Aero.route
            ) {

                AeroScreen(

                    onNavigateToCampus = {

                        navController.navigate(
                            Screen.Campus.route
                        ) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            /*
             * ====================================================
             * CAMPUS
             * ====================================================
             */
            composable(
                route = Screen.Campus.route
            ) {

                CampusScreen(

                    onNavigateToConnector = {

                        navController.navigate(
                            Screen.Connector.route
                        ) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            /*
             * ====================================================
             * CONNECTOR
             * ====================================================
             */
            composable(
                route = Screen.Connector.route
            ) {

                ConnectorScreen()
            }

            /*
             * ====================================================
             * RIDEOS
             * ====================================================
             */
            composable(
                route = Screen.RideOS.route
            ) {

                RideOSScreen()
            }

            /*
             * ====================================================
             * WORKSPACE
             * ====================================================
             */
            composable(
                route = Screen.Workspace.route
            ) {

                WorkspaceScreen()
            }

            /*
             * ====================================================
             * MARKETPLACE
             * ====================================================
             */
            composable(
                route = Screen.Marketplace.route
            ) {

                MarketplaceScreen()
            }

            /*
             * ====================================================
             * GAMING
             * ====================================================
             */
            composable(
                route = Screen.Gaming.route
            ) {

                GamingScreen()
            }

            /*
             * ====================================================
             * BILLING
             * ====================================================
             */
            composable(
                route = Screen.Billing.route
            ) {

                BillingScreen()
            }

            /*
             * ====================================================
             * INDUSTRIES
             * ====================================================
             */
            composable(
                route = Screen.Industries.route
            ) {

                IndustriesScreen(

                    onNavigateToRoute = { route ->
                        navigateToRoute(route)
                    }
                )
            }

            /*
             * ====================================================
             * COMPANY
             * ====================================================
             */
            composable(
                route = Screen.Company.route
            ) {

                CompanyScreen()
            }

            /*
             * ====================================================
             * RESOURCES
             * ====================================================
             */
            composable(
                route = Screen.Resources.route
            ) {

                ResourcesScreen(

                    onNavigateToRoute = { route ->
                        navigateToRoute(route)
                    }
                )
            }

            /*
             * ====================================================
             * EARLY ACCESS
             * ====================================================
             */
            composable(
                route = Screen.EarlyAccess.route
            ) {

                EarlyAccessScreen()
            }

            /*
             * ====================================================
             * AUTH / SIGN IN
             * ====================================================
             */
            composable(
                route = Screen.SignIn.route
            ) {

                AuthScreen(

                    onSignInSuccess = {

                        navController.navigate(
                            Screen.Profile.route
                        ) {

                            popUpTo(
                                Screen.SignIn.route
                            ) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    },

                    onContinueAsGuest = {

                        navigateBottomTab(
                            Screen.Home.route
                        )
                    }
                )
            }

            /*
             * ====================================================
             * PROFILE
             * ====================================================
             */
            composable(
                route = Screen.Profile.route
            ) {

                ProfileScreen(

                    onSignOut = {

                        navController.navigate(
                            Screen.SignIn.route
                        ) {

                            popUpTo(
                                Screen.Profile.route
                            ) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    },

                    onNavigateToSettings = {

                        navController.navigate(
                            Screen.Settings.route
                        ) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            /*
             * ====================================================
             * SETTINGS
             * ====================================================
             */
            composable(
                route = Screen.Settings.route
            ) {

                SettingsScreen()
            }

            /*
             * ====================================================
             * SUPPORT
             * ====================================================
             */
            composable(
                route = Screen.Support.route
            ) {

                SupportScreen()
            }

            /*
             * ====================================================
             * ABOUT
             * ====================================================
             */
            composable(
                route = Screen.About.route
            ) {

                AboutScreen()
            }

            /*
             * ====================================================
             * PRIVACY
             * ====================================================
             */
            composable(
                route = Screen.Privacy.route
            ) {

                PrivacyScreen()
            }

            /*
             * ====================================================
             * TERMS
             * ====================================================
             */
            composable(
                route = Screen.Terms.route
            ) {

                TermsScreen()
            }

            /*
             * ====================================================
             * SEARCH
             * ====================================================
             */
            composable(
                route = Screen.Search.route
            ) {

                SearchScreen(

                    onNavigateToRoute = { route ->
                        navigateToRoute(route)
                    }
                )
            }

            /*
             * ====================================================
             * NOTIFICATIONS
             * ====================================================
             */
            composable(
                route = Screen.Notifications.route
            ) {

                NotificationsScreen()
            }

            /*
             * ====================================================
             * MENU
             * ====================================================
             */
            composable(
                route = Screen.Menu.route
            ) {

                MenuScreen(

                    onNavigate = { route ->
                        navigateToRoute(route)
                    },

                    onCloseMenu = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}