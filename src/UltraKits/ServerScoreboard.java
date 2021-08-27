package UltraKits;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.io.*;
import org.bukkit.scoreboard.*;
import org.bukkit.event.entity.*;
import UltraKits.u1v1.*;
import org.bukkit.event.*;

public class ServerScoreboard implements Listener
{
    public static HashMap<String, Scoreboard> scoreboards;
    public static HashMap<String, Score> deaths;
    public static HashMap<String, Score> kills;
    public static HashMap<String, Score> killstreak;
    public static HashMap<String, Score> kdr;
    public static HashMap<String, Integer> ks;
    public static boolean enableSB;
    
    static {
        ServerScoreboard.scoreboards = new HashMap<String, Scoreboard>();
        ServerScoreboard.deaths = new HashMap<String, Score>();
        ServerScoreboard.kills = new HashMap<String, Score>();
        ServerScoreboard.killstreak = new HashMap<String, Score>();
        ServerScoreboard.kdr = new HashMap<String, Score>();
        ServerScoreboard.ks = new HashMap<String, Integer>();
        ServerScoreboard.enableSB = Main.plugin.getConfig().getBoolean("EnableScoreboard");
    }
    
    public static void criarScoreboard(final Player p) {
        if (ServerScoreboard.enableSB) {
            if (!ServerScoreboard.scoreboards.containsKey(p.getName())) {
                if (Main.m.getConfigurationSection(p.getName()) == null) {
                    final Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
                    final Objective obj = sb.registerNewObjective(p.getName(), "dummy");
                    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                    obj.setDisplayName(Main.plugin.getConfig().getString("Scoreboard.title").replaceAll("&", "§").replaceAll("PLAYER", p.getName()));
                    final Score d = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.deaths").replaceAll("&", "§")));
                    final Score k = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.kills").replaceAll("&", "§")));
                    final Score kd = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.kdr").replaceAll("&", "§")));
                    final Score ks = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.killstreak").replaceAll("&", "§")));
                    d.setScore(0);
                    k.setScore(0);
                    kd.setScore(0);
                    ks.setScore(0);
                    ServerScoreboard.scoreboards.put(p.getName(), sb);
                    ServerScoreboard.deaths.put(p.getName(), d);
                    ServerScoreboard.kills.put(p.getName(), k);
                    ServerScoreboard.kdr.put(p.getName(), kd);
                    ServerScoreboard.killstreak.put(p.getName(), ks);
                    Main.m.set(String.valueOf(p.getName()) + ".deaths", (Object)0);
                    Main.m.set(String.valueOf(p.getName()) + ".kills", (Object)0);
                    Main.m.set(String.valueOf(p.getName()) + ".killstreak", (Object)0);
                    try {
                        Main.m.save(Main.n);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.setScoreboard((Scoreboard)ServerScoreboard.scoreboards.get(p.getName()));
                }
                else {
                    final Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
                    final Objective obj = sb.registerNewObjective(p.getName(), "dummy");
                    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
                    obj.setDisplayName(Main.plugin.getConfig().getString("Scoreboard.title").replaceAll("&", "§").replaceAll("PLAYER", p.getName()));
                    final Score d = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.deaths").replaceAll("&", "§")));
                    final Score k = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.kills").replaceAll("&", "§")));
                    final Score kd = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.kdr").replaceAll("&", "§")));
                    final Score ks = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.killstreak").replaceAll("&", "§")));
                    d.setScore(Main.m.getInt(String.valueOf(p.getName()) + ".deaths"));
                    k.setScore(Main.m.getInt(String.valueOf(p.getName()) + ".kills"));
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
                    ks.setScore(Main.m.getInt(String.valueOf(p.getName()) + ".killstreak"));
                    ServerScoreboard.scoreboards.put(p.getName(), sb);
                    ServerScoreboard.deaths.put(p.getName(), d);
                    ServerScoreboard.kills.put(p.getName(), k);
                    ServerScoreboard.kdr.put(p.getName(), kd);
                    ServerScoreboard.killstreak.put(p.getName(), ks);
                    p.setScoreboard((Scoreboard)ServerScoreboard.scoreboards.get(p.getName()));
                }
            }
            else {
                p.setScoreboard((Scoreboard)ServerScoreboard.scoreboards.get(p.getName()));
            }
        }
    }
    
    @EventHandler
    public void main(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player p = e.getEntity();
            final Player killer = e.getEntity().getKiller();
            if (ServerScoreboard.enableSB && !Commands.em.contains(p.getName()) && !Commands.em.contains(killer.getName())) {
                final Score d = ServerScoreboard.deaths.get(p.getName());
                final Score k = ServerScoreboard.kills.get(killer.getName());
                d.setScore(d.getScore() + 1);
                k.setScore(k.getScore() + 1);
                ServerScoreboard.deaths.put(p.getName(), d);
                ServerScoreboard.kills.put(killer.getName(), k);
                if (ServerScoreboard.ks.containsKey(p.getName())) {
                    ServerScoreboard.ks.remove(p.getName());
                }
                if (ServerScoreboard.ks.containsKey(killer.getName())) {
                    if (ServerScoreboard.ks.get(killer.getName()) + 1 == 5) {
                        final Score ks = ServerScoreboard.killstreak.get(killer.getName());
                        ks.setScore(ks.getScore() + 1);
                        ServerScoreboard.killstreak.put(killer.getName(), ks);
                        ServerScoreboard.ks.remove(killer.getName());
                    }
                    else {
                        ServerScoreboard.ks.put(killer.getName(), ServerScoreboard.ks.get(killer.getName()) + 1);
                    }
                }
                else {
                    ServerScoreboard.ks.put(killer.getName(), 1);
                }
            }
            if (!Commands.em.contains(p.getName()) && ServerScoreboard.kills.containsKey(p.getName()) && ServerScoreboard.deaths.containsKey(p.getName())) {
                if (ServerScoreboard.kills.get(p.getName()).getScore() > 0 && ServerScoreboard.deaths.get(p.getName()).getScore() > 0) {
                    final Score kd = ServerScoreboard.kdr.get(p.getName());
                    kd.setScore(ServerScoreboard.kills.get(p.getName()).getScore() / ServerScoreboard.deaths.get(p.getName()).getScore());
                    ServerScoreboard.kdr.put(p.getName(), kd);
                }
                else if (ServerScoreboard.kills.get(p.getName()).getScore() > 0 && ServerScoreboard.deaths.get(p.getName()).getScore() == 0) {
                    final Score kd = ServerScoreboard.kdr.get(p.getName());
                    kd.setScore(ServerScoreboard.kills.get(p.getName()).getScore());
                    ServerScoreboard.kdr.put(p.getName(), kd);
                }
                else if (ServerScoreboard.kills.get(p.getName()).getScore() == 0 && ServerScoreboard.deaths.get(p.getName()).getScore() > 0) {
                    final Score kd = ServerScoreboard.kdr.get(p.getName());
                    kd.setScore(0);
                    ServerScoreboard.kdr.put(p.getName(), kd);
                }
                else if (ServerScoreboard.kills.get(p.getName()).getScore() == 0 && ServerScoreboard.deaths.get(p.getName()).getScore() == 0) {
                    final Score kd = ServerScoreboard.kdr.get(p.getName());
                    kd.setScore(0);
                    ServerScoreboard.kdr.put(p.getName(), kd);
                }
            }
            if (!Commands.em.contains(p.getName()) && ServerScoreboard.kills.containsKey(killer.getName()) && ServerScoreboard.deaths.containsKey(killer.getName())) {
                if (ServerScoreboard.kills.get(killer.getName()).getScore() > 0 && ServerScoreboard.deaths.get(killer.getName()).getScore() > 0) {
                    final Score kd = ServerScoreboard.kdr.get(killer.getName());
                    kd.setScore(ServerScoreboard.kills.get(killer.getName()).getScore() / ServerScoreboard.deaths.get(killer.getName()).getScore());
                    ServerScoreboard.kdr.put(killer.getName(), kd);
                }
                else if (ServerScoreboard.kills.get(killer.getName()).getScore() > 0 && ServerScoreboard.deaths.get(killer.getName()).getScore() == 0) {
                    final Score kd = ServerScoreboard.kdr.get(killer.getName());
                    kd.setScore(ServerScoreboard.kills.get(killer.getName()).getScore());
                    ServerScoreboard.kdr.put(killer.getName(), kd);
                }
                else if (ServerScoreboard.kills.get(killer.getName()).getScore() == 0 && ServerScoreboard.deaths.get(killer.getName()).getScore() > 0) {
                    final Score kd = ServerScoreboard.kdr.get(killer.getName());
                    kd.setScore(0);
                    ServerScoreboard.kdr.put(killer.getName(), kd);
                }
                else if (ServerScoreboard.kills.get(killer.getName()).getScore() == 0 && ServerScoreboard.deaths.get(killer.getName()).getScore() == 0) {
                    final Score kd = ServerScoreboard.kdr.get(killer.getName());
                    kd.setScore(0);
                    ServerScoreboard.kdr.put(killer.getName(), kd);
                }
            }
        }
    }
    
    public static void resetScoreboard(final Player p) {
        if (ServerScoreboard.kills.containsKey(p.getName())) {
            final Score k = ServerScoreboard.kills.get(p.getName());
            k.setScore(0);
            ServerScoreboard.kills.put(p.getName(), k);
        }
        if (ServerScoreboard.deaths.containsKey(p.getName())) {
            final Score d = ServerScoreboard.deaths.get(p.getName());
            d.setScore(0);
            ServerScoreboard.deaths.put(p.getName(), d);
        }
        if (ServerScoreboard.kdr.containsKey(p.getName())) {
            final Score kd = ServerScoreboard.kdr.get(p.getName());
            kd.setScore(0);
            ServerScoreboard.kdr.put(p.getName(), kd);
        }
        if (ServerScoreboard.killstreak.containsKey(p.getName())) {
            final Score ks = ServerScoreboard.killstreak.get(p.getName());
            ks.setScore(0);
            ServerScoreboard.killstreak.put(p.getName(), ks);
        }
    }
}
