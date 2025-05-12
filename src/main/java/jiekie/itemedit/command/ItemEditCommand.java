package jiekie.itemedit.command;

import jiekie.itemedit.ItemEditPlugin;
import jiekie.itemedit.util.ChatUtil;
import jiekie.itemedit.util.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ItemEditCommand implements CommandExecutor {
    private final ItemEditPlugin plugin;

    public ItemEditCommand(ItemEditPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            ChatUtil.notPlayer(sender);
            return true;
        }

        Player player = (Player) sender;
        if(!player.isOp()) {
            ChatUtil.notOp(player);
            return true;
        }

        if(args == null || args.length == 0) {
            ChatUtil.commandHelper(player);
            return true;
        }

        switch (args[0]) {
            case "이름":
                setItemName(player, args);
                break;

            case "설명":
                addItemlore(player, args);
                break;

            case "설명제거":
                removeItemLore(player);
                break;

            case "도움말":
                ChatUtil.commandList(player);
                break;

            default:
                ChatUtil.commandHelper(player);
                break;
        }

        return true;
    }

    /* 이름 */
    private void setItemName(Player player, String[] args) {
        if(args.length == 1) {
            player.sendMessage(ChatUtil.wrongCommand() + " (/아이템 이름 아이템명)");
            return;
        }

        if(itemDoesNotExistInHand(player)) {
            ChatUtil.showMessage(player, ChatUtil.NO_ITEM);
            return;
        }

        String itemName = ChatColor.RESET + getContents(args);

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemName);
        item.setItemMeta(meta);

        ChatUtil.showMessage(player, ChatUtil.ITEM_NAME_IS_CHANGED);
        SoundUtil.playNoteBlockBell(player);
    }

    private boolean itemDoesNotExistInHand(Player player) {
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();
        if(item.getType() == Material.AIR) {
            return true;
        }

        return false;
    }

    /* 설명 */
    private void addItemlore(Player player, String[] args) {
        if(itemDoesNotExistInHand(player)) {
            ChatUtil.showMessage(player, ChatUtil.NO_ITEM);
            return;
        }

        String contents = null;
        if(args.length == 1)
            contents = " ";
        else
            contents = ChatColor.RESET.toString() + ChatColor.DARK_GRAY + getContents(args);

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        if(meta.hasLore()) {
            List<String> lore = meta.getLore();
            lore.add(contents);
            meta.setLore(lore);

        } else {
            meta.setLore(Arrays.asList((contents)));
        }
        item.setItemMeta(meta);

        ChatUtil.showMessage(player, ChatUtil.ITEM_LORE_IS_ADDED);
        SoundUtil.playNoteBlockBell(player);
    }

    /* 설명 제거 */
    private void removeItemLore(Player player) {
        if(itemDoesNotExistInHand(player)) {
            ChatUtil.showMessage(player, ChatUtil.NO_ITEM);
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        meta.setLore(null);
        item.setItemMeta(meta);

        ChatUtil.showMessage(player, ChatUtil.ITEM_LORE_IS_REMOVED);
        SoundUtil.playNoteBlockBell(player);
    }

    /* 내용 조합 */
    private String getContents(String[] args) {
        StringBuffer sb = new StringBuffer();
        for(int i = 1 ; i < args.length ; i++) {
            if(i != 1)
                sb.append(" ");
            sb.append(args[i]);
        }

        String contents = sb.toString();
        return ChatColor.translateAlternateColorCodes('&', contents);
    }
}
