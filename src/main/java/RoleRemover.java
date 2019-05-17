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
           // commandEvent.reply("You do not have that role.\nNo action taken.");
            builder.clear();

        } else {
            commandEvent.getGuild().getController().removeSingleRoleFromMember(fr.getAuthor(), fr.getR()).complete();
            //commandEvent.reply("Role removed!");
            builder.appendDescription(name + ", role removed");
            builder.setColor(Color.red);
            commandEvent.reply(builder.build());
        }

        //builder.setThumbnail("https://cdn.discordapp.com/embed/avatars/0.png");
       // builder.setAuthor("Sush");
       // builder.setImage("https://cdn.discordapp.com/embed/avatars/0.png");
       // builder.setTitle("hmm");
    }
}
