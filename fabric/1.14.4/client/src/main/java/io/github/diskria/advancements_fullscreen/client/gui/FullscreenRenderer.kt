package io.github.diskria.advancements_fullscreen.client.gui

import io.github.diskria.advancements_fullscreen.client._AdvancementsScreen
import net.minecraft.client.gui.GuiComponent

object FullscreenRenderer {

    private const val ATLAS_WIDTH: Int = 256
    private const val ATLAS_HEIGHT: Int = 256

    fun render(x: Int, y: Int, w: Int, h: Int) {
        val tw = _AdvancementsScreen.WINDOW_WIDTH
        val th = _AdvancementsScreen.WINDOW_HEIGHT

        val shadow = 6

        val t = _AdvancementsScreen.WINDOW_INSIDE_X + shadow
        val l = _AdvancementsScreen.WINDOW_INSIDE_X + shadow
        val r = _AdvancementsScreen.WINDOW_INSIDE_Y + shadow
        val b = _AdvancementsScreen.WINDOW_INSIDE_X + shadow

        val ur = tw - l
        val vb = th - b

        draw(x, y, 0, 0, t, r, t, r)
        draw(x + t, y, t, 0, 1, r, w - t - l, r)
        draw(x + w - l, y, ur, 0, l, r, l, r)
        draw(x + w - l, y + r, ur, r, l, 1, l, h - r - b)
        draw(x + w - l, y + h - b, ur, vb, l, b, l, b)
        draw(x + t, y + h - b, t, vb, 1, b, w - t - l, b)
        draw(x, y + h - b, 0, vb, t, b, t, b)
        draw(x, y + r, 0, r, t, 1, t, h - r - b)
    }

    private fun draw(x: Int, y: Int, u: Int, v: Int, rw: Int, rh: Int, w: Int, h: Int) {
        GuiComponent.blit(
            x,
            y,
            w,
            h,
            u.toFloat(),
            v.toFloat(),
            rw,
            rh,
            ATLAS_WIDTH,
            ATLAS_HEIGHT
        )
    }
}
