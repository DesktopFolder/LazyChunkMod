package net.fabricmc.LazyChunks.mixin;

import net.fabricmc.LazyChunks.LazyChunkMod;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
	@Inject(at = @At("HEAD"), method = "tick", cancellable = true)
	private void tick(CallbackInfo info) {
		if (LazyChunkMod.itickFrozen)
		{
			info.cancel();
		}
	}
}
