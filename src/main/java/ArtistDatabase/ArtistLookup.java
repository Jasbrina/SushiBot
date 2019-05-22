package Main.ArtistDatabase;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class ArtistLookup extends Command {
    Lookup p;
    List<Object> obj = null;

    public ArtistLookup(){
        p = new Lookup();
        this.name = "artist";
        this.aliases = new String[]{"artist"};
        this.help = "Prints a requested user's social media links. " +
                "Linked to /r/AnimeSketch Discord's artist database. " +
                "Usage: -artist <artist's name here>";
    }

    @Override
    protected void execute(CommandEvent commandEvent){
        List<String> s = new ArrayList<String>();
        List<String> n = new ArrayList<String>();
        String msg = commandEvent.getMessage().getContentRaw();
        if(commandEvent.getMessage().getContentRaw().length() == 7){
            commandEvent.reply("Please enter a name.");
        }
        else{
            String artistfind = msg.substring(8, msg.length());
            System.out.println(artistfind);
            try{
                s = p.getArtist(artistfind);
            }catch(IOException|GeneralSecurityException e){
                e.printStackTrace();
            }
            n = p.sites();

            String reply = "";

            for(int i = 0; i < s.size(); i++) reply += "**" + n.get(i)+ "** " + s.get(i) + "\n";
            System.out.println(reply);
            EmbedBuilder b = new EmbedBuilder();
            b.setAuthor(p.getName() + "'s Links");
            b.setColor(new Color(242, 126, 84));
            b.appendDescription(reply);
            commandEvent.getChannel().sendMessage(b.build()).queue();
        }
    }
}
