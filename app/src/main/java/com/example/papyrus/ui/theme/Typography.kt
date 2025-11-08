package com.example.papyrus.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.papyrus.R

// Local font family using TTF files
val literataFamily = FontFamily(
    Font(R.font.literata_regular, FontWeight.Normal),
    Font(R.font.literata_bold, FontWeight.Bold)
)

// Custom typography for your vintage app
val PapyrusTypography = Typography(
    headlineMedium = TextStyle(
        fontFamily = literataFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = literataFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    // Add more as desired!
)