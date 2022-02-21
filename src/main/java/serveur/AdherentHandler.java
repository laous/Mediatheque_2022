package serveur;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdherentHandler extends Thread{
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    // Constructor
    public AdherentHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run()
    {
        String received;
        String toreturn;
        while (true)
        {
            try {

                // Ask user what he wants
                dos.writeUTF(
                        "Veuillez choisir une option\n"+
                        "1- Afficher mes informations\n"+
                        "2- Afficher mes emprunts\n"+
                        "3- Gerer mes emprunts\n"+
                        "4- Quitter\n"
                );

                // receive the answer from client
                received = dis.readUTF();

                if(received == "4")
                {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // creating Date object
                Date date = new Date();

                // write on output stream based on the
                // answer from the client
                switch (received) {

                    case "1" :
//                        toreturn = fordate.format(date);
                        dos.writeUTF("Affichage Informations");
                        break;

                    case "2" :
//                        toreturn = fortime.format(date);
                        dos.writeUTF("Affichage Emprunts");
                        break;
                    case "3" :
//                        toreturn = fortime.format(date);
                        dos.writeUTF("Gestion Emprunts");
                        break;

                    default:
                        dos.writeUTF("Invalid input");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
