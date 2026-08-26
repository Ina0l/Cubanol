package net.Ina0_.cubanol.sound;

import net.Ina0_.cubanol.Cubanol;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, Cubanol.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> DESTEMMED_GRAPES_BUCKET_EMPTY = registerSoundEvent("destemmed_grapes_bucket_empty");


    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name){
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus event){
        SOUND_EVENTS.register(event);
    }
}
