package net.fabricmc.LazyChunks.mixin;

import net.fabricmc.LazyChunks.LazyChunkMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
	// Seems we need this constructor to be able to extend Entity,
	// which we need to do to be able to call super.tick()?
	public ItemEntityMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
		this.bobOffs = 0; // silence warnings
	}

	@Shadow private int age;
	@Shadow public final float bobOffs;
	protected int bonusAge = -1;

	@Inject(at = @At("HEAD"), method = "tick", cancellable = true)
	private void tick(CallbackInfo info) {
		if (LazyChunkMod.itickFrozen)
		{
			// Let's just make this look super smooth.
			if (this.bonusAge == -1)
			{
				this.bonusAge = this.age;
			}
			else {
				++this.bonusAge;
			}
			super.tick();
			info.cancel();
		}
		else if (this.bonusAge != -1) this.bonusAge = -1; // kinda ugly but it works :^)
	}

	@Inject(at = @At("HEAD"), method = "getSpin", cancellable = true)
	private void getSpin(float f, CallbackInfoReturnable<Float> cir) {
		if (LazyChunkMod.itickFrozen)
		{
			float spin = ((float)this.bonusAge + f) / 20.0f + this.bobOffs;
			cir.setReturnValue(spin);
		}
	}
}
