package src;

public class AluguelPremium extends Aluguel {

    public AluguelPremium(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {
        super(nomeCliente, quantidadeDias, valorTotal, status);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Aluguel Premium");
        exibirDadosComuns();
        System.out.println("Quilometragem ilimitada");

    }

    @Override
    public boolean possuiSeguroIncluso() {
        return true;
    }
}