package src;

public class AluguelBasico extends Aluguel {
    public AluguelBasico(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {
        super(nomeCliente, quantidadeDias, valorTotal, status);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Aluguel Básico");
        exibirDadosComuns();
        System.out.println("Limite de quilometragem: " + (100L * getQuantidadeDias()) + " km (100 km por dia)");
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return false;
    }
}
