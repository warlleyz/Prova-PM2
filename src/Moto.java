package src;

public class Moto extends Veiculo implements Inspecionavel {
    private int cilindrada;
    private boolean inspecionado;

    public Moto(String placa, String modelo, int anoFabricacao, double valorDiaria,
            double quilometragem, StatusVeiculo status, int cilindrada) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.cilindrada = cilindrada;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Tipo: Moto");
        exibirDadosComuns();
        System.out.println("Cilindrada (cc): " + cilindrada);
        System.out.println("Inspecionado: " + (inspecionado ? "Sim" : "Não"));
    }

    @Override
    public boolean possuiSeguroEspecial() {
        return false;
    }

    @Override
    public void realizarInspecao() {
        inspecionado = true;
        System.out.println("Inspeção registrada para " + getPlaca() + ".");
    }
}
