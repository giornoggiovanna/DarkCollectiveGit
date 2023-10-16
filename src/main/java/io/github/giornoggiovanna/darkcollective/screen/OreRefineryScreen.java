package io.github.giornoggiovanna.darkcollective.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class OreRefineryScreen extends AbstractContainerScreen<OreRefineryMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DarkCollective.ModID, "textures/gui/or_gui.png");

    public OreRefineryScreen(OreRefineryMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth)/2;
        int y = (height - imageHeight)/2;

        graphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(graphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics grpahics, int x, int y){
        if(menu.isCrafting()){
            grpahics.blit(TEXTURE, x+76, y+41, 221, 32, menu.getScaledProgress(), 17);
        }

    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
