package me.creuch.listkeys.inventory;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.gui.type.util.Gui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import me.creuch.listkeys.ListKeys;
import me.creuch.listkeys.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainGUI {
    public static ListKeys instance = ListKeys.getInstance();
    public static FileConfiguration config = instance.getConfig();

    public static Gui buildInventory() {
        MiniMessage mm = MiniMessage.miniMessage();
        ChestGui gui = new ChestGui(3, ComponentHolder.of(mm.deserialize(Messages.replaceColors(config.getString("prefix")))));
        StaticPane pane = new StaticPane(0, 0, 9, 3);
        pane.setOnClick(inventoryClickEvent -> inventoryClickEvent.setCancelled(true));
        GuiItem item = new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        ItemMeta meta = item.getItem().getItemMeta();
        meta.displayName(mm.deserialize("<bold> "));
        item.getItem().setItemMeta(meta);
        for(int i = 0; i < gui.getInventory().getSize(); i++) {
            pane.addItem(item, Slot.fromIndex(i));
        }
        gui.addPane(pane);
        StaticPane paneMcList = new StaticPane(0, 0, 1, 1);
        GuiItem mcListItem = new GuiItem(new ItemStack(Material.RED_CONCRETE));
        ItemMeta mcListmeta = mcListItem.getItem().getItemMeta();
        mcListmeta.displayName(mm.deserialize(Messages.replaceColors("<#fb5a00>&lM<#fb6900>&lc<#fc7900>&lL<#fc8800>&li<#fd9800>&ls<#fda700>&lt")));
        meta.lore().add(mm.deserialize(Messages.replaceColors("&8» &6Kliknij &lLPM&6, aby odebrać nagrodę z&f McList&6!")));
        mcListItem.getItem().setItemMeta(meta);
        paneMcList.addItem(mcListItem, Slot.fromIndex(11));
        StaticPane paneMcNagroda = new StaticPane(0, 0, 1, 1);
        GuiItem mcNagrodaItem = new GuiItem(new ItemStack(Material.MAGENTA_CONCRETE));
        ItemMeta mcNagrodaMeta = mcNagrodaItem.getItem().getItemMeta();
        mcNagrodaMeta.displayName(mm.deserialize(Messages.replaceColors("<#ab00fb>&lM<#b500f8>&lc<#c000f5>&lN<#ca00f2>&la<#d400f0>&lg<#de00ed>&lr<#e900ea>&lo<#f300e7>&ld<#fd00e4>&la")));
        meta.lore().add(mm.deserialize(Messages.replaceColors("&8» &6Kliknij &lLPM&6, aby odebrać nagrodę z&f McNagroda&6!")));
        mcListItem.getItem().setItemMeta(meta);
        paneMcNagroda.addItem(mcNagrodaItem, Slot.fromIndex(15));
        gui.addPane(paneMcList);
        gui.addPane(paneMcNagroda);
        return gui;
    }
}
