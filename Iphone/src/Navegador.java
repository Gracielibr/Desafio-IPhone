import java.util.Scanner;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Navegador implements NavegadorNaInternet {

    private Scanner scanner = new Scanner(System.in);


    public void adicionarNovaAba() {
        System.out.println("Nova aba aberta.");
        System.out.print("Digite o que deseja pesquisar: ");
        String busca = scanner.nextLine().trim();
        exibirPagina(busca);
    }

    public void exibirPagina(String busca) {
        if (!busca.isEmpty()) {
            String url = "https://www.google.com/search?q=" + busca.replace(" ", "+");
            System.out.println("Abrindo navegador na página: " + url);

            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                System.out.println("Erro ao tentar abrir o navegador: " + e.getMessage());
            }

            
            System.out.print("Era isso que você queria? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (resposta.equals("n")) {
                atualizarPagina();
            } else {
            
                System.out.print("Deseja fazer uma nova pesquisa? (s/n): ");
                String novaBusca = scanner.nextLine().trim().toLowerCase();

                if (novaBusca.equals("s")) {
                    adicionarNovaAba(); 
                } else {
                    System.out.println("Encerrando o navegador.");
                }
            }

        } else {
            System.out.println("Busca vazia. Nenhuma página foi aberta.");
        }
    }

    public void atualizarPagina() {
        System.out.print("Descreva especificamente o que deseja: ");
        String novaBusca = scanner.nextLine().trim();
        exibirPagina(novaBusca); 
    }
}