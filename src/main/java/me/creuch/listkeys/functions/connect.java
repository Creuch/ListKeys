package me.creuch.listkeys.functions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jpackage.internal.IOUtils;
import me.creuch.listkeys.ListKeys;
import me.creuch.listkeys.Messages;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class connect {
    public static ListKeys instance = ListKeys.getInstance();
    public static FileConfiguration config = instance.getConfig();

    public static void connectMcList(Player p) {
        URI out =
                URI.create(
                        "https://www.mclist.pl/api/1.1/like/"
                                + p.getAddress().getAddress().getHostAddress()
                                + "/"
                                + config.getString("mcListServerIp")
                );
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode outInJson = mapper.readTree(out.toURL());
            if (outInJson.asText().equalsIgnoreCase("1")) {
                Messages.send(p, "&7Głos został oddany! Oto Twoja nagroda");
                List<String> commands = (List<String>) config.getList("mcListCommands");
                String command;
                for(int i = 0; i < commands.size(); i++) {
                    command = commands.get(i);
                    command = command.replace("{PLAYER}", p.getName());
                    instance.getServer().dispatchCommand(instance.getServer().getConsoleSender(), command);
                }
            } else {
                Messages.send(p, "&cSprawdzenie głosu nie powiodło się!");
                Messages.send(p, "&7Spróbuj zagłosować ponownie.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void connectMcNagroda(Player p) {
        URI out =
                URI.create(
                        "https://serwery-minecraft.pl/api/server-by-key/"
                                + config.getString("mcNagrodaToken")
                                + "/get-vote/"
                                + p.getName()
                );
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode outInJson = mapper.readTree(out.toURL());
            if(outInJson.get("can_claim_reward").asBoolean()) {
                Messages.send(p, "&7Głos został oddany! Oto Twoja nagroda");
                List<String> commands = (List<String>) config.getList("mcNagrodaCommands");
                String command;
                for(int i = 0; i < commands.size(); i++) {
                    command = commands.get(i);
                    command = command.replace("{PLAYER}", p.getName());
                    instance.getServer().dispatchCommand(instance.getServer().getConsoleSender(), command);
                }
            } else {
                Messages.send(p, "&cNie możesz odebrać nagrody!");
                Messages.send(p, "&7Błąd: " + outInJson.get("error"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


