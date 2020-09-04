package bo.edu.uagrm.ficct.inf310.arboles;

public class ExceptionClaveYaExiste extends Exception{
    public ExceptionClaveYaExiste(){
        super("La clave ya existe en el arbol");
    }
    public ExceptionClaveYaExiste(String mensaje){
        super(mensaje);
    }
}
