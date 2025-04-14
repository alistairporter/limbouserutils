package xyz.aporter.limbouserutils;

import com.loohp.limbo.Limbo;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.commands.TabCompletor;
import com.loohp.limbo.events.EventHandler;
import com.loohp.limbo.events.Listener;
import com.loohp.limbo.events.player.PlayerJoinEvent;
import com.loohp.limbo.plugins.LimboPlugin;
import com.loohp.limbo.plugins.PluginManager;
import net.md_5.bungee.api.ChatColor;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class LimboUserUtils extends LimboPlugin implements Listener {

    @Override
    public void onEnable() {
        try {
            Limbo.getInstance().getPluginManager().registerCommands(this, new CustomCommands());
            Limbo.getInstance().getConsole().sendMessage("[LimboUserUtils] Initializing Plugin ");
            Limbo.getInstance().getEventsManager().registerEvents(this, this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        try {
            String playername = event.getPlayer().getName();
            Limbo.getInstance().getConsole().sendMessage("[LimboUserUtils] Player Join Detected for " + playername + "! ");
            event.getPlayer().sendMessage(ChatColor.RED + "Hello  " + ChatColor.GREEN + playername);
            event.getPlayer().sendMessage(ChatColor.RED + "If you are seeing this, either you have misspelt the server address, or the server has fallen on it's arse.");
            event.getPlayer().sendMessage(ChatColor.RED + "If neither of these are the case and you are a random script kiddie, please leave and rid us of your presence.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public class CustomCommands implements CommandExecutor, TabCompletor {
        @Override
        public void execute(CommandSender sender, String[] args) {
            String[] insults = new String[]{
                "You really ought to lookup the definition of insanity.",
                "Your mother smells like hamsters and your father smells of elderberries!",
                "Never gonna give you up, never gonna let you down...",
                "Seriously? You really though that would work?",
                "You're the kind of person who eats glue for fun, aren't you?",
                "Your presence lowers the brightness of a room.",
                "May both sides of your pillow be uncomfortably warm.",
                "I'm jealous of all the people who haven't had to meet you.",
                "You couldn't pour water out of a boot if the instructions were on the heel.",
                "You're about as useful as a marzipan dildo.",
                "You're so dense that light bends around you.",
                "Did you know that 90% of household dust is dead human skin? That's what you are to me.",
                "I’d love to stop and chat but I’d rather have type 2 diabetes.",
                "You're like that coffee machine! Bean to cup, you fuck up."
            };
            Random rand = new Random();

            if (args.length == 0) {
                return;
            }

            if (args[0].equalsIgnoreCase("op") || args[0].equalsIgnoreCase("stop") || args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("summon") || args[0].equalsIgnoreCase("tp")) {
                sender.sendMessage(ChatColor.RED + insults[rand.nextInt(insults.length)]);
            }
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String[] args){
            List<String> tab = new ArrayList<>();
            tab.add("op");
            tab.add("stop");
            tab.add("give");
            tab.add("summon");
            tab.add("summon");
            tab.add("tp");
            return tab;
        }
    }
}
