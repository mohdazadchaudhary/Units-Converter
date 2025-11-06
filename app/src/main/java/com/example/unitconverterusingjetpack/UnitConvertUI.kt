package com.example.unitconverterusingjetpack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UnitConvertUI() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }

    var inputUnit by remember { mutableStateOf("Input") }
    var outputUnit by remember { mutableStateOf("Output") }

    var isInputExpanded by remember { mutableStateOf(false) }
    var isOutputExpanded by remember { mutableStateOf(false) }
    var inputConversionFactor by remember { mutableStateOf(1.0) }
    var outputConversionFactor by remember { mutableStateOf(1.0) }

    fun convertUnits() {
        val input = inputValue.toDoubleOrNull() ?: 0.0
        val result = ((input * inputConversionFactor / outputConversionFactor) * 100).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.padding(32.dp))

        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
            },
            label = { Text(text = "Enter the Value") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DropdownButton(
                label = inputUnit,
                expended = isInputExpanded,
                onExpandedChange = { isInputExpanded = it },
                onOptionSelected = { unit, factor ->
                    inputUnit = unit
                    inputConversionFactor = factor
                    convertUnits()
                }
            )

            Spacer(modifier = Modifier.padding(16.dp))

            DropdownButton(
                label = outputUnit,
                expended = isOutputExpanded,
                onExpandedChange = { isOutputExpanded = it },
                onOptionSelected = { unit, factor ->
                    outputUnit = unit
                    outputConversionFactor = factor
                    convertUnits()
                }
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun DropdownButton(
    label: String,
    expended: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onOptionSelected: (String, Double) -> Unit
) {
    Box {
        Button(onClick = { onExpandedChange(!expended) }) {
            Text(text = label)
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.rotate(if (expended) 180f else 0f)
            )
        }

        DropdownMenu(expanded = expended, onDismissRequest = { onExpandedChange(false) }) {
            listOf(
                "Centimeters" to 0.01,
                "Meter" to 1.0,
                "Foot" to 0.3048,
                "Inch" to 0.0254,
                "Kilometer" to 1000.0,
                "Millimeters" to 0.001,
            ).forEach { (unit, factor) ->
                DropdownMenuItem(
                    text = { Text(unit) },
                    onClick = {
                        onExpandedChange(false)
                        onOptionSelected(unit, factor)
                    }
                )
            }
        }
    }
}
