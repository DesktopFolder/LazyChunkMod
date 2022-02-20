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

	private static int itickFreeze(CommandContext<CommandSourceStack> c) {
		itickFrozen = !itickFrozen;
		var source = c.getSource();
		if (source == null)
		{
			LOGGER.info("Invalid source. Your game will now crash. Try harder next time.");
		}
		LOGGER.info(source.getDisplayName().getString());
		var str = itickFrozen ? "Game is frozen. You may now break the bedrock." : "Game is not frozen. You should turn off the heat.";
		source.sendSuccess(new TextComponent(str).withStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY)), false);
		return 0 /* Toto - "This is the amount of 'something' that gets executed." Read: Not a way to subtly mess with people. For shame.*/;
	}
}
