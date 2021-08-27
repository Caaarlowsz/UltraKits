package UltraKits.Habilidades;

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import UltraKits.*;
import java.util.*;
import org.bukkit.potion.*;
import org.bukkit.event.*;

public class Wither implements Listener
{
    @EventHandler
    public void onEntityDamage(final EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        final LivingEntity entity = (LivingEntity)e.getEntity();
        final Player p = (Player)e.getDamager();
        if (!Main.wither.contains(p.getName())) {
            return;
        }
        final Random rand = new Random();
        final int percent = rand.nextInt(100);
        if (percent <= 33) {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 120, 0));
        }
    }
}
