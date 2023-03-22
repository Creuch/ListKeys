package me.creuch.listkeys.inventory;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.gui.type.util.Gui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import me.creuch.listkeys.ListKeys;
import me.creuch.listkeys.Messages;
import me.creuch.listkeys.functions.connect;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainGUI {
    public static ListKeys instance = ListKeys.getInstance();
    public static FileConfiguration config = instance.getConfig();

    public static Gui buildInventory() {
        MiniMessage mm = MiniMessage.miniMessage();
        ChestGui gui = new ChestGui(3, ComponentHolder.of(mm.deserialize(Messages.replaceColors(config.getString("prefix")))));
        StaticPane paneMcList = new StaticPane(0, 0, 9, 3);
        GuiItem mcListItem = new GuiItem(new ItemStack(Material.RED_CONCRETE));
        ItemMeta mcListMeta = mcListItem.getItem().getItemMeta();
        mcListMeta.displayName(mm.deserialize(Messages.replaceColors("<#fb5a00>&lM<#fb6900>&lc<#fc7900>&lL<#fc8800>&li<#fd9800>&ls<#fda700>&lt")));
        List<Component> mcListLore = new ArrayList<>();
        mcListLore.add(mm.deserialize(Messages.replaceColors("&8 ")));
        mcListLore.add(mm.deserialize(Messages.replaceColors("&8» &6Kliknij &lLPM&6, aby odebrać nagrodę z&f McList&6!")));
        mcListLore.add(mm.deserialize(Messages.replaceColors("&8» &6Kliknij &lPPM&6, aby otrzymać link do głosowania&6!")));
        mcListMeta.lore(mcListLore);
        mcListItem.getItem().setItemMeta(mcListMeta);
        paneMcList.addItem(mcListItem, Slot.fromIndex(11));
        StaticPane paneMcNagroda = new StaticPane(0, 0, 9, 3);
        GuiItem mcNagrodaItem = new GuiItem(new ItemStack(Material.MAGENTA_CONCRETE));
        ItemMeta mcNagrodaMeta = mcNagrodaItem.getItem().getItemMeta();
        mcNagrodaMeta.displayName(mm.deserialize(Messages.replaceColors("<#ab00fb>&lM<#b500f8>&lc<#c000f5>&lN<#ca00f2>&la<#d400f0>&lg<#de00ed>&lr<#e900ea>&lo<#f300e7>&ld<#fd00e4>&la")));
        List<Component> mcNagrodaLore = new ArrayList<>();
        mcNagrodaLore.add(mm.deserialize(Messages.replaceColors("&8 ")));
        mcNagrodaLore.add(mm.deserialize(Messages.replaceColors("&8» &6Kliknij &lLPM&6, aby odebrać nagrodę z&f McNagroda&6!")));
        mcListLore.add(mm.deserialize(Messages.replaceColors("&8» &6Kliknij &lPPM&6, aby otrzymać link do głosowania&6!")));
        mcNagrodaMeta.lore(mcNagrodaLore);
        mcNagrodaItem.getItem().setItemMeta(mcNagrodaMeta);
        paneMcNagroda.addItem(mcNagrodaItem, Slot.fromIndex(15));
        gui.addPane(paneMcList);
        gui.addPane(paneMcNagroda);
        StaticPane pane = new StaticPane(0, 0, 9, 3);
        GuiItem item = new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        ItemMeta meta = item.getItem().getItemMeta();
        meta.displayName(mm.deserialize("<bold> "));
        item.getItem().setItemMeta(meta);
        for(int i = 0; i < gui.getInventory().getSize(); i++) {
            if(i != 11 && i != 15) {
                pane.addItem(item, Slot.fromIndex(i));
            }
        }
        gui.setOnTopClick(MainGUI::callItems);
        gui.addPane(pane);
        return gui;
    }

    public static void callItems(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);
        if(e.getSlot() == 11) {
            p.closeInventory();
            if(e.isLeftClick()) {
                connect.connectMcList(p);
            } else if(e.isRightClick()) {
                Component textComponent = MiniMessage.miniMessage().deserialize(Messages.replaceColors(config.getString("prefix") + "&7Link do oddania głosu "))
                        .clickEvent(ClickEvent.openUrl(config.getString("mcListLink")))
                        .hoverEvent(HoverEvent.showText(MiniMessage.miniMessage().deserialize("<white>Kliknij, aby otworzyć link!")))
                        .append(MiniMessage.miniMessage().deserialize(Messages.replaceColors("&8[&fᴋʟɪᴋ&8]"))
                        );
                p.sendMessage(textComponent);
            }
        } else if(e.getSlot() == 15) {
            p.closeInventory();
            if(e.isLeftClick()) {
                connect.connectMcNagroda(p);
            } else if(e.isRightClick()) {
                Component textComponent = MiniMessage.miniMessage().deserialize(Messages.replaceColors(config.getString("prefix") + "&7Link do oddania głosu "))
                        .clickEvent(ClickEvent.openUrl(config.getString("mcNagrodaLink")))
                        .hoverEvent(HoverEvent.showText(MiniMessage.miniMessage().deserialize("<white>Kliknij, aby otworzyć link!")))
                        .append(MiniMessage.miniMessage().deserialize(Messages.replaceColors("&8[&fᴋʟɪᴋ&8]"))
                        );
                p.sendMessage(textComponent);
            }
        }
    }
}
