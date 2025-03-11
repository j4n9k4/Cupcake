package com.example.cupcake.test

import androidx.navigation.NavController
import org.junit.Assert.assertEquals

fun NavController.asserCurrentRouteName(expectedRouteName: String)
{
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}