package dao;

import java.io.*;
import java.util.*;

public class Arquivo {

    public void criarArquivo(String nome) throws FileNotFoundException {
        try {
            File arquivo = new File(nome);
            arquivo.createNewFile();
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public void escreverArquivo(String arquivo, boolean modo, String conteudo) {
        try (Writer escreve = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(arquivo, modo), java.nio.charset.StandardCharsets.UTF_8))) {
            if (abrirArquivo(arquivo) == null) {
                escreve.write(conteudo);
            } else {
                escreve.write("\n" + conteudo);
            }
            escreve.close();
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }

    public void incluirGasto(String nome, String conteudo) {
        if (abrirArquivo(nome) == null) {
            escreverArquivo(nome, true, conteudo);
        } else {
            escreverArquivo(nome, true, "\n" + conteudo);
        }
    }

    public void apagarArquivo(String nome) {
        File apagarArq = new File(nome);
        apagarArq.delete();
    }

    public List listarArquivos(String extensao) {
        List<String> listaArquivos = new ArrayList<>();
        String nomeArq;
        String extArq;
        File diretorio = new File(".");
        String[] arquivos = diretorio.list();

        for (String arquivo : arquivos) {
            if (arquivo.contains(".")) {
                nomeArq = arquivo.substring(0, arquivo.indexOf("."));
                extArq = arquivo.substring(arquivo.indexOf("."), arquivo.length());
                if (("." + extensao).equals(extArq)) {
                    listaArquivos.add(nomeArq);
                }
            }
        }
        return listaArquivos;
    }

    public boolean existeArquivo(String nomeArq) {
        File arquivo = new File(nomeArq);
        if (arquivo.exists() && !arquivo.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    public String[] abrirArquivo(String arquivo) {
        String linha = null;
        ArrayList<String> aList = new ArrayList<String>();
        try {
            FileReader leitorArq = new FileReader(arquivo);
            BufferedReader leitor = new BufferedReader(leitorArq);
            while ((linha = leitor.readLine()) != null) {
                aList.add(linha);
            }
            leitor.close();
        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
        if (aList.isEmpty() == true) {
            return null;
        } else {
            String[] linhas = new String[aList.size()];
            linhas = aList.toArray(linhas);
            return linhas;
        }
    }
}
