package UltraKits.Eventos;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;

import UltraKits.Main;
import UltraKits.ServerScoreboard;
import UltraKits.SettingsManager;
import UltraKits.Habilidades.Ghost;
import UltraKits.Habilidades.Indio;
import UltraKits.Habilidades.Ninja;
import UltraKits.u1v1.Commands;
import UltraKits.u1v1.Desafiar;
import me.confuser.barapi.BarAPI;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Events implements Listener {
	public static ItemStack warps;
	public static ItemStack vertd;
	public static ItemStack loja;
	public static ItemStack kits;
	public static ItemStack umveum;
	public static HashMap<String, String> bars;
	public static Economy econ;
	public static EconomyResponse r;
	public Plugin plugin;
	SettingsManager settings;

	static {
		Events.warps = new ItemStack(Material.NAME_TAG);
		Events.vertd = new ItemStack(Material.DIAMOND);
		Events.loja = new ItemStack(Material.EMERALD);
		Events.kits = new ItemStack(Material.CHEST);
		Events.umveum = new ItemStack(Material.BLAZE_ROD);
		Events.bars = new HashMap<String, String>();
		Events.econ = null;
	}

	public Events(final Main plugin) {
		this.settings = SettingsManager.getInstance();
		this.plugin = (Plugin) plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void soup(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)
				&& p.getItemInHand().getType() == Material.MUSHROOM_SOUP && ((Damageable) p).getHealth() > 0.0
				&& ((Damageable) p).getHealth() < 20.0) {
			if (!Main.quickdropper.contains(p.getName())) {
				p.getItemInHand().setType(Material.BOWL);
			} else {
				p.getItemInHand().setType(Material.BOWL);
				p.getInventory().remove(new ItemStack(Material.BOWL, 1));
				p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.BOWL));
			}
			final int regen = Main.plugin.getConfig().getInt("SoupRegen");
			final int feed = Main.plugin.getConfig().getInt("SoupFeed");
			if (((Damageable) p).getHealth() + regen <= 20.0) {
				p.setHealth(((Damageable) p).getHealth() + regen);
			} else {
				p.setHealth(20.0);
			}
			if (p.getFoodLevel() + feed <= 20) {
				p.setFoodLevel(p.getFoodLevel() + feed);
			} else {
				p.setFoodLevel(20);
			}
			Main.plugin.getConfig().getBoolean("SoupSound");
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onchat(final AsyncPlayerChatEvent e) {
		String msg = e.getMessage();
		msg = msg.replace("Plugin Criado por Junioor_PvP ", "... ");
		final StringBuilder sb = new StringBuilder(msg);
		sb.setCharAt(0, Character.toUpperCase(msg.charAt(0)));
		e.setMessage(sb.toString());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onLoginBan(final PlayerLoginEvent e) {
		final Player p = e.getPlayer();
		if (e.getResult().equals((Object) PlayerLoginEvent.Result.KICK_FULL)) {
			e.disallow(PlayerLoginEvent.Result.KICK_FULL, ChatColor.RED + "O Servidor est\u00e1 cheio.\n "
					+ ChatColor.GREEN + Main.plugin.getConfig().getString("ServerSite"));
		} else if (e.getResult().equals((Object) PlayerLoginEvent.Result.KICK_WHITELIST)) {
			e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST,
					ChatColor.RED + "Voc\u00ea nao est\u00e1 na " + ChatColor.WHITE + "WhiteList\n " + ChatColor.GREEN
							+ Main.plugin.getConfig().getString("ServerSite"));
		} else if (e.getResult().equals((Object) PlayerLoginEvent.Result.KICK_BANNED)) {
			e.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.RED + "Voc\u00ea est\u00e1 BANIDO! \n"
					+ ChatColor.GREEN + Main.plugin.getConfig().getString("ServerSite"));
		}
		if (p.getName().length() + 4 <= 16) {
			if (p.hasPermission("tab.vip")) {
				p.setDisplayName("§b" + p.getName() + ChatColor.RESET);
				p.setPlayerListName("§b" + p.getName() + ChatColor.RESET);
			} else if (p.hasPermission("tab.vipplus")) {
				p.setDisplayName("§9" + p.getName() + ChatColor.RESET);
				p.setPlayerListName("§9" + p.getName() + ChatColor.RESET);
			} else if (p.hasPermission("tab.mvp")) {
				p.setDisplayName("§d" + p.getName() + ChatColor.RESET);
				p.setPlayerListName("§d" + p.getName() + ChatColor.RESET);
			} else if (p.hasPermission("tab.mvpplus")) {
				p.setDisplayName("§a" + p.getName() + ChatColor.RESET);
				p.setPlayerListName("§a" + p.getName() + ChatColor.RESET);
			} else if (p.hasPermission("tab.dono")) {
				p.setDisplayName("§4" + p.getName() + ChatColor.RESET);
				p.setPlayerListName("§4" + p.getName() + ChatColor.RESET);
			} else if (p.hasPermission("tab.admin")) {
				p.setDisplayName("§5" + p.getName() + ChatColor.RESET);
				p.setPlayerListName("§5" + p.getName() + ChatColor.RESET);
			} else if (p.hasPermission("tab.mod")) {
				p.setDisplayName("§2" + p.getName() + ChatColor.RESET);
				p.setPlayerListName("§2" + p.getName() + ChatColor.RESET);
			} else if (p.hasPermission("tab.subdono")) {
				p.setDisplayName("§6" + p.getName() + ChatColor.RESET);
				p.setPlayerListName("§6" + p.getName() + ChatColor.RESET);
			} else if (p.hasPermission("tab.youtuber")) {
				p.setDisplayName("§6[Youtuber]" + p.getName() + ChatColor.RESET);
				p.setPlayerListName("§6[Youtuber]" + p.getName() + ChatColor.RESET);
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void comandoInvalido(final PlayerCommandPreprocessEvent event) {
		if (!event.isCancelled()) {
			final Player player = event.getPlayer();
			final String cmd = event.getMessage().split(" ")[0];
			final HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
			if (topic == null) {
				player.sendMessage("§cComando inexistente ou invalido!");
				player.playSound(player.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDrop(final PlayerDropItemEvent e) {
		final Player p = e.getPlayer();
		if (e.getItemDrop().getItemStack().getType() != Material.BOWL
				&& e.getItemDrop().getItemStack().getType() != Material.MUSHROOM_SOUP) {
			e.setCancelled(true);
			p.updateInventory();
			p.sendMessage(ChatColor.RED + "Este item nao pode ser dropado!");
		}
	}

	@EventHandler
	public void chuva(final WeatherChangeEvent event) {
		if (event.toWeatherState()) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onKitReset(final PlayerDeathEvent e) {
		e.getDrops().clear();
		e.setDeathMessage((String) null);
		final Player p = e.getEntity();
		Ninja.resetTarget(p);
		for (final PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
	}

	@EventHandler
	public void onPlayerColor(final SignChangeEvent e) {
		if (e.getPlayer().hasPermission("uk.admin")) {
			if (e.getLine(0).contains("&")) {
				e.setLine(0, e.getLine(0).replace("&", "§"));
			}
			if (e.getLine(1).contains("&")) {
				e.setLine(1, e.getLine(1).replace("&", "§"));
			}
			if (e.getLine(2).contains("&")) {
				e.setLine(2, e.getLine(2).replace("&", "§"));
			}
			if (e.getLine(3).contains("&")) {
				e.setLine(3, e.getLine(3).replace("&", "§"));
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerLoseHunger(final FoodLevelChangeEvent e) {
		e.setFoodLevel(20);
	}

	@EventHandler
	public void explodeEvent(final EntityExplodeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(final PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		p.getInventory().clear();
		p.setHealth(20.0);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
			@Override
			public void run() {
				p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
				ServerScoreboard.criarScoreboard(p);
			}
		}, 1L);
		Main.resetKit(p);
		Main.restaurarItens(p);
		Desafiar.showPlayer(p);
		p.getInventory().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR),
				new ItemStack(Material.AIR), new ItemStack(Material.AIR) });
		p.getInventory().setArmorContents((ItemStack[]) null);
		try {
			final World w = Bukkit.getServer().getWorld(this.settings.getData().getString("spawn.world"));
			final double x = this.settings.getData().getDouble("spawn.x");
			final double y = this.settings.getData().getDouble("spawn.y");
			final double z = this.settings.getData().getDouble("spawn.z");
			final Location loc = new Location(w, x, y, z);
			loc.setPitch((float) this.settings.getData().getDouble("spawn.pitch"));
			loc.setYaw((float) this.settings.getData().getDouble("spawn.yaw"));
			p.teleport(loc);
		} catch (IllegalArgumentException ex) {
			p.sendMessage(ChatColor.RED + "Spawn nao definido ainda. Pe\u00e7a a um Staff para seta-lo.");
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeft(final PlayerQuitEvent e) {
		final Player p = e.getPlayer();
		if (Ghost.task.containsKey(p.getName())) {
			final BukkitTask task = Ghost.task.get(p.getName());
			task.cancel();
			Ghost.task.remove(p.getName());
		}
		if (Ghost.arm.containsKey(p.getName())) {
			Ghost.arm.remove(p.getName());
		}
		if (Ghost.inv.containsKey(p.getName())) {
			Ghost.inv.remove(p.getName());
		}
		if (Ghost.fantasmas.contains(p.getName())) {
			Ghost.fantasmas.remove(p.getName());
		}
		if (Indio.tiros.containsKey(p.getName())) {
			Indio.tiros.remove(p.getName());
		}
		for (final Map.Entry<String, String> en : Events.bars.entrySet()) {
			final String s = en.getKey();
			final String r = en.getValue();
			if (p.getName() == s) {
				Bukkit.getPlayer(r);
			}
		}
		e.setQuitMessage((String) null);
		Bukkit.getServer().broadcast(ChatColor.RED + "- " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET
				+ ChatColor.WHITE + " saiu do servidor", "exit.vanish.receive");
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void msg(final PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		Main.desafio.remove(p.getName());
		Main.admin.remove(p.getName());
		e.setJoinMessage(ChatColor.GREEN + "+ " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET
				+ ChatColor.WHITE + " entrou no servidor");
		p.getPlayer().setTotalExperience(0);
		BarAPI.setMessage(p,
				Main.plugin.getConfig().getString("WelcomeBar").replaceAll("&", "§").replaceAll("PLAYER", p.getName()),
				5);
		for (final PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerRespawn(final PlayerRespawnEvent e) {
		final Player p = e.getPlayer();
		if (!Commands.em.contains(p.getName())) {
			Main.resetKit(p);
		}
	}

	@EventHandler
	public void barKit(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player p = (Player) e.getEntity();
			final Player damager = (Player) e.getDamager();
			if (!Commands.em.contains(p.getName())) {
				BarAPI.setMessage(damager, String.valueOf(p.getName()) + " - " + this.getKit(p), 5);
				if (Events.bars.containsKey(damager.getName())) {
					if (Events.bars.get(damager.getName()) != p.getName()) {
						Events.bars.put(damager.getName(), p.getName());
					}
				} else {
					Events.bars.put(damager.getName(), p.getName());
				}
			} else {
				BarAPI.removeBar(p);
			}
		}
	}

	public String getKit(final Player p) {
		if (Main.hg.contains(p.getName())) {
			return "PvP";
		}
		if (Main.arqueiro.contains(p.getName())) {
			return "Arqueiro";
		}
		if (Main.urgal.contains(p.getName())) {
			return "Urgal";
		}
		if (Main.whister.contains(p.getName())) {
			return "Shooter";
		}
		if (Main.pyro.contains(p.getName())) {
			return "Pyro";
		}
		if (Main.Trocador.contains(p.getName())) {
			return "Trocador";
		}
		if (Main.soldado.contains(p.getName())) {
			return "Soldado";
		}
		if (Main.viper.contains(p.getName())) {
			return "Viper";
		}
		if (Main.ninja.contains(p.getName())) {
			return "Ninja";
		}
		if (Main.anchor.contains(p.getName())) {
			return "Anchor";
		}
		if (Main.tank.contains(p.getName())) {
			return "Tank";
		}
		if (Main.switcher.contains(p.getName())) {
			return "Switcher";
		}
		if (Main.darkmage.contains(p.getName())) {
			return "Darkmage";
		}
		if (Main.Teleporter.contains(p.getName())) {
			return "Teleporter";
		}
		if (Main.thor.contains(p.getName())) {
			return "Thor";
		}
		if (Main.specialist.contains(p.getName())) {
			return "Specialist";
		}
		if (Main.launcher.contains(p.getName())) {
			return "Launcher";
		}
		if (Main.milkman.contains(p.getName())) {
			return "Milkman";
		}
		if (Main.skeleton.contains(p.getName())) {
			return "Skeleton";
		}
		if (Main.fisherman.contains(p.getName())) {
			return "Fisherman";
		}
		if (Main.phantom.contains(p.getName())) {
			return "Phantom";
		}
		if (Main.gladiator.contains(p.getName())) {
			return "Gladiator";
		}
		if (Main.flash.contains(p.getName())) {
			return "Flash";
		}
		if (Main.grappler.contains(p.getName())) {
			return "Grappler";
		}
		if (Main.endermage.contains(p.getName())) {
			return "Endermage";
		}
		if (Main.monk.contains(p.getName())) {
			return "Monk";
		}
		if (Main.camel.contains(p.getName())) {
			return "Camel";
		}
		if (Main.frosty.contains(p.getName())) {
			return "Frosty";
		}
		if (Main.wither.contains(p.getName())) {
			return "Wither";
		}
		if (Main.poseidon.contains(p.getName())) {
			return "Poseidon";
		}
		if (Main.stomper.contains(p.getName())) {
			return "Stomper";
		}
		if (Main.reaper.contains(p.getName())) {
			return "Reaper";
		}
		if (Main.pisca.contains(p.getName())) {
			return "Remix";
		}
		if (Main.turtle.contains(p.getName())) {
			return "Turtle";
		}
		if (Main.jumper.contains(p.getName())) {
			return "Jumper";
		}
		if (Main.snail.contains(p.getName())) {
			return "Snail";
		}
		if (Main.fireman.contains(p.getName())) {
			return "Fireman";
		}
		if (Main.kangaroo.contains(p.getName())) {
			return "Kangaroo";
		}
		if (Main.viking.contains(p.getName())) {
			return "Viking";
		}
		if (Main.madman.contains(p.getName())) {
			return "Madman";
		}
		if (Main.grandpa.contains(p.getName())) {
			return "Grandpa";
		}
		if (Main.ghost.contains(p.getName())) {
			return "Ghost";
		}
		if (Main.barbarian.contains(p.getName())) {
			return "Barbarian";
		}
		if (Main.spiderman.contains(p.getName())) {
			return "SpiderMan";
		}
		if (Main.berserker.contains(p.getName())) {
			return "Berserker";
		}
		if (Main.indio.contains(p.getName())) {
			return "Indio";
		}
		if (Main.ryu.contains(p.getName())) {
			return "Ryu";
		}
		if (Main.neji.contains(p.getName())) {
			return "Neji";
		}
		if (Main.granadier.contains(p.getName())) {
			return "Granadier";
		}
		if (Main.rhino.contains(p.getName())) {
			return "Rhino";
		}
		if (Main.alien.contains(p.getName())) {
			return "Alien";
		}
		if (Main.critical.contains(p.getName())) {
			return "Critical";
		}
		if (Main.hulk.contains(p.getName())) {
			return "Hulk";
		}
		if (Main.lobisomem.contains(p.getName())) {
			return "Lobisomem";
		}
		if (Main.phantom.contains(p.getName())) {
			return "Phantom";
		}
		if (Main.vitality.contains(p.getName())) {
			return "Vitality";
		}
		if (Main.quickdropper.contains(p.getName())) {
			return "QuickDropper";
		}
		return "Sem Kit";
	}
}
