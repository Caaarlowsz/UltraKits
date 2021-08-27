package UltraKits.Comandos;

import UltraKits.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import UltraKits.u1v1.*;
import org.bukkit.plugin.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;

public class Comandos implements CommandExecutor
{
    public Main plugin;
    SettingsManager settings;
    
    public Comandos(final Main plugin) {
        this.settings = SettingsManager.getInstance();
        this.plugin = plugin;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        final String cm = cmd.getName();
        if (cmd.getName().equalsIgnoreCase("tpall") && sender.hasPermission("uk.tpall")) {
            final Player s = (Player)sender;
            Player[] onlinePlayers;
            for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, j = 0; j < length; ++j) {
                final Player player = onlinePlayers[j];
                player.teleport(s.getLocation());
            }
            Bukkit.getServer().broadcastMessage(Main.plugin.getConfig().getString("MensagemTpAll").replaceAll("&", "§"));
            return true;
        }
        final Player p = Bukkit.getPlayer(sender.getName());
        if (cm.equalsIgnoreCase("setspawn") && args.length == 0 && p.hasPermission("uk.setspawn")) {
            this.settings.getData().set("spawn.world", (Object)p.getLocation().getWorld().getName());
            this.settings.getData().set("spawn.x", (Object)p.getLocation().getX());
            this.settings.getData().set("spawn.y", (Object)p.getLocation().getY());
            this.settings.getData().set("spawn.z", (Object)p.getLocation().getZ());
            this.settings.getData().set("spawn.pitch", (Object)p.getLocation().getPitch());
            this.settings.getData().set("spawn.yaw", (Object)p.getLocation().getYaw());
            this.settings.saveData();
            p.sendMessage("§aVoce selecionou o local do spawn!");
        }
        if (!cmd.getName().equalsIgnoreCase("kick")) {
            if (commandLabel.equalsIgnoreCase("mute")) {
                if (sender.hasPermission("uk.mute")) {
                    if (args.length == 0) {
                        p.sendMessage(ChatColor.RED + "Sintaxe correta: /mute <jogador>");
                    }
                    else if (args.length == 1) {
                        final Player player = Bukkit.getPlayer(args[0]);
                        if (player == null) {
                            p.sendMessage(ChatColor.RED + "Voce nao pode silenciar um jogador offline");
                            return true;
                        }
                        p.sendMessage(ChatColor.GREEN + "Jogador silenciado!");
                        player.sendMessage(ChatColor.RED + "Voce foi silenciado, portanto nao podera falar!");
                        Main.mute.add(player.getName());
                    }
                }
                else {
                    p.sendMessage(ChatColor.DARK_RED + "Voce nao tem permissao!");
                }
            }
            if (commandLabel.equalsIgnoreCase("morrer")) {
                p.setHealth(0.0);
                p.sendMessage(ChatColor.RED + "Voce Se Matou!");
            }
            if (commandLabel.equalsIgnoreCase("desmute")) {
                if (sender.hasPermission("uk.desmute")) {
                    if (args.length == 0) {
                        p.sendMessage(ChatColor.DARK_GREEN + "Sintaxe correta: /desmute <jogador>");
                    }
                    else if (args.length == 1) {
                        final Player player = Bukkit.getPlayer(args[0]);
                        if (player == null) {
                            p.sendMessage(ChatColor.RED + "Nao e possivel deixar que um jogador offline volte a falar!");
                            return true;
                        }
                        p.sendMessage(ChatColor.GREEN + "O jogador ja pode falar novamente");
                        Main.mute.remove(player.getName());
                    }
                }
                else {
                    p.sendMessage(ChatColor.DARK_RED + "Voce Nao Tem Permissao!");
                }
            }
            if (cm.equalsIgnoreCase("spawn")) {
                p.sendMessage(ChatColor.GREEN + "Aguarde 5 segundos para se teleportar!");
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (!Commands.em.contains(p.getName())) {
                                final World w = Bukkit.getServer().getWorld(Comandos.this.settings.getData().getString("spawn.world"));
                                final double x = Comandos.this.settings.getData().getDouble("spawn.x");
                                final double y = Comandos.this.settings.getData().getDouble("spawn.y");
                                final double z = Comandos.this.settings.getData().getDouble("spawn.z");
                                final Location loc1 = new Location(w, x, y, z);
                                loc1.setPitch((float)Comandos.this.settings.getData().getDouble("spawn.pitch"));
                                loc1.setYaw((float)Comandos.this.settings.getData().getDouble("spawn.yaw"));
                                p.teleport(loc1);
                                Main.resetKit(p);
                                Main.restaurarItens(p);
                            }
                            else {
                                p.sendMessage(ChatColor.RED + "Voce nao pode digitar este comando no 1v1. Digite /1v1 para sair.");
                            }
                        }
                        catch (IllegalArgumentException e) {
                            p.sendMessage(ChatColor.RED + "Spawn nao definido ainda. Pe\u00e7a a um Admin para seta-lo.");
                        }
                    }
                }, 100L);
            }
            if (cmd.getName().equalsIgnoreCase("head")) {
                if (!p.hasPermission("uk.head")) {
                    p.sendMessage(ChatColor.DARK_RED + "Voce nao tem permissao");
                    return true;
                }
                if (args.length == 0) {
                    p.sendMessage(ChatColor.RED + "Sintaxe correta : /head <player>");
                    return true;
                }
                if (args.length == 1) {
                    final ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
                    final SkullMeta meta = (SkullMeta)skull.getItemMeta();
                    meta.setOwner(args[0]);
                    meta.setDisplayName(ChatColor.GOLD + "Cabeca de " + args[0]);
                    skull.setItemMeta((ItemMeta)meta);
                    p.getInventory().addItem(new ItemStack[] { skull });
                    p.sendMessage(ChatColor.GREEN + "Voce recebeu a cabeca de " + args[0]);
                    return true;
                }
            }
            if (commandLabel.equalsIgnoreCase("fly") && sender.hasPermission("uk.fly")) {
                if (p.getAllowFlight()) {
                    p.setFlying(false);
                    p.setAllowFlight(false);
                    p.sendMessage(ChatColor.RED + "Modo de voo desativado");
                }
                else {
                    p.setAllowFlight(true);
                    p.setFlySpeed(0.3f);
                    p.sendMessage(ChatColor.GREEN + "Modo de voo ativado");
                }
            }
            if (commandLabel.equalsIgnoreCase("sair")) {
                Main.resetKit(p);
                p.chat("/spawn");
                Main.restaurarItens(p);
            }
            if (cm.equalsIgnoreCase("gm")) {
                if (!p.hasPermission("uk.gm")) {
                    p.sendMessage(ChatColor.RED + "Voce nao tem permissao.");
                    return true;
                }
                if (args.length == 0) {
                    if (p.getGameMode() == GameMode.SURVIVAL) {
                        p.setGameMode(GameMode.CREATIVE);
                    }
                    else if (p.getGameMode() == GameMode.CREATIVE) {
                        p.setGameMode(GameMode.SURVIVAL);
                    }
                    p.sendMessage(ChatColor.GOLD + "GameMode alterado para: " + ChatColor.RED + p.getGameMode().name());
                    return true;
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("0")) {
                        p.setGameMode(GameMode.SURVIVAL);
                    }
                    else if (args[0].equalsIgnoreCase("1")) {
                        p.setGameMode(GameMode.CREATIVE);
                    }
                    p.sendMessage(ChatColor.GOLD + "GameMode alterado para: " + ChatColor.RED + p.getGameMode().name());
                    return true;
                }
            }
            return true;
        }
        if (!p.hasPermission("uk.kick")) {
            p.sendMessage(ChatColor.DARK_RED + "Voce nao tem permissao!");
            return true;
        }
        if (args.length == 0) {
            p.sendMessage(ChatColor.DARK_RED + "Uso Correto: /kick <jogador> <motivo>");
            return true;
        }
        if (args.length == 1) {
            final Player t = Bukkit.getPlayerExact(args[0]);
            if (t == null) {
                p.sendMessage(ChatColor.RED + "Jogador " + args[0] + " nao encontrado!");
                return true;
            }
            p.sendMessage(ChatColor.GREEN + "Voce kickou " + ChatColor.GREEN + t.getName());
            Bukkit.getServer().broadcastMessage(ChatColor.RED + t.getName() + ChatColor.GRAY + " foi kickado do servidor por " + ChatColor.RED + p.getName() + ChatColor.GRAY + "!");
            t.kickPlayer(ChatColor.RED + "Voce foi kickado do servidor!");
            return true;
        }
        else {
            if (args.length < 2) {
                return true;
            }
            final Player t = Bukkit.getPlayerExact(args[0]);
            if (t == null) {
                p.sendMessage(ChatColor.RED + "Jogador " + args[0] + ChatColor.RED + " nao encontrado");
                return true;
            }
            final StringBuilder str = new StringBuilder();
            for (int i = 1; i < args.length; ++i) {
                str.append(args[i]).append(" ");
            }
            final String msg = str.toString();
            p.sendMessage(ChatColor.GRAY + "Voce kickou " + ChatColor.RED + t.getName() + ChatColor.GRAY + " por " + ChatColor.RED + msg);
            Bukkit.getServer().broadcastMessage(ChatColor.RED + t.getName() + ChatColor.GRAY + " foi kickado do servidor por " + ChatColor.RED + msg + ChatColor.GRAY + " pelo " + ChatColor.RED + p.getName() + ChatColor.GRAY + "!");
            t.kickPlayer(ChatColor.GRAY + "Voce foi kickado do servidor por " + ChatColor.RED + msg);
            return true;
        }
    }
}
