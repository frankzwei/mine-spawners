package me.katanya04.minespawners.config;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

/**
 * A square button with an image of a pickaxe, upon clicking toggles the blacklisted state of that pickaxe
 */
@Environment(EnvType.CLIENT)
public class PickaxeButton extends Button {

    public final Item pickaxe;
    public final Screen screen;

    public PickaxeButton(int x, int y, int size, Item pickaxe, Screen screen) {
        super(x, y, size, size, net.minecraft.network.chat.Component.empty(),
                self -> {
                    List<String> blacklistedPickaxes = SimpleConfig.BLACKLISTED_PICKAXES.getValue();
                    if (blacklistedPickaxes.contains(pickaxe.toString())) {
                        blacklistedPickaxes.remove(pickaxe.toString());
                    } else {
                        blacklistedPickaxes.add(pickaxe.toString());
                    }

                    SimpleConfig.BLACKLISTED_PICKAXES.setValue(blacklistedPickaxes);
                },

                supplier -> Component.literal(pickaxe.getDefaultInstance().getHoverName().toString())
        );

        this.pickaxe = pickaxe;
        this.screen = screen;
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float deltaTicks) {
        int itemX = this.getX() + (this.width - 16) / 2;
        int itemY = this.getY() + (this.height - 16) / 2;
        graphics.item(this.pickaxe.getDefaultInstance(), itemX, itemY);
        if (this.isHovered()) {
            graphics.setTooltipForNextFrame(this.pickaxe.getDefaultInstance().getHoverName(), itemX, itemY);
        }

        if (SimpleConfig.BLACKLISTED_PICKAXES.contains(pickaxe.toString())) {
            graphics.text(this.screen.getFont(), Component.literal("X").withStyle(ChatFormatting.BOLD), this.getX(), this.getY(), 0xFFFF0000, true);
        }
    }
}