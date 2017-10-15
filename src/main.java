import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

/**
 * Created by SiD on 5/24/17.
 */
public class main {

    public static final  String BOT_TOKEN = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";// Your BOT Token
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
    }
}
