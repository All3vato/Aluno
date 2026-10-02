import java.util.Locale;
import java.util.Scanner;

public class Aluno {
    private static final double MENSALIDADE_BASE = 500.00;

    private int matricula;
    private String nome;
    private double descontoPercentual;
    private double saldoDevedor;

    public Aluno(int matricula, String nome) {
        this(matricula, nome, 0.0);
    }

    public Aluno(int matricula, String nome, double desconto) {
        this.matricula = matricula;
        this.nome = nome;
        this.descontoPercentual = desconto;
        this.saldoDevedor = 0.0;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getDescontoPercentual() {
        return descontoPercentual;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void gerarMensalidade(double valorBase) {
        saldoDevedor += valorBase * (1 - descontoPercentual / 100);
    }

    public void efetuarPagamento(double valor) {
        saldoDevedor -= valor;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Insira o número de matrícula: ");
        int matricula = sc.nextInt();
        sc.nextLine();

        System.out.print("Insira o nome do aluno: ");
        String nome = sc.nextLine();

        System.out.print("Possui cupom de desconto inicial (s/n)? ");
        String resposta = sc.nextLine();

        Aluno aluno;
        if (resposta.equalsIgnoreCase("s")) {
            System.out.print("Insira o percentual de desconto (%): ");
            double desconto = sc.nextDouble();
            sc.nextLine();
            aluno = new Aluno(matricula, nome, desconto);
        } else {
            aluno = new Aluno(matricula, nome);
        }

        System.out.printf(Locale.US, "Dados do aluno: Matrícula: %d, Aluno: %s, Desconto: %.1f%%, Saldo Devedor: R$ %.2f%n",
                aluno.getMatricula(), aluno.getNome(), aluno.getDescontoPercentual(), aluno.getSaldoDevedor());

        System.out.println("Gerando mensalidade base de R$ 500.00 com desconto...");
        aluno.gerarMensalidade(MENSALIDADE_BASE);
        System.out.printf(Locale.US, "Dados atualizados: Matrícula: %d, Aluno: %s, Saldo Devedor: R$ %.2f%n",
                aluno.getMatricula(), aluno.getNome(), aluno.getSaldoDevedor());

        System.out.print("Insira o valor do pagamento: ");
        double pagamento = sc.nextDouble();
        sc.nextLine();
        aluno.efetuarPagamento(pagamento);
        System.out.printf(Locale.US, "Dados atualizados: Matrícula: %d, Aluno: %s, Saldo Devedor: R$ %.2f%n",
                aluno.getMatricula(), aluno.getNome(), aluno.getSaldoDevedor());

        sc.close();
    }
}
