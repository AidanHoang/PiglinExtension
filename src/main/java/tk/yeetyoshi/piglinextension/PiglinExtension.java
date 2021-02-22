package tk.yeetyoshi.piglinextension;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

import static org.bukkit.scoreboard.Criterias.*;

public final class PiglinExtension extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("[PiglinExtension] Piglin Extension has been successfully started up!");
        // Plugin startup logic

        this.getCommand("stats").setExecutor(new commandStats());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    public class commandStats implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            if (Bukkit.getScoreboardManager().getMainScoreboard() == null) {
                Scoreboard board = manager.getNewScoreboard();
            }
            Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
            if (board.getObjective("totalkill") == null) {
                board.registerNewObjective("totalkill", TOTAL_KILLS);
            }
            if (board.getObjective("playerkill") == null) {
                board.registerNewObjective("playerkill", PLAYER_KILLS);
            }
            if (board.getObjective("totaldeath") == null) {
                board.registerNewObjective("totaldeath", DEATHS);
            }

            Player player = (Player) sender;
            Objective objTotal = player.getScoreboard().getObjective("totalkill");
            Objective objPlayer = player.getScoreboard().getObjective("playerkill");
            Objective objDeaths = player.getScoreboard().getObjective("totaldeath");

            Score totalKills = objTotal.getScore(player);
            Score playerKills = objPlayer.getScore(player);
            Score playerDeaths = objDeaths.getScore(player);

            if (cmd.getName().equalsIgnoreCase("stats") && !(player == null)) {
                Team team = player.getScoreboard().getPlayerTeam(player);
                player.sendMessage("§9§lPiglin §4§lTrouble §f§lStats:");
                player.sendMessage("§8=====================");
                player.sendMessage("§6Welcome " + player.getName() + " to Piglin Trouble!");
                player.sendMessage("§fYour Team: §d" + team.getName() + (" §fTeam"));
                player.sendMessage("§fTotal Kills: §b" + totalKills.getScore() + " §fKills");
                player.sendMessage("§fPlayer Kills: §e" + playerKills.getScore() + " §fKills");
                player.sendMessage("§fTotal Deaths: §c" + playerDeaths.getScore() + " §fDeaths");
                player.sendMessage("§8=====================");
                player.sendMessage("§7§oPiglin §7§oExtension §7§oby §7§oYeetYoshi");
            }
            else {
                player.sendMessage("§cYou have sent an INVALID player!");
                }
            {
                return false;
            }

        }
    }
}
