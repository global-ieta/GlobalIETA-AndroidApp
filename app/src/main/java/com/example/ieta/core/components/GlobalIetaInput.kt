package com.example.ieta.core.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ieta.core.design.GlobalIETAColor
import com.example.ieta.core.design.GlobalIetaTheme
import com.example.ieta.core.design.cyanGlow

@Composable
fun GlobalIetaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String = "",
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var isFocused by remember { mutableStateOf(false) }

    val animatedBorderColor by animateColorAsState(
        targetValue = when {
            isError -> GlobalIETAColor.Error
            isFocused -> GlobalIETAColor.PrimaryCyan
            else -> GlobalIETAColor.MutedBorder
        },
        animationSpec = tween(durationMillis = 180, easing = FastOutSlowInEasing),
        label = "TextFieldBorderColor"
    )

    Column(modifier = modifier) {
        if (label != null) {
            Text(
                text = label.uppercase(),
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    color = if (isError) GlobalIETAColor.Error else GlobalIETAColor.SecondaryText
                ),
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (isFocused && !isError) {
                        Modifier.cyanGlow(
                            color = GlobalIETAColor.PrimaryCyan,
                            blurRadius = 8.dp,
                            alpha = 0.3f,
                            cornerRadius = 8.dp
                        )
                    } else Modifier
                )
                .clip(RoundedCornerShape(8.dp))
                .background(GlobalIETAColor.DeepSurface)
                .border(1.dp, animatedBorderColor, RoundedCornerShape(8.dp))
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = if (isFocused) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.MutedText,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }

                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = GlobalIetaTheme.typography.body.copy(
                                color = GlobalIETAColor.MutedText
                            )
                        )
                    }
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        textStyle = GlobalIetaTheme.typography.body.copy(
                            color = GlobalIETAColor.PrimaryText
                        ),
                        singleLine = singleLine,
                        visualTransformation = visualTransformation,
                        keyboardOptions = keyboardOptions,
                        cursorBrush = SolidColor(GlobalIETAColor.PrimaryCyan),
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged { isFocused = it.isFocused }
                    )
                }

                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        tint = GlobalIETAColor.SecondaryText,
                        modifier = Modifier
                            .size(20.dp)
                            .then(
                                if (onTrailingIconClick != null) {
                                    Modifier.clickable { onTrailingIconClick() }
                                } else Modifier
                            )
                    )
                }
            }
        }

        if (isError && errorMessage != null) {
            Text(
                text = if (errorMessage.contains("SYSTEM STATUS")) errorMessage else "SYSTEM STATUS // ERROR: $errorMessage",
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    color = GlobalIETAColor.Error
                ),
                modifier = Modifier.padding(top = 6.dp, start = 4.dp)
            )
        }
    }
}

@Composable
fun GlobalIetaDropdown(
    label: String? = null,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val animatedBorderColor by animateColorAsState(
        targetValue = if (expanded) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.MutedBorder,
        animationSpec = tween(durationMillis = 180, easing = FastOutSlowInEasing),
        label = "DropdownBorderColor"
    )

    Column(modifier = modifier) {
        if (label != null) {
            Text(
                text = label.uppercase(),
                style = GlobalIetaTheme.typography.technicalLabel.copy(
                    color = GlobalIETAColor.SecondaryText
                ),
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (expanded) {
                        Modifier.cyanGlow(
                            color = GlobalIETAColor.PrimaryCyan,
                            blurRadius = 8.dp,
                            alpha = 0.3f,
                            cornerRadius = 8.dp
                        )
                    } else Modifier
                )
                .clip(RoundedCornerShape(8.dp))
                .background(GlobalIETAColor.DeepSurface)
                .border(1.dp, animatedBorderColor, RoundedCornerShape(8.dp))
                .clickable { expanded = true }
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedOption,
                    style = GlobalIetaTheme.typography.body.copy(
                        color = GlobalIETAColor.PrimaryText
                    ),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Rounded.ArrowDropDown,
                    contentDescription = "Dropdown",
                    tint = GlobalIETAColor.PrimaryCyan
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(GlobalIETAColor.ElevatedSurface)
                    .border(1.dp, GlobalIETAColor.Border, RoundedCornerShape(8.dp))
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option,
                                style = GlobalIetaTheme.typography.body.copy(
                                    color = if (option == selectedOption) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.PrimaryText
                                )
                            )
                        },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun GlobalIetaCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    val animatedBoxColor by animateColorAsState(
        targetValue = if (checked) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.DeepSurface,
        animationSpec = tween(durationMillis = 180, easing = FastOutSlowInEasing),
        label = "CheckboxBgColor"
    )

    val animatedBorderColor by animateColorAsState(
        targetValue = if (checked) GlobalIETAColor.PrimaryCyan else GlobalIETAColor.MutedBorder,
        animationSpec = tween(durationMillis = 180, easing = FastOutSlowInEasing),
        label = "CheckboxBorderColor"
    )

    Row(
        modifier = modifier.clickable { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(animatedBoxColor)
                .border(1.dp, animatedBorderColor, RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = null,
                    tint = GlobalIETAColor.PrimaryBg,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = label,
            style = GlobalIetaTheme.typography.body.copy(
                color = GlobalIETAColor.PrimaryText
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF020812)
@Composable
fun GlobalIetaInputPreview() {
    GlobalIetaTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            GlobalIetaTextField(
                value = "user@domain.io",
                onValueChange = {},
                label = "System Credentials"
            )
            Spacer(modifier = Modifier.height(12.dp))
            GlobalIetaDropdown(
                label = "Select Vertical",
                selectedOption = "Gaming & Interactive",
                options = listOf("Gaming & Interactive", "EduTech VR", "Aviation & Logistics"),
                onOptionSelected = {}
            )
            Spacer(modifier = Modifier.height(12.dp))
            GlobalIetaCheckbox(
                checked = true,
                onCheckedChange = {},
                label = "I agree to Early Access terms & conditions"
            )
        }
    }
}
