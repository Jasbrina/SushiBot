package Main;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

public class RoleRemover extends Command {
    public RoleRemover() {
        this.name = "rr";
        this.aliases = new String[]{"rr"};
        this.help = "Removes role from user";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        EmbedBuilder builder = new EmbedBuilder();
        FindRole fr = new FindRole();
        fr.execute(commandEvent);
        String name = fr.getAuthor().getEffectiveName();

        if (!fr.getAuthor().getRoles().contains(fr.getR())) {
            builder.appendDescription("You do not have that role. \nNo action taken.");
            builder.setAuthor(name);
            commandEvent.reply(builder.build());
            builder.clear();

        } else {
            commandEvent.getGuild().getController().removeSingleRoleFromMember(fr.getAuthor(), fr.getR()).complete();
            builder.appendDescription(name + ", role removed");
            builder.setColor(Color.red);
            commandEvent.reply(builder.build());
        }
    }
}
