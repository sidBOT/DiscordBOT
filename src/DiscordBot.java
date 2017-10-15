import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;



import javax.security.auth.login.LoginException;
import java.util.Random;

/**
 * Created by SiD on 5/24/17.
 */
public class DiscordBot extends NumberFormatException {

    public static final  String BOT_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    public String random1() {
        Random rand = new Random();
        int randomNum = rand.nextInt((100 - 1) + 1) + 1;
        String r = Integer.toString(randomNum);
        //System.out.println("r" + r);
        return  r;
    }
    public static void main(String[] args) {
        JDA discord = null;
        try {
            discord = new JDABuilder(AccountType.BOT).setToken(BOT_TOKEN).buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RateLimitedException e) {
            e.printStackTrace();
        }
        discord.addEventListener(new BotListener());

        //discord.addEventListener(new );
    }

}
