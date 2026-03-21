import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        List<Conta> contas = new ArrayList<>();

        System.out.println("=== Sistema Bancário Profissional ===");

        // Cadastro de múltiplos clientes e contas
        int quantidadeClientes;
        while (true) {
            System.out.print("Quantos clientes deseja cadastrar? ");
            if (teclado.hasNextInt()) {
                quantidadeClientes = teclado.nextInt();
                teclado.nextLine();
                break;
            } else {
                System.out.println("Digite um número válido!");
                teclado.next();
            }
        }

        for (int i = 0; i < quantidadeClientes; i++) {
            System.out.println("\n=== Cadastro do Cliente #" + (i + 1) + " ===");
            System.out.print("Nome: ");
            String nome = teclado.nextLine();

            int numeroConta;
            while (true) {
                System.out.print("Número da conta: ");
                if (teclado.hasNextInt()) {
                    numeroConta = teclado.nextInt();
                    teclado.nextLine();
                    break;
                } else {
                    System.out.println("Número inválido!");
                    teclado.next();
                }
            }

            double saldoInicial;
            while (true) {
                System.out.print("Saldo inicial: ");
                if (teclado.hasNextDouble()) {
                    saldoInicial = teclado.nextDouble();
                    teclado.nextLine();
                    break;
                } else {
                    System.out.println("Valor inválido!");
                    teclado.next();
                }
            }

            Cliente cliente = new Cliente(nome);
            Conta conta = new Conta(cliente, numeroConta, saldoInicial);
            contas.add(conta);
        }

        // Menu principal
        int opcao;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Selecionar conta");
            System.out.println("2 - Listar contas");
            System.out.println("3 - Cadastrar nova conta");
            System.out.println("4 - Transferir para outra conta");
            System.out.println("5 - Sair");

            while (!teclado.hasNextInt()) {
                System.out.println("Opção inválida!");
                teclado.next();
            }
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    if (contas.isEmpty()) {
                        System.out.println("Nenhuma conta cadastrada!");
                        break;
                    }

                    System.out.println("Digite o número da conta que deseja acessar: ");
                    int numContaSelecionada = teclado.nextInt();
                    teclado.nextLine();

                    Conta contaSelecionada = null;
                    for (Conta c : contas) {
                        if (c.getNumeroConta() == numContaSelecionada) {
                            contaSelecionada = c;
                            break;
                        }
                    }

                    if (contaSelecionada == null) {
                        System.out.println("Conta não encontrada!");
                        break;
                    }

                    // Menu da conta
                    int opcaoConta;
                    do {
                        System.out.println("\n=== Menu Conta: " + contaSelecionada.getNumeroConta() + " ===");
                        System.out.println("1 - Depositar");
                        System.out.println("2 - Sacar");
                        System.out.println("3 - Ver saldo");
                        System.out.println("4 - Ver extrato");
                        System.out.println("5 - Voltar");

                        while (!teclado.hasNextInt()) {
                            System.out.println("Opção inválida!");
                            teclado.next();
                        }
                        opcaoConta = teclado.nextInt();
                        teclado.nextLine();

                        switch (opcaoConta) {
                            case 1:
                                System.out.print("Valor para depósito: ");
                                while (!teclado.hasNextDouble()) {
                                    System.out.println("Valor inválido!");
                                    teclado.next();
                                }
                                double deposito = teclado.nextDouble();
                                teclado.nextLine();
                                contaSelecionada.depositar(deposito);
                                System.out.println("Depósito realizado!");
                                break;

                            case 2:
                                System.out.print("Valor para saque: ");
                                while (!teclado.hasNextDouble()) {
                                    System.out.println("Valor inválido!");
                                    teclado.next();
                                }
                                double saque = teclado.nextDouble();
                                teclado.nextLine();
                                if (contaSelecionada.sacar(saque)) {
                                    System.out.println("Saque realizado!");
                                } else {
                                    System.out.println("Saldo insuficiente ou valor inválido.");
                                }
                                break;

                            case 3:
                                System.out.println("Saldo atual: R$ " + contaSelecionada.getSaldo());
                                break;

                            case 4:
                                System.out.println("\n=== EXTRATO ===");
                                System.out.println("Cliente: " + contaSelecionada.getCliente().getNome());
                                System.out.println("Conta: " + contaSelecionada.getNumeroConta());
                                for (String movimento : contaSelecionada.getExtrato()) {
                                    System.out.println(movimento);
                                }
                                System.out.println("Saldo atual: R$ " + contaSelecionada.getSaldo());
                                break;

                            case 5:
                                System.out.println("Retornando ao menu principal...");
                                break;

                            default:
                                System.out.println("Opção inválida!");
                        }
                    } while (opcaoConta != 5);
                    break;

                case 2:
                    if (contas.isEmpty()) {
                        System.out.println("Nenhuma conta cadastrada!");
                    } else {
                        System.out.println("=== Contas Cadastradas ===");
                        for (Conta c : contas) {
                            System.out.println("Conta: " + c.getNumeroConta() + " | Cliente: " + c.getCliente().getNome() + " | Saldo: R$ " + c.getSaldo());
                        }
                    }
                    break;

                case 3: // Cadastrar nova conta
                    System.out.println("\n=== Cadastro de Nova Conta ===");
                    System.out.print("Nome do cliente: ");
                    String nomeNovo = teclado.nextLine();

                    int numeroNovaConta;
                    while (true) {
                        System.out.print("Número da conta: ");
                        if (teclado.hasNextInt()) {
                            numeroNovaConta = teclado.nextInt();
                            teclado.nextLine();
                            break;
                        } else {
                            System.out.println("Número inválido!");
                            teclado.next();
                        }
                    }

                    double saldoNovo;
                    while (true) {
                        System.out.print("Saldo inicial: ");
                        if (teclado.hasNextDouble()) {
                            saldoNovo = teclado.nextDouble();
                            teclado.nextLine();
                            break;
                        } else {
                            System.out.println("Valor inválido!");
                            teclado.next();
                        }
                    }

                    Cliente novoCliente = new Cliente(nomeNovo);
                    Conta novaConta = new Conta(novoCliente, numeroNovaConta, saldoNovo);
                    contas.add(novaConta);

                    System.out.println("Nova conta cadastrada com sucesso!");
                    break;

                case 4: // Transferência
                    System.out.print("Digite o número da conta de origem: ");
                    int contaOrigemNum = teclado.nextInt();
                    teclado.nextLine();

                    Conta contaOrigem = null;
                    for (Conta c : contas) {
                        if (c.getNumeroConta() == contaOrigemNum) {
                            contaOrigem = c;
                            break;
                        }
                    }

                    if (contaOrigem == null) {
                        System.out.println("Conta de origem não encontrada!");
                        break;
                    }

                    System.out.print("Digite o número da conta destino: ");
                    int contaDestinoNum = teclado.nextInt();
                    teclado.nextLine();

                    Conta contaDestino = null;
                    for (Conta c : contas) {
                        if (c.getNumeroConta() == contaDestinoNum) {
                            contaDestino = c;
                            break;
                        }
                    }

                    if (contaDestino == null) {
                        System.out.println("Conta destino não encontrada!");
                        break;
                    }

                    System.out.print("Digite o valor da transferência: ");
                    double valorTransferencia = teclado.nextDouble();
                    teclado.nextLine();

                    if (contaOrigem.transferir(contaDestino, valorTransferencia)) {
                        System.out.println("Transferência realizada com sucesso!");
                    } else {
                        System.out.println("Falha na transferência: saldo insuficiente ou valor inválido.");
                    }
                    break;
                case 5:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 5);

        teclado.close();
    }
}