package org.example.rmi;

import org.example.rmi.sistema.IEstoque;

import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Conectando ao servidor
            IEstoque estoque = (IEstoque) Naming.lookup("rmi://localhost:1099/estoque");
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\nEscolha uma operação:");
                System.out.println("1. Adicionar produto");
                System.out.println("2. Listar produtos");
                System.out.println("3. Remover produto");
                System.out.println("4. Filtrar por intervalo de preços");
                System.out.println("5. Sair");

                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("Digite o ID, nome, quantidade e preço do produto:");
                        int id = scanner.nextInt();
                        String nome = scanner.next();
                        int quantidade = scanner.nextInt();
                        double preco = scanner.nextDouble();
                        estoque.addProduto(id, nome, quantidade, preco);
                        break;
                    case 2:
                        System.out.println("Produtos no estoque:");
                        System.out.println(estoque.listarProdutos());
                        break;
                    case 3:
                        System.out.println("Digite o ID do produto para remover:");
                        int removeId = scanner.nextInt();
                        estoque.removeProduto(removeId);
                        break;
                    case 4:
                        System.out.println("Digite o intervalo de preços (min e max):");
                        double minPreco = scanner.nextDouble();
                        double maxPreco = scanner.nextDouble();
                        System.out.println("Produtos filtrados:");
                        System.out.println(estoque.filterByPriceRange(minPreco, maxPreco));
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
