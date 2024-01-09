package io.github.giornoggiovanna.darkcollective.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.giornoggiovanna.darkcollective.DarkCollective;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class FabricatorScreen extends AbstractContainerScreen<FabricatorMenu> {

    public static final ResourceLocation TEXTURE =
        new ResourceLocation(DarkCollective.ModID, "textures/gui/fabricatormk1_gui.png");



    public FabricatorScreen(FabricatorMenu menu, Inventory inventory, Component title) {
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
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth)/2;
        int y = (height - imageHeight)/2;

        graphics.blit(TEXTURE, x, y, 0, 0, 0, 175, 165, 256, 256);
        graphics.blit(TEXTURE, x+61, y+74,175, 0, menu.getEnergyStoredScale(), 2);

        Component text = Component.literal("Energy " + menu.getEnergy() + " / " + menu.getMaxEnergy());

        if(isHovering(62, 75, 15, 2, mouseX, mouseY)){
            graphics.renderTooltip(this.font, text, mouseX, mouseY);
        }

    }
}
