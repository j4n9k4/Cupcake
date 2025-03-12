package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.R

class CupcakeOrderScreenTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent()
    {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        flavors.forEach{ flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, subtotal)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()

        composeTestRule.onNodeWithText("Vanilla").performClick()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }

    @Test
    fun selectOptionScreen_clickOption_nextButtonEnabled()
    {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        composeTestRule.onNodeWithText("Vanilla").performClick()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()

    }
}