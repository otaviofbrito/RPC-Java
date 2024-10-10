package org.example.rmi.sistema;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class Estoque extends UnicastRemoteObject implements IEstoque {
    LinkedList<Produto> produtos;

    public Estoque() throws RemoteException {
        super();
        this.produtos = new LinkedList<>();
    }

    // Adicionar produtos ao estoque
    @Override
    public String addProduto(int id, String nome, int quantidade, double preco) throws RemoteException {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return "Erro: Produto com ID " + id + " já existe no estoque.";
            }
        }

        Produto new_produto = new Produto(id, nome, preco, quantidade);
        this.produtos.add(new_produto);
        return "Produto adicionado com sucesso: " + new_produto;
    }


    // Remover produtos do estoque por ID
    @Override
    public String removeProduto(int id) throws RemoteException {
        boolean produtoRemovido = this.produtos.removeIf(produto -> produto.getId() == id);

        if (produtoRemovido) {
            return "Produto com ID " + id + " excluído com sucesso!";
        } else {
           return "Erro: Produto com ID " + id + " não encontrado no estoque.";
        }
    }


    // Listar todos os produtos do estoque
    @Override
    public String listarProdutos() throws RemoteException {
        if (produtos.isEmpty()) {
            return "Nenhum produto no estoque.";
        }

        StringBuilder sb = new StringBuilder();
        for (Produto produto : produtos) {
            sb.append(produto.toString()).append("\n");
        }

        return sb.toString();
    }

    // Filtrar produtos por intervalo de valores de preço
    @Override
    public String filterByPriceRange(double minPrice, double maxPrice) throws RemoteException {
        List<Produto> filteredProducts = produtos.stream()
                .filter(produto -> produto.getPreco() >= minPrice && produto.getPreco() <= maxPrice)
                .toList();

        if (filteredProducts.isEmpty()) {
            return "Nenhum produto encontrado no intervalo de preços.";
        }

        StringBuilder sb = new StringBuilder();
        for (Produto produto : filteredProducts) {
            sb.append(produto.toString()).append("\n");
        }

        return sb.toString();
    }

    public LinkedList<Produto> getProdutos() throws RemoteException {
        return this.produtos;
    }
}
