package net.fabricmc.LazyChunks.mixin;

import net.fabricmc.LazyChunks.LazyChunkMod;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.world.entity.item.ItemEntity;

// Sponge Includes (Mixin infrastructure)
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkHolder.class)
public class ChunkHolderMixin {
//	@Inject(at = @At("HEAD"), method = "tick", cancellable = true)
//	private void tick(CallbackInfo info) {
//		if (lazychunks.itickFrozen)
//		{
//			info.cancel();
//		}
//	}
}
