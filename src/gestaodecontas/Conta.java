package gestaodecontas;

public class Conta {
    private int numero; 
    private int senha;  
    private double saldo;  
    private String[] historico;  
    private double valorLancamento[];  
    private int ultimoLancamento;    

    public Conta(int numero, int senha, double saldo) {   
        this.numero = numero;   
        this.senha = senha;   
        this.saldo = saldo;   
        this.historico = new String[11];   
        this.valorLancamento = new double[11];  
    } 

    public int getNumero() {   
        return this.numero;  
    }    

    public double getSaldo(int senha) {   
       if(this.senha != senha){    
           return -1;   
       }   
       return this.saldo;  
    }    

    public boolean debitaValor(int senha, double valor, String operacao){   
        if(valor < 0 | this.senha != senha | this.saldo < valor){    
            return false;   
        }   
        if(this.ultimoLancamento == 10){    
            for (int i = 0; i < 10; i++) {     
                this.historico[i] = this.historico[i+1];     
                this.valorLancamento[i] = this.valorLancamento[i+1];    
            }   
        } else{
            this.ultimoLancamento++;   
        }   
        this.historico[this.ultimoLancamento] = operacao;   
        this.valorLancamento[this.ultimoLancamento] = -valor;   
        this.saldo -= valor;   
        return true;  
    } 
    
    public boolean depositaValor(int num, double valor, String operacao)
    {
        if(valor < 0| this.numero!=num )
        {
            return false;
        }
        
        if(this.ultimoLancamento == 10)
        {
            for(int i = 0; i< 10; i++)
            {
                this.historico[i] = this. historico[i+1];
                this.valorLancamento[i] = this.valorLancamento[i+1];
            }
        }
        else{
            this.ultimoLancamento++;
        }
        
        this.historico[this.ultimoLancamento] = operacao;
        this.valorLancamento[this.ultimoLancamento] = valor;
        this.saldo += valor;
       return true;
    }
    
    public void extrato(int senha){
        if(this.senha == senha){
            for (int i = 0; i < this.historico.length; i++) {
                if(this.historico[i]!=null) 
                	System.out.println(this.historico[i]+"        "+this.valorLancamento[i]);
            }
            System.out.println("Saldo: "+this.saldo);
        }
    }
    
    public String toString() {
    	
    	
    	return "Conta: "+numero+" Senha: "+senha+" Saldo: "+saldo;
    }
}