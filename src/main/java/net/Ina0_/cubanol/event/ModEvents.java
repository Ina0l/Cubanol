package net.Ina0_.cubanol.event;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Cubanol.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void entityTickEvent(EntityTickEvent.Post event){
        Entity entity = event.getEntity();
        if(entity instanceof Player player){
            if(player.getPersistentData().contains(Cubanol.MOD_ID + ":wire_selected_block")){
                if (!player.getMainHandItem().is(ModItems.WIRE)) {
                    player.getPersistentData().remove(Cubanol.MOD_ID + ":wire_selected_block");
                }
            }
        }
    }
}
