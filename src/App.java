import java.util.Scanner;
public class App {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        DBManager dbm = new DBManager();
        dbm.connect();
        System.out.println("Seleziona la query da eseguire:");
        System.out.println("1)  Registrazione di una scuderia\n"
                            + "2)  Inserimento dei dati di una vettura, compresi i componenti di cui è composta\n"
                            + "3)  Aggiunta di un nuovo pilota all'equipaggio\n"
                            + "4)  Registrazione di un finanziamento per una scuderia\n"
                            + "5)  Iscrizione di una vettura ad una gara\n"
                            + "6)  Registrazione del risultato di ogni vettura iscritta ad una gara\n"
                            + "7)  Verifica della possibilità di montare un componente su una vettura\n"
                            + "8)  Per ciascuna scuderia stampare la somma totale dei finanziamenti ricevuti\n"
                            + "9)  Stampa annuale delle scuderie che hanno partecipato al campionato compreso il numero di finanziamenti\n"
                            + "10) Visualizzare i piloti che hanno vinto in un circuito di casa\n"
                            + "11) Per ciascuna scuderia, visualizzare la percentuale di gentleman driver di cui si compone l'equipaggio\n"
                            + "12) Stampa mensile dei costruttori compreso il numero di componenti che ha fornito\n"
                            + "13) Stampa della classifica finale dei punti conseguiti da tutte le vetture\n"
                            + "14) Stampa delle classifiche finali di punti per tipo di motore\n"
                            + "15) Stampare un report che elenchi ciascuna scuderia sulla base del rapporto punti/minuti di gara, mediando tra le macchine appartenenti a ciascuna scuderia");
        String sel = scan.next();
        switch (sel) {
            case "1":
                
                break;
            case "2":

                break;
            case "3":

                break;
            case "4":

                break;
            case "5":

                break;
            case "6":

                break;
            case "7":

                break;
            case "8":

                break;
            case "9":

                break;
            case "10":

                break;
            case "11":

                break;
            case "12":

                break;
            case "13":

                break;
            case "14":

                break;
            case "15":

                break;
            default:
                break;
        }

        scan.close();
    }
}