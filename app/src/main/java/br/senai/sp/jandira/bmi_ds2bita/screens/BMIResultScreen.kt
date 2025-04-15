package br.senai.sp.jandira.bmi_ds2bita.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.bmi_ds2bita.R
import br.senai.sp.jandira.bmi_ds2bita.model.BmiStatus
import br.senai.sp.jandira.bmi_ds2bita.model.bmiCalculator
import br.senai.sp.jandira.bmi_ds2bita.screens.components.BmiLevels
import java.util.Locale

@Composable
fun BMIResultScreen(controleDeNavegacao: NavHostController?) {

    // Abrimos o arquivo "user_file"
    val context = LocalContext.current
    val userFile = context.getSharedPreferences(
        "user_file", Context.MODE_PRIVATE
    )

    // Extraímos os dados e guardamos em variáveis locais
    val userAge = userFile.getInt("user_age", 0)
    val userWeight = userFile.getInt("user_weight", 0)
    val userHeight = userFile.getInt("user_height", 0)

    val resultBmi = bmiCalculator(userWeight, userHeight.toDouble())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFF673AB7),
                        Color(0xFF2196F3)
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.your_result),
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f),
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp
                ),
                colors = CardDefaults
                    .cardColors(
                        containerColor = Color.White
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(130.dp),
                        colors = CardDefaults
                            .cardColors(containerColor = Color.White),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 4.dp,
                            color = resultBmi.color
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            val bmiValue = resultBmi.bmiValues.second
                            Text(
                                text = String.format(Locale.getDefault(), "%.1f", bmiValue),
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Text(
                        text = resultBmi.bmiValues.first,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                    Card(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(80.dp),
                        colors = CardDefaults
                            .cardColors(
                                containerColor = Color(0xFFaaaaaa)
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.age)
                                )
                                Text(
                                    text = "$userAge",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            VerticalDivider(modifier = Modifier.fillMaxHeight())
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.weight)
                                )
                                Text(
                                    text = "$userWeight Kg",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            VerticalDivider(modifier = Modifier.fillMaxHeight())
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.height)
                                )
                                Text(
                                    text = "$userHeight cm",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    ) {
                        BmiLevels(
                            leftText = stringResource(R.string.underweight),
                            rightText = "< " + String.format(Locale.getDefault(), "%.1f", 18.5),
                            bulletBackground = colorResource(R.color.light_blue),
                            background = if (resultBmi.status == BmiStatus.UNDER_WEIGHT) colorResource(R.color.light_blue) else Color.Transparent
                        )
                        BmiLevels(
                            leftText = stringResource(R.string.normal),
                            rightText = String.format(Locale.getDefault(), "%.1f", 18.6) + " - " + String.format(Locale.getDefault(), "%.1f", 24.9),
                            bulletBackground = colorResource(R.color.light_green),
                            background = if (resultBmi.status == BmiStatus.NORMAL) colorResource(R.color.light_green) else Color.Transparent
                        )
                        BmiLevels(
                            leftText = stringResource(R.string.overweight),
                            rightText = String.format(Locale.getDefault(), "%.1f", 25.0) + " - " + String.format(Locale.getDefault(), "%.1f", 29.9),
                            bulletBackground = colorResource(R.color.yellow),
                            background = if (resultBmi.status == BmiStatus.OVER_WEIGHT) colorResource(R.color.yellow) else Color.Transparent
                        )
                        BmiLevels(
                            leftText = stringResource(R.string.obesity1),
                            rightText = String.format(Locale.getDefault(), "%.1f", 30.0) + " - " + String.format(Locale.getDefault(), "%.1f", 34.9),
                            bulletBackground = colorResource(R.color.light_orange),
                            background = if (resultBmi.status == BmiStatus.OBESITY_1) colorResource(R.color.light_orange) else Color.Transparent
                        )
                        BmiLevels(
                            leftText = stringResource(R.string.obesity2),
                            rightText = String.format(Locale.getDefault(), "%.1f", 35.0) + " - " + String.format(Locale.getDefault(), "%.1f", 39.9),
                            bulletBackground = colorResource(R.color.dark_orange),
                            background = if (resultBmi.status == BmiStatus.OBESITY_2) colorResource(R.color.dark_orange) else Color.Transparent
                        )
                        BmiLevels(
                            leftText = stringResource(R.string.obesity3),
                            rightText = "> " + String.format(Locale.getDefault(), "%.1f", 40.0),
                            bulletBackground = colorResource(R.color.red),
                            background = if (resultBmi.status == BmiStatus.OBESITY_3) colorResource(R.color.red) else Color.Transparent
                        )
                    }
                    HorizontalDivider()
                    Button(
                        onClick = {
                            controleDeNavegacao?.navigate("user_data")
                        },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.new_calculate),
                            fontSize = 20.sp
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun BMIResultScreenPreview() {
    BMIResultScreen(null)
}