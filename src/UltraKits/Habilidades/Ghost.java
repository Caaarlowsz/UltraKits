package UltraKits.Habilidades;

import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.event.block.*;
import UltraKits.*;
import java.util.concurrent.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;
import org.bukkit.scheduler.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;

public class Ghost implements Listener
{
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, ItemStack[]> arm;
    public static HashMap<String, ItemStack[]> inv;
    public static HashMap<String, BukkitTask> task;
    public static ArrayList<String> fantasmas;
    public static ItemStack peito;
    
    static {
        Ghost.cooldown = new HashMap<String, Long>();
        Ghost.arm = new HashMap<String, ItemStack[]>();
        Ghost.inv = new HashMap<String, ItemStack[]>();
        Ghost.task = new HashMap<String, BukkitTask>();
        Ghost.fantasmas = new ArrayList<String>();
        Ghost.peito = new ItemStack(Material.LEATHER_CHESTPLATE);
    }
    
    @EventHandler
    public void transformar(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.POTION && p.getItemInHand().getDurability() == 8238 && Main.ghost.contains(p.getName())) {
            e.setCancelled(true);
            p.updateInventory();
            if (Ghost.cooldown.containsKey(p.getName()) && Ghost.cooldown.get(p.getName()) > System.currentTimeMillis()) {
                p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(Ghost.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos para poder usar novamente.");
                return;
            }
            if (Main.areaPvP(p)) {
                Ghost.arm.put(p.getName(), p.getEquipment().getArmorContents());
                Ghost.inv.put(p.getName(), p.getInventory().getContents());
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
                p.getInventory().clear();
                p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) });
                final ItemStack cabeca = new ItemStack(Material.SKULL_ITEM);
                cabeca.setDurability((short)1);
                Ghost.peito = new ItemStack(Material.LEATHER_CHESTPLATE);
                final LeatherArmorMeta meta = (LeatherArmorMeta)Ghost.peito.getItemMeta();
                meta.setColor(Color.BLACK);
                Ghost.peito.setItemMeta((ItemMeta)meta);
                p.getEquipment().setHelmet(cabeca);
                p.getEquipment().setChestplate(Ghost.peito);
                final ItemStack foice = new ItemStack(Material.IRON_HOE);
                final ItemMeta im = foice.getItemMeta();
                im.setDisplayName(ChatColor.RED + "Seifadora de Vidas");
                foice.setItemMeta(im);
                p.getInventory().addItem(new ItemStack[] { foice });
                p.updateInventory();
                Ghost.fantasmas.add(p.getName());
                Ghost.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(80L));
                p.sendMessage(ChatColor.GREEN + "Voce se transformou em um fantasma! " + ChatColor.DARK_PURPLE + "ASSOMBRE " + ChatColor.GREEN + "seus inimigos!");
                p.playSound(p.getLocation(), Sound.AMBIENCE_CAVE, 1.0f, 1.0f);
                final BukkitTask id = new BukkitRunnable() {
                    public void run() {
                        p.getInventory().clear();
                        if (Ghost.arm.containsKey(p.getName())) {
                            p.getEquipment().setArmorContents((ItemStack[])Ghost.arm.get(p.getName()));
                            Ghost.arm.remove(p.getName());
                        }
                        if (Ghost.inv.containsKey(p.getName())) {
                            p.getInventory().setContents((ItemStack[])Ghost.inv.get(p.getName()));
                            Ghost.inv.remove(p.getName());
                        }
                        Ghost.fantasmas.remove(p.getName());
                        if (Ghost.task.containsKey(p.getName())) {
                            Ghost.task.remove(p.getName());
                        }
                        p.removePotionEffect(PotionEffectType.INVISIBILITY);
                        p.updateInventory();
                        p.sendMessage(ChatColor.GOLD + "Voce voltou a forma humana!");
                        p.playSound(p.getLocation(), Sound.ZOMBIE_UNFECT, 1.0f, 1.0f);
                    }
                }.runTaskLater(Main.plugin, 400L);
                Ghost.task.put(p.getName(), id);
                return;
            }
            p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
        }
    }
    
    @EventHandler
    public void dano(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            final Player p = (Player)e.getEntity();
            final Player d = (Player)e.getDamager();
            if (Main.ghost.contains(d.getName()) && d.getItemInHand().getType() == Material.IRON_HOE) {
                e.setDamage(e.getDamage() * 3.5);
                d.sendMessage(ChatColor.AQUA + "SUGUE A ALMA DE " + ChatColor.DARK_RED + p.getName() + ChatColor.AQUA + " !");
                d.playSound(d.getLocation(), Sound.GHAST_SCREAM, 1.0f, 1.0f);
            }
        }
    }
    
    @EventHandler
    public void dano2(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            if (Ghost.fantasmas.contains(p.getName())) {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void itens(final PlayerPickupItemEvent e) {
        if (Ghost.fantasmas.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }
    
    public static void resetGhost(final Player p) {
        if (Ghost.task.containsKey(p.getName())) {
            final BukkitTask task = Ghost.task.get(p.getName());
            task.cancel();
            Ghost.task.remove(p.getName());
        }
    }
}
