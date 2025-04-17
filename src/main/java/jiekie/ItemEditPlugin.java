package jiekie;

import jiekie.command.ItemEditCommand;
import jiekie.completer.ItemEditTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemEditPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // 명령어 등록
        getCommand("아이템").setExecutor(new ItemEditCommand(this));

        // 자동완성 등록
        getCommand("아이템").setTabCompleter(new ItemEditTabCompleter(this));

        getLogger().info("아이템 설정 플러그인 by Jiekie");
        getLogger().info("Copyright © 2025 Jiekie. All rights reserved.");
    }

    @Override
    public void onDisable() {}
}
