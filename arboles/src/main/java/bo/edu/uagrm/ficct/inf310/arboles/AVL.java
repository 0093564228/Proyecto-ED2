package bo.edu.uagrm.ficct.inf310.arboles;

public class AVL<K extends Comparable<K>, V> extends ArbolBinarioBusqueda<K,V> {
    private static final byte DIFERENCIA_MAXIMA = 1;
    @Override
    /**
     * Método insertar recursivo.
     * @param clave
     * @param valor
     * @throws ExceptionClaveYaExiste
     */

    public void insertar(K clave, V valor) throws ExceptionClaveYaExiste {
        this.raiz = insertar(this.raiz, clave, valor);
    }

    private NodoBinario<K,V> insertar(NodoBinario<K,V> nodoActual, K clave, V valor)throws ExceptionClaveYaExiste {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            NodoBinario<K, V> nuevoNodo = new NodoBinario<K, V>(clave, valor);
            return nuevoNodo;
        }
        K claveActual = nodoActual.getClave();
        if (clave.compareTo(claveActual) > 0) {
            NodoBinario<K, V> supuestoNuevoHijoDerecho = insertar(nodoActual.getHijoDerecho(), clave, valor);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
            return balancear(nodoActual);
        }
        if (clave.compareTo(claveActual) < 0) {
            NodoBinario<K, V> supuestoNuevoHijoIzquierdo = insertar(nodoActual.getHijoIzquierdo(), clave, valor);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return balancear(nodoActual);
        }
        throw new ExceptionClaveYaExiste();
    }
    /**
     * Este método balancea un nodo a la vez, es decir, balancea el nodo previo del método recursivo "insertar(NodoBinario<><K, V>)".
     * @param nodoActual
     * @return
     */
    private NodoBinario<K, V> balancear(NodoBinario<K, V> nodoActual) {
        int alturaPorRamaIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaPorRamaDerecha = altura(nodoActual.getHijoDerecho());
        int diferenciaEnAltura = alturaPorRamaIzquierda - alturaPorRamaDerecha;

        if (diferenciaEnAltura > DIFERENCIA_MAXIMA) {
            NodoBinario<K, V> hijoIzquierdo = nodoActual.getHijoIzquierdo();
            alturaPorRamaIzquierda = altura(hijoIzquierdo.getHijoIzquierdo());
            alturaPorRamaDerecha = altura(hijoIzquierdo.getHijoDerecho());
            if (alturaPorRamaDerecha > alturaPorRamaIzquierda  ) {
                return rotacionDobleADerecha(nodoActual);
            }else{
                return rotacionSimpleADerecha(nodoActual);
            }
        }
        if (diferenciaEnAltura <-DIFERENCIA_MAXIMA) {
            NodoBinario<K, V> hijoDerecho = nodoActual.getHijoDerecho();
            alturaPorRamaIzquierda = altura(hijoDerecho.getHijoIzquierdo());
            alturaPorRamaDerecha   = altura(hijoDerecho.getHijoDerecho());
            if (alturaPorRamaIzquierda > alturaPorRamaDerecha) {
                return rotacionDobleAIzquierda(nodoActual);
            }else{
                return rotacionSimpleAIzquierda(nodoActual);
            }
        }
        return nodoActual;
    }
    /**
     * Este método retorna el nieto del nodo actual, es decir, el hijo izquierdo del hijo derecho del nodo actual.
     * @param nodoActual
     * @return
     */
    private NodoBinario<K, V> rotacionDobleAIzquierda(NodoBinario<K, V> nodoActual) {
        nodoActual.setHijoDerecho(rotacionSimpleADerecha(nodoActual.getHijoDerecho()));
        NodoBinario<K, V> nodoARetornar = rotacionSimpleAIzquierda(nodoActual);
        return nodoARetornar;
    }
    /**
     * Este método retorna el nieto del nodo actual, es decir, el hijo derecho del hijo izquierdo del nodo actual.
     * @param nodoActual
     * @return
     */
    private NodoBinario<K, V> rotacionDobleADerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> hijoIzquierdo;
        NodoBinario<K, V> nodoARetornar;
        hijoIzquierdo = rotacionSimpleAIzquierda(nodoActual.getHijoIzquierdo());
        nodoActual.setHijoIzquierdo(hijoIzquierdo);
        nodoARetornar = rotacionSimpleADerecha(nodoActual);
        return nodoARetornar;
        /*
        nodoActual.setHijoIzquierdo(rotacionSimpleAIzquierda(nodoActual.getHijoIzquierdo()));
        NodoBinario<K, V> nodoARetornar = rotacionSimpleADerecha(nodoActual);
        return nodoARetornar;*/
    }
    /**
     * Este método retorna el hijo izquierdo del nodo actual.
     * @param nodoActual
     * @return
     */
    private NodoBinario<K, V> rotacionSimpleADerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoARetornar = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoARetornar.getHijoDerecho());
        nodoARetornar.setHijoDerecho(nodoActual);
        return nodoARetornar;
    }
    /**
     * Este método retorna el hijo derecho del nodo actual.
     * @param nodoActual
     * @return
     */
    private NodoBinario<K, V> rotacionSimpleAIzquierda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoARetornar = nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoARetornar.getHijoIzquierdo());
        nodoARetornar.setHijoIzquierdo(nodoActual);
        return nodoARetornar;
    }

    @Override
    public V eliminar(K clave) throws ExceptionClaveNoExiste {
        V valorARetornar = this.buscar(clave);
        if (valorARetornar == null) {
            throw new ExceptionClaveNoExiste();
        }

        this.raiz = eliminar(this.raiz, clave);
        return valorARetornar;

    }

    private NodoBinario<K,V> eliminar(NodoBinario<K,V> nodoActual, K clave) {
        K claveActual = nodoActual.getClave();
        if (clave.compareTo(claveActual) > 0) {
            NodoBinario<K, V> supuestoNuevoDerecho = eliminar(nodoActual.getHijoDerecho(),clave);
            nodoActual.setHijoDerecho(supuestoNuevoDerecho);
            return balancear(nodoActual);
        }
        if (clave.compareTo(claveActual) < 0) {
            NodoBinario<K, V> supuestoNuevoHijoIzquierdo = eliminar(nodoActual.getHijoIzquierdo(),clave);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return balancear(nodoActual);
        }

        if (nodoActual.esHoja()) {
            return nodoVacioParaElArbol();
        }
        if (!nodoActual.esVacioHijoIzquierdo() &&  nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoIzquierdo();
        }
        if ( nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoDerecho();
        }
        NodoBinario<K, V> nodoRemplazo = this.buscarNodoSucesor(nodoActual.getHijoDerecho());
        NodoBinario<K, V> posibleNuevoHijo = eliminar(nodoActual.getHijoDerecho(),nodoRemplazo.getClave());
        nodoActual.setHijoDerecho(posibleNuevoHijo);

        nodoRemplazo.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
        nodoRemplazo.setHijoDerecho(nodoActual.getHijoDerecho());

        nodoActual.setHijoIzquierdo(nodoVacioParaElArbol());
        nodoActual.setHijoDerecho(nodoVacioParaElArbol());

        return nodoRemplazo;
    }
}
