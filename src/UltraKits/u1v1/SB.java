package UltraKits.u1v1;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.io.*;
import org.bukkit.scoreboard.*;
import org.bukkit.event.entity.*;
import UltraKits.*;
import org.bukkit.event.*;

public class SB implements Listener
{
    public static HashMap<String, Scoreboard> scoreboards;
    public static HashMap<String, Score> deaths;
    public static HashMap<String, Score> kills;
    public static HashMap<String, Score> killstreak;
    public static HashMap<String, Score> kdr;
    public static HashMap<String, Integer> ks;
    public static boolean enableSB;
    
    static {
        SB.scoreboards = new HashMap<String, Scoreboard>();
        SB.deaths = new HashMap<String, Score>();
        SB.kills = new HashMap<String, Score>();
        SB.killstreak = new HashMap<String, Score>();
        SB.kdr = new HashMap<String, Score>();
        SB.ks = new HashMap<String, Integer>();
        SB.enableSB = Main.plugin.getConfig().getBoolean("EnableScoreboard2");
    }
    
    public static void criarScoreboard(final Player p) {
        if (SB.enableSB) {
            if (!SB.scoreboards.containsKey(p.getName())) {
                if (Main.m.getConfigurationSection(p.getName()) == null) {
                    final Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
                    final Objective obj = sb.registerNewObjective(p.getName(), "dummy");
                    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                    obj.setDisplayName(Main.plugin.getConfig().getString("Scoreboard1v1.title").replaceAll("&", "§").replaceAll("PLAYER", p.getName()));
                    final Score d = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard1v1.deaths").replaceAll("&", "§")));
                    final Score k = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard1v1.kills").replaceAll("&", "§")));
                    final Score kd = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard1v1.kdr").replaceAll("&", "§")));
                    final Score ks = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard1v1.killstreak").replaceAll("&", "§")));
                    d.setScore(0);
                    k.setScore(0);
                    kd.setScore(0);
                    ks.setScore(0);
                    SB.scoreboards.put(p.getName(), sb);
                    SB.deaths.put(p.getName(), d);
                    SB.kills.put(p.getName(), k);
                    SB.kdr.put(p.getName(), kd);
                    SB.killstreak.put(p.getName(), ks);
                    Main.stats.set(String.valueOf(p.getName()) + ".deaths", (Object)0);
                    Main.stats.set(String.valueOf(p.getName()) + ".kills", (Object)0);
                    Main.stats.set(String.valueOf(p.getName()) + ".killstreak", (Object)0);
                    try {
                        Main.stats.save(Main.s);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.setScoreboard((Scoreboard)SB.scoreboards.get(p.getName()));
                }
                else {
                    final Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
                    final Objective obj = sb.registerNewObjective(p.getName(), "dummy");
                    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                    obj.setDisplayName(Main.plugin.getConfig().getString("Scoreboard1v1.title").replaceAll("&", "§").replaceAll("PLAYER", p.getName()));
                    final Score d = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard1v1.deaths").replaceAll("&", "§")));
                    final Score k = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard1v1.kills").replaceAll("&", "§")));
                    final Score kd = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard1v1.kdr").replaceAll("&", "§")));
                    final Score ks = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard1v1.killstreak").replaceAll("&", "§")));
                    d.setScore(Main.stats.getInt(String.valueOf(p.getName()) + ".deaths"));
                    k.setScore(Main.stats.getInt(String.valueOf(p.getName()) + ".kills"));
                    if (k.getScore() > 0 && d.getScore() > 0) {
                        kd.setScore(k.getScore() / d.getScore());
                    }
                    else if (k.getScore() > 0 && d.getScore() <= 0) {
                        kd.setScore(k.getScore());
                    }
                    else if (k.getScore() == 0 && d.getScore() == 0) {
                        kd.setScore(0);
                    }
                    else if (k.getScore() == 0 && d.getScore() > 0) {
                        k.setScore(0);
                    }
                    ks.setScore(Main.stats.getInt(String.valueOf(p.getName()) + ".killstreak"));
                    SB.scoreboards.put(p.getName(), sb);
                    SB.deaths.put(p.getName(), d);
                    SB.kills.put(p.getName(), k);
                    SB.kdr.put(p.getName(), kd);
                    SB.killstreak.put(p.getName(), ks);
                    p.setScoreboard((Scoreboard)SB.scoreboards.get(p.getName()));
                }
            }
            else {
                p.setScoreboard((Scoreboard)SB.scoreboards.get(p.getName()));
            }
        }
    }
    
    @EventHandler
    public void main(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player p = e.getEntity();
            final Player killer = e.getEntity().getKiller();
            if (SB.enableSB && Commands.em.contains(p.getName()) && Commands.em.contains(killer.getName())) {
                final Score d = SB.deaths.get(p.getName());
                final Score k = SB.kills.get(killer.getName());
                d.setScore(d.getScore() + 1);
                k.setScore(k.getScore() + 1);
                SB.deaths.put(p.getName(), d);
                SB.kills.put(killer.getName(), k);
                if (SB.ks.containsKey(p.getName())) {
                    SB.ks.remove(p.getName());
                }
                if (SB.ks.containsKey(killer.getName())) {
                    if (SB.ks.get(killer.getName()) + 1 == 5) {
                        final Score ks = SB.killstreak.get(killer.getName());
                        ks.setScore(ks.getScore() + 1);
                        SB.killstreak.put(killer.getName(), ks);
                        ServerScoreboard.ks.remove(killer.getName());
                    }
                    else {
                        SB.ks.put(killer.getName(), SB.ks.get(killer.getName()) + 1);
                    }
                }
                else {
                    SB.ks.put(killer.getName(), 1);
                }
            }
            if (Commands.em.contains(p.getName()) && SB.kills.containsKey(p.getName()) && SB.deaths.containsKey(p.getName())) {
                if (SB.kills.get(p.getName()).getScore() > 0 && SB.deaths.get(p.getName()).getScore() > 0) {
                    final Score kd = SB.kdr.get(p.getName());
                    kd.setScore(SB.kills.get(p.getName()).getScore() / SB.deaths.get(p.getName()).getScore());
                    SB.kdr.put(p.getName(), kd);
                }
                else if (SB.kills.get(p.getName()).getScore() > 0 && SB.deaths.get(p.getName()).getScore() == 0) {
                    final Score kd = SB.kdr.get(p.getName());
                    kd.setScore(SB.kills.get(p.getName()).getScore());
                    SB.kdr.put(p.getName(), kd);
                }
                else if (SB.kills.get(p.getName()).getScore() == 0 && SB.deaths.get(p.getName()).getScore() > 0) {
                    final Score kd = SB.kdr.get(p.getName());
                    kd.setScore(0);
                    SB.kdr.put(p.getName(), kd);
                }
                else if (SB.kills.get(p.getName()).getScore() == 0 && SB.deaths.get(p.getName()).getScore() == 0) {
                    final Score kd = SB.kdr.get(p.getName());
                    kd.setScore(0);
                    SB.kdr.put(p.getName(), kd);
                }
            }
            if (Commands.em.contains(p.getName()) && SB.kills.containsKey(killer.getName()) && SB.deaths.containsKey(killer.getName())) {
                if (SB.kills.get(killer.getName()).getScore() > 0 && SB.deaths.get(killer.getName()).getScore() > 0) {
                    final Score kd = SB.kdr.get(killer.getName());
                    kd.setScore(SB.kills.get(killer.getName()).getScore() / SB.deaths.get(killer.getName()).getScore());
                    SB.kdr.put(killer.getName(), kd);
                }
                else if (SB.kills.get(killer.getName()).getScore() > 0 && SB.deaths.get(killer.getName()).getScore() == 0) {
                    final Score kd = SB.kdr.get(killer.getName());
                    kd.setScore(SB.kills.get(killer.getName()).getScore());
                    SB.kdr.put(killer.getName(), kd);
                }
                else if (SB.kills.get(killer.getName()).getScore() == 0 && SB.deaths.get(killer.getName()).getScore() > 0) {
                    final Score kd = SB.kdr.get(killer.getName());
                    kd.setScore(0);
                    SB.kdr.put(killer.getName(), kd);
                }
                else if (SB.kills.get(killer.getName()).getScore() == 0 && SB.deaths.get(killer.getName()).getScore() == 0) {
                    final Score kd = SB.kdr.get(killer.getName());
                    kd.setScore(0);
                    SB.kdr.put(killer.getName(), kd);
                }
            }
        }
    }
    
    public static void resetScoreboard(final Player p) {
        if (SB.kills.containsKey(p.getName())) {
            final Score k = SB.kills.get(p.getName());
            k.setScore(0);
            SB.kills.put(p.getName(), k);
        }
        if (SB.deaths.containsKey(p.getName())) {
            final Score d = SB.deaths.get(p.getName());
            d.setScore(0);
            SB.deaths.put(p.getName(), d);
        }
        if (SB.kdr.containsKey(p.getName())) {
            final Score kd = SB.kdr.get(p.getName());
            kd.setScore(0);
            SB.kdr.put(p.getName(), kd);
        }
        if (SB.killstreak.containsKey(p.getName())) {
            final Score ks = SB.killstreak.get(p.getName());
            ks.setScore(0);
            SB.killstreak.put(p.getName(), ks);
        }
    }
}
