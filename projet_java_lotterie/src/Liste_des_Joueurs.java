import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Liste_des_Joueurs {
    public static void Joueurs(int nombre_de_joueurs, Scanner scanner, Serveur_Lotterie server, CountDownLatch latch,int n,int k){
        for (int i = 0; i < nombre_de_joueurs; i++) {
            new Thread(new Joueur(server, n, k, scanner, latch)).start();
        }
    }
}
