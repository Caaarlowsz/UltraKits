package UltraKits.Habilidades;

import org.bukkit.plugin.*;
import UltraKits.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import java.util.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.*;
import org.bukkit.block.*;
import org.bukkit.*;

public class Flash implements Listener
{
    public Plugin plugin;
    
    public Flash(final Main plugin) {
        this.plugin = (Plugin)plugin;
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void oss5(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        if (Main.flash.contains(p.getName())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void FlashA(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (!Main.flash.contains(p.getName())) {
            return;
        }
        if (!e.getAction().name().contains("RIGHT")) {
            return;
        }
        if (p.getItemInHand().getType() != Material.REDSTONE_TORCH_ON) {
            return;
        }
        if (Main.reload.contains(p.getName())) {
            return;
        }
        if (Main.areaPvP(p)) {
            if (!Main.inGladiator(p)) {
                Main.reload.add(p.getName());
                p.sendMessage(ChatColor.RED + "Voce so podera usar depois de 15 segundos!");
                p.getWorld().strikeLightningEffect(p.getLocation());
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                p.getEyeLocation().getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
                final Block b = p.getTargetBlock((HashSet)null, 100).getRelative(BlockFace.UP);
                final Location loc = b.getLocation();
                loc.setPitch(p.getLocation().getPitch());
                loc.setYaw(p.getLocation().getYaw());
                p.teleport(loc);
                p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0));
                p.getItemInHand().setType(Material.REDSTONE_TORCH_OFF);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        p.sendMessage(ChatColor.GREEN + "Carregado!");
                        Main.reload.remove(p.getName());
                        ItemStack[] contents;
                        for (int length = (contents = p.getInventory().getContents()).length, j = 0; j < length; ++j) {
                            final ItemStack i = contents[j];
                            if (i != null && i.getType() == Material.REDSTONE_TORCH_OFF) {
                                i.setType(Material.REDSTONE_TORCH_ON);
                            }
                        }
                    }
                }, 300L);
            }
            else {
                p.sendMessage(ChatColor.RED + "Voce nao pode usar esta habilidade no Gladiator.");
            }
        }
        else {
            p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
        }
    }
}
