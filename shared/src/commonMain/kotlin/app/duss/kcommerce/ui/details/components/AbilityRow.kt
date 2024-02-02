package app.duss.kcommerce.ui.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.duss.kcommerce.core.model.PokemonInfo
import app.duss.kcommerce.ui.utils.PokemonAbilityUtils

@Composable
internal fun AbilityRow(
    types: List<PokemonInfo.TypeResponse>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        items(types, key = { it.slot }) { typeResponse ->
            AbilityItem(
                name = typeResponse.type.name,
                containerColor = PokemonAbilityUtils.getAbilityColor(typeResponse.type.name)
            )
        }
    }
}