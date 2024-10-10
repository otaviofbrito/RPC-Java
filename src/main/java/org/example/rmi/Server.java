package org.example.rmi;

import org.example.rmi.sistema.Estoque;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Estoque estoqueServer = new Estoque();
            Naming.rebind("rmi://localhost:1099/estoque", estoqueServer);
            System.out.println("Servidor de Estoque pronto e aguardando conexões...");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
