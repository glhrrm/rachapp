package dao;

import java.io.*;
import java.util.*;
import rachapp.Item;

public class ItemDAO extends Arquivo implements DAO {

    private List<Item> lista = new ArrayList();

    @Override
    public boolean criar(Object obj) {
        if (obj instanceof Item) {
            lista.add((Item) obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean excluir(Object obj) {
        boolean resposta = lista.remove(obj);
        return resposta;
    }

    @Override
    public Object pesquisar(Object obj) {
        String nome;
        if (obj != null && obj instanceof String) {
            nome = (String) obj;
            for (int i = 0; i < lista.size(); i++) {
                Item item = (Item) lista.get(i);
                if (item.getNome().equals(nome)) {
                    return obj;
                }
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
            File arquivo = new File(nome + ".item.txt");
            arquivo.createNewFile();
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    @Override
    public void incluirGasto(String nome, String conteudo) {
        if (abrirArquivo(nome + ".item.txt") == null) {
            escreverArquivo(nome, true, conteudo);
        } else {
            escreverArquivo(nome, true, "\n" + conteudo);
        }
    }

    public void escreverArquivo(String arquivo, boolean modo, String conteudo) {
        try (Writer escreve = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(arquivo + ".item.txt", modo), java.nio.charset.StandardCharsets.UTF_8))) {
            if (abrirArquivo(arquivo + ".item.txt") == null) {
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
