package bo.edu.uagrm.ficct.inf310.arboles;

import java.util.LinkedList;
import java.util.List;
import java.lang.Object;
public class NodoMVias<K, V> {

    private List<K> listaDeClaves;
    private List<V> listaDeValores;
    private List<NodoMVias<K, V>> listaDeHijos;

    public NodoMVias(int orden) {
        listaDeClaves = new LinkedList<K>();
        listaDeValores = new LinkedList<V>();
        listaDeHijos = new LinkedList<NodoMVias<K, V>>();
        for (int i = 0; i < orden - 1; i++) {//preguntar por el orden >= 2?
            listaDeClaves.add((K) NodoMVias.datoVacio());
            listaDeValores.add((V)NodoMVias.datoVacio());
            listaDeHijos.add(NodoMVias.nodoVacio());
        }
        listaDeHijos.add(NodoMVias.nodoVacio());
    }

    public NodoMVias(int orden, K primeraClave, V primerValor) {
        this(orden);//se ejecuta el constructor con el parametro orden
        this.listaDeClaves.set(0, primeraClave);
        this.listaDeValores.set(0, primerValor);
    }

    public static NodoMVias nodoVacio() {
        return null;
    }

    public static Object datoVacio() {
        return null;
    }
    /*
    public static Object claveVacia() {
        return null;
    }*/

    /*
       Retorna la clave de la posicion indicada.
       Pre: El valor del parámetro posicion esta dentro de
       rango.
     */
    public K getClave(int posicion) {
        return this.listaDeClaves.get(posicion);
    }

    public V getValor(int posicion) {
        return this.listaDeValores.get(posicion);
    }

    public void setClave(int posicion, K clave) {
        this.listaDeClaves.set(posicion, clave);
    }

    public void setValor(int posicion, V valor) {
        this.listaDeValores.set(posicion, valor);
    }

    public NodoMVias<K, V> getHijo(int posicion) {
        return this.listaDeHijos.get(posicion);
    }

    public void setHijo(int posicion, NodoMVias<K, V> nodo) {
        this.listaDeHijos.set(posicion, nodo);
    }

    public static boolean esNodoVacio(NodoMVias<?, ?> nodo) {
        return nodo == NodoMVias.nodoVacio();
    }

    public boolean esDatoVacio(int posicion) {
        return this.listaDeClaves.get(posicion) == NodoMVias.datoVacio();
    }

    public boolean esHijoVacio(int posicion) {
        return this.listaDeHijos.get(posicion) == NodoMVias.nodoVacio();
    }

    public boolean esHoja() {
        for (int i = 0; i < listaDeHijos.size(); i++) {
            if (!this.esHijoVacio(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean estanDatosLlenos() {
        for (K unaclave: this.listaDeClaves) {
            if (unaclave == NodoMVias.datoVacio()) {
                return false;
            }
        }
        return true;
    }

    public boolean estanDatosVacios() {
        for (K unaClave : this.listaDeClaves) {
            if (unaClave != NodoMVias.datoVacio()) {
                return false;
            }
        }
        return true;
    }

    public int cantidadDeDatosNoVacios() {
        int cantidad = 0;
        for (int i = 0; i < this.listaDeClaves.size(); i++) {
            if (!this.esDatoVacio(i)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int cantidadDeHijosVacios() {
        int cantidad = 0;
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (this.esHijoVacio(i)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int cantidadDeHijosNoVacios() {
        int cantidad = 0;
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (!this.esHijoVacio(i)) {
                cantidad++;
            }
        }
        return cantidad;
    }
}