import java.util.Scanner;
import java.util.concurrent.*;

public class Main{
    public static void main(String[] args) {
        System.out.println(" Début de vente des billets");
        int n = 90; // Nombre total de numéros
        int k = 5;  // Nombre de numéros par billet
        int t =1;  // Seuil pour gagner
        int nombre_de_joueurs = 5; // Nombre de joueurs
        int durée = 40; // Durée de la vente des billets en secondes

        Scanner scanner = new Scanner(System.in);
        Serveur_Lotterie server = new Serveur_Lotterie(n, k, t);
        server.start(); // Démarrer le serveur de vente

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(server::Arreter_serveur, durée, TimeUnit.SECONDS);

        CountDownLatch latch = new CountDownLatch(nombre_de_joueurs);


        Liste_des_Joueurs.Joueurs(nombre_de_joueurs,scanner,server,latch,n,k);


        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            scanner.close();
            scheduler.shutdown();
        }
    }
}

