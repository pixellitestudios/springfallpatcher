package studio.pixellite.patcher.patches;

import me.lucko.helper.Events;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import me.lucko.helper.utils.Players;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandColonSyntaxPatch implements TerminableModule {
  /** A list of colon commands that will not be denied by this patch. */
  private static final List<String> WHITELISTED_COMMANDS = List.of("towny:confirm",
          "towny:deny");

  @Override
  public void setup(@NotNull TerminableConsumer consumer) {
    Events.subscribe(PlayerCommandPreprocessEvent.class)
            .handler(e -> {
              String[] command = e.getMessage().split(" ");

              if(WHITELISTED_COMMANDS.contains(command[0])) {
                return;
              }

              if(command[0].contains(":") ||
                      !e.getPlayer().hasPermission("pixellite.bypasscolonsyntax")) {
                e.setCancelled(true);
                Players.msg(e.getPlayer(), "&cThat command syntax is disabled.");
              }
            }).bindWith(consumer);
  }
}
