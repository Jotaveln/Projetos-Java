import java.util.ArrayList;
import java.util.List;

public class Conta {
    private Cliente cliente;
    private int numeroConta;
    private double saldo;
    private List<String> extrato;

    public Conta(Cliente cliente, int numeroConta, double saldoInicial) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
        this.extrato = new ArrayList<>();
        extrato.add("Conta criada com saldo inicial: R$ " + saldoInicial);
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            extrato.add("Depósito: +R$ " + valor);
        } else {
            extrato.add("Tentativa de depósito inválido: R$ " + valor);
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            extrato.add("Saque: -R$ " + valor);
            return true;
        } else {
            extrato.add("Tentativa de saque inválido: R$ " + valor);
            return false;
        }
    }

    // Adicione dentro da classe Conta, junto com depositar e sacar
    public boolean transferir(Conta destino, double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            destino.saldo += valor;

            // Registrar no extrato das duas contas
            this.extrato.add("Transferência de R$ " + valor + " para conta " + destino.getNumeroConta());
            destino.extrato.add("Recebido R$ " + valor + " da conta " + this.getNumeroConta());

            return true; // transferência bem-sucedida
        } else {
            this.extrato.add("Tentativa de transferência inválida: R$ " + valor + " para conta " + destino.getNumeroConta());
            return false; // transferência falhou
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public List<String> getExtrato() {
        return new ArrayList<>(extrato);
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }
}