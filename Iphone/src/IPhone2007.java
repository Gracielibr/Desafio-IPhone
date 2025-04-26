import java.util.Scanner;
import java.net.URI;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;


public class IPhone2007 extends AparelhoTelefonico {


    private NavegadorNaInternet navegador = new Navegador();
    private ReprodutorMusical reprodutor = new Reprodutor();

    

    public void abrirNavegador() {
        navegador.adicionarNovaAba();
    }

    public void abrirReprodutor() {
        reprodutor.abrirMenuReprodutor();
    }


    public static void main(String[] args) {
        IPhone2007 iphone = new IPhone2007();
    Scanner scanner = new Scanner(System.in);
    int opcao = 0;

    while (true) {
        System.out.println("==========================");
        System.out.println("Bem-vindo ao iPhone 2007!");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Aparelho Telefônico");
        System.out.println("2 - Navegador na Internet");
        System.out.println("3 - Reprodutor Musical");
        System.out.println("0 - Sair");

        try {
            opcao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido.");
            continue;
        }

        switch (opcao) {
            case 1:
            iphone.menuTelefonico();
            break;

            case 2:
            System.out.println("Bem-vindo ao navegado do Iphone");
            iphone.abrirNavegador(); 
              
             break;
                
            case 3:
            iphone.abrirReprodutor();
            break;
                
            case 0:
                System.out.println("Encerrando o iPhone. Até mais!");
                scanner.close();
                return;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
 }
}
