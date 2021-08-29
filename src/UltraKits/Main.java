package UltraKits;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Score;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

import UltraKits.Comandos.Admin;
import UltraKits.Comandos.Chat;
import UltraKits.Comandos.Comandos;
import UltraKits.Comandos.CombatLog;
import UltraKits.Comandos.CommandBuild;
import UltraKits.Comandos.MuteP;
import UltraKits.Comandos.Report;
import UltraKits.Comandos.Skit;
import UltraKits.Comandos.Tag;
import UltraKits.Comandos.Tell;
import UltraKits.Eventos.AntiFlood;
import UltraKits.Eventos.Events;
import UltraKits.Eventos.JumpBlocks;
import UltraKits.Eventos.Killstreak;
import UltraKits.Habilidades.Alien;
import UltraKits.Habilidades.Anchor;
import UltraKits.Habilidades.Barbarian;
import UltraKits.Habilidades.Berserker;
import UltraKits.Habilidades.Camel;
import UltraKits.Habilidades.Critical;
import UltraKits.Habilidades.Darkmage;
import UltraKits.Habilidades.Endermage;
import UltraKits.Habilidades.Eyer;
import UltraKits.Habilidades.Fireman;
import UltraKits.Habilidades.Fisherman;
import UltraKits.Habilidades.Flash;
import UltraKits.Habilidades.Frosty;
import UltraKits.Habilidades.Ghost;
import UltraKits.Habilidades.Gladiator;
import UltraKits.Habilidades.Granadier;
import UltraKits.Habilidades.Grappler;
import UltraKits.Habilidades.Hulk;
import UltraKits.Habilidades.Indio;
import UltraKits.Habilidades.Jumper;
import UltraKits.Habilidades.Kangaroo;
import UltraKits.Habilidades.Launcher;
import UltraKits.Habilidades.Lobisomem;
import UltraKits.Habilidades.Milkman;
import UltraKits.Habilidades.Monk;
import UltraKits.Habilidades.Neji;
import UltraKits.Habilidades.Ninja;
import UltraKits.Habilidades.Phantom;
import UltraKits.Habilidades.Poseidon;
import UltraKits.Habilidades.Pyro;
import UltraKits.Habilidades.Reaper;
import UltraKits.Habilidades.Rhino;
import UltraKits.Habilidades.Ryu;
import UltraKits.Habilidades.Shooter;
import UltraKits.Habilidades.Skeleton;
import UltraKits.Habilidades.Snail;
import UltraKits.Habilidades.Soldado;
import UltraKits.Habilidades.Specialist;
import UltraKits.Habilidades.SpiderMan;
import UltraKits.Habilidades.Stomper;
import UltraKits.Habilidades.Switcher;
import UltraKits.Habilidades.Thor;
import UltraKits.Habilidades.Trocador;
import UltraKits.Habilidades.Turtle;
import UltraKits.Habilidades.Viking;
import UltraKits.Habilidades.Viper;
import UltraKits.Habilidades.Vitality;
import UltraKits.Habilidades.Wither;
import UltraKits.Inventarios.MenuInv;
import UltraKits.Inventarios.MenuInvListener;
import UltraKits.Inventarios.Warps;
import UltraKits.Inventarios.WarpsListener;
import UltraKits.Warps.Block;
import UltraKits.Warps.Challenge;
import UltraKits.Warps.Earlyhg;
import UltraKits.Warps.MainList;
import UltraKits.Warps.Pot;
import UltraKits.u1v1.Commands;
import UltraKits.u1v1.Custom;
import UltraKits.u1v1.Desafiar;
import UltraKits.u1v1.ItemManager;
import UltraKits.u1v1.SB;

public class Main extends JavaPlugin implements Listener {
	public static SettingsManager settings;
	public static Plugin plugin;
	public static File d;
	public static FileConfiguration data;
	public static File k;
	public static FileConfiguration kit;
	public static File s;
	public static FileConfiguration stats;
	public static File w;
	public static FileConfiguration warps;
	public static File n;
	public static FileConfiguration m;
	public static List<String> lista;
	public static HashMap<CommandSender, CommandSender> replies;
	public static ArrayList<String> admin;
	public static ArrayList<String> gladgladiator;
	public static ArrayList<String> invis;
	public static ArrayList<String> reload;
	public static ArrayList<String> kits;
	public static ArrayList<String> skeleton;
	public static ArrayList<String> hg;
	public static Map<String, Integer> killstreaks;
	public static ArrayList<String> whister;
	public static ArrayList<String> arqueiro;
	public static ArrayList<String> stomper;
	public static ArrayList<String> Trocador;
	public static ArrayList<String> viper;
	public static ArrayList<String> cooldown;
	public static ArrayList<String> thor;
	public static ArrayList<String> launcher;
	public static ArrayList<String> block;
	public static ArrayList<String> turtle;
	public static ArrayList<String> kitfreeze;
	public static ArrayList<String> construiroon;
	public static ArrayList<String> construiroff;
	public static ArrayList<String> flash;
	public static ArrayList<String> mute;
	public static ArrayList<String> anchor;
	public static ArrayList<String> bomber;
	public static ArrayList<String> bowman;
	public static ArrayList<String> jumper;
	public static ArrayList<String> enderhg;
	public static ArrayList<String> urgal;
	public static ArrayList<String> pyro;
	public static ArrayList<String> monk;
	public static ArrayList<String> Teleporter;
	public static ArrayList<String> fisherman;
	public static ArrayList<String> reaper;
	public static ArrayList<String> vanished;
	public static ArrayList<String> frosty;
	public static ArrayList<String> pisca;
	public static ArrayList<String> specialist;
	public static ArrayList<String> switcher;
	public static ArrayList<String> snail;
	public static ArrayList<String> milkman;
	public static ArrayList<String> flat;
	public static ArrayList<String> kangaroo;
	public static ArrayList<String> gladiator;
	public static ArrayList<String> camel;
	public static ArrayList<String> cooldown1;
	public static ArrayList<String> grappler;
	public static ArrayList<String> poseidon;
	public static ArrayList<String> fireman;
	public static ArrayList<String> wither;
	public static ArrayList<String> Checkcooldown;
	public static ArrayList<String> tank;
	public static ArrayList<String> endermage;
	public static ArrayList<String> phantom;
	public static ArrayList<String> desafio;
	public static ArrayList<String> ninja;
	public static ArrayList<String> darkmage;
	public static ArrayList<String> soldado;
	public static ArrayList<String> viking;
	public static ArrayList<String> madman;
	public static ArrayList<String> grandpa;
	public static ArrayList<String> ghost;
	public static ArrayList<String> barbarian;
	public static ArrayList<String> spiderman;
	public static ArrayList<String> berserker;
	public static ArrayList<String> indio;
	public static ArrayList<String> ryu;
	public static ArrayList<String> neji;
	public static ArrayList<String> lobisomem;
	public static ArrayList<String> granadier;
	public static ArrayList<String> rhino;
	public static ArrayList<String> alien;
	public static ArrayList<String> hulk;
	public static ArrayList<String> critical;
	public static ArrayList<String> vitality;
	public static ArrayList<String> quickdropper;

	static {
		Main.settings = SettingsManager.getInstance();
		Main.lista = new ArrayList<String>();
		Main.replies = new HashMap<CommandSender, CommandSender>();
		Main.admin = new ArrayList<String>();
		Main.gladgladiator = new ArrayList<String>();
		Main.invis = new ArrayList<String>();
		Main.reload = new ArrayList<String>();
		Main.kits = new ArrayList<String>();
		Main.skeleton = new ArrayList<String>();
		Main.hg = new ArrayList<String>();
		Main.killstreaks = new HashMap<String, Integer>();
		Main.whister = new ArrayList<String>();
		Main.arqueiro = new ArrayList<String>();
		Main.stomper = new ArrayList<String>();
		Main.Trocador = new ArrayList<String>();
		Main.viper = new ArrayList<String>();
		Main.cooldown = new ArrayList<String>();
		Main.thor = new ArrayList<String>();
		Main.launcher = new ArrayList<String>();
		Main.block = new ArrayList<String>();
		Main.turtle = new ArrayList<String>();
		Main.kitfreeze = new ArrayList<String>();
		Main.construiroon = new ArrayList<String>();
		Main.construiroff = new ArrayList<String>();
		Main.flash = new ArrayList<String>();
		Main.mute = new ArrayList<String>();
		Main.anchor = new ArrayList<String>();
		Main.bomber = new ArrayList<String>();
		Main.bowman = new ArrayList<String>();
		Main.jumper = new ArrayList<String>();
		Main.enderhg = new ArrayList<String>();
		Main.urgal = new ArrayList<String>();
		Main.pyro = new ArrayList<String>();
		Main.monk = new ArrayList<String>();
		Main.Teleporter = new ArrayList<String>();
		Main.fisherman = new ArrayList<String>();
		Main.reaper = new ArrayList<String>();
		Main.vanished = new ArrayList<String>();
		Main.frosty = new ArrayList<String>();
		Main.pisca = new ArrayList<String>();
		Main.specialist = new ArrayList<String>();
		Main.switcher = new ArrayList<String>();
		Main.snail = new ArrayList<String>();
		Main.milkman = new ArrayList<String>();
		Main.flat = new ArrayList<String>();
		Main.kangaroo = new ArrayList<String>();
		Main.gladiator = new ArrayList<String>();
		Main.camel = new ArrayList<String>();
		Main.cooldown1 = new ArrayList<String>();
		Main.grappler = new ArrayList<String>();
		Main.poseidon = new ArrayList<String>();
		Main.fireman = new ArrayList<String>();
		Main.wither = new ArrayList<String>();
		Main.Checkcooldown = new ArrayList<String>();
		Main.tank = new ArrayList<String>();
		Main.endermage = new ArrayList<String>();
		Main.phantom = new ArrayList<String>();
		Main.desafio = new ArrayList<String>();
		Main.ninja = new ArrayList<String>();
		Main.darkmage = new ArrayList<String>();
		Main.soldado = new ArrayList<String>();
		Main.viking = new ArrayList<String>();
		Main.madman = new ArrayList<String>();
		Main.grandpa = new ArrayList<String>();
		Main.ghost = new ArrayList<String>();
		Main.barbarian = new ArrayList<String>();
		Main.spiderman = new ArrayList<String>();
		Main.berserker = new ArrayList<String>();
		Main.indio = new ArrayList<String>();
		Main.ryu = new ArrayList<String>();
		Main.neji = new ArrayList<String>();
		Main.lobisomem = new ArrayList<String>();
		Main.granadier = new ArrayList<String>();
		Main.rhino = new ArrayList<String>();
		Main.alien = new ArrayList<String>();
		Main.hulk = new ArrayList<String>();
		Main.critical = new ArrayList<String>();
		Main.vitality = new ArrayList<String>();
		Main.quickdropper = new ArrayList<String>();
	}

	public void onEnable() {
		((Main) (Main.plugin = (Plugin) this)).autenticacao();
		this.verificarLicensa();
		this.registerEvents(Main.plugin, (Listener) this, (Listener) new Warps(), (Listener) new AntiFlood(),
				(Listener) new Killstreak(), (Listener) new WarpsListener(), (Listener) new Pot(),
				(Listener) new MainList(), (Listener) new Block(), (Listener) new Earlyhg(), (Listener) new Challenge(),
				(Listener) new Pyro(this), (Listener) new JumpBlocks(), (Listener) new Milkman(),
				(Listener) new Gladiator(this), (Listener) new MuteP(), (Listener) new CommandBuild(),
				(Listener) new Jumper(this), (Listener) new Switcher(), (Listener) new Skit(),
				(Listener) new Specialist(), (Listener) new SettingsManager(), (Listener) new Placas(),
				(Listener) new Shooter(this), (Listener) new Endermage(this), (Listener) new WarpsListener(),
				(Listener) new Launcher(this), (Listener) new KitItems(), (Listener) new Events(this),
				(Listener) new Anchor(this), (Listener) new MenuInv(), (Listener) new Fireman(this),
				(Listener) new MenuInvListener(), (Listener) new Thor(this), (Listener) new Fisherman(),
				(Listener) new Camel(), (Listener) new Frosty(), (Listener) new Grappler(), (Listener) new Ninja(),
				(Listener) new Kangaroo(), (Listener) new Monk(this), (Listener) new Trocador(this),
				(Listener) new Tell(), (Listener) new Report(), (Listener) new Reaper(), (Listener) new Eyer(),
				(Listener) new Poseidon(this), (Listener) new Flash(this), (Listener) new Skeleton(this),
				(Listener) new Turtle(this), (Listener) new Snail(), (Listener) new Stomper(), (Listener) new Viper(),
				(Listener) new Darkmage(), (Listener) new Wither(), (Listener) new Soldado(this),
				(Listener) new Custom(), (Listener) new Desafiar(), (Listener) new UltraKits.u1v1.Events(),
				(Listener) new Chat(), (Listener) new Viking(), (Listener) new CombatLog(), (Listener) new Ghost(),
				(Listener) new SpiderMan(), (Listener) new Barbarian(), (Listener) new Berserker(),
				(Listener) new ServerScoreboard(), (Listener) new Indio(), (Listener) new Ryu(), (Listener) new Neji(),
				(Listener) new Lobisomem(), (Listener) new Granadier(), (Listener) new Rhino(),
				(Listener) new Phantom(), (Listener) new Alien(), (Listener) new Hulk(), (Listener) new Critical(),
				(Listener) new SB(), (Listener) new Vitality(), (Listener) new Admin());
		this.comandos();
		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		this.configFiles();
		Main.settings.setup((Plugin) this);
		ItemManager.loadItems();
		Custom.loadCustomItems();
	}

	public void onDisable() {
		this.devolverItems();
		HandlerList.unregisterAll(Main.plugin);
		Main.plugin = null;
	}

	@EventHandler
	public void playerDeath(final PlayerDeathEvent e) {
		final Player p = e.getEntity();
		resetKit(p);
	}

	@EventHandler
	public void respawnar(final PlayerRespawnEvent e) {
		final Player p = e.getPlayer();
		resetKit(p);
		restaurarItens(p);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable) new Runnable() {
			@Override
			public void run() {
				try {
					final World w = Bukkit.getServer().getWorld(Main.settings.getData().getString("spawn.world"));
					final double x = Main.settings.getData().getDouble("spawn.x");
					final double y = Main.settings.getData().getDouble("spawn.y");
					final double z = Main.settings.getData().getDouble("spawn.z");
					final Location loc = new Location(w, x, y, z);
					loc.setPitch((float) Main.settings.getData().getDouble("spawn.pitch"));
					loc.setYaw((float) Main.settings.getData().getDouble("spawn.yaw"));
					p.teleport(loc);
				} catch (IllegalArgumentException ex) {
					p.sendMessage(ChatColor.RED + "Spawn nao definido ainda. Pe\u00e7a a um Staff para seta-lo.");
				}
			}
		}, 1L);
	}

	@EventHandler
	public void antiPluginView(final PlayerCommandPreprocessEvent e) {
		final Player p = e.getPlayer();
		if (e.getMessage().equalsIgnoreCase("/pl") || e.getMessage().equalsIgnoreCase("/plugins")
				|| e.getMessage().equalsIgnoreCase("/plugin") || e.getMessage().equalsIgnoreCase("/help")
				|| e.getMessage().equalsIgnoreCase("/?")) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Aqui n\u00e3o Doid\u00e3o!");
			p.playSound(p.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
		}
	}

	public static WorldGuardPlugin getWorldGuard() {
		final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}
		return (WorldGuardPlugin) plugin;
	}

	public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("[UltraKits] Comando apenas para players in-game.");
			return true;
		}
		final Player p = (Player) sender;
		if (cmd.equalsIgnoreCase("kit")) {
			if (args.length == 0) {
				MenuInv.guiKits(p);
				return true;
			}
			if (args.length == 1) {
				if (Main.kits.contains(p.getName())) {
					p.sendMessage(ChatColor.RED + "Voce ja esta utilizando um kit!");
					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
					return true;
				}
				if (args[0].equalsIgnoreCase("pvp")) {
					if (!p.hasPermission("kit.pvp") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitPvP(p);
				}
				if (args[0].equalsIgnoreCase("arqueiro")) {
					if (!p.hasPermission("kit.arqueiro") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitArcher(p);
				}
				if (args[0].equalsIgnoreCase("urgal")) {
					if (!p.hasPermission("kit.urgal") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitUrgal(p);
				}
				if (args[0].equalsIgnoreCase("shooter")) {
					if (!p.hasPermission("kit.shooter") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitShooter(p);
				}
				if (args[0].equalsIgnoreCase("pyro")) {
					if (!p.hasPermission("kit.pyro") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitpyro(p);
				}
				if (args[0].equalsIgnoreCase("trocador")) {
					if (!p.hasPermission("kit.trocador") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitTrocador(p);
				}
				if (args[0].equalsIgnoreCase("soldado")) {
					if (!p.hasPermission("kit.soldado") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitSoldado(p);
				}
				if (args[0].equalsIgnoreCase("viper")) {
					if (!p.hasPermission("kit.viper") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitViper(p);
				}
				if (args[0].equalsIgnoreCase("ninja")) {
					if (!p.hasPermission("kit.ninja") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitNinja(p);
				}
				if (args[0].equalsIgnoreCase("anchor")) {
					if (!p.hasPermission("kit.anchor") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitAnchor(p);
				}
				if (args[0].equalsIgnoreCase("granadier")) {
					if (!p.hasPermission("kit.granadier") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitGranadier(p);
				}
				if (args[0].equalsIgnoreCase("switcher")) {
					if (!p.hasPermission("kit.switcher") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitSwitcher(p);
				}
				if (args[0].equalsIgnoreCase("darkmage")) {
					if (!p.hasPermission("kit.darkmage") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitDarkmage(p);
				}
				if (args[0].equalsIgnoreCase("thor")) {
					if (!p.hasPermission("kit.thor") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitThor(p);
				}
				if (args[0].equalsIgnoreCase("specialist")) {
					if (!p.hasPermission("kit.specialist") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitSpecialist(p);
				}
				if (args[0].equalsIgnoreCase("launcher")) {
					if (!p.hasPermission("kit.launcher") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitlauncher(p);
				}
				if (args[0].equalsIgnoreCase("milkman")) {
					if (!p.hasPermission("kit.milkman") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitmilkman(p);
				}
				if (args[0].equalsIgnoreCase("skeleton")) {
					if (!p.hasPermission("kit.skeleton") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitskeleton(p);
				}
				if (args[0].equalsIgnoreCase("fisherman")) {
					if (!p.hasPermission("kit.fisherman") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitFisherman(p);
				}
				if (args[0].equalsIgnoreCase("phantom")) {
					if (!p.hasPermission("kit.phantom") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitPhantom(p);
				}
				if (args[0].equalsIgnoreCase("gladiator")) {
					if (!p.hasPermission("kit.gladiator") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitGladiator(p);
				}
				if (args[0].equalsIgnoreCase("flash")) {
					if (!p.hasPermission("kit.flash") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitFlash(p);
				}
				if (args[0].equalsIgnoreCase("grappler")) {
					if (!p.hasPermission("kit.grappler") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitGrappler(p);
				}
				if (args[0].equalsIgnoreCase("endermage")) {
					if (!p.hasPermission("kit.endermage") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitEndermage(p);
				}
				if (args[0].equalsIgnoreCase("monk")) {
					if (!p.hasPermission("kit.monk") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitMonk(p);
				}
				if (args[0].equalsIgnoreCase("camel")) {
					if (!p.hasPermission("kit.camel") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitCamel(p);
				}
				if (args[0].equalsIgnoreCase("frosty")) {
					if (!p.hasPermission("kit.frosty") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitFrosty(p);
				}
				if (args[0].equalsIgnoreCase("wither")) {
					if (!p.hasPermission("kit.wither") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitwither(p);
				}
				if (args[0].equalsIgnoreCase("poseidon")) {
					if (!p.hasPermission("kit.poseidon") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitPoseidon(p);
				}
				if (args[0].equalsIgnoreCase("stomper")) {
					if (!p.hasPermission("kit.stomper") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitstomper(p);
				}
				if (args[0].equalsIgnoreCase("reaper")) {
					if (!p.hasPermission("kit.reaper") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitreaper(p);
				}
				if (args[0].equalsIgnoreCase("turtle")) {
					if (!p.hasPermission("kit.turtle") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitturtle(p);
				}
				if (args[0].equalsIgnoreCase("jumper")) {
					if (!p.hasPermission("kit.jumper") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitJumper(p);
				}
				if (args[0].equalsIgnoreCase("snail")) {
					if (!p.hasPermission("kit.snail") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitSnail(p);
				}
				if (args[0].equalsIgnoreCase("fireman")) {
					if (!p.hasPermission("kit.fireman") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitFireman(p);
				}
				if (args[0].equalsIgnoreCase("kangaroo")) {
					if (!p.hasPermission("kit.kangaroo") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitKangaroo(p);
				}
				if (args[0].equalsIgnoreCase("viking")) {
					if (!p.hasPermission("kit.viking") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitViking(p);
				}
				if (args[0].equalsIgnoreCase("madman")) {
					if (!p.hasPermission("kit.madman") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitMadman(p);
				}
				if (args[0].equalsIgnoreCase("grandpa")) {
					if (!p.hasPermission("kit.grandpa") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitGrandpa(p);
				}
				if (args[0].equalsIgnoreCase("ghost")) {
					if (!p.hasPermission("kit.ghost") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitGhost(p);
				}
				if (args[0].equalsIgnoreCase("teleporter")) {
					if (!p.hasPermission("kit.teleporter") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitTeleporter(p);
				}
				if (args[0].equalsIgnoreCase("spiderman")) {
					if (!p.hasPermission("kit.spiderman") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitSpiderman(p);
				}
				if (args[0].equalsIgnoreCase("barbarian")) {
					if (!p.hasPermission("kit.barbarian") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitBarbarian(p);
				}
				if (args[0].equalsIgnoreCase("berserker")) {
					if (!p.hasPermission("kit.berserker") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitBerserker(p);
				}
				if (args[0].equalsIgnoreCase("indio")) {
					if (!p.hasPermission("kit.indio") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitIndio(p);
				}
				if (args[0].equalsIgnoreCase("ryu")) {
					if (!p.hasPermission("kit.ryu") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitRyu(p);
				}
				if (args[0].equalsIgnoreCase("neji")) {
					if (!p.hasPermission("kit.neji") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitNeji(p);
				}
				if (args[0].equalsIgnoreCase("lobisomem")) {
					if (!p.hasPermission("kit.lobisomem") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitLobisomem(p);
				}
				if (args[0].equalsIgnoreCase("rhino")) {
					if (!p.hasPermission("kit.rhino") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitRhino(p);
				}
				if (args[0].equalsIgnoreCase("phantom")) {
					if (!p.hasPermission("kit.phantom") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitPhantom(p);
				}
				if (args[0].equalsIgnoreCase("alien")) {
					if (!p.hasPermission("kit.alien") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitAlien(p);
				}
				if (args[0].equalsIgnoreCase("hulk")) {
					if (!p.hasPermission("kit.hulk") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitHulk(p);
				}
				if (args[0].equalsIgnoreCase("critical")) {
					if (!p.hasPermission("kit.critical") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitCritical(p);
				}
				if (args[0].equalsIgnoreCase("quickdropper")) {
					if (!p.hasPermission("kit.quickdropper") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitQuickDropper(p);
				}
				if (args[0].equalsIgnoreCase("vitality")) {
					if (!p.hasPermission("kit.vitality") && !p.hasPermission("kit.*")) {
						p.sendMessage(Main.plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						return true;
					}
					KitItems.kitVitality(p);
				}
			}
		} else if (cmd.equalsIgnoreCase("kits")) {
			MenuInv.guiKits(p);
			return true;
		}
		return false;
	}

	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getItemInHand().equals((Object) Events.kits)) {
				MenuInv.guiKits(p);
			} else if (p.getItemInHand().equals((Object) Events.loja)) {
				p.chat("/" + Main.plugin.getConfig().getString("ComandoBuycraft"));
			} else if (p.getItemInHand().equals((Object) Events.warps)) {
				Warps.guiKits(p);
			}
		}
	}

	public void setExecutor(final String command, final CommandExecutor executor) {
		this.getCommand(command).setExecutor(executor);
	}

	public void registerEvents(final Plugin plugin, final Listener... listeners) {
		for (final Listener listener : listeners) {
			Bukkit.getPluginManager().registerEvents(listener, plugin);
		}
	}

	public void comandos() {
		this.getCommand("1v1").setExecutor((CommandExecutor) new Commands());
		this.getCommand("spawn").setExecutor((CommandExecutor) new Comandos(this));
		this.getCommand("setspawn").setExecutor((CommandExecutor) new Comandos(this));
		this.getCommand("morrer").setExecutor((CommandExecutor) new Comandos(this));
		this.getCommand("gm").setExecutor((CommandExecutor) new Comandos(this));
		this.getCommand("mute").setExecutor((CommandExecutor) new Comandos(this));
		this.getCommand("desmute").setExecutor((CommandExecutor) new Comandos(this));
		this.getCommand("tpall").setExecutor((CommandExecutor) new Comandos(this));
		this.getCommand("fly").setExecutor((CommandExecutor) new Comandos(this));
		this.getCommand("warp").setExecutor((CommandExecutor) new Comandos(this));
		this.getCommand("cor").setExecutor((CommandExecutor) new Tag());
		this.getCommand("skit").setExecutor((CommandExecutor) new Skit());
		this.getCommand("lc").setExecutor((CommandExecutor) new Challenge());
		this.getCommand("fps").setExecutor((CommandExecutor) new MainList());
		this.getCommand("sair").setExecutor((CommandExecutor) new Comandos(this));
		this.getCommand("sky").setExecutor((CommandExecutor) new Pot());
		this.getCommand("evento").setExecutor((CommandExecutor) new Earlyhg());
		this.getCommand("tell").setExecutor((CommandExecutor) new Tell());
		this.getCommand("head").setExecutor((CommandExecutor) new Comandos(this));
		this.getCommand("build").setExecutor((CommandExecutor) new CommandBuild());
		this.getCommand("report").setExecutor((CommandExecutor) new Report());
		this.getCommand("cc").setExecutor((CommandExecutor) new Chat());
		this.getCommand("admin").setExecutor((CommandExecutor) new Admin());
		this.getCommand("invsee").setExecutor((CommandExecutor) new Admin());
		this.getCommand("v").setExecutor((CommandExecutor) new Admin());
		this.getCommand("ac").setExecutor((CommandExecutor) new Admin());
		this.getCommand("kick").setExecutor((CommandExecutor) new Comandos(this));
	}

	public void autenticacao() {
		URL host = null;
		try {
			host = new URL("http://prokits.url.ph/licencas.txt");
		} catch (MalformedURLException e1) {
			Bukkit.shutdown();
		}
		URLConnection connection = null;
		try {
			connection = host.openConnection();
		} catch (IOException e2) {
			Bukkit.shutdown();
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (IOException e3) {
			Bukkit.shutdown();
		}
		try {
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				Main.lista.add(inputLine);
			}
		} catch (IOException e4) {
			Bukkit.shutdown();
		}
	}

	public void verificarLicensa() {
		new BukkitRunnable() {
			public void run() {
				if (Main.lista.contains(String.valueOf(Bukkit.getIp()) + ":" + Bukkit.getPort())) {
					Bukkit.getConsoleSender().sendMessage("§b[UltraKits] §aServer autorizado.");
					Bukkit.getConsoleSender().sendMessage(
							"§b[UltraKits] §aO IP deste Servidor:§f " + Bukkit.getIp() + ":" + Bukkit.getPort());
				} else {
					Bukkit.getConsoleSender().sendMessage("§b[UltraKits] §cServer nao autorizado.");
					Bukkit.getConsoleSender().sendMessage("§aPossiveis causas:");
					Bukkit.getConsoleSender().sendMessage("§c- Voce nao esta conectado a internet");
					Bukkit.getConsoleSender()
							.sendMessage("§c- O IP informado ao vendedor nao corresponde ao dessa maquina");
					Bukkit.getConsoleSender().sendMessage("§c- Voce nao possui os direitos desse plugin");
					Bukkit.getConsoleSender().sendMessage("§c- Voce esta usando uma copia roubada");
					Bukkit.getConsoleSender().sendMessage("§aCompre esse plugin pelo skype: §bdlogylnaelc");
					Bukkit.shutdown();
				}
			}
		}.runTaskLater(Main.plugin, 60L);
	}

	public void devolverItems() {
		if (!Commands.inv.isEmpty()) {
			for (final Map.Entry<String, ItemStack[]> e : Commands.inv.entrySet()) {
				final Player p = Bukkit.getPlayer((String) e.getKey());
				if (p != null) {
					p.getInventory().setContents((ItemStack[]) e.getValue());
				}
			}
		}
		if (!Commands.arm.isEmpty()) {
			for (final Map.Entry<String, ItemStack[]> e : Commands.arm.entrySet()) {
				final Player p = Bukkit.getPlayer((String) e.getKey());
				if (p != null) {
					p.getEquipment().setArmorContents((ItemStack[]) Commands.arm.get(e.getKey()));
				}
			}
		}
		Player[] onlinePlayers;
		for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
			final Player p2 = onlinePlayers[i];
			if (Commands.em.contains(p2.getName())) {
				Commands.em.remove(p2.getName());
				if (Main.data.getConfigurationSection("Saida") != null) {
					p2.teleport(new Location(Bukkit.getWorld(Main.data.getString("Saida.world")),
							Main.data.getDouble("Saida.x"), Main.data.getDouble("Saida.y"),
							Main.data.getDouble("Saida.z"), (float) Main.data.getDouble("Saida.yaw"),
							(float) Main.data.getDouble("Saida.pitch")));
				} else {
					p2.teleport(p2.getWorld().getSpawnLocation());
				}
			}
		}
	}

	public void salvarStats() {
		for (final Map.Entry<String, Score> e : SB.kills.entrySet()) {
			final String p = e.getKey();
			final Score score = e.getValue();
			Main.stats.set(String.valueOf(p) + ".kills", (Object) score.getScore());
		}
		for (final Map.Entry<String, Score> e : SB.deaths.entrySet()) {
			final String p = e.getKey();
			final Score score = e.getValue();
			Main.stats.set(String.valueOf(p) + ".deaths", (Object) score.getScore());
		}
		for (final Map.Entry<String, Score> e : SB.killstreak.entrySet()) {
			final String p = e.getKey();
			final Score score = e.getValue();
			Main.stats.set(String.valueOf(p) + ".killstreak", (Object) score.getScore());
		}
		try {
			Main.stats.save(Main.s);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public void salvaStats2() {
		for (final Map.Entry<String, Score> e : ServerScoreboard.kills.entrySet()) {
			final String p = e.getKey();
			final Score score = e.getValue();
			Main.m.set(String.valueOf(p) + ".kills", (Object) score.getScore());
		}
		for (final Map.Entry<String, Score> e : ServerScoreboard.deaths.entrySet()) {
			final String p = e.getKey();
			final Score score = e.getValue();
			Main.m.set(String.valueOf(p) + ".deaths", (Object) score.getScore());
		}
		for (final Map.Entry<String, Score> e : ServerScoreboard.killstreak.entrySet()) {
			final String p = e.getKey();
			final Score score = e.getValue();
			Main.m.set(String.valueOf(p) + ".killstreak", (Object) score.getScore());
		}
		try {
			Main.m.save(Main.n);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public void configFiles() {
		Main.d = new File(Main.plugin.getDataFolder(), "data1v1.yml");
		Main.data = (FileConfiguration) YamlConfiguration.loadConfiguration(Main.d);
		try {
			Main.data.save(Main.d);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.k = new File(Main.plugin.getDataFolder(), "kits1v1.yml");
		Main.kit = (FileConfiguration) YamlConfiguration.loadConfiguration(Main.k);
		try {
			Main.kit.save(Main.k);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.s = new File(Main.plugin.getDataFolder(), "score1v1.yml");
		Main.stats = (FileConfiguration) YamlConfiguration.loadConfiguration(Main.s);
		try {
			Main.stats.save(Main.s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.w = new File(Main.plugin.getDataFolder(), "warps.yml");
		Main.warps = (FileConfiguration) YamlConfiguration.loadConfiguration(Main.w);
		try {
			Main.warps.save(Main.w);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.n = new File(Main.plugin.getDataFolder(), "scores.yml");
		Main.m = (FileConfiguration) YamlConfiguration.loadConfiguration(Main.n);
		try {
			Main.m.save(Main.n);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void resetKit(final Player p) {
		Main.kits.remove(p.getName());
		Main.hg.remove(p.getName());
		Main.arqueiro.remove(p.getName());
		Main.fisherman.remove(p.getName());
		Main.stomper.remove(p.getName());
		Main.reaper.remove(p.getName());
		Main.kangaroo.remove(p.getName());
		Main.urgal.remove(p.getName());
		Main.fireman.remove(p.getName());
		Main.viper.remove(p.getName());
		Main.poseidon.remove(p.getName());
		Main.pyro.remove(p.getName());
		Main.whister.remove(p.getName());
		Main.camel.remove(p.getName());
		Main.endermage.remove(p.getName());
		Main.darkmage.remove(p.getName());
		Main.soldado.remove(p.getName());
		Main.tank.remove(p.getName());
		Main.desafio.remove(p.getName());
		Main.anchor.remove(p.getName());
		Main.ninja.remove(p.getName());
		Main.grappler.remove(p.getName());
		Main.Trocador.remove(p.getName());
		Main.specialist.remove(p.getName());
		Main.milkman.remove(p.getName());
		Main.tank.remove(p.getName());
		Main.thor.remove(p.getName());
		Main.frosty.remove(p.getName());
		Main.launcher.remove(p.getName());
		Main.flash.remove(p.getName());
		Main.skeleton.remove(p.getName());
		Main.turtle.remove(p.getName());
		Main.monk.remove(p.getName());
		Main.snail.remove(p.getName());
		Main.jumper.remove(p.getName());
		Main.switcher.remove(p.getName());
		Main.gladiator.remove(p.getName());
		Main.wither.remove(p.getName());
		Main.phantom.remove(p.getName());
		Main.reload.remove(p.getName());
		Main.cooldown.remove(p.getName());
		Main.cooldown1.remove(p.getName());
		Monk.coolmonk.remove(p.getName());
		Main.viking.remove(p.getName());
		Main.madman.remove(p.getName());
		Main.grandpa.remove(p.getName());
		Main.ghost.remove(p.getName());
		Main.barbarian.remove(p.getName());
		Main.spiderman.remove(p.getName());
		Main.berserker.remove(p.getName());
		Main.Teleporter.remove(p.getName());
		Main.indio.remove(p.getName());
		Main.ryu.remove(p.getName());
		Main.neji.remove(p.getName());
		Main.lobisomem.remove(p.getName());
		Main.granadier.remove(p.getName());
		Main.rhino.remove(p.getName());
		Main.alien.remove(p.getName());
		Main.hulk.remove(p.getName());
		Main.critical.remove(p.getName());
		Main.vitality.remove(p.getName());
		Main.quickdropper.remove(p.getName());
		Ghost.resetGhost(p);
		Block.aff.remove(p.getName());
	}

	public static void restaurarItens(final Player p) {
		p.getInventory().clear();
		p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR),
				new ItemStack(Material.AIR), new ItemStack(Material.AIR) });
		final ItemMeta rkits = Events.kits.getItemMeta();
		rkits.setDisplayName(ChatColor.GREEN + "Kits de: " + p.getName());
		Events.kits.setItemMeta(rkits);
		Events.warps = new ItemStack(Material.NAME_TAG);
		final ItemMeta rwarps = Events.warps.getItemMeta();
		rwarps.setDisplayName(ChatColor.GOLD + "Warps");
		Events.warps.setItemMeta(rwarps);
		final ItemMeta rloja = Events.loja.getItemMeta();
		rloja.setDisplayName(ChatColor.GREEN + "Loja");
		Events.loja.setItemMeta(rloja);
		p.getInventory().setItem(5, Events.kits);
		p.getInventory().setItem(3, Events.warps);
		p.getInventory().setItem(4, Events.loja);
	}

	public static boolean areaPvP(final Player p) {
		final ApplicableRegionSet region = getWorldGuard().getRegionManager(p.getWorld())
				.getApplicableRegions(p.getLocation());
		return region.allows(DefaultFlag.PVP);
	}

	public static boolean inGladiator(final Player p) {
		return Gladiator.fighting.containsKey(p.getName());
	}
}
