package alphacomercio;

import java.util.Scanner;

public class AlphaComercio {

    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
        Sistema sis = new Sistema();
        
        int escolha =-1;
        
        while(escolha !=0) {
        	sis.iniciar();
        	escolha = in.nextInt();
        	
        	switch (escolha) {
			case 1:
				sis.lerArquivo();
				System.out.println("Informe o CEP:");
				String cep = in.next();
				sis.buscarLogradouro(cep);
				break;
			case 2:
				sis.buscarLogradourosBairro();
				break;
			case 0:
				System.out.println("Programa finalizado!");
				break;
			}
        	
        }
    }

}
