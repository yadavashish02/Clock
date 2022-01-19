package com.hitmeows.clock.presentation

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlin.math.cos
import kotlin.math.sin


@Preview(showBackground = true)
@Composable
fun Clock(viewModel: ClockScreenViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    var prevMin = -1
    var prevHrs = -1
    var isHandsVisible by remember {
        mutableStateOf(true)
    }


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Box(
            modifier = Modifier.fillMaxSize(0.9f),
            contentAlignment = Alignment.Center,
        ) {

            var y by remember {
                mutableStateOf(sin(state.sec.toFloat()))
            }
            var x by remember {
                mutableStateOf(cos(state.sec.toFloat()))
            }

            Button(onClick = { /*TODO*/ }, modifier = Modifier.offset()) {

            }

            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    Color.Cyan
                        .copy(
                            1f,
                            0.4f,
                            0.4f,
                            0.6f
                        )
                )
            }

            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    Color.Cyan
                        .copy(
                            0.8f,
                            0.5f,
                            0.5f,
                            0.7f
                        ),
                    radius = size.minDimension/2.5f
                )
            }


            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    Color.Cyan.copy(0.8f, 0.5f, 0.8f, 0.9f),
                    (size.minDimension / 2.5f) - 2 * size.minDimension / 25.0f,
                    blendMode = BlendMode.Softlight
                )
            }


            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    Color.Cyan.copy(0.8f, 0.7f, 0.8f, 0.9f),
                    (size.minDimension / 2.5f) - 4 * size.minDimension / 25.0f,
                    blendMode = BlendMode.Softlight
                )
            }

            Button(shape = CircleShape,
                onClick = {
                isHandsVisible = !isHandsVisible
                prevHrs = -1
                prevMin = -1
            }
            ) {
                Text(text = "meow")
            }

            
            if(isHandsVisible) {
                SecCircle(state = state)
                if(prevMin!=state.min) {
                    prevMin = state.min
                    MinCircle(state = state)
                }
                if(prevHrs!=state.hrs) {
                    prevHrs = state.hrs
                    HrsCircle(state = state, format = 12)
                }
            }
        }
    }
}


@Composable
fun SecCircle(state: ClockState) {
    val angle = (360f * state.sec) / 60
    var color by remember { mutableStateOf(Color.Red)}
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        rotate(angle) {
            drawCircle(
                color.copy(0.3f),
                size.minDimension / 37.5f,
                center.plus(
                    Offset(0f, size.minDimension / -2.5f)
                )
            )
        }
    }
}

@Composable
fun MinCircle(state: ClockState) {
    val angle = (360f * state.min) / 60

    Canvas(modifier = Modifier.fillMaxSize()) {
        rotate(angle) {
            drawCircle(
                Color.DarkGray.copy(0.75f),
                size.minDimension / 25f,
                center.plus(Offset(0f, (size.minDimension / -2.5f) + 2 * size.minDimension / 25.0f))
            )
        }
    }
}

@Composable
fun HrsCircle(state: ClockState, format: Int) {
    val angle = (360f * state.hrs) / format

    Canvas(modifier = Modifier.fillMaxSize()) {
        rotate(angle) {
            drawCircle(
                Color.DarkGray,
                size.minDimension / 31.25f,
                center.plus(Offset(0f, (size.minDimension / -2.5f) + 4 * size.minDimension / 25.0f))
            )
        }
    }
}