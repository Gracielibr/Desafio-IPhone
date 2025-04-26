import java.util.Scanner;

public abstract class AparelhoTelefonico {

   public void menuTelefonico() {
      Scanner scanner = new Scanner(System.in);  
      System.out.println("Aparelho Telefônico:");
      while (true) {
          System.out.print("Digite 'c' para ligar ou 'm' para atender: ");
          String acao = scanner.nextLine().trim().toLowerCase();
  
          if (acao.equals("c")) {
              System.out.print("Digite o número para ligar: ");
              String numero = scanner.nextLine().trim();
  
            
              while (!numero.matches("\\d+")) {
                  System.out.println("Número inválido. Digite apenas números.");
                  System.out.print("Digite o número para ligar: ");
                  numero = scanner.nextLine().trim();
              }  
              ligar(numero);
              break;
  
          } else if (acao.equals("m")) {
              atender();
              break;
  
          } else {
              System.out.println("Opção inválida. Digite apenas 'c' para ligar ou 'm' para atender.");
          }
      }
  }

   


    public void ligar(String numero) {
        Scanner scanner = new Scanner(System.in);
        
         while (numero == null || !numero.matches("\\d+")) {
         System.out.println("Número inválido. Digite apenas números.");
         System.out.print("Digite o número para ligar: ");
         numero = scanner.nextLine().trim();
      }
 
               
        int tentativas = 0;
        boolean chamadaConectada = false;

        while (tentativas < 3 && !chamadaConectada) {
            System.out.println("Ligando para " + numero + "...");
            System.out.println("Chamando...");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Erro na ligação.");
            }

            System.out.print("A pessoa atendeu? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (resposta.equals("s")) {
                chamadaConectada = true;
                System.out.println("Chamada conectada!");
                System.out.println("Conversando...");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Erro na chamada.");
                }

                String desligar = "";
                while (!desligar.equals("s")) {
                    System.out.print("Deseja desligar? (s/n): ");
                    desligar = scanner.nextLine().trim().toLowerCase();

                    if (desligar.equals("s")) {
                        System.out.println("Ligação encerrada.");
                    } else if (desligar.equals("n")) {
                        System.out.println("A ligação continua...");
                    } else {
                        System.out.println("Opção inválida. Digite 's' ou 'n'.");
                    }
                }

            } else {
               tentativas++;
               if (tentativas < 3) {
                   System.out.println("Ninguém atendeu.");
                   System.out.print("Deseja tentar ligar novamente? (s/n): ");
                   String tentarNovamente = scanner.nextLine().trim().toLowerCase();
                   if (!tentarNovamente.equals("s")) {
                       break;
                   }
               } else {
                   System.out.println("Ninguém atendeu.");
               }
           }
       }
   
       if (!chamadaConectada && tentativas >= 3) {
           System.out.println("Número máximo de tentativas atingido.");
       }
   
       if (!chamadaConectada) {
            System.out.print("Deseja deixar um correio de voz? (s/n): ");
            String correio = scanner.nextLine().trim().toLowerCase();

            if (correio.equals("s")) {
                iniciarCorreioVoz();
            } else {
                System.out.println("Ligação encerrada sem mensagem.");
            }
        }
    }

    public void iniciarCorreioVoz() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Grave sua mensagem após o sinal: ");
        String mensagem = scanner.nextLine();
        System.out.println("Mensagem salva: \"" + mensagem + "\"");
        System.out.println("Correio de voz encerrado.");
    }

    
    public void atender() {
      Scanner scanner = new Scanner(System.in);

      System.out.println("Chamada recebida!");
      System.out.print("Deseja atender a ligação? (s/n): ");
      String resposta = scanner.nextLine().trim().toLowerCase();

      if (resposta.equals("s")) {
          System.out.println("Ligação atendida.");
          System.out.println("Conversando...");

          try {
              Thread.sleep(2000);
          } catch (InterruptedException e) {
              System.out.println("Erro durante a conversa.");
          }

          String desligar = "";
          while (!desligar.equals("s")) {
              System.out.print("Deseja desligar a chamada? (s/n): ");
              desligar = scanner.nextLine().trim().toLowerCase();

              if (desligar.equals("s")) {
                  System.out.println("Chamada encerrada.");
              } else if (desligar.equals("n")) {
                  System.out.println("Continuando a ligação...");
              } else {
                  System.out.println("Opção inválida. Digite 's' ou 'n'.");
              }
          }
      } else {
          System.out.println("Ligação não atendida.");
      }
  }


}
