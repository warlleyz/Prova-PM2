package src;

import java.util.ArrayList;
import java.util.Scanner;

public class PM_Prova2 {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Veiculo> veiculos = new ArrayList<>();
    private static ArrayList<Aluguel> alugueis = new ArrayList<>();

    public static void main(String[] args) {
        criarDados();
        int opcao;
        do {
            System.out.println("\n--- Sistema de aluguel ---");
            System.out.println("1 - Associar veículos a um aluguel");
            System.out.println("2 - Exibir todos os veículos");
            System.out.println("3 - Exibir todos os aluguéis");
            System.out.println("4 - Exibir veículos disponíveis");
            System.out.println("5 - Avaliar");
            System.out.println("6 - Realizar inspeção");
            System.out.println("7 - Remover veículos em manutenção");
            System.out.println("8 - Cadastrar aluguel");
            System.out.println("0 - Sair");

            opcao = lerInteiro("Opção: ");

            switch (opcao) {
                case 1: associarVeiculo(); break;
                case 2: exibirVeiculos(false); break;
                case 3: exibirAlugueis(); break;
                case 4: exibirVeiculos(true); break;
                case 5: avaliar(); break;
                case 6: inspecionar(); break;
                case 7: removerManutencao(); break;
                case 8: cadastrarAluguel(); break;
                case 0: System.out.println("Programa encerrado."); break;
                default: System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
        sc.close();
    }

    private static void criarDados() {
        veiculos.add(new Carro("ABC1D23", "Mobi", 2021, 120, 35000, StatusVeiculo.DISPONIVEL, 4));
        veiculos.add(new Moto("DEF4G56", "CG 160", 2022, 70, 15000, StatusVeiculo.DISPONIVEL, 160));
        veiculos.add(new Van("HIJ7K89", "Sprinter", 2020, 250, 60000, StatusVeiculo.MANUTENCAO, 15));
        alugueis.add(new AluguelBasico("Gabriel", 3, 0, StatusAluguel.ATIVO));
        alugueis.add(new AluguelExecutivo("Julia", 2, 0, StatusAluguel.ATIVO));
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (!sc.hasNextLine()) {
                System.exit(0);
            }

            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro.");
            }
        }
    }

    private static void exibirVeiculos(boolean somenteDisponiveis) {
        boolean encontrou = false;
        for (Veiculo veiculo : veiculos) {
            if (!somenteDisponiveis || veiculo.getStatus() == StatusVeiculo.DISPONIVEL) {
                veiculo.exibirDetalhes();
                System.out.println();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum veículo encontrado.");
        }
    }

    private static void exibirAlugueis() {
        for (int i = 0; i < alugueis.size(); i++) {
            System.out.println("Aluguel " + (i + 1));
            alugueis.get(i).exibirDetalhes();
            System.out.println();
        }

        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel cadastrado.");
        }
    }

    private static void associarVeiculo() {
        exibirAlugueis();
        int numero = lerInteiro("Número do aluguel (0 para voltar): ");

        if (numero == 0) return;
        if (numero < 1 || numero > alugueis.size()) {
            System.out.println("Aluguel inválido.");
            return;
        }

        Aluguel aluguel = alugueis.get(numero - 1);

        if (aluguel.getStatus() != StatusAluguel.ATIVO) {
            System.out.println("O aluguel não está ativo.");
            return;
        }

        while (true) {
            ArrayList<Veiculo> disponiveis = new ArrayList<>();
            for (Veiculo veiculo : veiculos) {
                if (veiculo.getStatus() == StatusVeiculo.DISPONIVEL) {
                    disponiveis.add(veiculo);
                    System.out.println(disponiveis.size() + " - " + veiculo.getPlaca()
                            + " - " + veiculo.getModelo());
                }
            }
            if (disponiveis.isEmpty()) {
                System.out.println("Não há veículos disponíveis.");
                return;
            }
            int escolha = lerInteiro("Número do veículo (0 para concluir): ");
            if (escolha == 0) return;
            if (escolha < 1 || escolha > disponiveis.size()) {
                System.out.println("Veículo inválido.");
            } else if (aluguel.adicionarVeiculo(disponiveis.get(escolha - 1))) {
                System.out.println("Veículo associado com sucesso.");
            } else {
                System.out.println("Não foi possível associar o veículo.");
            }
        }
    }

    private static void avaliar() {
        ArrayList<Avaliavel> avaliaveis = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo instanceof Avaliavel) {
                avaliaveis.add((Avaliavel) veiculo);
                System.out.println(avaliaveis.size() + " - " + veiculo.getModelo()
                        + " (" + veiculo.getPlaca() + ")");
            }
        }

        for (Aluguel aluguel : alugueis) {
            if (aluguel instanceof Avaliavel) {
                avaliaveis.add((Avaliavel) aluguel);
                System.out.println(avaliaveis.size() + " - Aluguel de " + aluguel.getNomeCliente());
            }
        }

        if (avaliaveis.isEmpty()) {
            System.out.println("Nenhum objeto avaliável.");
            return;
        }

        int escolha = lerInteiro("Escolha um objeto (0 para voltar): ");
        if (escolha == 0) return;
        
        if (escolha < 1 || escolha > avaliaveis.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        int nota = lerInteiro("Nota (0 a 10): ");
        avaliaveis.get(escolha - 1).avaliar(nota);
    }

    private static void inspecionar() {
        ArrayList<Veiculo> inspecionaveis = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo instanceof Inspecionavel) {
                inspecionaveis.add(veiculo);
                System.out.println(inspecionaveis.size() + " - " + veiculo.getModelo()
                        + " (" + veiculo.getPlaca() + ")");
            }
        }

        if (inspecionaveis.isEmpty()) {
            System.out.println("Nenhum veículo inspecionável.");
            return;
        }

        int escolha = lerInteiro("Escolha um veículo (0 para voltar): ");
        if (escolha == 0) return;

        if (escolha < 1 || escolha > inspecionaveis.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Inspecionavel veiculo = (Inspecionavel) inspecionaveis.get(escolha - 1);
        veiculo.realizarInspecao();
    }

    private static void removerManutencao() {
        int removidos = 0;
        for (int i = veiculos.size() - 1; i >= 0; i--) {
            Veiculo veiculo = veiculos.get(i);
            if (veiculo.getStatus() == StatusVeiculo.MANUTENCAO) {
                for (Aluguel aluguel : alugueis) {
                    aluguel.removerVeiculo(veiculo);
                }
                veiculos.remove(i);
                removidos++;
            }
        }
        System.out.println("Veículos removidos: " + removidos);
    }

    private static void cadastrarAluguel() {
        System.out.println("1 - Básico | 2 - Premium | 3 - Executivo");
        int tipo = lerInteiro("Tipo de aluguel: ");
        
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo inválido.");
            return;
        }

        System.out.print("Nome do cliente: ");
        if (!sc.hasNextLine()) return;
        String cliente = sc.nextLine().trim();
        int dias = lerInteiro("Quantidade de dias: ");
        
        if (cliente.isEmpty() || dias <= 0) {
            System.out.println("Dados inválidos.");
            return;
        }

        switch (tipo) {
            case 1: alugueis.add(new AluguelBasico(cliente, dias, 0, StatusAluguel.ATIVO)); break;
            case 2: alugueis.add(new AluguelPremium(cliente, dias, 0, StatusAluguel.ATIVO)); break;
            case 3: alugueis.add(new AluguelExecutivo(cliente, dias, 0, StatusAluguel.ATIVO)); break;
        }

        System.out.println("Aluguel cadastrado.");
    }
}
