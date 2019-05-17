import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

public class RoleAdder extends Command {
    public RoleAdder() {
        this.name = "ra";
        this.aliases = new String[]{"ra"};
        this.help = "Adds role to user.";
    }

    //TODO: add exception for when can't add an unreachable role
    @Override
    protected void execute(CommandEvent commandEvent) {
        FindRole find = new FindRole();
        find.execute(commandEvent);


        if (find.getAuthor().getRoles().contains(find.getR())) {
            commandEvent.reply("You already have that role...");
            System.out.println(find.getAuthor().getRoles().contains(find.getR()));

        } else {
            if (find.getGuildid().contains(find.getR().toString()))
                commandEvent.reply("You currently do not have that role.");;
            commandEvent.getGuild().getController().addSingleRoleToMember(find.getAuthor(), find.getR()).complete();
           // commandEvent.reply(find.getR().getName()+ " role added!");
            commandEvent.reply(new EmbedBuilder().appendDescription(find.getAuthor().getEffectiveName()+", " +find.getR().getName() +" role added!").setColor(Color.red).build());
        }
    }
}