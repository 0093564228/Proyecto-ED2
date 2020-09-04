package bo.edu.uagrm.ficct.inf310.arboles;

public class ExceptionClaveNoExiste extends Exception{
    public ExceptionClaveNoExiste(){
        super("La clave no existe en el arbol");
    }
    public ExceptionClaveNoExiste(String mensaje){
        super(mensaje);
    }
}
