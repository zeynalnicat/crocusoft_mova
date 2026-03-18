package com.example.crocusoft_mova.core


import com.example.crocusoft_mova.ui.navigation.AppRoutes

data class BottomSheetNavigations(
    val route: AppRoutes.DashboardRoute,
    val title: String,
    val icon: Int,
) {
    companion object {
        val navigations = listOf(
            BottomSheetNavigations(
                AppRoutes.DashboardRoute.Home,
                "Home",
                Drawables.home
            ),
            BottomSheetNavigations(
                route = AppRoutes.DashboardRoute.Explore,
                title = "Explore",
                icon = Drawables.explore
            ),
            BottomSheetNavigations(
                route = AppRoutes.DashboardRoute.MyList,
                title = "My List",
                icon = Drawables.my_list
            ),
            BottomSheetNavigations(
                route = AppRoutes.DashboardRoute.Download,
                title = "Download",
                icon = Drawables.download
            ),
            BottomSheetNavigations(
                route = AppRoutes.DashboardRoute.Profile,
                title = "Profile",
                icon = Drawables.profile
            ),

            )

    }
}
