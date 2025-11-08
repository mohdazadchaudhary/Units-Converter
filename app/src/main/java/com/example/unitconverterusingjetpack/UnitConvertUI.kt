package com.example.unitconverterusingjetpack

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UnitConvertUI() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }

    var inputUnit by remember { mutableStateOf("Select Unit") }
    var outputUnit by remember { mutableStateOf("Select Unit") }

    var isInputExpanded by remember { mutableStateOf(false) }
    var isOutputExpanded by remember { mutableStateOf(false) }

    var inputConversionFactor by remember { mutableStateOf(1.0) }
    var outputConversionFactor by remember { mutableStateOf(1.0) }

    fun convertUnits() {
        val input = inputValue.toDoubleOrNull()
        if (input != null && inputUnit != "Select Unit" && outputUnit != "Select Unit") {
            val result = (input * inputConversionFactor / outputConversionFactor)
            outputValue = "%.2f".format(result)
        } else {
            outputValue = ""
        }
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

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            label = { Text(text = "Enter the Value") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.width(16.dp))

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

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = if (outputValue.isNotEmpty()) "Result: $outputValue $outputUnit" else "",
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
                "Millimeter" to 0.001,
                "Centimeter" to 0.01,
                "Meter" to 1.0,
                "Kilometer" to 1000.0,
                "Inch" to 0.0254,
                "Foot" to 0.3048
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
