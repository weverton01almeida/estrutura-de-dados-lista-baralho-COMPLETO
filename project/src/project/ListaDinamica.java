package project;

public class ListaDinamica {
	NoLista inicio;

	public ListaDinamica() {
		this.inicio = null;
	}

	public void insereOrdenado(baralho NovoBaralho) {
		NoLista novoNo = new NoLista(NovoBaralho);

		NoLista ant = null;
		NoLista p = this.inicio;

		while (p != null && p.info.id < NovoBaralho.id) {
			ant = p;
			p = p.proximo;
		}

		if (ant == null) {
			novoNo.proximo = this.inicio;
			this.inicio = novoNo;
		}

		else {
			novoNo.proximo = ant.proximo;
			ant.proximo = novoNo;
		}
	}

	// insere valor no começo da lista
	public void inserirNoInicio(baralho valor) {
		if (this.inicio == null) {
			// lista vazia, então só é preciso criar o nó
			this.inicio = new NoLista(valor);
		}

		else {
			// cria-se novo no e atualiza o NoLista inicio
			NoLista novoNo = new NoLista(valor);

			novoNo.proximo = this.inicio;

			this.inicio = novoNo;
		}

	}

	// insere valor no fim da lista
	public void inserirNoFim(baralho valor) {
		if (this.inicio == null) {
			// lista vazia
			this.inicio = new NoLista(valor);
		}

		else {
			// procura pelo fim da lista
			NoLista atual = this.inicio;

			while (atual.proximo != null) {
				atual = atual.proximo;
			}

			// insere o nó no fim da lista
			atual.proximo = new NoLista(valor);
		}

	}

	public void retira(baralho v) // Em qualquer posicao
	{

		NoLista ant = null;
		NoLista p = this.inicio;

		while (p != null && p.info != v) {
			ant = p;
			p = p.proximo;
		}

		if (p != null) {
			if (ant == null) {
				this.inicio = p.proximo;
			}

			else {
				ant.proximo = p.proximo;
			}
		}
	}

	public NoLista busca(baralho v) {
		int i = 0;

		for (NoLista p = this.inicio; p != null; p = p.proximo) {
			if (p.info == v) {
				System.out.println("\n\nachou na posicao: " + i + "\n\n");
				return p;
			}
			i++;
		}

		return null;
	}

	public void imprime() {
		for (NoLista q = this.inicio; q != null; q = q.proximo) {
			if (q.info.ValorDaCarta < 2 || q.info.ValorDaCarta > 10) {
				if (q.info.ValorDaCarta == 1) {
					System.out.println("Ás de " + q.info.NAIPE);
				} else {
					if (q.info.ValorDaCarta == 11) {
						System.out.println("Valete de " + q.info.NAIPE);
					} else {
						if (q.info.ValorDaCarta == 12) {
							System.out.println("Dama de " + q.info.NAIPE);
						} else {
							if (q.info.ValorDaCarta == 13) {
								System.out.println("Rei de " + q.info.NAIPE);
							}
						}
					}
				}
			} else {
				System.out.println(q.info.ValorDaCarta + " de " + q.info.NAIPE);
			}
		}
	}

	public void Embaralhar() {
		int id = 1;
		while (id < 53) {
			int naipeID;
			int valordacarta;
			valordacarta = 1 + (int) (Math.random() * 13);
			naipeID = 1 + (int) (Math.random() * 4);
			baralho copag = new baralho();
			copag.id = id;
			if (naipeID == 1) {
				copag.NAIPE = "OUROS";
			} else {
				if (naipeID == 2) {
					copag.NAIPE = "PAUS";
				} else {
					if (naipeID == 3) {
						copag.NAIPE = "COPAS";
					} else {
						if (naipeID == 4) {
							copag.NAIPE = "ESPADAS";
						}
					}
				}
			}
			copag.NAIPEID = naipeID;
			copag.ValorDaCarta = valordacarta;
			if (busca(copag) == null) {
				inserirNoInicio(copag);
				id++;
			}
		}
	}

	public void BaralhoOrdenado() {
		int id = 1;
		for (int naipeID = 1; naipeID < 5; naipeID++) {
			for (int valordacarta = 1; valordacarta < 14; valordacarta++, id++) {
				baralho copag = new baralho();
				copag.id = id;
				if (naipeID == 1) {
					copag.NAIPE = "OUROS";
				} else {
					if (naipeID == 2) {
						copag.NAIPE = "PAUS";
					} else {
						if (naipeID == 3) {
							copag.NAIPE = "COPAS";
						} else {
							if (naipeID == 4) {
								copag.NAIPE = "ESPADAS";
							}
						}
					}
				}
				copag.NAIPEID = naipeID;
				copag.ValorDaCarta = valordacarta;
				inserirNoFim(copag);
			}
		}
	}

	public baralho retiraMonte() {

		NoLista p = this.inicio;

		inicio = inicio.proximo;

		return p.info;

	}

	public void Distribui(ListaDinamica mao1, ListaDinamica mao2, ListaDinamica mao3, ListaDinamica mao4,
			int QntCartas) {
		for (int i = 0; i < QntCartas; i++) {
			mao1.inserirNoFim(this.retiraMonte());
		}
		for (int j = 0; j < QntCartas; j++) {
			mao2.inserirNoFim(this.retiraMonte());
		}
		for (int k = 0; k < QntCartas; k++) {
			mao3.inserirNoFim(this.retiraMonte());
		}
		for (int l = 0; l < QntCartas; l++) {
			mao4.inserirNoFim(this.retiraMonte());
		}
	}

	public void concatena(ListaDinamica b1, ListaDinamica b2) {

		while (b1.inicio != null && b2.inicio != null) {
			this.inserirNoInicio(b1.retiraMonte());
			this.inserirNoInicio(b2.retiraMonte());
		}
		if (b1.inicio == null) {
			while (b2.inicio != null) {
				this.inserirNoInicio(b2.retiraMonte());
			}
		} else {
			while (b1.inicio != null) {
				this.inserirNoInicio(b1.retiraMonte());
			}
		}

	}

	public void Uniao(ListaDinamica b1, ListaDinamica b2) {

		while (b1.inicio != null && b2.inicio != null) {
			this.insereOrdenado(b1.retiraMonte());
			this.insereOrdenado(b2.retiraMonte());
		}
		if (b1.inicio == null) {
			while (b2.inicio != null) {
				this.insereOrdenado(b2.retiraMonte());
			}
		} else {
			while (b1.inicio != null) {
				this.insereOrdenado(b1.retiraMonte());
			}
		}

	}
	public void  retira_elementos(int n){
		NoLista p = this.inicio;
		for (int i = 0; p != null && i < n;i++ ) {
			this.inicio = inicio.proximo;
		}
		if(p == null){
			System.out.println("Nao ha mais cartas");
		}
	}
}
