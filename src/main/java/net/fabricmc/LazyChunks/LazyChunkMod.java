package net.fabricmc.LazyChunks;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ModInitializer;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Style;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.commands.Commands.literal;

public class LazyChunkMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("lazychunks");
    public static boolean itickFrozen = false;

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, Commands.CommandSelection arg) {
        dispatcher.register(literal("itick")
                .then(literal("freeze").executes(LazyChunkMod::itickFreeze))
                .then(literal("status").executes(LazyChunkMod::itickStatus))
        );
    }

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        // GERONIMOOOOOOOOOOOOOOOOOOOOOO
        LOGGER.info("Initializing Lazy Chunk Mod. (By doing nothing. It's lazy, after all.)");
    }

    private static void formatQuietResponse(CommandSourceStack source, String response) {
        if (source == null) {
            LOGGER.info("Invalid source for 'formatQuietResponse'. Sounds like a you problem, but fine, we won't crash... this time.");
            return;
        }
        LOGGER.info(source.getDisplayName().getString());
        source.sendSuccess(new TextComponent(response).withStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY)), false);
    }

    private static int itickFreeze(CommandContext<CommandSourceStack> c) {
        itickFrozen = !itickFrozen;
        formatQuietResponse(c.getSource(), itickFrozen ? "Items are frozen. You may now break the bedrock." :
                "Items are not frozen. You should turn off the heat - that wasn't going to work anyways.");
        return 0 /* Toto: "This is the amount of 'something' that gets executed." Read: Not a way to subtly mess with people. For shame.*/;
    }

    private static int itickStatus(CommandContext<CommandSourceStack> c) {
        formatQuietResponse(c.getSource(), itickFrozen ? "Items are frozen... you couldn't tell? Seriously?" :
                "Items are not frozen. No, really. Pick one up and see.");
        return 0 /* Toto: "This is the amount of 'something' that gets executed." Read: Not a way to subtly mess with people. For shame.*/;
    }
}
