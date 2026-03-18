package com.example.crocusoft_mova.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.crocusoft_mova.presentation.auth.choose_interest.ChooseInterestView
import com.example.crocusoft_mova.presentation.auth.pin.PinView
import com.example.crocusoft_mova.presentation.auth.fill_profile.FillProfileView
import com.example.crocusoft_mova.presentation.auth.login_choice.LoginChoiceView
import com.example.crocusoft_mova.presentation.auth.signin.SignInView
import com.example.crocusoft_mova.presentation.auth.signup.SignUpView
import com.example.crocusoft_mova.presentation.dashboard.download.DownloadView
import com.example.crocusoft_mova.presentation.dashboard.explore.ExploreView
import com.example.crocusoft_mova.presentation.dashboard.home.HomeView
import com.example.crocusoft_mova.presentation.dashboard.movie_detail.MovieDetailView
import com.example.crocusoft_mova.presentation.dashboard.my_list.MyListView
import com.example.crocusoft_mova.presentation.dashboard.profile.ProfileView
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MainRoutes(
    innerPadding: PaddingValues,
    navController: NavHostController,
    firebaseAuth: FirebaseAuth
) {

    val currentUser = firebaseAuth.currentUser

    val startDestination =
        if (currentUser == null) AppRoutes.AuthRoute.SignChoice else AppRoutes.DashboardRoute.Pin


    NavHost(
        startDestination = startDestination,
        navController = navController,

        ) {
        composable<AppRoutes.AuthRoute.SignUp> {
            SignUpView(
                innerPadding = innerPadding,
                navController
            )
        }
        composable<AppRoutes.AuthRoute.SignIn> {
            SignInView(
                navController
            )
        }
        composable<AppRoutes.AuthRoute.SignChoice> {
            LoginChoiceView(
                navController
            )
        }

        composable<AppRoutes.AuthRoute.ChooseInterest> {
            ChooseInterestView(
                navController = navController
            )
        }

        composable<AppRoutes.AuthRoute.FillProfile> {
            FillProfileView(
                navController = navController
            )
        }

        composable<AppRoutes.AuthRoute.NewPin> {
            PinView(
                navController = navController
            )
        }

        composable<AppRoutes.DashboardRoute.Home> {
            HomeView(
                navController = navController
            )
        }

        composable<AppRoutes.DashboardRoute.Home> {
            HomeView(

                navController = navController
            )
        }

        composable<AppRoutes.DashboardRoute.Pin> {
            PinView(
                navController = navController,
                isNewPinScreen = false
            )
        }


        composable<AppRoutes.DashboardRoute.Explore> {
            ExploreView(
                navController = navController,
            )
        }


        composable<AppRoutes.DashboardRoute.MyList> {
            MyListView(
                navController = navController,
            )
        }

        composable<AppRoutes.DashboardRoute.Download> {
            DownloadView(
                navController = navController,
            )
        }

        composable<AppRoutes.DashboardRoute.Profile> {
            ProfileView(
                navController = navController,
            )
        }

        composable<AppRoutes.DashboardRoute.MovieDetail> { backStackEntry ->
            val detail = backStackEntry.toRoute<AppRoutes.DashboardRoute.MovieDetail>()
            MovieDetailView(
                navController = navController,
                movieId = detail.movieId
            )
        }


    }


}