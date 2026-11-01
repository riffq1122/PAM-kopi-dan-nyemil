package lat.pam.kopidannyemil.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lat.pam.kopidannyemil.data.CartState
import lat.pam.kopidannyemil.navigation.Routes
import lat.pam.kopidannyemil.ui.theme.Caramel
import lat.pam.kopidannyemil.ui.theme.Cream
import lat.pam.kopidannyemil.ui.theme.Ink
import lat.pam.kopidannyemil.ui.theme.KopiDanNyemilTheme

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    selected: String,
    onHomeClick: () -> Unit,
    onOrderClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val cartCount = CartState.totalItemCount

    val selectedIndex = when (selected) {
        Routes.HOME -> 0
        Routes.ORDER -> 1
        Routes.PROFILE -> 2
        else -> 0
    }

    val animatedIndex by animateFloatAsState(
        targetValue = selectedIndex.toFloat(),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "tabIndicatorIndex"
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .background(Cream)
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        val tabCount = 3
        val tabWidth = maxWidth / tabCount
        val pillWidth = tabWidth * 0.78f
        val pillOffsetX = (tabWidth * animatedIndex) + (tabWidth - pillWidth) / 2

        // Dynamic sliding active indicator box
        Box(
            modifier = Modifier
                .offset(x = pillOffsetX)
                .width(pillWidth)
                .height(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Caramel)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                label = "Home",
                isSelected = selected == Routes.HOME,
                onClick = onHomeClick,
                modifier = Modifier.width(tabWidth),
                badgeCount = 0
            )
            BottomBarItem(
                label = "Order",
                isSelected = selected == Routes.ORDER,
                onClick = onOrderClick,
                modifier = Modifier.width(tabWidth),
                badgeCount = cartCount
            )
            BottomBarItem(
                label = "Profile",
                isSelected = selected == Routes.PROFILE,
                onClick = onProfileClick,
                modifier = Modifier.width(tabWidth),
                badgeCount = 0
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    badgeCount: Int = 0
) {
    val interactionSource = remember { MutableInteractionSource() }

    val animatedTextColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else Ink,
        animationSpec = tween(durationMillis = 250),
        label = "animatedTextColor"
    )

    Box(
        modifier = modifier
            .height(44.dp)
            .pressScale(interactionSource, pressedScale = 0.92f)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = animatedTextColor,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }

        if (badgeCount > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-8).dp, y = 4.dp)
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) Color.White else Caramel),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (badgeCount > 99) "99+" else badgeCount.toString(),
                    color = if (isSelected) Caramel else Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBottomBarPreview() {
    KopiDanNyemilTheme {
        AppBottomBar(
            selected = Routes.HOME,
            onHomeClick = {},
            onOrderClick = {},
            onProfileClick = {}
        )
    }
}
