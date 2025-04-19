package jiekie.completer;

import jiekie.ItemEditPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemEditTabCompleter implements TabCompleter {
    private final ItemEditPlugin plugin;

    public ItemEditTabCompleter(ItemEditPlugin plugin) {
        this.plugin = plugin;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("jk.item_edit.command")) return Collections.emptyList();

        if(args.length == 1)
            return Arrays.asList("이름", "설명", "설명제거", "도움말");

        return Collections.emptyList();
    }
}
