package bo.edu.uagrm.ficct.inf310.arboles;

import java.util.List;

public class NodoBinario<K,V> {

    //Listas
    private List<K> listaDeClavesInOrden;
    private List<V> listaDeValoresInOrden;

    private List<K> listaDeClavesPostOrden;
    private List<V> listaDeValoresPostOrden;

    private List<K> listaDeClavesPreOrden;
    private List<V> listaDeValoresPreOrden;


    private K clave;
    private V valor;
    private NodoBinario<K, V> hijoIzquierdo;
    private NodoBinario<K, V> hijoDerecho;


    public List<K> getListaDeClavesInOrden() {
        return listaDeClavesInOrden;
    }

    public void setListaDeClavesInOrden(List<K> listaDeClavesInOrden) {
        this.listaDeClavesInOrden = listaDeClavesInOrden;
    }

    public List<V> getListaDeValoresInOrden() {
        return listaDeValoresInOrden;
    }

    public void setListaDeValoresInOrden(List<V> listaDeValoresInOrden) {
        this.listaDeValoresInOrden = listaDeValoresInOrden;
    }

    public List<K> getListaDeClavesPostOrden() {
        return listaDeClavesPostOrden;
    }

    public void setListaDeClavesPostOrden(List<K> listaDeClavesPostOrden) {
        this.listaDeClavesPostOrden = listaDeClavesPostOrden;
    }

    public List<V> getListaDeValoresPostOrden() {
        return listaDeValoresPostOrden;
    }

    public void setListaDeValoresPostOrden(List<V> listaDeValoresPostOrden) {
        this.listaDeValoresPostOrden = listaDeValoresPostOrden;
    }

    public List<K> getListaDeClavesPreOrden() {
        return listaDeClavesPreOrden;
    }

    public void setListaDeClavesPreOrden(List<K> listaDeClavesPreOrden) {
        this.listaDeClavesPreOrden = listaDeClavesPreOrden;
    }

    public List<V> getListaDeValoresPreOrden() {
        return listaDeValoresPreOrden;
    }

    public void setListaDeValoresPreOrden(List<V> listaDeValoresPreOrden) {
        this.listaDeValoresPreOrden = listaDeValoresPreOrden;
    }

    public NodoBinario() {

    };

    public NodoBinario(boolean estado) {
        if (estado) {
            List<K> listaDeClavesInOrden;
            List<V> listaDeValoresInOrden;

            List<K> listaDeClavesPostOrden;
            List<V> listaDeValoresPostOrden;
        }else{
            List<K> listaDeClavesInOrden;
            List<V> listaDeValoresInOrden;

            List<K> listaDeClavesPreOrden;
            List<V> listaDeValoresPreOrden;
        }
    };

    public NodoBinario(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public K getClave() {
        return this.clave;
    }

    public void setClave(K clave) {
        this.clave = clave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public NodoBinario<K, V> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoBinario<K, V> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoBinario<K, V> getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoBinario<K, V> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public boolean esVacioHijoIzquierdo() {
        return NodoBinario.esNodoVacio(this.getHijoIzquierdo());
    }

    public boolean esVacioHijoDerecho() {
        return NodoBinario.esNodoVacio(this.getHijoDerecho());
    }

    public boolean esHoja() {
        return this.esVacioHijoIzquierdo() &&
                this.esVacioHijoDerecho();
    }
    public static boolean esNodoVacio(NodoBinario<?, ?> nodo) {
        return nodo == nodoVacio();
    }

    public static NodoBinario<?, ?> nodoVacio() {
        return null;
    }
}