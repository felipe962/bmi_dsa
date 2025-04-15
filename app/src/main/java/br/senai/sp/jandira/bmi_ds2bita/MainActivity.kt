package br.senai.sp.jandira.bmi_ds2bita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.bmi_ds2bita.screens.BMIResultScreen
import br.senai.sp.jandira.bmi_ds2bita.screens.HomeScreen
import br.senai.sp.jandira.bmi_ds2bita.screens.UserDataScreen
import br.senai.sp.jandira.bmi_ds2bita.ui.theme.BMIDS2BITATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            BMIDS2BITATheme {
                val controleDeNavegacao = rememberNavController()
                NavHost(
                    navController = controleDeNavegacao,
                    startDestination = "home"
                ){
                    composable(route = "home"){ HomeScreen(controleDeNavegacao) }
                    composable(route = "user_data"){ UserDataScreen(controleDeNavegacao) }
                    composable(route = "bmi_result"){ BMIResultScreen(controleDeNavegacao) }
                }
            }
        }
    }
}

