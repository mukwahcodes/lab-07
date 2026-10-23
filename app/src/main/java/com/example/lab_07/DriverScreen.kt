package com.example.lab_07

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

/*
In DriversScreen.kt:
There should be a button to return to the HomeScreen
There should be text indicating that it's the DriversScreen (i.e., "FormulaZero Drivers")
There should be a text field and a button to add drivers, where drivers added will be displayed in a list format
There should be a button to clear all drivers displayed
* */

@Composable
fun DriverScreen(navController: NavController) {
    var driver by remember { mutableStateOf("") }
    val allDrivers = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // back button
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.testTag("backButtonInDriver") // for testing
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // title text for screen
        Text(
            text = "Driver Screen",
            fontSize = 30.sp
        )

        // text field and add button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = driver,
                onValueChange = {
                    driver = it // update driver to new value
                },
                label = {
                    Text("Driver")
                },
                modifier = Modifier.testTag("driverTextField") // for testing
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (driver.isNotBlank()) {
                        allDrivers.add(driver)
                        driver = ""
                    }
                },
                modifier = Modifier.testTag("addDriverButton") // for testing
            ) {
                Text("Add")
            }
        }

        // list of drivers (weight keeps the Clear button visible below it)
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(allDrivers) { name ->
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = name,
                    fontSize = 24.sp
                )
            }
        }

        // clear button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    allDrivers.clear()
                },
                modifier = Modifier.testTag("clearButton") // for testing
            ) {
                Text("Clear")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDriverScreen() {
    val navController = rememberNavController()
    DriverScreen(navController)
}