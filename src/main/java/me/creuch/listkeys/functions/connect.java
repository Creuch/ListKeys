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

public class connect {
    public static ListKeys instance = ListKeys.getInstance();
    public static FileConfiguration config = instance.getConfig();

    public static void connectMcList(Player p) {
        String out =
                URI.create(
                        "https://www.mclist.pl/api/1.1/like/"
                                + p.getAddress().getAddress().getHostAddress()
                                + "/"
                                + config.getString("mclistServerIp")
                ).getScheme();
        if (out.equalsIgnoreCase("1")) {
            Messages.send(p, "&7Głos został oddany! Oto Twoja nagroda");
        }
    }

    public static void connectMcNagroda(Player p) {
        URI out =
                URI.create(
                        "https://serwery-minecraft.pl/api/server-by-key/"
                                + config.getString("mcnagrodaToken")
                                + "/get-vote/"
                                + p.getName()
                );
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode outInJson = mapper.readTree(out.toURL());
            if(outInJson.get("can_claim_reward").asBoolean()) {
                Messages.send(p, "&aPrzyznano nagrodę!");
            } else {
                Messages.send(p, "&cNie możesz odebrać nagrody!");
                Messages.send(p, "&7Błąd: " + outInJson.get("error"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


