package bo.edu.uagrm.ficct.inf310.arboles;

import java.util.List;

public class ArbolMviasBusqueda<K extends Comparable<K>,V> implements IArbolBusqueda<K, V>  {
    protected NodoMVias<K, V> raiz;
    protected int orden;

    public ArbolMviasBusqueda() {
        this.orden = 3;
    }

    public ArbolMviasBusqueda(int orden) throws ExcepcionOrdenInvalido{
        if (orden < 3) {
            throw new  ExcepcionOrdenInvalido();
        }
        this.orden = orden;
    }
    public  NodoMVias<K,V> nodoVacioParaElArbol() {
       return (NodoMVias<K,V>)NodoMVias.nodoVacio();
    }

    /**
     * Metodo insertar iterativo
     * @param clave
     * @param valor
     * @throws ExceptionClaveYaExiste
     */
    public void insertar(K clave, V valor) throws ExceptionClaveYaExiste {
        if (this.esArbolVacio()) {
            this.raiz = new NodoMVias<K, V>(orden, clave, valor);
        }

    }

    public V eliminar(K clave) throws ExceptionClaveNoExiste {
        return null;
    }

    public V buscar(K claveABuscar) {
        NodoMVias<K,V> nodoActual = this.raiz;
        while(!NodoMVias.esNodoVacio(nodoActual)) {
            NodoMVias<K,V> nodoAnterior = nodoActual;
            //Inicio del for
            for (int i = 0; i < nodoActual.cantidadDeDatosNoVacios() &&
                    nodoAnterior == nodoActual; i++) {
                    K claveActual = nodoActual.getClave(i);
                if (claveABuscar.compareTo(claveActual) == 0) {
                    return nodoActual.getValor(i);
                }
                if (claveABuscar.compareTo(claveActual) < 0) {
                    if (nodoActual.esHijoVacio(i)) {
                        return (V)NodoMVias.datoVacio();
                    }
                    nodoActual = nodoActual.getHijo(i);
                }
            }//Fin del for
            if (nodoActual == nodoAnterior) {
                nodoActual = nodoAnterior.getHijo(orden - 1);
            }
        }

        return (V)NodoMVias.datoVacio();
    }

    public boolean contiene(K clave) {
        return this.buscar(clave) != NodoMVias.datoVacio();
    }

    public int size() {
        return 0;
    }

    public int altura() {
        return 0;
    }

    public void vaciar() {
        this.raiz = this.nodoVacioParaElArbol();
    }

    public boolean esArbolVacio() {
        return NodoMVias.esNodoVacio(this.raiz);
    }

    public int nivel() {
        return 0;
    }

    public List<K> recorridoEnInorden() {
        return null;
    }

    public List<K> recorridoEnPreorden() {
        return null;
    }

    public List<K> recorridoEnPostorden() {
        return null;
    }

    public List<K> recorridoPorNiveles() {
        return null;
    }
}
