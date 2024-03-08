package com.example.merlinproject.ui.features.campus.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.merlinproject.R
import com.example.merlinproject.ui.theme.MerlinProjectIcons
import com.example.merlinproject.ui.theme.lexendFontFamily

@Composable
fun CardContactCampus(
    modifier: Modifier = Modifier,
    campusAddress: String = "",
    clickEmail: () -> Unit ,
    clickPhone: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_medium_material),
                end = dimensionResource(id = R.dimen.padding_medium_material),
            )
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                Row(verticalAlignment = Alignment.CenterVertically, content = {
                    Text(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.padding_min_material))
                            .weight(1f),
                        text = stringResource(id = R.string.contact_text),
                        fontFamily = lexendFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                )
                Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_min_material)),
                    verticalAlignment = Alignment.CenterVertically, content = {
                        Icon(
                            modifier = Modifier
                                .width(dimensionResource(id = R.dimen.icon_size_card))
                                .height(dimensionResource(id = R.dimen.icon_size_card)),
                            imageVector = MerlinProjectIcons.MapsCampusIcon,
                            tint = MaterialTheme.colorScheme.tertiary,
                            contentDescription = stringResource(
                                id = R.string.icons_default_description
                            )
                        )

                        Text(
                            text = campusAddress,
                            modifier = Modifier.padding(
                                dimensionResource(id = R.dimen.padding_min_material)
                            ),
                            textAlign = TextAlign.Start,
                            fontFamily = lexendFontFamily,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                )
                Row(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_min_material)),
                    verticalAlignment = Alignment.Bottom
                ) {
                    OutlinedButton(
                        onClick = {
                            clickEmail()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = MerlinProjectIcons.EmailCampusIcon,
                            contentDescription = stringResource(id = R.string.icons_default_description)
                        )
                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.width_medium_material)))
                        Text(
                            text = stringResource(id = R.string.email_button_text),
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.width_medium_material)))
                    Button(
                        onClick = {
                            clickPhone()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = MerlinProjectIcons.PhoneCampusIcon,
                            contentDescription = stringResource(id = R.string.icons_default_description)
                        )
                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.width_medium_material)))
                        Text(
                            text = stringResource(id = R.string.phone_button_text),
                            fontFamily = lexendFontFamily,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
            }
        )
    }
}


