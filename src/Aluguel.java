/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;
import java.util.ArrayList;

/**
 *
 * @author glbra
 */

public abstract class Aluguel {
    private String nomeCliente;
    private int quantidadeDias;
    private double valorTotal;
    private StatusAluguel status;
    private ArrayList<Veiculo> veiculos = new ArrayList<>();

    public Aluguel(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {
        this.nomeCliente = nomeCliente;
        this.quantidadeDias = quantidadeDias;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
        calcularValorTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusAluguel getStatus() {
        return status;
    }

    public void setStatus(StatusAluguel status) {
        this.status = status;
    }

    public boolean adicionarVeiculo(Veiculo veiculo) {
        if (status != StatusAluguel.ATIVO || veiculo.getStatus() != StatusVeiculo.DISPONIVEL
                || veiculos.contains(veiculo)) {
            return false;
        }
        veiculos.add(veiculo);
        veiculo.setStatus(StatusVeiculo.ALUGADO);
        calcularValorTotal();
        return true;
    }

    public void removerVeiculo(Veiculo veiculo) {
        if (veiculos.remove(veiculo)) {
            calcularValorTotal();
        }
    }

    private void calcularValorTotal() {
        valorTotal = 0;
        for (Veiculo veiculo : veiculos) {
            valorTotal += veiculo.getValorDiaria() * quantidadeDias;
        }
    }

    protected void exibirDadosComuns() {
        System.out.println("Cliente: " + nomeCliente + " | Dias: " + quantidadeDias);
        System.out.printf("Valor total: R$ %.2f | Status: %s%n", valorTotal, status);
        System.out.println("Seguro incluso: " + (possuiSeguroIncluso() ? "Sim" : "Não"));
        System.out.println("Veículos associados:");
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo associado.");
        }

        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo.getPlaca() + " - " + veiculo.getModelo());
        }
    }

    public abstract void exibirDetalhes();
    public abstract boolean possuiSeguroIncluso(); 
}
