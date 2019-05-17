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
        //fr.execute(commandEvent);

        String rolesS = "";
        for(int i = 0; i < roles.size(); i++){
            rolesS += roles.get(i) + "\n";
        }

        EmbedBuilder builder = new EmbedBuilder();
            builder.setDescription("");
            builder.setTitle("List of Available Roles:");
            builder.appendDescription(rolesS)
                    .appendDescription("\n");
                   // .appendDescription("To add a role, use -r <role name here>");
            builder.setFooter("To add a role, use -ra <role name here>", null);
            builder.setColor(Color.red);
            commandEvent.reply(builder.build());

        TextChannel tx = commandEvent.getTextChannel();
        tx.sendMessage("yeet c:").queue();

        // c.getParent().getTextChannels()..get().sendMessage().queue();


        //System.out.println("lookup");
       // MessageHistory m = commandEvent.getChannel().getHistory();

       // System.out.println(commandEvent.getChannel().getHistory().size());
        //int size = m.size();
        //System.out.println("pls" + size);

       // String n = commandEvent.getChannel().getLatestMessageId();
       // commandEvent.getChannel().
       // System.out.println(commandEvent.getChannel().getHistoryAfter(n,1).toString());

       // Message mg = m.getRetrievedHistory().get(size-1);
        //mg.pin().queue();
       // System.out.println("BBBBBBBBB" + mg.getContentRaw());



        List<Emote> f = commandEvent.getGuild().getEmotes();

        User u = commandEvent.getSelfUser();
        //commandEvent.getChannel().addReactionById("\uD83D\uDE02").queue();

        //commandEvent.getGuild().getMember(u).getJDA().;


        commandEvent.getEvent().getMessage().addReaction("\uD83D\uDE02").queue();

        //commandEvent.getMessage().addReaction("\uD83D\uDE02").queue();


        //prints the full list of roles available in guild.
//        //TODO: only display assignable roles
//        for(int i=0; i< fr.getGuildid().size(); i++){
//            String x = fr.getGuildid().get(i);
//            System.out.println("hmm");
//            System.out.println(x);
//            commandEvent.reply("lookup" +x);
//           // builder.appendDescription(x);
//        }


    }
}
