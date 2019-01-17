package dao;

public interface DAO {
    
    public boolean criar (Object obj);
    
    public boolean excluir (Object obj);
    
    public Object pesquisar (Object obj);
    
    public void listar();
    
    public boolean editar (Object obj);
}
