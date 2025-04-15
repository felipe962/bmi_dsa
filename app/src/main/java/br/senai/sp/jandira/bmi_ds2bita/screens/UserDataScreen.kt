package br.senai.sp.jandira.bmi_ds2bita.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.bmi_ds2bita.R

@Composable
fun UserDataScreen(controleDeNavegacao: NavHostController?) {

    val ageState = remember {
        mutableStateOf("")
    }

    val weightState = remember {
        mutableStateOf("")
    }

    val heightState = remember {
        mutableStateOf("")
    }

    val selectedColorState = remember {
        mutableStateOf(Color(0xFF9C27B0))
    }

    val unselectedColorState = remember {
        mutableStateOf(Color.LightGray)
    }

    val isMaleClicked = remember {
        mutableStateOf(false)
    }

    val isFemaleClicked = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val userFile = context
        .getSharedPreferences("user_file", Context.MODE_PRIVATE)

    val userName = userFile.getString("user_name", "User not found")

    val editor = userFile.edit()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFF9C27B0),
                        Color(0xFFE91E63),
                    )
                )
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "${stringResource(R.string.hi)}, $userName!",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        horizontal = 32.dp,
                        vertical = 32.dp
                    )
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f),
                shape = RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier
                                    .size(130.dp),
                                shape = CircleShape,
                                border = BorderStroke(
                                    width = 2.dp,
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            Color.Magenta,
                                            Color.Blue,
                                            Color.Yellow
                                        )
                                    )
                                )
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.man_bb),
                                    contentDescription = ""
                                )
                            }
                            Button(
                                onClick = {
                                    isMaleClicked.value = true
                                    isFemaleClicked.value = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 8.dp,
                                        vertical = 8.dp
                                    ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isMaleClicked.value) selectedColorState.value else unselectedColorState.value
                                )
                            ) {
                                Text(
                                    text = stringResource(R.string.male),
                                    fontSize = 20.sp
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier
                                    .size(130.dp),
                                shape = CircleShape,
                                border = BorderStroke(
                                    width = 2.dp,
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            Color.Magenta,
                                            Color.Blue,
                                            Color.Yellow
                                        )
                                    )
                                )
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.woman_bb),
                                    contentDescription = ""
                                )
                            }
                            Button(
                                onClick = {
                                    isMaleClicked.value = false
                                    isFemaleClicked.value = true
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 8.dp,
                                        vertical = 8.dp
                                    ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isFemaleClicked.value) selectedColorState.value else unselectedColorState.value
                                )
                            ) {
                                Text(
                                    text = stringResource(R.string.female),
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = ageState.value,
                            onValueChange = {
                                ageState.value = it
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            label = {
                                Text(
                                    text = stringResource(R.string.age)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Numbers,
                                    contentDescription = "",
                                    tint = Color(0xFF9C27B0)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )
                        OutlinedTextField(
                            value = weightState.value,
                            onValueChange = {
                                weightState.value = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            shape = RoundedCornerShape(16.dp),
                            label = {
                                Text(
                                    text = stringResource(R.string.weight)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Balance,
                                    contentDescription = "",
                                    tint = Color(0xFF9C27B0)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )
                        OutlinedTextField(
                            value = heightState.value,
                            onValueChange = {
                                heightState.value = it
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            label = {
                                Text(
                                    text = stringResource(R.string.height)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Height,
                                    contentDescription = "",
                                    tint = Color(0xFF9C27B0)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            )
                        )
                    }
                    Button(
                        onClick = {

                            editor.putInt("user_age", ageState.value.toInt())
                            editor.putInt("user_weight", weightState.value.toInt())
                            editor.putInt("user_height", heightState.value.toInt())
                            editor.apply()

                            controleDeNavegacao?.navigate("bmi_result")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.calculate),
                            fontSize = 22.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserDataScreenPreview() {
    UserDataScreen(null)
}