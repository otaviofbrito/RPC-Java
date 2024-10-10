package org.example.rmi.sistema;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEstoque extends Remote {

    String addProduto(int id, String nome, int quantidade, double preco) throws RemoteException;
    String removeProduto(int id) throws RemoteException;
    String listarProdutos() throws RemoteException;
    String filterByPriceRange(double minPrice, double maxPrice) throws RemoteException;

}
