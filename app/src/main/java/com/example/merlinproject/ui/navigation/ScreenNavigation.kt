package com.example.merlinproject.ui.navigation

import com.example.merlinproject.ui.navigation.RoutesLoginAndRegister.USER_LOGIN
import com.example.merlinproject.ui.navigation.RoutesLoginAndRegister.USER_REGISTER
import com.example.merlinproject.ui.navigation.RoutesLoginAndRegister.USER_SIGNUP
import com.example.merlinproject.ui.navigation.RoutesLoginAndRegister.WELCOME_SCREEN_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.AGENDA_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.BACHELORS_SCREEN_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.CALENDAR_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.CURSES_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.FORGET_PASSWORD_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.HELP_AND_QUESTION_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.HOME_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.LOGIN_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.ONBOARDING_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.PROFESSION_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.RATING_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.REGISTER_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.SETTINGS_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.SPLASH_SCREEN_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.TEACHER_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.TERMS_AND_CONDITIONS_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.RoutesNavigationDrawer.USER_NAVIGATION_ROUTE_NAME
import com.example.merlinproject.ui.navigation.SplashScreen.SPLASH_SCREEN

sealed class ScreensNavigation(val route: String) {

    object SplashScreen : ScreensNavigation(SPLASH_SCREEN_ROUTE_NAME)
    object WelcomeScreen: ScreensNavigation(WELCOME_SCREEN_NAVIGATION_ROUTE_NAME)
    object OnboardingScreen : ScreensNavigation(ONBOARDING_NAVIGATION_ROUTE_NAME)
    object LoginScreen: ScreensNavigation(LOGIN_NAVIGATION_ROUTE_NAME)
    object RegisterScreen: ScreensNavigation(REGISTER_NAVIGATION_ROUTE_NAME)
    object ForgetPasswordScreen: ScreensNavigation(FORGET_PASSWORD_NAVIGATION_ROUTE_NAME)
    object BachelorsScreen: ScreensNavigation(BACHELORS_SCREEN_ROUTE_NAME)

    object HomeScreen : ScreensNavigation(HOME_NAVIGATION_ROUTE_NAME)
    object AgendaScreen : ScreensNavigation(AGENDA_NAVIGATION_ROUTE_NAME)
    object CalendarScreen : ScreensNavigation(CALENDAR_NAVIGATION_ROUTE_NAME)
    object UserScreen : ScreensNavigation(USER_NAVIGATION_ROUTE_NAME)
    object TeachersScreen : ScreensNavigation(TEACHER_NAVIGATION_ROUTE_NAME)
    object RatingScreen : ScreensNavigation(RATING_NAVIGATION_ROUTE_NAME)
    object SettingsScreen : ScreensNavigation(SETTINGS_NAVIGATION_ROUTE_NAME)
    object CursesScreen : ScreensNavigation(CURSES_NAVIGATION_ROUTE_NAME)
    object ProfessionScreen : ScreensNavigation(PROFESSION_NAVIGATION_ROUTE_NAME)
    object TermsAndConditionScreen : ScreensNavigation(TERMS_AND_CONDITIONS_NAVIGATION_ROUTE_NAME)
    object HelpAndQuestionScreen : ScreensNavigation(HELP_AND_QUESTION_NAVIGATION_ROUTE_NAME)
}