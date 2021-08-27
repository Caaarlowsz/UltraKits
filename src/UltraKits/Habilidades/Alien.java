package UltraKits.Habilidades;

import UltraKits.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import org.bukkit.scheduler.*;
import org.bukkit.*;
import java.util.concurrent.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import java.util.*;
import org.bukkit.event.player.*;

public class Alien implements Listener
{
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, ArrayList<String>> players;
    public static ArrayList<String> levitados;
    
    static {
        Alien.cooldown = new HashMap<String, Long>();
        Alien.players = new HashMap<String, ArrayList<String>>();
        Alien.levitados = new ArrayList<String>();
    }
    
    @EventHandler
    public void levitar(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (e.getAction().name().contains("RIGHT") && p.getItemInHand().getType() == Material.ENDER_PORTAL_FRAME && Main.alien.contains(p.getName())) {
            e.setCancelled(true);
            p.updateInventory();
            if (!Alien.cooldown.containsKey(p.getName()) || Alien.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                if (Main.areaPvP(p)) {
                    final List<Entity> nearby = (List<Entity>)p.getNearbyEntities(10.0, 10.0, 10.0);
                    for (final Entity en : nearby) {
                        if (en instanceof Player) {
                            final Player s = (Player)en;
                            if (s == p) {
                                continue;
                            }
                            if (Main.areaPvP(s)) {
                                if (!Main.inGladiator(p) && !Main.inGladiator(s)) {
                                    Alien.levitados.add(s.getName());
                                    final Location l = new Location(s.getWorld(), s.getLocation().getX(), s.getLocation().getY() + 4.0, s.getLocation().getZ());
                                    s.setAllowFlight(true);
                                    s.setFlying(true);
                                    s.teleport(l);
                                    final ArrayList<String> all = new ArrayList<String>();
                                    all.add(s.getName());
                                    Alien.players.put(p.getName(), all);
                                    s.sendMessage(ChatColor.AQUA + "O Alien " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " levitou voc\u00ea para estudos.");
                                    s.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 1200, 0));
                                    s.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0));
                                    new BukkitRunnable() {
                                        int n = 0;
                                        
                                        public void run() {
                                            if (this.n < 5) {
                                                if (Alien.players.containsKey(p.getName())) {
                                                    final ArrayList<String> a = Alien.players.get(p.getName());
                                                    for (final String b : a) {
                                                        final Player s = Bukkit.getPlayer(b);
                                                        if (s != null) {
                                                            s.playSound(s.getLocation(), Sound.VILLAGER_HAGGLE, 1.0f, 1.0f);
                                                        }
                                                    }
                                                }
                                                ++this.n;
                                            }
                                            else {
                                                if (Alien.players.containsKey(p.getName())) {
                                                    final ArrayList<String> a = Alien.players.get(p.getName());
                                                    for (final String b : a) {
                                                        if (Alien.levitados.contains(b)) {
                                                            Alien.levitados.remove(b);
                                                        }
                                                        final Player s = Bukkit.getPlayer(b);
                                                        if (s != null) {
                                                            s.setFlying(false);
                                                            s.setAllowFlight(false);
                                                            s.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
                                                            s.sendMessage(ChatColor.YELLOW + "O estudo do Alien " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.YELLOW + " acabou seus estudos.");
                                                        }
                                                    }
                                                    Alien.players.remove(p.getName());
                                                }
                                                this.cancel();
                                            }
                                        }
                                    }.runTaskTimer(Main.plugin, 0L, 40L);
                                }
                                Alien.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(60L));
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0f, 1.0f);
                                p.sendMessage(ChatColor.BLUE + "Voce usou suas habilidades de " + ChatColor.DARK_PURPLE + "Alien");
                            }
                            else {
                                p.sendMessage(ChatColor.RED + "Voce nao pode usar esta habilidade no Gladiator.");
                            }
                        }
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
                }
                return;
            }
            p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(Alien.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos para poder usar novamente.");
        }
    }
    
    @EventHandler
    public void mover(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if ((e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ() || e.getTo().getBlockY() != e.getFrom().getBlockY()) && Alien.levitados.contains(p.getName())) {
            p.teleport(e.getFrom());
            p.sendMessage(ChatColor.RED + "Voce nao pode se mover enquanto estiver sobre dominio do Alien.");
        }
    }
    
    @EventHandler
    public void morrer(final PlayerDeathEvent e) {
        final Player p = e.getEntity();
        if (Alien.levitados.contains(p.getName())) {
            p.setFlying(false);
            p.setAllowFlight(false);
            Alien.levitados.remove(p.getName());
            for (final Map.Entry en : Alien.players.entrySet()) {
                final ArrayList<String> a = en.getValue();
                if (a.contains(p.getName())) {
                    a.remove(p.getName());
                }
                Alien.players.put(en.getKey(), a);
            }
        }
    }
    
    @EventHandler
    public void sair(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (Alien.levitados.contains(p.getName())) {
            p.setFlying(false);
            p.setAllowFlight(false);
            Alien.levitados.remove(p.getName());
            for (final Map.Entry en : Alien.players.entrySet()) {
                final ArrayList<String> a = en.getValue();
                if (a.contains(p.getName())) {
                    a.remove(p.getName());
                }
                Alien.players.put(en.getKey(), a);
            }
        }
    }
}
