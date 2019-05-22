package Main;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

import java.util.ArrayList;
import java.util.List;

public class FindRole extends Command {
    private Guild guild;
    private Member author;
    private Role r;
    private List<String> guildid;
    public FindRole(){}


    void setGuild(CommandEvent commandEvent){
        guild = commandEvent.getGuild();
    }


    @Override
    protected void execute(CommandEvent commandEvent){
        long authorID = commandEvent.getMessage().getAuthor().getIdLong();
        Member mem = commandEvent.getGuild().getMemberById(authorID);
        guild = commandEvent.getGuild();
        author = commandEvent.getMessage().getMember();
        String msgm = commandEvent.getMessage().getContentRaw();
        String msg = msgm.substring(4, msgm.length());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + msg);
        int ind;

        List<Role> guildroles = guild.getRoles();
        guildid = new ArrayList<>();
        //get roles
        for (int i= 0; i<guildroles.size(); i++){
            guildid.add(guildroles.get(i).getName());
            System.out.println(guildid.get(i));
        }

        if (guildid.contains(msg)) {
            ind = guildid.indexOf(msg);
            System.out.println("true");
        }
        else {commandEvent.reply("Role does not exist. Use command -r to display a list of assignable roles."); return;}
        r = guildroles.get(ind);
    }

    public Guild getGuild(){return guild;}
    public Member getAuthor(){return author;}
    public Role getR(){return r;}
    public List<String> getGuildid(){return guildid;}
}
