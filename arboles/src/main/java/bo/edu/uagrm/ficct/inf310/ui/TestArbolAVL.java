//by Abel Alejando López Cabero - SEMESTRE 1-2020 - Copyright© x2
package bo.edu.uagrm.ficct.inf310.ui;

import bo.edu.uagrm.ficct.inf310.arboles.*;

public class TestArbolAVL {
    public static void main(String[] args)throws ExceptionClaveNoExiste, ExceptionClaveYaExiste {
        IArbolBusqueda<Integer, String> arbolDePrueba = new AVL<Integer, String>();
        Integer [] arregloI = {54,18,70,82,65,15,69};//,72,32,75,60} ;
        String  [] arregloS = {"Cristhian Sosa", "Julio Gonzales", "Liz Llanos", "Dilker Cartagena", "Gabriel Coca",
                "Luis Fernando","Alejandro Cruz","Carlos Angola", "Abel López"      , "Alejandro Cabero"};
        //Método insertar
        int dimension = arregloI.length;
        for (int i = 0;i < dimension;i++) {
            arbolDePrueba.insertar(arregloI[i], arregloS[i]);
        }
        arbolDePrueba.toString();
        System.out.println(arbolDePrueba);

        /*Método elimnar...
        System.out.println("Valor eliminado = " + arbolDePrueba.eliminar(18));
        System.out.println(arbolDePrueba);
        System.out.println("Valor eliminado = " + arbolDePrueba.eliminar(90));
        System.out.println("Valor eliminado = " + arbolDePrueba.eliminar(82));
        System.out.println("Valor eliminado = " + arbolDePrueba.eliminar(70));
        System.out.println(arbolDePrueba);
        System.out.println("Valor eliminado = " + arbolDePrueba.eliminar(00));
        ...funciona! */

    }
}
