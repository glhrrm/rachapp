package rachapp;

import dao.*;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) throws FileNotFoundException {

        EventoDAO cadastroEvento = new EventoDAO();
        UsuarioDAO cadastroUsuario = new UsuarioDAO();
        ItemDAO cadastroItem = new ItemDAO();

        int opcao;

        do {
            String[] opcoesInicial = {"Criar evento", "Fechar conta", "Sair"};
            opcao = JOptionPane.showOptionDialog(null,
                    "BEM-VINDO AO RACHAPP :)\nO que você quer fazer?\n",
                    "",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoesInicial,
                    opcoesInicial[0]);

            switch(opcao) {
                case 0:

                    // CRIA O EVENTO
                    String nomeEvento = JOptionPane.showInputDialog(null, "Nome do evento");
                    Evento evento = new Evento(nomeEvento);
                    cadastroEvento.criar(evento);
                    cadastroEvento.criarArquivo(nomeEvento);

                    // ADICIONA PARTICIPANTES
                    do {
                        String[] opcoesParticipantes = {"Administrador", "Visitante", "Continuar"};
                        opcao = JOptionPane.showOptionDialog(null,
                                "Que tipo de participante você quer cadastrar no evento " + nomeEvento + "?\n",
                                "",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                opcoesParticipantes,
                                opcoesParticipantes[0]);

                        switch (opcao) {
                            case 0:
                                String nomeAdministrador = JOptionPane.showInputDialog(null, "Nome do administrador");
                                String login = JOptionPane.showInputDialog(null, "Informe um login para " + nomeAdministrador);
                                String senha = JOptionPane.showInputDialog(null, "Informe uma senha para " + nomeAdministrador);
                                Usuario administrador = new Administrador(nomeAdministrador, evento, login, senha);
                                cadastroUsuario.criar(administrador);
                                cadastroUsuario.criarArquivo(nomeAdministrador);
                                break;
                            case 1:
                                String nomeVisitante = JOptionPane.showInputDialog(null, "Nome do visitante");
                                Usuario visitante = new Usuario(nomeVisitante, evento);
                                cadastroUsuario.criar(visitante);
                                cadastroUsuario.criarArquivo(nomeVisitante);
                                break;
                        }
                    } while (opcao != 2);

                    // ADICIONA ITENS
                    do {
                        opcao = JOptionPane.showConfirmDialog(null,
                                "Vamos adicionar itens ao evento " + nomeEvento + "?", "", JOptionPane.YES_NO_OPTION);

                        if (opcao == JOptionPane.YES_OPTION) {
                            String nomeItem = JOptionPane.showInputDialog(null, "Nome do item");
                            String valorItem = JOptionPane.showInputDialog(null, "Informe o valor de " + nomeItem);
                            double valorDouble = Double.valueOf(valorItem);
                            Item item = new Item(nomeItem, valorDouble, evento);
                            cadastroItem.criar(item);
                            cadastroItem.criarArquivo(nomeItem);

                            // COMPRA O ITEM
                            String opcaoUsuario = JOptionPane.showInputDialog(null,
                                    "Quem COMPROU " + nomeItem + "?\nSepare por espaços em branco ;)\n" //
                                    + evento.listarParticipantes());
                            List<String> opcaoSplit = Arrays.asList(opcaoUsuario.split(" "));
                            String conteudo;

                            for (Usuario participante : evento.getParticipantes()) {
                                for (String entrada : opcaoSplit) {
                                    if (entrada.equals(participante.getNome())) {
                                        participante.comprar(item);
                                        conteudo = participante.getNome() + ":" + item.getNome() + ":" + item.getValor();
                                        cadastroEvento.incluirGasto(nomeEvento, conteudo);
                                        conteudo = item.getNome() + ":" + item.getValor();
                                        cadastroUsuario.incluirGasto(participante.getNome(), conteudo);
                                        conteudo = participante.getNome() + ":" + item.getValor();
                                        cadastroItem.incluirGasto(nomeItem, conteudo);
                                    }
                                }
                            }

                            // CONSOME O ITEM
                            opcaoUsuario = JOptionPane.showInputDialog(null,
                                    "Quem CONSUMIU " + nomeItem + "?\nSepare por espaços em branco ;)\n"
                                    + evento.listarParticipantes());
                            opcaoSplit = Arrays.asList(opcaoUsuario.split(" "));

                            for (Usuario participante : evento.getParticipantes()) {
                                for (String entrada : opcaoSplit) {
                                    if (entrada.equals(participante.getNome())) {
                                        participante.consumir(item);
                                        conteudo = participante.getNome() + ":" + item.getNome() + ":" + item.getValor().negate();
                                        cadastroEvento.incluirGasto(nomeEvento, conteudo);
                                        conteudo = item.getNome() + ":" + item.getValor().negate();
                                        cadastroUsuario.incluirGasto(participante.getNome(), conteudo);
                                        conteudo = participante.getNome() + ":" + item.getValor().negate();
                                        cadastroItem.incluirGasto(nomeItem, conteudo);
                                    }
                                }
                            }
                        }
                    } while (opcao == JOptionPane.YES_OPTION);
                    break;
                    
                case 1:

                    // FECHA CONTA
                    String opcaoEvento = JOptionPane.showInputDialog(null,
                            "Escolha um evento:\n"
                            + cadastroEvento.listarArquivos("evento.txt"));
                    for (Object obj : cadastroEvento.listarArquivos("evento.txt")) {
                        if (obj.equals(opcaoEvento)) {
                            Evento eventoSelecionado = cadastroEvento.pesquisar(opcaoEvento);
                            JOptionPane.showMessageDialog(null,
                                    eventoSelecionado.toString() + "\n\n" +
                                    eventoSelecionado.fecharConta());
                        }
                    }
                    break;
                    
                case 2:
                    
                    JOptionPane.showMessageDialog(null,
                            "Desenvolvido por\nGuilherme Parzianello\ne Maiquel Rheinheimer");
            }
        } while (opcao != 2);
    }
}
