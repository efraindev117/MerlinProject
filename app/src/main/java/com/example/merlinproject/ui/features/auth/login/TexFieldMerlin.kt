import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merlinproject.ui.theme.lexendFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldMerlin(
    modifier: Modifier = Modifier,
    value: String,
    singleLine: Boolean = true,
    supportingText: (@Composable () -> Unit),
    label: String,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    leadingIcon: ImageVector,
    trailingIcon: (@Composable () -> Unit),
    onValueChange: (String) -> Unit,
    validateField: () -> Unit = {},

    ) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        maxLines = maxLines,
        singleLine = singleLine,
        supportingText = { supportingText() },
        onValueChange = {
            onValueChange(it)
            validateField()
        },
        label = {
            Text(text = label,
                fontFamily = lexendFontFamily,
                fontWeight = FontWeight.Normal) },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        trailingIcon = {
            trailingIcon()
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(

            //textColor = Color.Gray,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.LightGray,
            unfocusedLeadingIconColor = Color.Black,
            focusedLeadingIconColor = Color.Black,
            disabledLabelColor = Color.Black,
            focusedLabelColor = Color.Black,
            errorLabelColor = Color.Red
        ),
    )
}
