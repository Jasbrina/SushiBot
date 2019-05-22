package Main;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;

import java.awt.*;
import java.util.List;

public class DisplayRole extends Command {
    public DisplayRole(){
        this.name = "r";
        this.aliases = new String[]{"rf", "fr", "r"};
        this.help = "Displays a list of available user-assignable roles.";
    }

    @Override
    protected void execute(CommandEvent commandEvent){
        FindRole fr = new FindRole();
        ListRoles lr = new ListRoles();
        lr.execute(commandEvent);

        List<String> roles = lr.getRoles();

        String rolesS = "";
        for(int i = 0; i < roles.size(); i++){
            rolesS += roles.get(i) + "\n";
        }

        EmbedBuilder builder = new EmbedBuilder();
            builder.setDescription("");
            builder.setTitle("List of Available Roles:");
            builder.appendDescription(rolesS)
                    .appendDescription("\n");
            builder.setFooter("To add a role, use -ra <role name here>", null);
            builder.setColor(Color.red);
            commandEvent.reply(builder.build());
    }
}
