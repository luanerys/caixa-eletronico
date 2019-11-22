package testecaixaeletronico;

import gestaodecaixaeletronico.*;
import gestaodecontas.*;

public class TesteCaixaEletronico {
	public static void main(String[] args) {
		
		CadastroContas contas = new CadastroContas(10);	
		
		for (int i = 1; i <= 10; i++) {
			contas.insereConta(new Conta(i, 123, 2000.0));
		}
		
		
		new Terminal(contas).iniciaOperacao();
	
	}
}
