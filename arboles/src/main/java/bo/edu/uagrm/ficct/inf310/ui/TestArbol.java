//by Abel Alejando López Cabero - SEMESTRE 1-2020 - Copyright©

package bo.edu.uagrm.ficct.inf310.ui;

import bo.edu.uagrm.ficct.inf310.arboles.ArbolBinarioBusqueda;
import bo.edu.uagrm.ficct.inf310.arboles.ExceptionClaveNoExiste;
import bo.edu.uagrm.ficct.inf310.arboles.ExceptionClaveYaExiste;
import bo.edu.uagrm.ficct.inf310.arboles.IArbolBusqueda;

public class TestArbol {
    public static  void main(String[] args) throws ExceptionClaveYaExiste, ExceptionClaveNoExiste {
        IArbolBusqueda<Integer, String> arbolDePrueba = new ArbolBinarioBusqueda<Integer, String>();
        Integer [] arregloI = {54,5,65};//,19,82,18,72,32,75,60} ;
        String  [] arregloS = {"Cristhian Sosa", "Julio Gonzales", "Liz Llanos"};/*   , "Dilker Cartagena", "Gabriel Coca",
                                "Luis Fernando", "Alejandro Cruz", "Carlos Angola", "Abel López"      , "Alejandro Cabero"};*/
        int dimension = arregloI.length;
        for (int i = 0;i < dimension;i++) {
            arbolDePrueba.insertar(arregloI[i], arregloS[i]);
        }
        arbolDePrueba.toString();
        System.out.println(arbolDePrueba);
        /*
        arbolDePrueba.insertar(arregloI[0],arregloS[0]);
        arbolDePrueba.insertar(5 ,"Julio Gonzales");
        arbolDePrueba.insertar(65,"Liz Llanos");
        arbolDePrueba.insertar(19,"Dilker Cartagena");
        arbolDePrueba.insertar(82,"Gabriel Coca");
        arbolDePrueba.insertar(18,"Luis Fernando");
        arbolDePrueba.insertar(72,"Alejandro Cruz");
        arbolDePrueba.insertar(32,"Carlos Angola");
         */
  /*      System.out.println("----------------------------------------------------------------------");
  Métodos de ÁrbolBinarioBúsqueda
        System.out.println("Valor buscado = " + arbolDePrueba.buscar(0));
        System.out.println("Nivel del árbol binario = " + arbolDePrueba.nivel());

        System.out.println("Recorrido por Niveles = " + arbolDePrueba.recorridoPorNiveles());
        System.out.println("Recorrido en Preorden = " + arbolDePrueba.recorridoEnPreorden());
        System.out.println("Recorrido en InOrden = " + arbolDePrueba.recorridoEnInorden());
        System.out.println("Cantidad de nodos en el arbol = " + arbolDePrueba.size());

        if(arbolDePrueba instanceof ArbolBinarioBusqueda) {
            System.out.println("Recorrido en InOrden Recursivo = "   + ((ArbolBinarioBusqueda)arbolDePrueba).recorridoInOrdenRecursivo());
            System.out.println("Recorrido en PostOrden Iterativo = " + ((ArbolBinarioBusqueda)arbolDePrueba).recorridoPostOrdenIterativo());
        }
        System.out.println("Recorrido en PostOrden Recursivo = " + arbolDePrueba.recorridoEnPostorden());
        System.out.println("Recorrido en PreOrden Recursivo = " + ((ArbolBinarioBusqueda)arbolDePrueba).recorridoEnPreordenRecursivo());
        System.out.println("Altura del arbol = " + arbolDePrueba.altura());
        //System.out.println("Valor eliminado = " + arbolDePrueba.eliminar(65)); anulamos esto para poder realizar los Ejercicios ABB
        //System.out.println(arbolDePrueba);
        System.out.println("Recorrido por Niveles = " + arbolDePrueba.recorridoPorNiveles());
        System.out.println("Cantidad de nodos incompletos desde nivel = " + ((ArbolBinarioBusqueda<Integer, String>) arbolDePrueba).cantidadDeNodosIncompletosDesdeNivel(2));
*/

        System.out.println("---Ejercicios ABB---");
        System.out.println("(1) Cantidad de nodos hojas recursivo = " + ((ArbolBinarioBusqueda<Integer, String>) arbolDePrueba).cantidadDeNodosHojaRecursivo());
        System.out.println("(2) Cantidad de nodos hojas iterativo = " + ((ArbolBinarioBusqueda<Integer, String>) arbolDePrueba).cantidadDeNodosHojaIteretivo());
        System.out.println("(3) Cantidad de nodos hojas recursivo desde nivel = " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).cantDeNodosHDesdeNivelRecursivo(3));
        System.out.println("(4) Cantidad de nodos hojas iterativo desde nivel = " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).cantDeNodosHDesdeNivelIterativo(1));
        System.out.println("(5) Cantidad de nodos hojas iterativo antes del nivel = " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).cantDeNodosHAntesDeNivelIterativo(7));
        System.out.println("(6) Es árbol binario balanceado recursivo = " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).esArbolBalancedo());
        System.out.println("(7) Es árbol binario balanceado iterativo = " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).esArbolBalanceadoIterativo());
        //System.out.println("(8)  = " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).;
        //System.out.println("(9)  = " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).;
        System.out.println("(10) Clave predecesor = " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).clavePredecesor(65));
        //System.out.println("(11) Eliminar AVL = " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).;
        System.out.println("(12) Cantidad de nodos con ambos hijos luego del nivel  = " + ((ArbolBinarioBusqueda<Integer, String>)arbolDePrueba).cantDeNodosConAmbosHijosLuegoDelNivel(3));

    }
}