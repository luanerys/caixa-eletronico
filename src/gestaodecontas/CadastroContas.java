package gestaodecontas;

public class CadastroContas {
    private Conta contas[];
    private int numeroDeContas;

    public CadastroContas(int numeroDeContas) {
    	
        this.contas = new Conta[numeroDeContas + 1];
    }

    public boolean insereConta(Conta conta) {
        if (this.numeroDeContas >= this.contas.length) {
            return false;
        } else {
            for (int i = 1; i <= this.numeroDeContas; i++) {
                if (conta.getNumero() == this.contas[i].getNumero()) {
                    return false;
                }
            }
            this.numeroDeContas++;
            this.contas[numeroDeContas] = conta;
            return true;
        }

    }

    public Conta buscaConta(int numcta) {

        for (int i = 1; i <= this.numeroDeContas; i++) {
            if (numcta == this.contas[i].getNumero()) {
                return this.contas[i];
            }
        }
        return null;
    }
    
 }
