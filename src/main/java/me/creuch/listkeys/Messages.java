package me.creuch.listkeys;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class Messages {
    static ListKeys instance = ListKeys.getInstance();
    static String prefix = instance.getConfig().getString("prefix");


    public static void send(CommandSender p, String msg) {
        MiniMessage mm = MiniMessage.miniMessage();
        String newMessage = replaceColors(msg);
        if(instance.getConfig().getBoolean("prefixEnabled")) {
            p.sendMessage(mm.deserialize(replaceColors(prefix + newMessage)));
        } else {
            p.sendMessage(mm.deserialize(replaceColors(newMessage)));
        }
    }

    public static void send(HumanEntity p, String msg) {
        MiniMessage mm = MiniMessage.miniMessage();
        String newMessage = replaceColors(msg);
        if(instance.getConfig().getBoolean("prefixEnabled")) {
            p.sendMessage(mm.deserialize(replaceColors(prefix + newMessage)));
        } else {
            p.sendMessage(mm.deserialize(replaceColors(newMessage)));
        }
    }

    public static void send(Player p, String msg) {
        MiniMessage mm = MiniMessage.miniMessage();
        String newMessage = replaceColors(msg);
        if(instance.getConfig().getBoolean("prefixEnabled")) {
            p.sendMessage(mm.deserialize(replaceColors(prefix + newMessage)));
        } else {
            p.sendMessage(mm.deserialize(replaceColors(newMessage)));
        }
    }
    public static void send(Audience p, String msg) {
        MiniMessage mm = MiniMessage.miniMessage();
        String newMessage = replaceColors(msg);
        if(instance.getConfig().getBoolean("prefixEnabled")) {
            p.sendMessage(mm.deserialize(replaceColors(prefix + newMessage)));
        } else {
            p.sendMessage(mm.deserialize(replaceColors(newMessage)));
        }
    }
    public static void sendConsole(String msg) {
        MiniMessage mm = MiniMessage.miniMessage();
        Audience console = (Audience) instance.getServer().getConsoleSender();
        String newMessage = replaceColors(msg);
        if(instance.getConfig().getBoolean("prefixEnabled")) {
            console.sendMessage(mm.deserialize(replaceColors(prefix + "&7 " + newMessage)));
        } else {
            console.sendMessage(mm.deserialize(replaceColors("&7 " + newMessage)));
        }

    }
    public static String replaceColors(String msg) {
        msg = msg.replace("&0", "<reset><black>");
        msg = msg.replace("&1", "<reset><dark_blue>");
        msg = msg.replace("&2", "<reset><dark_green>");
        msg = msg.replace("&3", "<reset><dark_aqua>");
        msg = msg.replace("&4", "<reset><dark_red>");
        msg = msg.replace("&5", "<reset><dark_purple>");
        msg = msg.replace("&6", "<reset><gold>");
        msg = msg.replace("&7", "<reset><gray>");
        msg = msg.replace("&8", "<reset><dark_gray>");
        msg = msg.replace("&9", "<reset><blue>");
        msg = msg.replace("&a", "<reset><green>");
        msg = msg.replace("&b", "<reset><aqua>");
        msg = msg.replace("&c", "<reset><red>");
        msg = msg.replace("&d", "<reset><light_purple>");
        msg = msg.replace("&e", "<reset><yellow>");
        msg = msg.replace("&f", "<reset><white>");
        msg = msg.replace("&l", "<bold>");
        msg = msg.replace("&m", "<st>");
        msg = msg.replace("&n", "<u>");
        msg = msg.replace("&o", "<italic>");
        msg = msg.replace("&r", "<reset>");
        return msg;
    }
}

