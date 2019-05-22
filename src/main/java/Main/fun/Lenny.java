package Main.fun;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import lenny.Lenny2;

public class Lenny extends Command {

    public Lenny(){
        this.name = "lenny";
        this.aliases = new String[]{"lenny"};
        this.help =  Lenny2.makeYay("");
    }

    @Override
    protected void execute(CommandEvent commandEvent){
        commandEvent.reply(lenny.Lenny.face);
    }

}
