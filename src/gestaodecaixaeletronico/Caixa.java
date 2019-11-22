package gestaodecaixaeletronico;
import gestaodecontas.*;

public class Caixa {
    private Terminal meuCaixaEletronico;
    private CadastroContas bdContas;
    private double saldoCaixa;

    public Caixa(Terminal terminal, CadastroContas bd) {

        this.meuCaixaEletronico = terminal;
        this.bdContas = bd;

    }

    public double consultaSaldo(int numeroDaConta, int senha) {

        Conta conta;
        conta = this.bdContas.buscaConta(numeroDaConta);

        if (conta != null) {
            return conta.getSaldo(senha);
        } else {
            return -1;
        }

    }

    public boolean transferencia(int ctOrigem, int ctDestino, double valor, int senhaCtOrigem){
    	Conta contaOrigem = bdContas.buscaConta(ctOrigem);
    	Conta contaDestino = bdContas.buscaConta(ctDestino);
    	if(contaOrigem == null || contaDestino == null || contaOrigem.debitaValor(senhaCtOrigem, valor,  "Transferencia") == false ){
    		return false;
    	
    	}
    	 contaDestino.depositaValor(ctDestino, valor, "Transferencia");
    	 return true;
    }
    
    
    public boolean efetuaSaque(int numeroDaConta, double valor, int senha) {

        if (valor < 0 || (valor % 50) != 0 || valor > 500 || valor > this.saldoCaixa) {
            return false;
        }

        Conta conta = bdContas.buscaConta(numeroDaConta);

        if (conta == null || conta.debitaValor(senha, valor, "Saque Automatico") == false) {
            return false;
        }
        this.liberaNotas((int) (valor / 50));
        this.saldoCaixa -= valor;

        if (this.saldoCaixa < 500) {
            this.meuCaixaEletronico.setModo(0);
        }
        return true;
    }

    public void recarrega() {

        this.saldoCaixa = 5000;
        this.meuCaixaEletronico.setModo(1);

    }

    private void liberaNotas(int qtd) {

        while (qtd-- > 0) {
            System.out.println("===/ R$50,00 /===");
        }

    }
    
    public boolean depositaDinheiro(int ctDestino, double valor){
    	if(valor < 0){
    	
    		return false;
    	}
    	
    	Conta contaDestino = bdContas.buscaConta(ctDestino);
    	
    	if(contaDestino == null || contaDestino.depositaValor(ctDestino, valor, "Depoisto em dinheiro") == false){
            return false;		
        }
    
    	this.saldoCaixa+=valor;
    		
    	return true;
    }
    public boolean depositaCheque(int ctDestino, double valor){
    	if(valor < 0){
            return false;
    	}
    
    	Conta contaDestino = bdContas.buscaConta(ctDestino);
    	
    	if(contaDestino == null || contaDestino.depositaValor(ctDestino, valor, "Depoisto em cheque") == false){
            return false;		
        }
    	this.saldoCaixa+=valor;		
        return true;
    }
    
    public void mostraExtrato(int numeroConta, int senha){
        Conta conta = bdContas.buscaConta(numeroConta);
        if (conta == null) {
            System.out.println("Conta inexistente");;
        }
        conta.extrato(senha);
    }
}