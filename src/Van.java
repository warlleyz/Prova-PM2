package src;

public class Van extends Veiculo implements Avaliavel, Inspecionavel {
    private int quantidadeMaximaPassageiros;
    private int nota = -1;
    private boolean inspecionado;

    public Van(String placa, String modelo, int anoFabricacao, double valorDiaria,
            double quilometragem, StatusVeiculo status, int quantidadeMaximaPassageiros) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.quantidadeMaximaPassageiros = quantidadeMaximaPassageiros;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Tipo: Van");
        exibirDadosComuns();
        System.out.println("Máximo de passageiros: " + quantidadeMaximaPassageiros);
        System.out.println("Avaliação: " + (nota == -1 ? "Sem avaliação" : nota));
        System.out.println("Inspecionado: " + (inspecionado ? "Sim" : "Não"));
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

    @Override
    public void realizarInspecao() {
        inspecionado = true;
        System.out.println("Inspeção registrada para " + getPlaca() + ".");
    }
}
