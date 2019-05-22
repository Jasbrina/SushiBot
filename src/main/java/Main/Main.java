package Main;

import ArtistDatabase.ArtistLookup;
import Main.Secret.Secret;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import Main.fun.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;


public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {
        Secret secret = new Secret();
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = secret.getBotID();
        RoleAdder ra = new RoleAdder();
        Minesweeper ms = new Minesweeper();
        RoleRemover rr = new RoleRemover();
        DisplayRole dr = new DisplayRole();
        GetAvatar ga = new GetAvatar();
        RI ri = new RI();
        hug sl = new hug();
        love love = new love();
        f f = new f();
        Ask ask = new Ask();
        ServerInfo s = new ServerInfo();
        UserInfo ui = new UserInfo();
        ArtistLookup al = new ArtistLookup();
        Lenny lenny = new Lenny();

        CommandClientBuilder ccb = new CommandClientBuilder();
        ccb.setPrefix("-");
        ccb.setOwnerId(secret.getOwnerID());

        ccb.setGame(Game.watching("you | -help"));

        ccb.addCommand(dr);
        ccb.addCommand(ra);
        ccb.addCommand(rr);
        ccb.addCommand(ms);
        ccb.addCommand(ga);
        ccb.addCommand(ri);
        ccb.addCommand(sl);
        ccb.addCommand(s);
        ccb.addCommand(ui);
        ccb.addCommand(love).addCommand(f).addCommand(ask).addCommand(lenny);
        ccb.addCommand(al);

        builder.setToken(token);
        builder.addEventListener(new Main(),ccb.build());
        builder.build();
    }

    //for general events that are not specifically a command
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Secret secret = new Secret();
        System.out.println("Recieved from " +
                event.getAuthor().getName() + ":" +
                event.getMessage().getContentDisplay()
        );

        if ((event.getMessage().getContentRaw().contains("goodnight")|| event.getMessage().getContentRaw().contains(
                "Goodnight")||(event.getMessage().getContentRaw().contains("gn")&&event.getMessage().getContentRaw().length() == 2)||(event.getMessage().getContentRaw().contains("night")&&event.getMessage().getContentRaw().length()==5)||(event.getMessage().getContentRaw().toLowerCase().contains("good") && event.getMessage().getContentRaw().toLowerCase().contains("night"))) && !event.getMessage().getAuthor().isBot()){
            event.getChannel().sendMessage("Goodnight, " + event.getMessage().getAuthor().getName() +"! Sleep well!").queue();
        }

        //only works on a certain server; personal command
        if (event.getMessage().getContentRaw().contains("jendy")||event.getMessage().getContentRaw().contains("JENDY")||event.getMessage().getContentRaw().contains("Jendy")||event.getMessage().getContentRaw().contains("jENDY")){
            love l = new love();
            Guild g = event.getGuild();

            String user1im = g.getMemberById(secret.getJid1()).getUser().getAvatarUrl();
            String user2im = g.getMemberById(secret.getJid2()).getUser().getAvatarUrl();
            l.jendy(user1im, user2im);

            event.getChannel().sendFile(new File("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot" +
                    "\\src\\main\\java\\Main\\fun\\images\\imagej.png")).queue();
        }

        if ((event.getMessage().getContentRaw().contains("morning")|| event.getMessage().getContentRaw().contains(
                "Morning")||event.getMessage().getContentRaw().contains("goodmorning")||(event.getMessage().getContentRaw().contains("good") && event.getMessage().getContentRaw().contains("morning"))) && !event.getMessage().getAuthor().isBot()){
            event.getChannel().sendMessage("Morning, " + event.getMessage().getAuthor().getName() +"!").queue();
        }

        if (event.getMessage().getContentRaw().contains("!oof") || event.getMessage().getContentRaw().contains("oof") || event.getMessage().getContentRaw().contains("Oof") || event.getMessage().getContentRaw().contains("owo")){
            int rand = ThreadLocalRandom.current().nextInt(1, 5 + 1);
            System.out.println(rand);
            if (event.getMessage().getContentRaw().contains("!oof")) {
                event.getChannel().sendMessage("wew").queue();
            }
            else if (rand == 4)
                event.getChannel().sendMessage("wew").queue();
        }

        if (event.getMessage().getContentRaw().contains("!sushi")) {
            String s = "http://twitter.com/sushi_hammer";
            event.getChannel().sendMessage(s).queue();
        }

        if (event.getMessage().getMentionedUsers().get(0).isBot()) event.getChannel().sendTyping().queue();
    }
}


