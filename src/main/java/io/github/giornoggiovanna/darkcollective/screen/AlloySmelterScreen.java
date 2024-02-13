package io.github.giornoggiovanna.darkcollective.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.event.ScreenEvent;

public class AlloySmelterScreen extends AbstractContainerScreen<AlloySmelterMenu> {

    public static final ResourceLocation TEXTURE =
            new ResourceLocation(DarkCollective.ModID, "textures/gui/alloy_smeltermk1_gui.png");

    public AlloySmelterScreen(AlloySmelterMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth)/2;
        int y = (height - imageHeight)/2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, 0, 175, 188, 256, 256);
        guiGraphics.blit(TEXTURE, x+77, y+53, 175, 0, 22, menu.getScaledBurntime());
    }
}
