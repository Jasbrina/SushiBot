package Main;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

import java.util.ArrayList;
import java.util.List;

public class ListRoles extends Command{
    private Guild server;
    private Member member;
    private Role role;
    private List<String> guildid;

    @Override
    protected void execute(CommandEvent commandEvent){
        long authorID = commandEvent.getMessage().getAuthor().getIdLong();
        Member m = commandEvent.getGuild().getMemberById(authorID);
        server = commandEvent.getGuild();
        member = commandEvent.getMessage().getMember();

        List<Role> guildRoles = server.getRoles();
        guildid = new ArrayList<>();
        List<Role> botRoles = commandEvent.getGuild().getMember(commandEvent.getAuthor()).getRoles();

        int x = botRoles.get(0).getPosition();
        for (int i = 0; i < botRoles.size(); i++){
            if (botRoles.size() == 1 || botRoles.get(i).getPosition() > x && botRoles.get(i).getPosition() != 0)
                x= botRoles.get(i).getPosition();
            System.out.println(botRoles.get(i).getPosition());
        }

        for (int i = 0; i < guildRoles.size(); i++){
            if (!guildRoles.get(i).isPublicRole() && guildRoles.get(i).getPosition() < x)
                //if guildRoles.get().getPosition()
                guildid.add(guildRoles.get(i).getName());
            System.out.println(guildRoles.get(i).getName() +" " +guildRoles.get(i).getPosition());
        }
    }

    public List<String> getRoles(){return guildid;}
}