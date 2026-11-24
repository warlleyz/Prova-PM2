package src;

public class Carro extends Veiculo implements Avaliavel {
    private int quantidadePortas;
    private int nota = -1;

    public Carro(String placa, String modelo, int anoFabricacao, double valorDiaria,
            double quilometragem, StatusVeiculo status, int quantidadePortas) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.quantidadePortas = quantidadePortas;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Tipo: Carro");
        exibirDadosComuns();
        System.out.println("Quantidade de portas: " + quantidadePortas);
        System.out.println("Avaliação: " + (nota == -1 ? "Sem avaliação" : nota));
    }

    @Override
    public boolean possuiSeguroEspecial() {
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
