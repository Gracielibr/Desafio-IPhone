import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;

public class Reprodutor implements ReprodutorMusical {

    private ArrayList<String> listaDeMusicas;
    private int musicaAtual = -1;
    private boolean tocando = false;

    public Reprodutor() {
        listaDeMusicas = new ArrayList<>();
        listaDeMusicas.add("Legião Urbana - Tempo Perdido");
        listaDeMusicas.add("Raul Seixas - Metamorfose Ambulante");
        listaDeMusicas.add("Lulu Santos - Como uma Onda");
        listaDeMusicas.add("Cássia Eller - O Segundo Sol");
        listaDeMusicas.add("Los Hermanos - Último Romance");
    }

    public void abrirMenuReprodutor() {
        Scanner scanner = new Scanner(System.in);  
        System.out.println("Bem-vindo ao seu reprodutor de música!");

        while (true) {
            System.out.println("\nEscolha o que você deseja:");
            System.out.println(" p  Playlist");
            System.out.println(" v  Ver vídeos no YouTube");
            System.out.println(" s  Sair");

            System.out.print("Digite uma opção: ");
            String acao = scanner.nextLine().trim().toLowerCase();

            if (acao.equals("p")) {
                while (true) {
                    mostrarMusicas();
            
                    System.out.println("\nO que você deseja fazer?");
                    System.out.println(" Digite 'e' para escolher uma música da playlist");
                    System.out.println(" '+' para adicionar nova música à playlist");
                    System.out.println(" 'v' para voltar");
            
                    System.out.print("Digite uma opção: ");
                    String opcao = scanner.nextLine().trim().toLowerCase();
            
                    if (opcao.equals("e")) {
                        System.out.print("Digite o nome da música: ");
                        String nome = scanner.nextLine();
                        selecionarMusica(nome);
            
                        if (musicaAtual != -1) {
                            break; 
                        }
            
                        System.out.println("Vamos tentar novamente...");
                    } else if (opcao.equals("+")) {
                        System.out.print("Digite o nome da nova música: ");
                        String novaMusica = scanner.nextLine();
                        listaDeMusicas.add(novaMusica);
                        System.out.println("Música adicionada com sucesso!");
                    } else if (opcao.equals("v")) {
                        return; 
                    } else {
                        System.out.println("Opção inválida, tente novamente.");
                    }
                
                }
        
            
                
                    while (true) {
                        System.out.println("\n=================");
                        System.out.println(" t  Tocar");                  
                        System.out.println(" e  Escolher outra música");                                     
                        System.out.println(" s  Sair ");

                        System.out.print("Escolha uma opção: ");
                        String acaoMusica = scanner.nextLine().trim().toLowerCase();

                        switch (acaoMusica) {
                            case "t":
                                tocar();
                                break;
                            case "p":
                                pausar();
                                break;
                            case "x":
                                parar();
                                break;
                            case "r":
                                repetir();
                                break;
                            case "e":
                                retornaPlaylist(scanner); 
                                break;
                            case "s":
                                parar();
                                return;
                            default:
                                System.out.println("Opção inválida.");
                        }
                    }
                

            } else if (acao.equals("v")) {
                System.out.print("Digite o nome do vídeo: ");
                String busca = scanner.nextLine();
                exibirPagina(busca, scanner);
                

            } else if (acao.equals("s")) {
                System.out.println("Saindo do reprodutor...");
                break;
            } else {
                System.out.println("Opção inválida, digite o nome dou parte do nome da música.");
            }
        }
    }

    public void exibirPagina(String busca, Scanner scanner) {
        if (busca.isEmpty()) {
            System.out.println("Busca vazia. Nenhuma página foi aberta.");
            return;
        }

        String url = "https://www.youtube.com/results?search_query=" + busca.replace(" ", "+");

        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
            System.out.println("Abrindo YouTube para: " + busca);
            System.out.println("Abrindo navegador na página: " + url);
        } catch (Exception e) {
            System.out.println("Erro ao tentar abrir o navegador.");
            return;
        }

        System.out.print("Era isso que você queria? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("n")) {
            System.out.println("Descreva especificamente o que deseja:");
            String novaBusca = scanner.nextLine().trim();
            exibirPagina(novaBusca, scanner);


        } else {
            System.out.print("Deseja fazer uma nova pesquisa? (s/n): ");
            String novaBusca = scanner.nextLine().trim().toLowerCase();

            if (novaBusca.equals("s")) {
                System.out.print("Digite a nova pesquisa: ");
                String nova = scanner.nextLine();
                exibirPagina(nova, scanner);
            } else {
                System.out.println("Encerrando o navegador.");
            }
        }
    }
    public void mostrarMusicas() {
        System.out.println("Playlist:");
        for (int i = 0; i < listaDeMusicas.size(); i++) {
            System.out.println((i + 1) + ". " + listaDeMusicas.get(i));
        }
    }

    public void selecionarMusica(String musica) {
        boolean encontrada = false;
        for (int i = 0; i < listaDeMusicas.size(); i++) {
            if (listaDeMusicas.get(i).toLowerCase().contains(musica.toLowerCase())) {
                musicaAtual = i;
                encontrada = true;
                System.out.println(" Música selecionada: " + listaDeMusicas.get(musicaAtual));
                break;
            }
        }

        if (!encontrada) {
            System.out.println("Música não encontrada na playlist. Digite o nome ou parte do nome da música.");
            musicaAtual = -1;
        }
    }

    public void tocar() {
        if (musicaAtual != -1) {
            if (!tocando) {
                tocando = true;
                System.out.println("Tocando: " + listaDeMusicas.get(musicaAtual));
            } else {
                System.out.println("Já está tocando: " + listaDeMusicas.get(musicaAtual));
            }
    
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println(" p  Pausar");
                System.out.println(" r  Repetir");
                System.out.println(" s  Parar");
                System.out.print("Escolha uma ação: ");
                String resposta = scanner.nextLine().trim().toLowerCase();
    
                switch (resposta) {
                    case "s":
                        parar();
                        return;
                    case "p":
                        pausar();
                        return;
                    case "r":
                        repetir();
                        break;
                    default:
                        System.out.println("Opção inválida. Digite 'p' para pausar, 'r' para repetir ou 's' para parar.");
                }
            }
    
        } else {
            System.out.println("Nenhuma música selecionada.");
        }
    }

    public void repetir() {
        System.out.println("Repetindo música.");
        if (musicaAtual != -1) {
            System.out.println("Repetindo: " + listaDeMusicas.get(musicaAtual));
        } else {
            System.out.println("Nenhuma música selecionada.");
        }
    }

    public void retornaPlaylist(Scanner scanner) {
        while (true) {
            mostrarMusicas();
            System.out.print("Digite o nome ou parte do nome da música: ");
            String nome = scanner.nextLine();
            selecionarMusica(nome);
    
            if (musicaAtual != -1) {
                break; 
            }
    
            System.out.println("Vamos tentar novamente...");
        }
    }
    public void parar() {
        Scanner scanner = new Scanner(System.in);
    
        if (tocando) {
            tocando = false;
            System.out.println("Música parada.");
        } else {
            System.out.println("Nenhuma música está tocando.");
        }
    
        while (true) {
            System.out.print("Deseja voltar ao menu de músicas? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();
    
            if (resposta.equals("s")) {
                retornaPlaylist(scanner);
                break;
            } else if (resposta.equals("n")) {
                System.out.println("Encerrando reprodução.");
                break;
            } else {
                System.out.println("Opção inválida. Digite 's' para sim ou 'n' para não.");
            }
        }
    }

    public void pausar() {
        if (tocando) {
            tocando = false;
            System.out.println("Música pausada.");
        } else {
            System.out.println("Nenhuma música está tocando.");
        }
    
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Deseja voltar a tocar? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();
    
            if (resposta.equals("s")) {
                System.out.println("Sua música está tocando...");
                tocar();
                break;
            } else if (resposta.equals("n")) {
                System.out.println("Música continuará pausada.");
               
            } else {
                System.out.println("Opção inválida. Digite 's' para sim ou 'n' para não.");
            }
        }
    }

}