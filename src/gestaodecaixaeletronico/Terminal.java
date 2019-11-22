package gestaodecaixaeletronico;
import java.util.Scanner;

import gestaodecontas.*;

public class Terminal {
    private Caixa meuCaixa;
    private int modoAtual;

    public Terminal(CadastroContas bd) {

        this.meuCaixa = new Caixa(this, bd);

    }

    public void iniciaOperacao() {

        int opcao;
        opcao = this.getOpcao();

        while (opcao != 8) {

            switch (opcao) {

                case 1:
                    double saldo = this.meuCaixa.consultaSaldo(getInt("Numero da Conta: "), getInt("Senha: "));
                    if (saldo != -1) {
                        System.out.println("Saldo Atual: " + saldo);
                    } else {
                        System.out.println("Conta/senha invalida");
                    }
                    break;
                case 2:
                    boolean b = this.meuCaixa.efetuaSaque(getInt("Numero da Conta:"), (double) getInt("Valor:"), getInt("Senha:"));
                    if (b) {
                        System.out.println("Retire o dinheiro");
                    } else {
                        System.out.println("Pedido de saque recusado");
                    }
                    break;

                case 3:
                    this.meuCaixa.recarrega();
                    break;
               
                case 4:
                    this.meuCaixa.transferencia(getInt("Conta de Origem: "), getInt("Conta de destino: "), (double) getInt("Valor: "),getInt("Senha: "));
                    break;
                    
                case 5:
                    boolean c = this.meuCaixa.depositaDinheiro(getInt("Numero da Conta: "), (double) getInt("Valor: "));
                    if (c) {
                        System.out.println("Deposito  realizado com sucesso");
                    } else {
                        System.out.println("Pedido de deposito recusado");
                    }
                    break;
                    
                case 6:
                    boolean c1 = this.meuCaixa.depositaCheque(getInt("Numero da Conta"), (double) getInt("Valor"));
                    if (c1) {
                        System.out.println("Deposito  realizado com sucesso");
                    } else {
                        System.out.println("Pedido de deposito recusado");
                    }
                    break;
                    
                case 7:
                	this.meuCaixa.mostraExtrato(getInt("Conta: "),getInt("Senha: "));
                	break;
            }
            opcao = getOpcao();
        }
    }

    public void setModo(int modo) {

        if (modo == 0 || modo == 1) {
            this.modoAtual = modo;
        }
    }

    private int getOpcao() {

        int opcao;
        do {
            if (this.modoAtual == 1) {
                opcao = getInt("Opcao: \n1 - Consulta saldo \n2 - Saque \n4 - Transferencia \n5 - Desposito em dinheiro \n6 - Deposito em cheque \n7 - Extrato \n8 - Sair");
                if (!(opcao>= 1 & opcao<3) & !(opcao>3 & opcao<9)) {
                    opcao = 0;
                }
            } else {
                opcao = getInt("opcao: \n3 - Recarrega \n5 - Sair");
                if (opcao != 3  & opcao != 8) {
                    opcao = 0;
                }
            }
        } while (opcao == 0);
        return opcao;
    }

    private int getInt(String string) {

        Scanner r = new Scanner(System.in);
        System.out.println("Escolha uma " + string);

        if (r.hasNextInt()) {
            return r.nextInt();
        }

        String st = r.next();
        System.out.println("Erro na Leitura de Dados");

        return 0;

    }
}