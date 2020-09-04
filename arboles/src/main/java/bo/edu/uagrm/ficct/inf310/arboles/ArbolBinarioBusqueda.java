package bo.edu.uagrm.ficct.inf310.arboles;

import java.util.*;

/** Clase Arbol Binario
 * donde implementamos los metodos de la interfaz IArbolBinario<K, V>.
 * Existen métodos protegidos que seran utilizados para otras implementaciones.
 * @param <K>
 * @param <V>
 */
public class ArbolBinarioBusqueda<K extends Comparable<K>,V> implements IArbolBusqueda<K,V> {

    //raiz protegida por que será usada para clases posteriores
    protected NodoBinario<K,V> raiz;

    /**
     * Constructor sin parametros.
     */
    public ArbolBinarioBusqueda(){
    }


    /**
     * Método adicional, retorna nodo vacio(null) para el árbol.
     */
    protected  NodoBinario<K,V> nodoVacioParaElArbol() {
        return (NodoBinario<K, V>)NodoBinario.nodoVacio();
    }

    /**
     * Método insertar iterativo.
     * @param clave
     * @param valor
     * @throws ExceptionClaveYaExiste
     */
    public void insertar(K clave, V valor) throws ExceptionClaveYaExiste {
        if (NodoBinario.esNodoVacio(this.raiz)){
            this.raiz = new NodoBinario<K,V>(clave,valor);
            return;
        }//en caso de que el arbol no es vacio
        NodoBinario<K,V> nodoActual = this.raiz;
        NodoBinario<K,V> nodoAnterior = this.nodoVacioParaElArbol();
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior = nodoActual;
            if (clave.compareTo(nodoActual.getClave()) > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            }else if (clave.compareTo(nodoActual.getClave()) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            }else{
                throw new ExceptionClaveYaExiste("La clave que quiere insertar ya existe en su arbol");
            }

        }//si termino el bucle es porque el nodo actual es vacio, entonces
         //ya encontre donde ingresar la clave y el valor.
        NodoBinario<K,V> nuevoNodo = new NodoBinario<K, V>(clave, valor);
        if (clave.compareTo(nodoAnterior.getClave()) > 0) {
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }else{
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }
    }

    /**
     * Método eliminar recursivo.
     * @param clave
     * @return retorna el valor eliminado
     * @throws ExceptionClaveNoExiste
     */
    public V eliminar(K clave) throws ExceptionClaveNoExiste {
        V valorARetornar = buscar(clave);       //simplificar esto
        if(valorARetornar == null) {
            throw new ExceptionClaveNoExiste();
        }                                       //hasta aqui , luego
        this.raiz = eliminar(raiz,clave);       //modificar algo aqui  -- posiblemente asigne nuevo nodo a la raiz o no
        return valorARetornar;
    }
    /**
     *
     * @param nodoActual        recibe como parametro un nodo en este caso comienza con la raiz.
     * @param claveAEliminar    clave a eliminar no pertenece a nodoActual, si no a la clave del método "eliminar(K clave)".
     * @return
     * @throws ExceptionClaveNoExiste
     */
    private NodoBinario<K,V> eliminar(NodoBinario<K,V> nodoActual, K claveAEliminar) throws ExceptionClaveNoExiste {
        if(NodoBinario.esNodoVacio(nodoActual)) {// esto va hacer un caso que no va a pasar porque directamenta lo va a encontrar en el if de arriba
            throw new ExceptionClaveNoExiste();  //esto nos va a servir de ayuda si queremos cambiar las lineas 63, 64, 65 y 66 y posiblemente modificar la linea 67
        }
        K claveActual = nodoActual.getClave();
        if(claveAEliminar.compareTo(claveActual) > 0) {
            NodoBinario<K,V> supuestoNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(),claveAEliminar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
            return nodoActual;
        }
        if(claveAEliminar.compareTo(claveActual) < 0) {
            NodoBinario<K,V> supuestoNuevoHijoIzquierdo = eliminar(nodoActual.getHijoIzquierdo(),claveAEliminar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return nodoActual;
        }
        // ya encontre el nodo a eliminar, entonces procedemos con los casos de eliminación
        //caso 1
        if (nodoActual.esHoja()) {
            return nodoVacioParaElArbol();
        }
        //caso 2
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoIzquierdo();
        }
        if (nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoDerecho();
        }

        //caso 3
        NodoBinario<K,V> nodoRemplazo = this.buscarNodoSucesor(nodoActual.getHijoDerecho());

        NodoBinario<K, V> posibleNuevoHijo = eliminar(nodoActual.getHijoDerecho(),nodoRemplazo.getClave());
        nodoActual.setHijoDerecho(posibleNuevoHijo);

        nodoRemplazo.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
        nodoRemplazo.setHijoDerecho(nodoActual.getHijoDerecho());
        //****************************************************
        nodoActual.setHijoIzquierdo(nodoVacioParaElArbol());
        nodoActual.setHijoDerecho(nodoVacioParaElArbol());
        //****************************************************
        return nodoRemplazo;
    }

    /**
     * Este método devuelve el nodo sucesor del nodo actual.
     * @param nodoActual
     * @return
     */
    protected NodoBinario<K,V> buscarNodoSucesor(NodoBinario<K,V> nodoActual) {
        NodoBinario<K,V> nodoAnterior = new NodoBinario<K, V>();
        while(!NodoBinario.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();

        }
        return nodoAnterior;
    }

    /**
     * Este método devuelve el valor de la clave eliminada.
     * @param clave
     * @return
     */
    public V buscar(K clave) {
        NodoBinario<K, V> nodoActual = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            if (clave.compareTo(nodoActual.getClave()) > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            }else if (clave.compareTo(nodoActual.getClave()) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            }else {
                return nodoActual.getValor();
            }
        }
        return null;
    }

    /**
     * Este método devuelve verdadero si el arbol contiene la clave, caso contrario, retorna falso.
     * @param clave
     * @return
     */
    public boolean contiene(K clave) {
        return buscar(clave) != null;
    }

    /**
     * Este método devuelve la cantidad de nodos en un árbol,
     * este método esta implementado con ayuda del recorrido por niveles iterativo.
     * @return
     */
    public int size() {
        if (this.esArbolVacio()) {
            return 0;
        }
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<NodoBinario<K,V>>();
        colaDeNodos.offer(this.raiz);
        int contadorDeNodos = 0;
        while (!colaDeNodos.isEmpty()) {
            NodoBinario<K,V> nodoActual = colaDeNodos.poll();
            contadorDeNodos = contadorDeNodos +1;
            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return contadorDeNodos;
    }

    /**
     * Este método devuele la altura del árbol, implementado con ayuda del recorrido por niveles iterativo.
     * @return
     */
    public int alturaIterativa() {
        if (this.esArbolVacio()) {
            return 0;
        }
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<NodoBinario<K, V>>();
        colaDeNodos.offer(this.raiz);
        int alturaDelArbol = 0;
        while(!colaDeNodos.isEmpty()) {
            int nodosDelNivel = colaDeNodos.size();
            while (nodosDelNivel > 0) {
                NodoBinario<K,V> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esVacioHijoIzquierdo()){
                    colaDeNodos.add(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.add(nodoActual.getHijoDerecho());
                }
                nodosDelNivel = nodosDelNivel - 1;
            }
            alturaDelArbol = alturaDelArbol +1;
        }
        return alturaDelArbol;
    }

    /**
     * Este método devuelve la altura del árbol, implementado con ayuda del recorrido PostOrden recursivo.
     * @return
     */
    public int altura() {
        return altura(this.raiz);
    }

    protected int altura(NodoBinario<K,V> nodoActual) {
        if(NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha = altura(nodoActual.getHijoDerecho());
        if(alturaPorIzquierda > alturaPorDerecha) {
            return alturaPorIzquierda + 1;
        }else {
            return alturaPorDerecha + 1;
        }
    }

    /**
     * Método que vacia el árbol.
     */
    public void vaciar() {
        this.raiz = this.nodoVacioParaElArbol();
    }

    /**
     * Devuelve verdadero si el árbol esta vacio o falso si el no esta vacio.
     * @return
     */
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    /**
     * Método que devuelve el nivel del arbol. Implementado con ayuda del recorrido por niveles iterativo.
     * @return
     */
    public int nivel() {
        if (esArbolVacio()) {
            return -1;
        }
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<NodoBinario<K, V>>();
        colaDeNodos.add(raiz);
        int contador = 0;
        while (!colaDeNodos.isEmpty()) {
            int cantidadDeNodosEnNivel = colaDeNodos.size();
            while (cantidadDeNodosEnNivel > 0) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.add(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.add(nodoActual.getHijoDerecho());
                }

                cantidadDeNodosEnNivel = cantidadDeNodosEnNivel - 1;
            }
            contador = contador + 1;
        }
        return contador - 1;
    }

    /**
     * Método adicional implementado forma recursiva.
     * @return
     */
    public int nivelRecursivo () {
        return nivelRecursivo(raiz) - 1;
    }

    private int nivelRecursivo(NodoBinario<K,V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int profuncidadPorIzquierda = nivelRecursivo(nodoActual.getHijoIzquierdo());
        int profuncidadPorDerecha = nivelRecursivo(nodoActual.getHijoDerecho());
        if (profuncidadPorIzquierda > profuncidadPorDerecha) {
            return profuncidadPorIzquierda + 1;
        }else{
            return profuncidadPorDerecha + 1;
        }
    }

    /**
     * Recorrido InOrden Iterativo.
     * @return
     */
    public List<K> recorridoEnInorden() {
        List<K> recorrido = new LinkedList<K>();
        if (this.esArbolVacio()) {
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<NodoBinario<K, V>>();
        NodoBinario<K,V> nodoActual = this.raiz;
        meterEnPilaParaInOrden(pilaDeNodos, nodoActual);
        //****
        while (!pilaDeNodos.isEmpty()) {
            nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if (!nodoActual.esVacioHijoDerecho()) {
                nodoActual = nodoActual.getHijoDerecho();
                meterEnPilaParaInOrden(pilaDeNodos, nodoActual);
            }
        }
        return recorrido;
    }

    /**
     * Método adicional.
     * @param pilaDeNodos
     * @param nodoActual
     */
    private void meterEnPilaParaInOrden(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
        while(!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
        }
    }

    /**
     * Recorrido PreOrden iterativo.
     * @return
     */
    public List<K> recorridoEnPreorden() {
        List<K> recorrido = new LinkedList<K>();
        if (this.esArbolVacio()){
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<NodoBinario<K,V>>();
        pilaDeNodos.push(raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<K,V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if (!nodoActual.esVacioHijoDerecho()) {
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }
            if (!nodoActual.esVacioHijoIzquierdo()) {
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }


        }
        return recorrido;
    }

    /**
     * Recorrido PreOrden recursivo.
     * @return
     */
    public List<K> recorridoEnPreordenRecursivo() {
        List<K> recorrido = new LinkedList<K>();
        recorridoEnPreordenRecursivo(this.raiz, recorrido);
        return recorrido;
    }
    private void recorridoEnPreordenRecursivo(NodoBinario<K,V> nodoActual, List<K> recorrido) {

        if(NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorrido.add(nodoActual.getClave());
        recorridoEnPreordenRecursivo(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPreordenRecursivo(nodoActual.getHijoDerecho(), recorrido);

    }

    /**
     * Recorrido PostOrden recursivo.
     * @return
     */
    public List<K> recorridoEnPostorden() {
        List<K> recorrido = new LinkedList<K>();
        recorridoEnPostOrdenAmigo(this.raiz, recorrido);
        return recorrido;
    }
    private void recorridoEnPostOrdenAmigo(NodoBinario<K,V> nodoActual, List<K> recorrido) {

        if(NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnPostOrdenAmigo(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPostOrdenAmigo(nodoActual.getHijoDerecho(), recorrido);
        recorrido.add(nodoActual.getClave());
    }

    /**
     * Recorrido PostOrden iterativo.
     * @return
     */
    public List<K> recorridoPostOrdenIterativo() {
        List<K> recorrido = new LinkedList<K>();
        if(this.esArbolVacio()) {
            return recorrido;
        }

        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<NodoBinario<K,V>>();
        meterEnPilaHijosIzquierdosPostOrden(this.raiz,pilaDeNodos);
        while(!pilaDeNodos.isEmpty()) {
            NodoBinario<K,V>  nodoActual = pilaDeNodos.pop();
            if(!nodoActual.esVacioHijoDerecho() && !recorrido.contains(nodoActual.getHijoDerecho().getClave())) {
                pilaDeNodos.push(nodoActual);
                meterEnPilaHijosIzquierdosPostOrden(nodoActual.getHijoDerecho(),pilaDeNodos);

            }else{
                recorrido.add(nodoActual.getClave());
            }

        }
        return recorrido;
    }
    protected void meterEnPilaHijosIzquierdosPostOrden(NodoBinario<K,V> nodoActual, Stack<NodoBinario<K,V>> pilaDeNodos) {
        while(!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
        }


    }

    /**
     * Recorrido InOrden recursivo.
     * @return
     */
    public List<K> recorridoInOrdenRecursivo() {
        List<K> recorrido = new LinkedList<K>();
        recorridoInOrdenRecursivoAmigo(this.raiz, recorrido);
        return recorrido;
    }
    private void recorridoInOrdenRecursivoAmigo(NodoBinario<K,V> nodoActual, List<K> recorrido) {
        //simular n para controlar la recursividad. Pero como?
        //con la altura del arbol
        if(NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoInOrdenRecursivoAmigo(nodoActual.getHijoIzquierdo(), recorrido);
        recorrido.add(nodoActual.getClave());
        recorridoInOrdenRecursivoAmigo(nodoActual.getHijoDerecho(), recorrido);
    }

    /**
     * Recorrido por Niveles iterativo.
     * @return
     */
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new LinkedList<K>();
        if (this.esArbolVacio()) {
            return recorrido;
        }
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<NodoBinario<K,V>>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoBinario<K,V> nodoActual = colaDeNodos.poll();
            recorrido.add(nodoActual.getClave());
            if (!nodoActual.esVacioHijoIzquierdo()){
                colaDeNodos.add(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.add(nodoActual.getHijoDerecho());
            }
        }
        return recorrido;
    }

    /**
     * Método adicional. Retorna la cantidad de nodos con hijos izquierdo.
     * @return
     */
    public int cantidadDeNodosConSoloHijoIzquierdo() {
        return cantidadDeNodosConSoloHijoIzquierdo(this.raiz);
    }
    private int cantidadDeNodosConSoloHijoIzquierdo(NodoBinario<K,V> nodoActual) {
        if(NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidadDeSoloHIPorIzquierda = cantidadDeNodosConSoloHijoIzquierdo(nodoActual.getHijoIzquierdo());
        int cantidadDeSoloHIPorDerecha = cantidadDeNodosConSoloHijoIzquierdo(nodoActual.getHijoDerecho());
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
            return cantidadDeSoloHIPorDerecha + cantidadDeSoloHIPorIzquierda + 1;
        }
        return cantidadDeSoloHIPorDerecha + cantidadDeSoloHIPorIzquierda;
    }

    /**
     * Método adcionnal. Retorna la cantidad de nodos incomplestos, es decir, cantidad de nodos con solo un hijo,
     * ya sea solo hijo izquierdo o solo hijo derecho.
     * @return
     */
    public int cantidadDeNodosIncompletosDesdeNivel(int deNivel) {//hacerlo iterativo
        return cantidadDeNodosincompletosDesdeNivel(this.raiz,deNivel, 0);

    }
    private int cantidadDeNodosincompletosDesdeNivel(NodoBinario<K,V> nodoActual,int deNivel, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidadDeNodosPorIzquierda = cantidadDeNodosincompletosDesdeNivel(nodoActual.getHijoIzquierdo(),deNivel, nivelActual + 1);
        int cantidadDeNodosPorDerecha   = cantidadDeNodosincompletosDesdeNivel(nodoActual.getHijoDerecho(),deNivel, nivelActual + 1);
        if (nivelActual >= deNivel) {
            if ((!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) ||
                    (nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho())) {
                return cantidadDeNodosPorIzquierda + cantidadDeNodosPorDerecha + 1;
            }
        }
        return cantidadDeNodosPorDerecha + cantidadDeNodosPorIzquierda;
    }

    /**
     * Método adicional. Genera el árbol para su posterior impresión.
     * @return
     */
    @Override
    public String toString() {
        String arbol="arbol\n";
        if (this.esArbolVacio()) {
            return arbol+"-->(null)";
        }
        Stack<NodoBinario<K, V>> pilaDeNodos=new Stack<NodoBinario<K, V>>();
        arbol=arbol+raiz.getClave()+"[raiz]";
        pilaDeNodos.push(raiz);
        arbol=imprimirDerecha(raiz.getHijoDerecho(),pilaDeNodos,arbol);
        while(!pilaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual=pilaDeNodos.pop();
            arbol=arbol+generadorDeEspacio(cantidadNodosHastaElActual(nodoActual.getClave()));
            if (!nodoActual.esVacioHijoIzquierdo()) {
                nodoActual=nodoActual.getHijoIzquierdo();
                arbol=arbol+"└─>"+nodoActual.getClave()+"[HI]";
                pilaDeNodos.push(nodoActual);
                arbol=imprimirDerecha(nodoActual.getHijoDerecho(), pilaDeNodos, arbol);
            }else{
                arbol=arbol+"└─═╣\n";
            }
        }
        return arbol;
    }

    /**
     * Método adicional.
     * @param datoABuscar
     * @return
     */
    public int cantidadNodosHastaElActual(K datoABuscar){
        NodoBinario<K, V> nodoActual = raiz;
        int cantidad=0;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            K datoActual = nodoActual.getClave();
            if (datoABuscar.compareTo(datoActual) == 0) {
                return cantidad;
            }
            if (datoABuscar.compareTo(datoActual) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();
            }
            cantidad++;
        }
        return cantidad;
    }

    /**
     * Método adicional.
     * @param cantidadDeEspacio
     * @return
     */
    public String generadorDeEspacio(int cantidadDeEspacio){
        String generador="        ";
        for (int i = 0; i < cantidadDeEspacio; i++) {
            generador=generador+"│        ";//179
        }
        return generador;
    }

    /**
     * Método adicional.
     * @param nodoActual
     * @param pilaDeNodos
     * @param arbol
     * @return
     */
    private String imprimirDerecha(NodoBinario<K, V> nodoActual, Stack<NodoBinario<K, V>> pilaDeNodos, String arbol) {
        while(!NodoBinario.esNodoVacio(nodoActual)){
            arbol=arbol+"──>"+nodoActual.getClave()+"[HD]";//196
            pilaDeNodos.push(nodoActual);
            nodoActual=nodoActual.getHijoDerecho();
        }
        return arbol+"═╣\n";
    }

    //Ejercicios Árbol Binario de Búsqueda.
    // (1) Método que devuelve la cantidad de nodos hojas en un árbol binario, implementado con ayuda del recorrido PostOrden recursivo.
    public int cantidadDeNodosHojaRecursivo() {
        return this.cantidadDeNodosHoja(raiz);
    }
    private int cantidadDeNodosHoja(NodoBinario<K,V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidadDeNodosHPorIzquierda = cantidadDeNodosHoja(nodoActual.getHijoIzquierdo());
        int cantidadDeNodosHPorDerecha = cantidadDeNodosHoja(nodoActual.getHijoDerecho());
        if (nodoActual.esHoja()) {
            return cantidadDeNodosHPorDerecha + cantidadDeNodosHPorIzquierda + 1;
        }
        return cantidadDeNodosHPorDerecha + cantidadDeNodosHPorIzquierda;
    }
    //(2) Método que devuelve la cantidad de nodos hojas en un árbol binario, implementado con ayuda del recorrido por Niveles iterativo.
    public int cantidadDeNodosHojaIteretivo() {
        if (this.esArbolVacio()) {
            return 0;
        }
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<NodoBinario<K, V>>();
        NodoBinario<K,V> nodoActual = this.raiz;
        colaDeNodos.add(nodoActual);
        int contador = 0;
        while (!colaDeNodos.isEmpty()) {
            nodoActual = colaDeNodos.poll();
            if (nodoActual.esHoja()) {
                contador = contador + 1;
            }
            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.add(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.add(nodoActual.getHijoDerecho());
            }
        }
        return contador;
    }
    //(3) Método recursivo que devuelve la cantidad de nodos hojas que existen en un árbol binario, pero solo en el nivel. Implementado con ayuda del recorrido PorstOrden.
    public int cantDeNodosHDesdeNivelRecursivo(int nivel) {
        return cantDeNodosHDesdeNivelRecursivo(raiz,nivel, 0);
    }
    private int cantDeNodosHDesdeNivelRecursivo(NodoBinario<K,V> nodoActual, int nivel, int nivelActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidadPorIzquierda = cantDeNodosHDesdeNivelRecursivo(nodoActual.getHijoIzquierdo(),nivel,nivelActual + 1);
        int cantidadPorDerecha = cantDeNodosHDesdeNivelRecursivo(nodoActual.getHijoDerecho(),nivel,nivelActual + 1);
        if (nivel == nivelActual) {
            if (nodoActual.esHoja()) {
                return cantidadPorDerecha + cantidadPorIzquierda + 1;
            }
        }
        return cantidadPorDerecha + cantidadPorIzquierda;
    }
    //(4) Método iterativo que devuelve la cantidad de nodos hojas que existen en un árbol binario, pero solo en el nivel.Implementado con ayuda del recorrido por niveles.
    public int cantDeNodosHDesdeNivelIterativo(int nivel) {
        if (esArbolVacio()) {
           return 0;
        }
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<NodoBinario<K, V>>();
        NodoBinario<K, V> nodoActual = this.raiz;
        colaDeNodos.add(nodoActual);
        int cantidadDeNodosEnNivel;
        int cantidadDeNodosHoja = 0;
        int nivelActual = 0;
        while (!colaDeNodos.isEmpty()) {
            cantidadDeNodosEnNivel = colaDeNodos.size();
            while (cantidadDeNodosEnNivel > 0) {
                nodoActual = colaDeNodos.poll();

                if (nivel == nivelActual) {
                    if (nodoActual.esHoja()) {
                        cantidadDeNodosHoja = cantidadDeNodosHoja + 1;
                    }
                }

                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.add(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.add(nodoActual.getHijoDerecho());
                }
                cantidadDeNodosEnNivel = cantidadDeNodosEnNivel - 1;
            }
            nivelActual = nivelActual + 1;
        }
        return cantidadDeNodosHoja;
    }
    //(5) Método iterativo que devuelve la cantidad de nodos hojas que existen en un árbol binario, pero solo antes del nivel.
    public int cantDeNodosHAntesDeNivelIterativo(int nivel) {
        if (esArbolVacio()) {
            return 0;
        }
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<NodoBinario<K, V>>();
        NodoBinario<K, V> nodoActual = this.raiz;
        colaDeNodos.add(nodoActual);
        int cantidadDeNodosEnNivel;
        int contadorDeNodos = 0;
        int nivelActual = 0;
        while (!colaDeNodos.isEmpty()) {
            cantidadDeNodosEnNivel = colaDeNodos.size();
            while (cantidadDeNodosEnNivel > 0) {
                nodoActual = colaDeNodos.poll();

                if (nivelActual < nivel) {
                    if (nodoActual.esHoja()) {
                        contadorDeNodos = contadorDeNodos + 1;
                    }
                }

                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.add(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.add(nodoActual.getHijoDerecho());
                }
                cantidadDeNodosEnNivel = cantidadDeNodosEnNivel - 1;
            }
            nivelActual = nivelActual + 1;
        }
        return contadorDeNodos;
    }
    //(6) Método recursivo que verifica si un árbol binario esta balanceado, segun las reglas de un árbol AVL.
    public boolean esArbolBalancedo() throws ExceptionClaveNoExiste {
        if (this.esArbolVacio()) {
            throw new ExceptionClaveNoExiste("Árbol vacio");
        }
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<NodoBinario<K, V>>();
        colaDeNodos.add(raiz);
        int cantidadDeNodos;
        while (!colaDeNodos.isEmpty()) {
            cantidadDeNodos = colaDeNodos.size();
            while (cantidadDeNodos > 0) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (esArbolBalancedoRecursivo(nodoActual)) {
                    if (!nodoActual.esVacioHijoIzquierdo()) {
                        colaDeNodos.add(nodoActual.getHijoIzquierdo());
                    }
                    if (!nodoActual.esVacioHijoDerecho()) {
                        colaDeNodos.add(nodoActual.getHijoDerecho());
                    }
                }else {
                    return false;
                }
                cantidadDeNodos = cantidadDeNodos - 1;
            }
        }
        return true;
    }
    private boolean esArbolBalancedoRecursivo(NodoBinario<K,V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return true;
        }
        boolean esBalanceadoPorIzquierda = esArbolBalancedoRecursivo(nodoActual.getHijoIzquierdo());
        boolean esBalanceadoPorDerecha   = esArbolBalancedoRecursivo(nodoActual.getHijoDerecho());

        int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha   = altura(nodoActual.getHijoDerecho());
        int diferenciaEnAltura = alturaPorIzquierda - alturaPorDerecha;

        if (diferenciaEnAltura > 1) {
            return false;
        }
        if (diferenciaEnAltura <-1) {
            return false;
        }
        return true;
    }
    //(7) Método iterativo que verifica si un árbol binario esta balanaceado, segun reglas de un árbol AVL, con recorrido PostOrden.
    public boolean esArbolBalanceadoIterativo() throws ExceptionClaveNoExiste{
        if (esArbolVacio()) {
            throw new ExceptionClaveNoExiste("Árbol vacio");
        }
        List<K> recorrido = new LinkedList<K>();
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<NodoBinario<K, V>>();
        NodoBinario<K, V> nodoActual = this.raiz;
        meterEnPilaHijosIzquierdosPostOrden(nodoActual, pilaDeNodos);

        while (!pilaDeNodos.isEmpty()) {
            nodoActual = pilaDeNodos.pop();

            if (!nodoActual.esVacioHijoDerecho() && !recorrido.contains(nodoActual.getHijoDerecho().getClave())) {
                pilaDeNodos.push(nodoActual);
                meterEnPilaHijosIzquierdosPostOrden(nodoActual.getHijoDerecho(), pilaDeNodos);
            }else {
                recorrido.add(nodoActual.getClave());
                int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
                int alturaPorDerecha = altura(nodoActual.getHijoDerecho());
                int diferenciaEnAltura = alturaPorIzquierda - alturaPorDerecha;
                if (diferenciaEnAltura > 1) {
                    return false;
                }
                if (diferenciaEnAltura < -1) {
                    return false;
                }
            }
        }
        return true;
    }
    //(8)Método reconstruir
    public void reconstruirArbol(boolean estado) {
        this.raiz = new NodoBinario<K, V>(estado);
        List<K> recorridoClave = new LinkedList<K>();
        List<V> recorridoValor = new LinkedList<V>();
        NodoBinario <K, V> nodoActual = this.raiz;
        if (estado){
            List<K> listaDeClavesInorden = new LinkedList<K>();
            List<V> listaDeValoresInorden = new LinkedList<V>();
            this.añadirElementosALaLista(listaDeClavesInorden, listaDeValoresInorden, recorridoEnInorden());

            List<K> listaDeClavesPostOrden = nodoActual.getListaDeClavesPostOrden();
            List<V> listaDeValoresPostOrden = nodoActual.getListaDeValoresPostOrden();
            this.añadirElementosALaLista(listaDeClavesPostOrden, listaDeValoresPostOrden, recorridoEnPostorden());



        }else{

        }


    }

    private void añadirElementosALaLista(List<K> listaDeClaves, List<V> listaDeValores, List<K> listaRecorrido) {
        int cantidadDeElementosEnLaLista = listaRecorrido.size();
        for (int i = 0; i < cantidadDeElementosEnLaLista; i++) {
            listaDeClaves.add(i,listaRecorrido.get(i));
            V valor = buscar(listaRecorrido.get(i));
            listaDeValores.add(i, valor);
        }
    }
    //(9) Método que devuelve el sucesor InOrden  de la clave de un nodo en el árbol binario de búsqueda.
    private NodoBinario<K, V> sucesorInOrden(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoAnterior;
        nodoAnterior = nodoActual;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior;
    }
    //(10) Método que devuelve el predecesor InOrden  de la clave de un nodo en un árbol binario de búsqueda. Implementado con ayuda del recorrido InOrden iterativo.
    private NodoBinario<K, V> predecesorInOrden(NodoBinario<K, V> nodoSucesor) {

        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<NodoBinario<K, V>>();
        NodoBinario<K, V> nodoActual = this.raiz;
        meterEnPilaParaInOrden(pilaDeNodos, nodoActual);
        NodoBinario<K, V> nodoPredecesor = nodoVacioParaElArbol();
        NodoBinario<K, V> nodoSucesorActual;
        while (!pilaDeNodos.isEmpty()) {
            nodoActual = pilaDeNodos.pop();

            if (!nodoActual.esVacioHijoDerecho()) {
                nodoSucesorActual = sucesorInOrden(nodoActual.getHijoDerecho());
            }else{
                nodoSucesorActual = nodoActual;
            }
            if (nodoSucesorActual.getClave().compareTo(nodoSucesor.getClave()) == 0) {
                nodoPredecesor = nodoActual;
                return nodoPredecesor;
            }

            if (!nodoActual.esVacioHijoDerecho()) {
                meterEnPilaParaInOrden(pilaDeNodos, nodoActual.getHijoDerecho());
            }
        }
        return nodoPredecesor;
    }
    //Método adicional para comprobar método predecesortInorden().
    public K clavePredecesor(K clave) {
        NodoBinario<K, V> nodoSucesor = buscarNodo(clave);
        NodoBinario<K, V> nodoPredecesor = predecesorInOrden(nodoSucesor);
        return nodoPredecesor.getClave();
    }

    private NodoBinario<K,V> buscarNodo(K clave) {
        NodoBinario<K, V> nodoActual = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            if (clave.compareTo(nodoActual.getClave()) > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            }else if (clave.compareTo(nodoActual.getClave()) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            }else {
                return nodoActual;
            }
        }
        return null;
    }
    //(11) Método eliminar de un árbol AVL. En clase AVL
    //(12) Método que retorna la cantidad de nodos que tienen ambos hijos luego del nivel N. Implementado con recorrido por Niveles.
    public int cantDeNodosConAmbosHijosLuegoDelNivel(int nivel){
        if (esArbolVacio()) {
            return 0;
        }
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<NodoBinario<K, V>>();
        NodoBinario<K, V> nodoActual = this.raiz;
        colaDeNodos.add(nodoActual);
        int nivelActual = 0;
        int cantidadDeNodos;
        int contadorDeNodosConAmbosHijos = 0;
        while (!colaDeNodos.isEmpty()) {
            cantidadDeNodos = colaDeNodos.size();
            while (cantidadDeNodos > 0) {
                nodoActual = colaDeNodos.poll();
                if (nivelActual > nivel) {
                    if (!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) {
                        contadorDeNodosConAmbosHijos = contadorDeNodosConAmbosHijos + 1;
                    }
                }

                if (!nodoActual.esVacioHijoIzquierdo()) {
                    colaDeNodos.add(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho()) {
                    colaDeNodos.add(nodoActual.getHijoDerecho());
                }
                cantidadDeNodos = cantidadDeNodos - 1;
            }
            nivelActual = nivelActual + 1;
        }
        return contadorDeNodosConAmbosHijos;
    }
}
