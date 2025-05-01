package com.markettwits.siberspokedex.items.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.markettwits.core_ui.items.extensions.noRippleClickable
import com.markettwits.core_ui.items.theme.FontNunito
import com.markettwits.siberspokedex.items.api.FilterType
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import siberspokedex.components.pokedex.items.impl.generated.resources.*

@Composable
fun PokemonListTopBar(
    selectedFilters: Set<FilterType>,
    onFilterToggle: (FilterType) -> Unit,
    onClickAllToggle: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 16.dp)
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "SibersPokedex with \uD83D\uDC95",
            color = MaterialTheme.colorScheme.outline,
            fontFamily = FontNunito.extraBold(),
            fontSize = 24.sp,
        )
        HorizontalDivider()
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            PokemonFilterItem(
                title = "All",
                isSelected = selectedFilters.isEmpty(),
                onClick = onClickAllToggle,
                selectedImage = Res.drawable.ic_all_active,
                unSelectedImage = Res.drawable.ic_all_disabled,
            )

            PokemonFilterItem(
                title = "Attack",
                isSelected = selectedFilters.contains(FilterType.ATTACK),
                onClick = { onFilterToggle(FilterType.ATTACK) },
                selectedImage = Res.drawable.ic_attack_active,
                unSelectedImage = Res.drawable.ic_attack_disabled,
            )

            PokemonFilterItem(
                title = "Defense",
                isSelected = selectedFilters.contains(FilterType.DEFENSE),
                onClick = { onFilterToggle(FilterType.DEFENSE) },
                selectedImage = Res.drawable.ic_defense_active,
                unSelectedImage = Res.drawable.ic_defense_disabled,
            )

            PokemonFilterItem(
                title = "HP",
                isSelected = selectedFilters.contains(FilterType.HP),
                onClick = { onFilterToggle(FilterType.HP) },
                selectedImage = Res.drawable.ic_hp_active,
                unSelectedImage = Res.drawable.ic_hp_disabled,
            )
        }
    }


}

@Composable
private fun PokemonFilterItem(
    modifier: Modifier = Modifier,
    title: String,
    selectedImage: DrawableResource,
    unSelectedImage: DrawableResource,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val animatedTextColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
        label = "textColorAnimation"
    )

    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1f,
        animationSpec = spring(dampingRatio = 0.4f, stiffness = 200f),
        label = "scaleAnimation"
    )

    val image = painterResource(if (isSelected) selectedImage else unSelectedImage)

    Column(
        modifier = modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .noRippleClickable(onClick)
            .animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .scale(scale)
        ) {
            Image(
                modifier = Modifier.size(54.dp),
                painter = image,
                contentDescription = title
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = FontNunito.bold(),
            color = animatedTextColor,
            modifier = Modifier.padding(bottom = 2.dp)
        )

        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(50)
                    )
                    .animateContentSize()
            )
        }
    }
}
