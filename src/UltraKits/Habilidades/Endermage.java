package UltraKits.Habilidades;

import UltraKits.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.inventory.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;

public class Endermage implements Listener
{
    public Main plugin;
    
    public Endermage(final Main plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlace(final PlayerInteractEvent event) {
        final ItemStack item = event.getItem();
        final Player p2 = event.getPlayer();
        if ((event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.LEFT_CLICK_BLOCK) || item == null || !Main.endermage.contains(p2.getName()) || event.getMaterial() != Material.PORTAL) {
            return;
        }
        event.setCancelled(true);
        final Block b = event.getClickedBlock();
        if (Main.endermage.contains(p2.getName()) && b.getTypeId() == 120) {
            return;
        }
        if (Main.areaPvP(p2)) {
            item.setAmount(0);
            if (item.getAmount() == 0) {
                event.getPlayer().setItemInHand(new ItemStack(0));
            }
            final Location portal = b.getLocation().clone().add(0.5, 0.5, 0.5);
            final Material material = b.getType();
            final byte dataValue = b.getData();
            portal.getBlock().setTypeId(120);
            final Player mager = event.getPlayer();
            for (int i = 0; i <= 5; ++i) {
                final int no = i;
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        Player[] onlinePlayers;
                        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
                            final Player p = onlinePlayers[i];
                            if (b.getTypeId() == 120 && !Main.endermage.contains(p.getName()) && p != mager.getPlayer() && Endermage.this.isEnderable(portal, p.getLocation())) {
                                final Location teleport = portal.clone().add(0.0, 0.5, 0.0);
                                if (p.getLocation().distance(portal) > 3.0) {
                                    mager.getPlayer().sendMessage(new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Jogador Puxado!").toString());
                                    mager.getPlayer().sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Voce tem 5 segundos de invencibilidade, se prepare, ou fuja!").toString());
                                    mager.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 2));
                                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 2));
                                    p.sendMessage(new StringBuilder().append(ChatColor.RED).append(ChatColor.BOLD).append("Voce Foi Puxado!").toString());
                                    p.sendMessage(ChatColor.RED + "Voce tem " + ChatColor.RED + ChatColor.BOLD + "5 segundos de invencibilidade, se prepare, ou fuja!");
                                    p.setNoDamageTicks(100);
                                    mager.getPlayer().setNoDamageTicks(100);
                                    mager.getPlayer().teleport(teleport);
                                    p.teleport(teleport);
                                    final ItemStack endermage = new ItemStack(Material.PORTAL, 1);
                                    final ItemMeta name = endermage.getItemMeta();
                                    name.setDisplayName(ChatColor.RED + "Endermage");
                                    endermage.setItemMeta(name);
                                    mager.getInventory().addItem(new ItemStack[] { endermage });
                                }
                                portal.getBlock().setTypeIdAndData(material.getId(), dataValue, true);
                            }
                        }
                        if (no == 5) {
                            final ItemStack endermage2 = new ItemStack(Material.PORTAL, 1);
                            final ItemMeta name2 = endermage2.getItemMeta();
                            name2.setDisplayName(ChatColor.RED + "Endermage");
                            endermage2.setItemMeta(name2);
                            mager.getInventory().addItem(new ItemStack[] { endermage2 });
                            portal.getBlock().setTypeIdAndData(material.getId(), dataValue, true);
                        }
                    }
                }, (long)(i * 20));
            }
            return;
        }
        p2.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
    }
    
    private boolean isEnderable(final Location portal, final Location player) {
        return Math.abs(portal.getX() - player.getX()) < 2.0 && Math.abs(portal.getZ() - player.getZ()) < 2.0 && Math.abs(portal.getY() - player.getY()) > 1.0;
    }
}
