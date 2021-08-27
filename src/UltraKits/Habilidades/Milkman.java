package UltraKits.Habilidades;

import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.event.player.*;
import UltraKits.*;
import org.bukkit.potion.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Milkman implements Listener
{
    private transient HashMap<ItemStack, Integer> cooldown;
    public int maxUses;
    public String milkbucketName;
    public String[] potionEffects;
    
    public Milkman() {
        this.cooldown = new HashMap<ItemStack, Integer>();
        this.maxUses = 5;
        this.milkbucketName = "Milkman's Bucket";
        this.potionEffects = new String[] { "REGENERATION 900 0", "FIRE_RESISTANCE 900 0", "SPEED 900 0" };
    }
    
    @EventHandler
    public void onConsume(final PlayerItemConsumeEvent event) {
        final ItemStack item = event.getItem();
        final Player p = event.getPlayer();
        if (Main.milkman.contains(p.getName())) {
            String[] potionEffects;
            for (int length = (potionEffects = this.potionEffects).length, i = 0; i < length; ++i) {
                final String string = potionEffects[i];
                final String[] effect = string.split(" ");
                final PotionEffect potionEffect = new PotionEffect(PotionEffectType.getByName(effect[0].toUpperCase()), Integer.parseInt(effect[1]), Integer.parseInt(effect[2]));
                p.addPotionEffect(potionEffect, true);
            }
            if (!this.cooldown.containsKey(item)) {
                this.cooldown.put(item, 0);
            }
            this.cooldown.put(item, this.cooldown.get(item) + 1);
            if (this.cooldown.get(item) == this.maxUses) {
                this.cooldown.remove(item);
                event.setCancelled(true);
                p.setItemInHand(new ItemStack(Material.BUCKET, item.getAmount(), item.getDurability()));
            }
            else {
                event.setCancelled(true);
            }
        }
    }
}
