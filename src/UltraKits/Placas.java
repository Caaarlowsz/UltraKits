package UltraKits;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.block.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

public class Placas implements Listener
{
    String servername;
    
    public Placas() {
        this.servername = Main.plugin.getConfig().getString("ServerName");
    }
    
    @EventHandler
    public void criar(final SignChangeEvent e) {
        final Player p = e.getPlayer();
        if (e.getLine(0).equalsIgnoreCase("[" + this.servername + "]")) {
            if (!p.hasPermission("uk.placas")) {
                e.getBlock().breakNaturally();
                p.sendMessage(ChatColor.RED + "Voce nao tem permissao.");
                return;
            }
            if (e.getLine(1).equalsIgnoreCase("sopas")) {
                e.setLine(0, new StringBuilder().append(ChatColor.ITALIC).append(ChatColor.BOLD).append("[").append(this.servername).append("]").toString());
                e.setLine(1, new StringBuilder().append(ChatColor.GOLD).append(ChatColor.BOLD).append("Sopas").toString());
            }
            else if (e.getLine(1).equalsIgnoreCase("pocoes")) {
                e.setLine(0, new StringBuilder().append(ChatColor.ITALIC).append(ChatColor.BOLD).append("[").append(this.servername).append("]").toString());
                e.setLine(1, new StringBuilder().append(ChatColor.LIGHT_PURPLE).append(ChatColor.BOLD).append("Pocoes").toString());
            }
            else if (e.getLine(1).equalsIgnoreCase("potes")) {
                e.setLine(0, new StringBuilder().append(ChatColor.ITALIC).append(ChatColor.BOLD).append("[").append(this.servername).append("]").toString());
                e.setLine(1, new StringBuilder().append(ChatColor.DARK_RED).append(ChatColor.BOLD).append("Potes").toString());
            }
            else if (e.getLine(1).equalsIgnoreCase("cogumelos")) {
                e.setLine(0, new StringBuilder().append(ChatColor.ITALIC).append(ChatColor.BOLD).append("[").append(this.servername).append("]").toString());
                e.setLine(1, new StringBuilder().append(ChatColor.AQUA).append(ChatColor.BOLD).append("Cogumelos").toString());
            }
            else if (e.getLine(1).equalsIgnoreCase("reparar")) {
                e.setLine(0, new StringBuilder().append(ChatColor.ITALIC).append(ChatColor.BOLD).append("[").append(this.servername).append("]").toString());
                e.setLine(1, new StringBuilder().append(ChatColor.GREEN).append(ChatColor.BOLD).append("Reparar").toString());
            }
        }
    }
    
    @EventHandler
    public void clicar(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN)) {
            final Sign s = (Sign)e.getClickedBlock().getState();
            if (s.getLine(0).equalsIgnoreCase(new StringBuilder().append(ChatColor.ITALIC).append(ChatColor.BOLD).append("[").append(this.servername).append("]").toString())) {
                if (ChatColor.stripColor(s.getLine(1)).equalsIgnoreCase("Sopas")) {
                    final Inventory sop = Bukkit.createInventory((InventoryHolder)p, 54, ChatColor.BLUE + "Sopas Gratis");
                    for (int i = 0; i < sop.getSize(); ++i) {
                        sop.setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
                    }
                    p.openInventory(sop);
                }
                else if (ChatColor.stripColor(s.getLine(1)).equalsIgnoreCase("Pocoes")) {
                    final Inventory sop = Bukkit.createInventory((InventoryHolder)p, 36, ChatColor.BLUE + "Pocoes Gratis");
                    for (int i = 0; i < sop.getSize(); ++i) {
                        final ItemStack potion = new ItemStack(Material.POTION);
                        potion.setDurability((short)16421);
                        sop.setItem(i, potion);
                    }
                    p.openInventory(sop);
                }
                else if (ChatColor.stripColor(s.getLine(1)).equalsIgnoreCase("Potes")) {
                    final Inventory sop = Bukkit.createInventory((InventoryHolder)p, 36, ChatColor.BLUE + "Potes Gratis");
                    for (int i = 0; i < sop.getSize(); ++i) {
                        final ItemStack potion = new ItemStack(Material.BOWL, 64);
                        sop.setItem(i, potion);
                    }
                    p.openInventory(sop);
                }
                else if (ChatColor.stripColor(s.getLine(1)).equalsIgnoreCase("Cogumelos")) {
                    final Inventory sop = Bukkit.createInventory((InventoryHolder)p, 36, ChatColor.BLUE + "Cogumelos Gratis");
                    for (int i = 0; i < 18; ++i) {
                        final ItemStack potion = new ItemStack(Material.BROWN_MUSHROOM, 64);
                        sop.setItem(i, potion);
                    }
                    for (int i = 18; i < 36; ++i) {
                        final ItemStack potion = new ItemStack(Material.RED_MUSHROOM, 64);
                        sop.setItem(i, potion);
                    }
                    p.openInventory(sop);
                }
                else if (ChatColor.stripColor(s.getLine(1)).equalsIgnoreCase("Reparar")) {
                    ItemStack[] contents;
                    for (int length = (contents = p.getInventory().getContents()).length, j = 0; j < length; ++j) {
                        final ItemStack is = contents[j];
                        try {
                            is.setDurability((short)0);
                        }
                        catch (NullPointerException ex) {}
                    }
                    ItemStack[] armorContents;
                    for (int length2 = (armorContents = p.getEquipment().getArmorContents()).length, k = 0; k < length2; ++k) {
                        final ItemStack is = armorContents[k];
                        try {
                            is.setDurability((short)0);
                        }
                        catch (NullPointerException ex2) {}
                    }
                    p.sendMessage(ChatColor.GREEN + "Seus itens foram reparados com sucesso.");
                }
            }
        }
    }
}
