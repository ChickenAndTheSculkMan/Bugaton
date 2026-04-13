package com.sculkman.bugaton.items;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BedBlock;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.TellRawCommand;
import net.minecraft.server.command.TitleCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class ModifiedSpyglassItem extends Item {
    public ModifiedSpyglassItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack offHandStack = user.getStackInHand(Hand.OFF_HAND);
        if (offHandStack.isFood()) {
            if (offHandStack.hasNbt() && offHandStack.getNbt().getBoolean("LacedByNightmane")) {
                user.sendMessage(Text.translatable("item.bugaton.modified_spyglass.laced"), true);
            } else {
                user.sendMessage(Text.translatable("item.bugaton.modified_spyglass.none"), true);
            }
        } else {
            user.sendMessage(Text.translatable("item.bugaton.modified_spyglass.not_food"), true);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (KeybindsScreen.hasShiftDown()) {
            tooltip.add(1, Text.translatable("tooltips.bugaton.modified_spyglass.shifting"));
            tooltip.add(2, Text.translatable("tooltips.bugaton.modified_spyglass.shifting1"));
        } else {
            tooltip.add(Text.translatable("tooltips.bugaton.not_shifting"));
        }
    }
}
