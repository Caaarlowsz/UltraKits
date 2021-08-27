package UltraKits.Habilidades;

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import UltraKits.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import org.bukkit.event.*;

public class Barbarian implements Listener
{
    @EventHandler
    public void onKill(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player k = e.getEntity().getKiller();
            if (Main.barbarian.contains(k.getName())) {
                if (k.getItemInHand().getType() == Material.WOOD_SWORD) {
                    k.setItemInHand(new ItemStack(Material.STONE_SWORD));
                    k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "1");
                }
                else if (k.getItemInHand().getType() == Material.STONE_SWORD) {
                    k.setItemInHand(new ItemStack(Material.IRON_SWORD));
                    k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "2");
                }
                else if (k.getItemInHand().getType() == Material.IRON_SWORD) {
                    k.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
                    k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "3");
                }
                else if (k.getItemInHand().getType() == Material.DIAMOND_SWORD) {
                    final int max = 5;
                    if (k.getItemInHand().containsEnchantment(Enchantment.DAMAGE_ALL)) {
                        final int lvl = k.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
                        if (lvl + 1 <= max) {
                            k.getItemInHand().removeEnchantment(Enchantment.DAMAGE_ALL);
                            k.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, lvl + 1);
                            k.updateInventory();
                            k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                            if (lvl + 1 == 2) {
                                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "5");
                            }
                            else if (lvl + 1 == 3) {
                                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "6");
                            }
                            else if (lvl + 1 == 4) {
                                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "7");
                            }
                            else if (lvl + 1 == 5) {
                                k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "8");
                            }
                        }
                        else {
                            k.sendMessage(ChatColor.GOLD + "Voce esta no level maximo!");
                        }
                    }
                    else {
                        k.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 1);
                        k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                        k.sendMessage(ChatColor.GREEN + "UPGRADE! Level " + ChatColor.WHITE + "4");
                    }
                }
            }
        }
    }
}
