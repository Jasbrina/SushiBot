import ArtistDatabase.lookup.PreProdTest;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class ArtistLookup extends Command {
    PreProdTest p;
    List<Object> hm = null;
    public ArtistLookup(){
        System.out.println("idk");
        p = new PreProdTest();
        System.out.println("oks");

        this.name = "artistlookup";
        this.aliases = new String[]{"a"};
        this.help = "Prints a requested user's social media links.";

        //initializes this only ONCE **
        try{
            //p.getval();
            hm = p.getval();}
        catch(IOException | GeneralSecurityException e){
            System.out.println();
        }
    }

    @Override
    protected void execute(CommandEvent commandEvent){
        commandEvent.reply(hm.get(0).toString());
        String msg = commandEvent.getMessage().toString();
        if(msg.substring(4,msg.length()).equals("")){
            commandEvent.reply("Please enter a name.");
            //TODO: add embed for this message
        }
        else{
            String artistfind = msg.substring(4, msg.length());
           // List<String> links = getArtistLinks(artistfind); //might need to use regex here to match the name up..
            //commandEvent.reply(links);
        }
    }
}
