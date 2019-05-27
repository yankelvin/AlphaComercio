package alphacomercio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Sistema {

	Scanner in = new Scanner(System.in);
	Scanner in1 = new Scanner(System.in);
	private final HashMap<String, Logradouro> logradouros;
	private final HashMap<String, UnidadeFederacao> ufs;

	public Sistema() {
		this.logradouros = new HashMap<>();
		this.ufs = new HashMap<>();
	}

	public void iniciar() {
		System.out.println("\n***** ALPHA COMÉRCIO *****");
		System.out.println("\n------ MENU ------");
		System.out.println("1 - Fazer busca por CEP");
		System.out.println("2 - Fazer busca por logradouros do bairro.");
		System.out.println("0 - Finalizar o programa.\n");
	}

	public void lerArquivo() {
		String arquivo = "AlphaComercio/src/alphacomercio/ceps.txt";

		System.out.printf("\nCarregando arquivo...\n");
		try (FileReader arq = new FileReader(arquivo)) {
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();

			while (linha != null) {
				// Ordem de leitura:
				// CEP -> Cidade/Estado -> Bairro -> Logradouro.
				String[] aux = linha.split("	");
				Logradouro logradouro;
				// Cep : String
				String cep = aux[0];

				// Cidade -> String nomeCidade & String unidadeFederacao para
				// criar UnidadeFederacao & adicionar Bairros
				String nomeCidade = aux[1];
				Cidade cidade = new Cidade(nomeCidade);
				String siglaUF = aux[1].substring(aux[1].length() - 2);
				UnidadeFederacao UF = new UnidadeFederacao(siglaUF);
				UF.addCidade(cidade);

				logradouro = new Logradouro();
				logradouro.setCep(cep);
				logradouro.setCidade(cidade);
				logradouro.setUnidadeFederacao(UF);

				// Bairro -> String nomeBairro & Logradouro
				if (aux.length >= 3) {
					String nomeBairro = aux[2];
					Bairro bairro = new Bairro(nomeBairro);
					logradouro.setBairro(bairro);

					cidade.addBairro(bairro);
					// Logradouro -> Todas as informações.
					if (aux.length >= 4) {
						String nomeLogradouro = aux[3];
						logradouro.setNomeLogradouro(nomeLogradouro);
					}
					bairro.addLogradouro(logradouro);
				}

				this.logradouros.put(cep, logradouro);
				linha = lerArq.readLine();
			}
			System.out.println("Carregamento concluído!");
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		System.out.println();
	}

	public void buscarLogradouro(String cep) {
		Logradouro logradouro = this.logradouros.get(cep);
		System.out.println("Nome cidade: " + logradouro.getCidade().getNomeCidade());
		System.out.println("Nome bairro: " + logradouro.getBairro().getNomeBairro());
		System.out.println("UF: " + logradouro.getUnidadeFederacao().getSiglaUnidadeFederacao());
		System.out.println("Nome Logradouro: " + logradouro.getNomeLogradouro());
	}

	public void carregarArquivo() {
		String arquivo = "AlphaComercio/src/alphacomercio/ceps.txt";

		try {
			System.out.println("Carregando arquivo...");
			FileReader arq = new FileReader(arquivo);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();

			while (linha != null) {

				String[] aux = linha.split("	");
				
				String cep = aux[0];
				String nomeCidade = aux[1];
				String siglaUf = aux[1].substring(aux[1].length() - 2);

				String nomeBairro = null;
				if (aux.length >= 3) {
					nomeBairro = aux[2];
				}

				String nomeLogradouro = null;
				if (aux.length >= 4) {
					nomeLogradouro = aux[3];
				}

				UnidadeFederacao unidade = null;
				if (!ufs.containsKey(siglaUf)) {
					unidade = new UnidadeFederacao(siglaUf);
					Cidade cidade = new Cidade(nomeCidade);
					Bairro ba = null;
					if (nomeBairro != null) {
						ba = new Bairro(nomeBairro);
						cidade.addBairro(ba);
					}
					Logradouro log = null;
					if (nomeLogradouro != null) {
						log = new Logradouro(nomeLogradouro);
						ba.addLogradouro(log);
					}
					unidade.addCidade(cidade);
					ufs.put(siglaUf, unidade);
				} else {
					UnidadeFederacao unidade1 = ufs.get(siglaUf);
					if (!unidade1.getCidades().containsKey(nomeCidade)) {
						Cidade cidade = new Cidade(nomeCidade);
						Bairro bairro = new Bairro(nomeBairro);
						Logradouro log = new Logradouro(nomeLogradouro);

						bairro.addLogradouro(log);
						cidade.addBairro(bairro);
						unidade1.addCidade(cidade);

					} else {
						Cidade cidade1 = unidade1.getCidades().get(nomeCidade);
						if (!cidade1.getBairros().containsKey(nomeBairro)) {
							Bairro bairro = new Bairro(nomeBairro);
							Logradouro log = new Logradouro(nomeLogradouro);
							bairro.addLogradouro(log);
							cidade1.addBairro(bairro);
						} else {
							Bairro bairro = cidade1.getBairros().get(nomeBairro);
							Logradouro log = new Logradouro(nomeLogradouro);
							log.setCep(cep);
							bairro.addLogradouro(log);
							
							if(!bairro.getLogradouros().contains(log)){
								bairro.addLogradouro(log);
							}
						}
					}
				}
				linha = lerArq.readLine();
			}
			
			System.out.println("Carregamento concluído!");
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());

		}
	}
	
	public void buscarLogradourosBairro() {
		System.out.println("\nInsira as informações abaixo:");
		System.out.println("\nSigla da UF:");
		String nomeUF = in.next();

		UnidadeFederacao uf = ufs.get(nomeUF);
		if (uf != null) {
			System.out.println("Nome cidade (NomeCidade/UF): ");
			String nomeCidade = in1.nextLine();
			Cidade cid = uf.getCidades().get(nomeCidade);
			if (cid != null) {
				System.out.println("Nome bairro:");
				String nomeBairro = in1.nextLine();
				Bairro bai = cid.getBairros().get(nomeBairro);
				if (bai != null) {
					imprimeLogradouros(bai);
				} else {
					System.out.println("Bairro não encontrado.");
				}
			} else {
				System.out.println("Cidade não encontrada.");
			}
		} else {
			System.out.println("UF não encontrada.");
		}
	}
	
	private void imprimeLogradouros(Bairro bairro) {
		System.out.println("\nLogradouros do bairro " + bairro.getNomeBairro() + ":");
		for(Logradouro log : bairro.getLogradouros()) {
			System.out.println("\nLogradouro: " + log.getNomeLogradouro());
			System.out.println("CEP: " + log.getCep());
			System.out.println("-----------------------------------------------------------");
		}
	}
}
