package dao;

import java.io.*;
import java.util.*;
import rachapp.Evento;

public class EventoDAO extends Arquivo implements DAO {

    private List<Evento> lista = new ArrayList<>();

    @Override
    public boolean criar(Object obj) {
        if (obj instanceof Evento) {
            lista.add((Evento) obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean excluir(Object obj) {
        boolean resposta = lista.remove((Evento) obj);
        return resposta;
    }

    @Override
    public Object pesquisar(Object obj) {
        String nome;
        if (obj != null && obj instanceof String) {
            nome = (String) obj;
            for (int i = 0; i < lista.size(); i++) {
                Evento evento = (Evento) lista.get(i);
                if (evento.getNome().equals(nome)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public Evento pesquisar(String nome) {
        for (Evento evento : lista) {
            if (evento.getNome().equals(nome)) {
                return evento;
            }
        }
        return null;
    }

    @Override
    public void listar() {
        System.out.println(lista);
    }

    @Override
    public boolean editar(Object obj) {
        return false;
    }

    @Override
    public void criarArquivo(String nome) throws FileNotFoundException {
        try {
            File arquivo = new File(nome + ".evento.txt");
            arquivo.createNewFile();
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    @Override
    public void incluirGasto(String nome, String conteudo) {
        if (abrirArquivo(nome + ".evento.txt") == null) {
            escreverArquivo(nome, true, conteudo);
        } else {
            escreverArquivo(nome, true, "\n" + conteudo);
        }
    }

    public void escreverArquivo(String arquivo, boolean modo, String conteudo) {
        try (Writer escreve = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(arquivo + ".evento.txt", modo), java.nio.charset.StandardCharsets.UTF_8))) {
            if (abrirArquivo(arquivo + ".evento.txt") == null) {
                escreve.write(conteudo);
            } else {
                escreve.write("\n" + conteudo);
            }
            escreve.close();
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }
}
