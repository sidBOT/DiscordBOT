
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.util.*;

/**
 * Created by SiD on 5/24/17.
 */
public class BotListener extends ListenerAdapter {
    public int counter = 0;
    DiscordBot b = new DiscordBot();
    private static String[] commands = {"/rm", "/kick", "/ban", "/purge", "/help"};
    public String R = b.random1();
//    final public String ;
//    final public String ;
//    final public String ;
//    final public String ;
//    final public String ;
//    final public String ;
    private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";

    Map<String, String> hmap = new HashMap<String, String>();
    public Boolean isGameOn = false;
    int niggerC = 0;
    File file = new File("/Users/SiD/Desktop/DiscordBot/src/score.txt");
    File file1 = new File("/Users/SiD/Desktop/DiscordBot/src/score1.txt");
    public  String buffer = "";

    public void onMessageReceived (MessageReceivedEvent event)  {
        try {
            //counter++;
            //System.out.println("c " + counter);
            String message = event.getMessage().getContent();
            String[] lineSplit = event.getMessage().getContent().split(" ");



            if (message.startsWith("#gstart") && isGameOn == false) {
                counter = 1;
                event.getChannel().sendMessage("Game has Been Started!\n" + "How to Play: I will produce a random number " +
                        "between 1-100. Your aim is to find out the number. More features coming out soon. No hacks " +
                        "please!.  TO ANSWER #x  (x = your number). #gscore to check your score")
                        .queue();

                isGameOn = true;

            } else if (counter != 0 && isGameOn == true) {
                String newS = "";
                char x;
                String y = "";
                for (int i = 1; i < event.getMessage().getContent().length(); i++) {
                    char c = message.charAt(i);
                    x = message.charAt(0);
                    y = String.valueOf(x);
                    newS = newS + c;
                }

                if (event.getMessage().getContent().equals("#" + R)) {
                    String user = event.getAuthor().getId();
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " You won! +10 \n" + "Game is " +
                            "Over")
                            .queue();
                    counter = 0;
                    R = b.random1();

                    FileWriter fw1= new FileWriter(file.getAbsoluteFile(),true);
                    BufferedWriter bw1 = new BufferedWriter(fw1);
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    String text = "";

                    while ((text = br.readLine()) != null) {
                        hmap.put(text, br.readLine());
                    }
                    fw1.close();
                    bw1.close();
                    br.close();
                    int flag = 0;

                    String value = user;
                    for(Map.Entry entry: hmap.entrySet()){
                        if(value.equals(entry.getKey())){
                            hmap.put(user, String.valueOf(Integer.parseInt(hmap.get(user))+10));
                            flag = 1;
                            break;
                        }
                    }
                    FileWriter fw= new FileWriter(file,false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    String msg = "";
                    for(Map.Entry entry: hmap.entrySet()) {
                        msg = msg + entry.getKey() + "\n" + entry.getValue() + "\n";
                    }
                    bw.write(msg);
                    bw.close();
                    fw.close();


                    if(flag != 1) {
                        hmap.put(user, "10");
                        System.out.println("L");
                        FileWriter fw2= new FileWriter(file,false);
                        BufferedWriter bw2 = new BufferedWriter(fw2);
                        String msg1 = "";
                        for(Map.Entry entry: hmap.entrySet()) {
                            msg1 = msg1 + entry.getKey() + "\n" + entry.getValue() + "\n";
                        }
                        bw2.write(msg1);
                        bw2.close();
                        fw2.close();
                        flag = 0;

                    }
                    isGameOn = false;
                } else if (message.startsWith("#gstart")) {
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Game is already on you sped").queue();
                } else if (message.startsWith("#gstop")) {
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Game stopped. #gstart for a " +
                            "new game").queue();
                    isGameOn = false;
                } else if ((Math.abs(Integer.valueOf(newS) - Integer.valueOf(R)) < 40) && (Math.abs(Integer.valueOf
                        (newS) - Integer.valueOf(R)) > 10) && y.equals("#")) {
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Getting hot")
                            .queue();
                } else if ((Math.abs(Integer.valueOf(newS) - Integer.valueOf(R)) <= 10) && (Math.abs(Integer.valueOf
                        (newS) - Integer.valueOf(R)) > 5) && y.equals("#")) {
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Getting hot")
                            .queue();
                } else if (Math.abs(Long.parseLong(newS) - Long.parseLong(R)) <= 5 && Math.abs(Long.parseLong
                        (newS) - Long.parseLong(R)) >= 1 && y.equals("#")) {
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Extremely hot. You are almost" +
                            " there")
                            .queue();
                } else if (Math.abs(Long.parseLong(newS) - Long.parseLong(R)) <= 60 && Math.abs(Long.parseLong(newS)
                        - Long.parseLong(R)) >= 40 && y.equals("#")) {
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Getting Colder")
                            .queue();
                } else if (Math.abs(Long.parseLong(newS) - Long.parseLong(R)) <= 90 && Math.abs(Long.parseLong(newS)
                        - Long.parseLong(R)) >= 61 && y.equals("#")) {
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Freezing in here")
                            .queue();
                } else if (Math.abs(Long.parseLong(newS) - Long.parseLong(R)) > 100 && y.equals("#")) {
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Invalid input")
                            .queue();
                }
            } else if (message.startsWith("#gstop") && isGameOn == false) {
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + " How tf should I stop a game when " +
                        "there's no game running at all")
                        .queue();


            } else if (message.startsWith("#hello")) {
                String name = event.getAuthor().getName();

                String response = name + ", Hello and welcome to this noob clan.";

                event.getTextChannel().sendMessage(response).queue();
            } else if (message.startsWith("#creator")) {

                String response = "I was created By Sid.";

                event.getTextChannel().sendMessage(response).queue();
            } else if (message.startsWith("#ping")) {
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + "pong!").queue();
            } else if (message.startsWith("#getAvatar")) {
                //event.getChannel().sendMessage(event.getAuthor().getAvatarUrl()).queue();
                List<User> mention = event.getMessage().getMentionedUsers();
                //event.getMessage().delete().queue();
                for (User user : mention) {
                    event.getChannel().sendMessage(user.getAvatarUrl()).queue();

                }
                //event.getChannel
            } else if (message.startsWith("#help")) {
                String response = "#blank: blank space\n" + "#hello : informal greeting\n" +
                        "#creator : Lovely person who made me\n" +
                        "#ping : pong\n" + "#getAvatar : get URL of your photo\n" + "#joke : tells a joke\n" + "#spam : " +
                        "spams you\n" + "#birth : Gives the exact date and time of when the group was created\n" + "#gstart" +
                        " : start a number guessing game\n" + "gstop : stop the game\n" + "#gscore : see your game's " +
                        "score\n";
                event.getTextChannel().sendMessage(response).queue();
            } else if (message.startsWith("#blank1")) {
                String response = "";
                event.getTextChannel().sendMessage(response).queue();
            } else if (message.startsWith("#joke")) {
                String response[] = new String[20];
                response[1] = "Joke1";
                response[2] = "Joke2";
                response[3] = "Joke3";
                response[4] = "Joke4";
                response[5] = "Joke5";
                response[6] = "Joke6";
                response[7] = "Joke7";
                //Add as many jokes as you want
                int ran = this.random();
                event.getTextChannel().sendMessage(response[ran]).queue();
            } else if (message.startsWith("#spam")) {
//
                List<User> mention = event.getMessage().getMentionedUsers();

                for (User user : mention) {

                    for(int i =0;i <=10; i++) {
                        event.getChannel().sendMessage(user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention() + user.getAsMention
                                () + user.getAsMention() + user.getAsMention() + user.getAsMention()).queue();
                    }

                }



            } else if (message.startsWith("#birth")) {
                int year = event.getTextChannel().getCreationTime().getYear();
                int day = event.getTextChannel().getCreationTime().getDayOfMonth();
                int month = event.getTextChannel().getCreationTime().getMonthValue();
                int hour = event.getTextChannel().getCreationTime().getHour();
                int minute = event.getTextChannel().getCreationTime().getMinute();
                int sec = event.getTextChannel().getCreationTime().getSecond();
                String DAY = Integer.toString(day);
                String YEAR = Integer.toString(year);
                String MONTH = Integer.toString(month);
                String HOUR = Integer.toString(hour);
                String MINUTE = Integer.toString(minute);
                String SEC = Integer.toString(sec);
                event.getChannel().sendMessage("Year: " + YEAR + " Month: " + MONTH + " Day: " +
                        DAY + " Hour: " +
                        HOUR +
                        " Minute: " + MINUTE + " Sec: " + SEC)
                        .queue();

            } else if (event.getMessage().getContent().contains("#skick1")) {

                List<User> mention = event.getMessage().getMentionedUsers();
                event.getMessage().delete().queue();
                for (User user : mention) {

                    event.getGuild().getController().kick(user.getId()).queue();
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " you successfully kicked " +
                            user.getName())
                            .queue();
                }



            } else if(message.startsWith("#sban1")) {
                List<User> mention = event.getMessage().getMentionedUsers();
                event.getMessage().delete().queue();
                for (User user : mention) {

                    event.getGuild().getController().ban(user.getId(), 1).queue();
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " you successfully banned " +
                            user.getName())
                            .queue();
                }

            } else if (message.startsWith("#gadd")) {

            }  else if (message.startsWith("#score")) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String m = "";
                while((m = br.readLine()) != null) {
                    if(m.equals(event.getAuthor().getId())) {
                        event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Your Score is " + br.readLine())
                                .queue();
                        return;
                    }else {
                        continue;
                    }
                }
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Your Score is 0" )
                        .queue();
                br.close();


            } else  {
                event.getMessage().delete().queue();
                String fromLang = "en";
                String toLang = "es";
                String text = event.getMessage().getContent();

                String ss = translate(fromLang, toLang, text);
                System.out.println("author: " + event.getAuthor().getName()
                        + " Message: " + ss + " Channel: " + event.getTextChannel().getName() + event.getAuthor
                        ().getId() +  "\n");
                String s = "author: " + event.getAuthor().getName()
                        + " Message: " + message + " Channel: " + event.getTextChannel().getName() + "channel id:"
                        +event.getTextChannel().getId() +
                "\n";
                FileWriter fw= new FileWriter(file1, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(s+ "\n");
                bw.close();
                fw.close();

            }

        }catch (NumberFormatException e) {
            e.getCause();
        }catch (IllegalArgumentException e) {
            e.getCause();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public int random() {
        Random rand = new Random();
        int randomNum = rand.nextInt((7 - 0) + 1) + 0;// 7 = 7 jokes. This number depends on the amount of jokes you have

        return  randomNum;
    }
    public String random1() {
        Random rand = new Random();
        int randomNum = rand.nextInt((5 - 1) + 1) + 1;
        String r = Integer.toString(randomNum);
        //1System.out.println(r);
        return  r;
    }
    public static String translate(String fromLang, String toLang, String text) throws Exception {
        // TODO: Should have used a 3rd party library to make a JSON string from an object
        String jsonPayload = new StringBuilder()
                .append("{")
                .append("\"fromLang\":\"")
                .append(fromLang)
                .append("\",")
                .append("\"toLang\":\"")
                .append(toLang)
                .append("\",")
                .append("\"text\":\"")
                .append(text)
                .append("\"")
                .append("}")
                .toString();

        URL url = new URL(ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
        conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(jsonPayload.getBytes());
        os.flush();
        os.close();

        int statusCode = conn.getResponseCode();
        System.out.println("Status Code: " + statusCode);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
        ));
        String output;
        while ((output = br.readLine()) != null) {
            return output;
        }
        conn.disconnect();
        return output;
    }

}
