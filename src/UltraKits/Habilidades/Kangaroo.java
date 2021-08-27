package UltraKits.Habilidades;

import java.util.*;
import UltraKits.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.util.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;

public class Kangaroo implements Listener
{
    ArrayList<Player> kangaroo;
    public Main plugin;
    
    public Kangaroo(final Main plugin) {
        this.kangaroo = new ArrayList<Player>();
        this.plugin = plugin;
    }
    
    public Kangaroo() {
        this.kangaroo = new ArrayList<Player>();
    }
    
    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        final Player p = event.getPlayer();
        if (p.getItemInHand().getType() == Material.FIREWORK) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                event.setCancelled(true);
            }
            if (!this.kangaroo.contains(p)) {
                if (Main.areaPvP(p)) {
                    if (!p.isSneaking()) {
                        p.setFallDistance(-5.0f);
                        final Vector vector = p.getEyeLocation().getDirection();
                        vector.multiply(0.6f);
                        vector.setY(1.2f);
                        p.setVelocity(vector);
                    }
                    else {
                        p.setFallDistance(-5.0f);
                        final Vector vector = p.getEyeLocation().getDirection();
                        vector.multiply(1.2f);
                        vector.setY(0.8);
                        p.setVelocity(vector);
                    }
                    this.kangaroo.add(p);
                }
                else {
                    p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
                }
            }
        }
    }
    
    @EventHandler
    public void onMove(final PlayerMoveEvent event) {
        final Player p = event.getPlayer();
        if (this.kangaroo.contains(p)) {
            final Block b = p.getLocation().getBlock();
            if (b.getType() != Material.AIR || b.getRelative(BlockFace.DOWN).getType() != Material.AIR) {
                this.kangaroo.remove(p);
            }
        }
    }
    
    @EventHandler
    public void onDamage(final EntityDamageEvent event) {
        final Entity e = event.getEntity();
        if (e instanceof Player) {
            final Player player = (Player)e;
            if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL && player.getInventory().contains(Material.FIREWORK) && event.getDamage() >= 7.0) {
                event.setDamage(7.0);
            }
        }
    }
}
