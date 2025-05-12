package jiekie.itemedit.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtil {
    /* error */
    public static final String NO_ITEM = getXPrefix() + "손에 아이템을 들고 설정해주시기 바랍니다.";

    /* feedback */
    public static final String ITEM_NAME_IS_CHANGED = getCheckPrefix() + "아이템 이름을 설정했습니다.";
    public static final String ITEM_LORE_IS_ADDED = getCheckPrefix() + "아이템 설명을 추가했습니다.";
    public static final String ITEM_LORE_IS_REMOVED = getCheckPrefix() + "아이템 설명을 제거했습니다.";

    /* prefix */
    public static String getCheckPrefix() {
        return "\uA001 ";
    }

    public static String getXPrefix() {
        return "\uA002 ";
    }

    public static String getWarnPrefix() {
        return "\uA003 ";
    }

    public static void showMessage(Player player, String message) {
        player.sendMessage(message);
    }

    /* validate */
    public static void notPlayer(CommandSender sender) {
        sender.sendMessage(getWarnPrefix() + "플레이어가 아닙니다.");
    }

    public static void notOp(Player player) {
        player.sendMessage(getWarnPrefix() + "권한이 없습니다.");
    }

    public static String wrongCommand() {
        return getWarnPrefix() + "명령어 사용법이 잘못되었습니다.";
    }

    /* command */
    public static void commandHelper(Player player) {
        player.sendMessage(getWarnPrefix() + "/아이템 도움말" + ChatColor.GRAY + " : 사용 가능한 명령어를 확인할 수 있습니다.");
    }

    public static void commandList(Player player) {
        player.sendMessage("");
        player.sendMessage(getWarnPrefix() + "아이템 명령어 목록");
        player.sendMessage("　　　① /아이템 이름 아이템명");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 아이템 이름을 설정합니다.");
        player.sendMessage("　　　② /아이템 설명 내용");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 아이템 설명을 가장 마지막 줄에 추가합니다.");
        player.sendMessage("　　　③ /아이템 설명제거");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 아이템의 모든 설명을 제거합니다.");
        player.sendMessage("　　　④ /아이템 도움말");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 사용 가능한 명령어를 확인할 수 있습니다.");
        player.sendMessage("");
    }
}
