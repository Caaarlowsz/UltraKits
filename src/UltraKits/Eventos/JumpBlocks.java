package UltraKits.Eventos;

import java.util.*;
import org.bukkit.event.player.*;
import org.bukkit.block.*;
import org.bukkit.*;
import org.bukkit.util.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;

public class JumpBlocks implements Listener
{
    public static ArrayList<String> jump;
    
    static {
        JumpBlocks.jump = new ArrayList<String>();
    }
    
    @EventHandler
    public void onJump(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SPONGE && e.getTo().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.BEACON) {
            JumpBlocks.jump.remove(p.getName());
            p.setVelocity(p.getVelocity().setY(3.0));
            p.playSound(p.getLocation(), Sound.SLIME_WALK2, 1.0f, 1.0f);
            JumpBlocks.jump.add(p.getName());
        }
        else if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.EMERALD_BLOCK && e.getTo().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.BEACON) {
            JumpBlocks.jump.remove(p.getName());
            p.setVelocity(p.getLocation().getDirection().multiply(6.0));
            p.setVelocity(new Vector(p.getVelocity().getX(), 1.5, p.getVelocity().getZ()));
            p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 40.0f, 1.0f);
            JumpBlocks.jump.add(p.getName());
        }
    }
    
    @EventHandler
    public void onFall(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            if (e.getCause().equals((Object)EntityDamageEvent.DamageCause.FALL) && JumpBlocks.jump.contains(p.getName())) {
                e.setCancelled(true);
                JumpBlocks.jump.remove(p.getName());
            }
        }
    }
}
