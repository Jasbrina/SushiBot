import ArtRepository.ArtMain;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

//import net.dv8tion.jda.core.JDABuilder;
//import com.jagrosh.jdautilities.command.CommandClientBuilder;
//import com.jagrosh.jdautilities.command.CommandClientBuilder;
//import com.jagrosh.jdautilities.command.CommandClientBuilder;
//import net.dv8tion.jda.core.OnlineStatus;
//import net.dv8tion.jda.core.entities.Game;

public class Main extends ListenerAdapter {
    protected static int rolePermCut;

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NTQyNDk3MDU3NTY3NDA4MTM3.Dzu3SQ.PRHkXUzy0Bob5Nc7c7Op7EyMGCM";
        RoleAdder ra = new RoleAdder();
        Minesweeper ms = new Minesweeper();
      //  FindRole fr = new FindRole();
        RoleRemover rr = new RoleRemover();
        DisplayRole dr = new DisplayRole();
        GetAvatar ga = new GetAvatar();
        ArtistLookup al = new ArtistLookup();

        CommandClientBuilder ccb = new CommandClientBuilder();
        ccb.setOwnerId("542497057567408137");
        ccb.setPrefix("-");

        //ccb.useDefaultGame();
        ccb.setGame(Game.watching("you | -help"));

        //ccb.setHelpConsumer()

        ccb.addCommand(dr);
        ccb.addCommand(ra);
        ccb.addCommand(rr);
        ccb.addCommand(ms);
        ccb.addCommand(ga);
        //ccb.addCommand(dr);
        ccb.addCommand(al);

        EmbedBuilder buildr = new EmbedBuilder();

        //ccb.addCommand(new execute());
       // CommandClient t = ccb.build();

        builder.setToken(token);
        builder.addEventListener(new Main(),ccb.build());
        builder.build();

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        System.out.println("Recieved from " +
                event.getAuthor().getName() + ":" +
                event.getMessage().getContentDisplay()
        );
        if (event.getMessage().getContentRaw().equals("!user")) { //how to change -artist into any suitable name
            String name = event.getMessage().getContentRaw();
            //ArtistLookup a_lu = new ArtistLookup(name);
//            try {
//                a_lu.findInDatabase();
//            } catch (){
//                event.getChannel().sendMessage("No user found in artist repository by that name");
//                System.out.println("No user found in artist repository by that name");
//            }
        }

        if (event.getMessage().getContentRaw().equals("-artist")) {
            ArtMain am = new ArtMain();
            event.getChannel().sendMessage("hm").queue();

        }

        if (event.getMessage().getContentRaw().equals("!wendy")) {
            ArtMain am = new ArtMain();
            event.getChannel().sendMessage("owo").queue();

        }

        if (event.getMessage().getContentRaw().contains("oof") || event.getMessage().getContentRaw().contains("Oof")){
            event.getChannel().sendMessage("wew").queue();
        }

//        if (event.getMessage().getContentRaw().equals("wew")) {
//            event.getChannel().sendMessage("oof").queue();
//        }


        if (event.getMessage().getContentRaw().equals("-avatar")) {
            String hm = event.getAuthor().getAvatarUrl();
            event.getChannel().sendMessage(hm).queue();
        }

        if (event.getMessage().getContentRaw().contains("owo")) {
            event.getChannel().sendMessage("(。O ω O。)♡").queue();
        }


    }

}


