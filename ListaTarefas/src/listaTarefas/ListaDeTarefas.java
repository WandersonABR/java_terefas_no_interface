package listaTarefas;

import java.util.ArrayList;
import java.util.Scanner;

public class ListaDeTarefas {

	public static Usuario usuarioLogado = null;
	public static Scanner input = new Scanner(System.in); // Cria fora pra usar em todo o meu programa.

	public static void main(String[] args) {

		ArrayList<Usuario> usuarios = new ArrayList<>();

		boolean rodando = true;
		while (rodando) {
			// Menu + input do usuário
			System.out.println("========== PÁGINA INICIAL ==========");
			System.out.println("[1] - Fazer cadastro");
			System.out.println("[2] - Fazer login");
			System.out.println("[3] - sair");
			System.out.print("Digite a opção: ");
			String opcao = input.nextLine();
			// Processar o input do usuário
			switch (opcao) {
			case "1": {
				System.out.println("========== CADASTRO ==========");
				System.out.print("Digite o email: ");
				String email = input.nextLine();
				System.out.println("Digite a senha: ");
				String senha = input.nextLine();
				// Crio um novo usurario
				Usuario u = new Usuario();
				// Seto os valores no objeto
				u.setEmail(email);
				u.setSenha(senha);

				ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
				u.setTarefas(tarefas); // usuario tem uma lista de tarefas que é a lista que eu instanciei acima

				// Adiciono o usuario na lista de usuários
				usuarios.add(u);
				System.out.println("Usuário cadastrado com sucesso!");
				break;
			}

			case "2": {
				System.out.println("========== LOGIN ==========");
				System.out.print("Digite o email: ");
				String email = input.nextLine();
				System.out.print("Digite a senha: ");
				String senha = input.nextLine();

				boolean loginSucesso = false;
				for (Usuario u : usuarios) {
					String uEmail = u.getEmail();
					String uSenha = u.getSenha();

					boolean emailConfere = email.equals(uEmail);
					boolean senhaConfere = senha.equals(uSenha);

					if (emailConfere && senhaConfere) {
						// Login feito com sucesso
						loginSucesso = true;
						usuarioLogado = u;
						break;
					}
				}

				if (!loginSucesso) {
					System.out.println("Email/Senha incorretos");
				} else {
					System.out.println("Login realizado com sucesso!");
					homePage();
				}

				break;
			}

			case "3": {
				rodando = false;
				System.out.println("Encerrado");
				break;
			}

			default: {
				System.out.println("Opção inválida!");
				break;
			}

			}
		}
		input.close();
		System.out.println("Fim do programa.");
	}

	public static void homePage() {

		boolean rodando = true;
		while (rodando) {
			System.out.println("========== HOME PAGE ==========");
			System.out.println("[1] - Mostrar tarefas");
			System.out.println("[2] - Mostrar tarefas finalizadas ");
			System.out.println("[3] - Mostrar tarefas não finalizadas ");
			System.out.println("[4] - Adicionar tarefa ");
			System.out.println("[5] - Finalizar tarefa");
			System.out.println("[6] - Remover tarefa");
			System.out.println("[7] - Logout");
			System.out.print("Digite a opção: ");
			String opcao = input.nextLine();

			switch (opcao) {
			case "1": {
				System.out.println("========== TAREFAS ==========");
				ArrayList<Tarefa> lista = usuarioLogado.getTarefas(); // Pega o array de tarefas do usuario logado

				if (lista.isEmpty()) {
					System.out.println("Lista de tarefas vazia.");
				} else {
					for (int i = 0; i < lista.size(); i++) {
						Tarefa t = lista.get(i);
						System.out.println("Tarefa: " + i);
						System.out.println("\tTitulo: " + t.getTitulo());
						System.out.println("\tFinalizada: " + t.isFinalizada()); // isFinalizada porque é booleana
					}
				}

				break;
			}
			case "2": {
				System.out.println("========== TAREFAS FINALIZADAS ==========");
				ArrayList<Tarefa> lista = usuarioLogado.getTarefas();
				ArrayList<Tarefa> finalizadas = new ArrayList<>();

				for (Tarefa t : lista) {
					if (t.isFinalizada()) {
						finalizadas.add(t);
					}
				}

				if (finalizadas.isEmpty()) {
					System.out.println("Não há tarefas finalizadas para mostrar");
				} else {
					for (int i = 0; i < finalizadas.size(); i++) {
						Tarefa t = finalizadas.get(i);
						System.out.println("Tarefa: " + i);
						System.out.println("\tTitulo: " + t.getTitulo());
						System.out.println("\tFinalizada: " + t.isFinalizada());
					}
				}

				break;
			}
			case "3": {
				System.out.println("========== TAREFAS NÃO FINALIZADAS ==========");
				ArrayList<Tarefa> lista = usuarioLogado.getTarefas();
				ArrayList<Tarefa> naoFinalizada = new ArrayList<>();

				for (Tarefa t : lista) {
					if (!t.isFinalizada()) {
						naoFinalizada.add(t);
					}
				}

				if (naoFinalizada.isEmpty()) {
					System.out.println("Lista de tarefas não finalizadas vazia");
				} else {
					for (int i = 0; i < naoFinalizada.size(); i++) {
						Tarefa t = naoFinalizada.get(i);
						System.out.println("Tarefa: " + i);
						System.out.println("\tTitulo: " + t.getTitulo());
						System.out.println("\tFinalizada: " + t.isFinalizada());
					}
				}

				break;
			}
			case "4": {
				System.out.println("========== NOVA TAREFA ==========");
				System.out.print("Digite o titulo da tarefa: ");
				String titulo = input.nextLine();

				Tarefa t = new Tarefa();
				t.setTitulo(titulo);
				t.setFinalizada(false); // False porque acabei de criar a tarefa

				usuarioLogado.getTarefas().add(t);
				System.out.println("Tarefa adicionada com sucesso!");

				break;
			}
			case "5": {
				System.out.println("========== FINALIZAR TAREFA ==========");
				ArrayList<Tarefa> lista = usuarioLogado.getTarefas();
				ArrayList<Tarefa> naoFinalizadas = new ArrayList<>();

				for (Tarefa t : lista) {
					if (!t.isFinalizada()) {
						naoFinalizadas.add(t);
					}
				}

				if (naoFinalizadas.isEmpty()) {
					System.out.println("Não há tarefas para finalizar");
				} else {
					for (int i = 0; i < naoFinalizadas.size(); i++) {
						Tarefa t = naoFinalizadas.get(i); // Só pra pegar a posição da tarefa
						System.out.println("[" + i + "] " + t.getTitulo());
					}

					System.out.print("Digite o id da tarefa que deseja finalizar: ");
					int id = input.nextInt();
					input.nextLine(); // Para limpar o buffer

					Tarefa tarefaFinalizada = naoFinalizadas.get(id);
					tarefaFinalizada.setFinalizada(true);
					System.out.println("Tarefa finalizada com sucesso!");
				}

				break;
			}
			case "6": {
				System.out.println("========== REMOVER TAREFA ==========");
				ArrayList<Tarefa> lista = usuarioLogado.getTarefas();

				if (lista.isEmpty()) {
					System.out.println("Não há tarefas para serem removidas");
				} else {
					for (int i = 0; i < lista.size(); i++) {
						Tarefa t = lista.get(i);
						System.out.println("[" + i + "] " + t.getTitulo());
					}

					System.out.print("Digite o id da tarefa que deseja remover: ");
					int id = input.nextInt();
					input.nextLine(); // Limpar o buffer

					lista.remove(id);
					System.out.println("Tarefa removida com sucesso!");
				}

				break;
			}
			case "7": {
				rodando = false;
				System.out.println("Fazendo logout!");
				usuarioLogado = null;
				break;
			}
			default: {
				System.out.println("Opção inválida!");
			}
			}

		}
	}
}
