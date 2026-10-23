package com.example.lab_07

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DriverScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()



    // Verify we can go from HomeScreen to DriversScreen
    @Test
    fun navigate_from_home_to_drivers_screen() {
        composeTestRule
            .onNodeWithTag("driverButton") // must match the tag on HomeScreen
            .performClick()

        composeTestRule
            .onNodeWithText("Driver Screen")
            .assertExists()
    }


    // Verify we can add a driver and it is displayed
    @Test
    fun add_driver_and_verify_it_is_displayed() {
        composeTestRule
            .onNodeWithTag("driverButton")
            .performClick()

        composeTestRule
            .onNodeWithTag("driverTextField")
            .performTextInput("Stephen")

        composeTestRule
            .onNodeWithTag("addDriverButton")
            .performClick()

        composeTestRule
            .onNodeWithText("Stephen")
            .assertExists()
    }

    // Verify we can clear the drivers from the list
    @Test
    fun clear_button_removes_all_drivers() {
        composeTestRule
            .onNodeWithTag("driverButton")
            .performClick()

        // add first driver
        composeTestRule
            .onNodeWithTag("driverTextField")
            .performTextInput("Random")
        composeTestRule
            .onNodeWithTag("addDriverButton")
            .performClick()

        // add second driver
        composeTestRule
            .onNodeWithTag("driverTextField")
            .performTextInput("Cat")
        composeTestRule
            .onNodeWithTag("addDriverButton")
            .performClick()

        composeTestRule.onNodeWithText("Random").assertExists()
        composeTestRule.onNodeWithText("Cat").assertExists()

        // clear the list
        composeTestRule
            .onNodeWithText("Clear")
            .performClick()

        composeTestRule.onNodeWithText("Cat").assertDoesNotExist()
        composeTestRule.onNodeWithText("Random").assertDoesNotExist()
    }

    // Verify the back button returns to HomeScreen
    @Test
    fun back_button_returns_to_home_screen() {
        composeTestRule
            .onNodeWithTag("driverButton")
            .performClick()

        composeTestRule
            .onNodeWithTag("backButtonInDriver")
            .performClick()

        composeTestRule
            .onNodeWithText("FormulaZero Home")
            .assertExists()
    }
}