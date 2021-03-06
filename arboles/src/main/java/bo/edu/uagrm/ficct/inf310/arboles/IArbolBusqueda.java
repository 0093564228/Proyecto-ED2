package bo.edu.uagrm.ficct.inf310.arboles;

import java.util.List;

public interface IArbolBusqueda<K extends Comparable<K>, V> {
    void insertar (K clave, V valor) throws ExceptionClaveYaExiste;
    V eliminar (K clave) throws ExceptionClaveNoExiste;
    V buscar (K clave);
    boolean contiene(K clave);
    int size();
    int altura();
    void vaciar();
    boolean  esArbolVacio();
    int nivel();
    List<K> recorridoEnInorden();
    List<K> recorridoEnPreorden();
    List<K> recorridoEnPostorden();
    List<K> recorridoPorNiveles();
}