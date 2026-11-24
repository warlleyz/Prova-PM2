package src;

public class AluguelExecutivo extends Aluguel implements Avaliavel {
    private int nota = -1;
    public AluguelExecutivo(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {
        super(nomeCliente, quantidadeDias, valorTotal, status);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Aluguel Executivo");
        exibirDadosComuns();
        System.out.println("Quilometragem ilimitada");
        System.out.println("Motorista particular: Sim");
        System.out.println("Avaliação: " + (nota == -1 ? "Sem avaliação" : nota));
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return false;
    }
    
    @Override
    public void avaliar(int nota) {
        if (nota >= 0 && nota <= 10) {
            this.nota = nota;
            System.out.println("Avaliação registrada.");
        } else {
            System.out.println("Nota inválida.");
        }
    }
}
