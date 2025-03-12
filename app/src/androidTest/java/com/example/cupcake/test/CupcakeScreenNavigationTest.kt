package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import com.example.cupcake.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CupcakeScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController
    @Before
    fun setupCupCakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }

            CupcakeApp(navController = navController)
        }

    }
    @Test
    fun cupcakeNavHost_verifyStartDestination()
    {
        navController.asserCurrentRouteName(CupcakeScreen.Start.name)

    }

    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen()
    {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun cupcakeNavHost_clickOneCupcake_navigateToSelectFlavorScreen()
    {
        navigateToFlavorScreen()
        navController.asserCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    private fun navigateToFlavorScreen()
    {

        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.chocolate)
            .performClick()

    }

    private fun navigateToPickupScreen()
    {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()

        composeTestRule.onNodeWithText(getFormattedDate())
            .performClick()

    }

    private fun navigateToSummaryScreen()
    {
        navigateToPickupScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    private fun performNavigateUp()
    {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()

    }

    @Test
    fun cupcakeNavHost_clickUpButton_navigateBackToStartScreen()
    {
        navigateToFlavorScreen()
        performNavigateUp()
        navController.asserCurrentRouteName(CupcakeScreen.Start.name)
    }
    @Test
    fun cupcakeNavHost_clickCancelButton_navigateBackToStartScreen()
    {
        navigateToFlavorScreen()
        performCancelButtonCheck()
    }

    private fun performCancelButtonCheck()
    {
        composeTestRule.onNodeWithStringId(R.string.cancel).performClick()
        navController.asserCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_navigateToPickUpScreenFromStar()
    {
        navigateToPickupScreen()
        navController.asserCurrentRouteName(CupcakeScreen.Pickup.name)
    }

    @Test
    fun cupcakeNavHost_clickUpButton_navigateToFlavorScreen()
    {
        navigateToPickupScreen()
        performNavigateUp()
        navController.asserCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelFromPickup_navigateBackToStartScreen()
    {
        navigateToPickupScreen()
        performCancelButtonCheck()
    }

    @Test
    fun cupcakeNavHost_navigateToSummaryScreen()
    {
        navigateToSummaryScreen()
        navController.asserCurrentRouteName(CupcakeScreen.Summary.name)
    }

    @Test
    fun cupcakeNavHost_clickCancel_navigateBackToStartScreen()
    {
        navigateToSummaryScreen()
        performCancelButtonCheck()
    }


}



