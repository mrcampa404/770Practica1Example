package prt1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Prt1 {

    public static Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {

        byte leer_opcion = 0;

        do {
            System.out.println("====BIENVENIDO, SELECCIONE UNA OPCION======"
                    + "\n\t 1) Encriptar"
                    + "\n\t 2) Desencriptar"
                    + "\n\t 3) Ataque"
                    + "\n\t 4) Reportes"
                    + "\n\t x) Salir");
            leer_opcion = Byte.valueOf(leer.nextLine());
            switch (leer_opcion) {
                case 1:
                    encriptar();
                    break;
                case 2:
                    //desencriptar(); 
                    break;
                case 3:
                    //ataque(); 
                    break;
                case 4:
                    //reporte(); 
                    break;
                default:
                    leer_opcion = 0;
                    break;
            }
        } while (leer_opcion != 0);
        System.out.println("Adios, regreso pronto");
    }

    public static void encriptar() {
        byte menu_encriptar = 0;
        int[][] matriz_m = null, matriz_a = null, matriz_b = null;
        do {
            System.out.println("===HOLA COMPA A LA ENCRIPTACION"
                    + "\n\t 1) Ingresar Mensaje"
                    + "\n\t 2) Ingresar Matriz A"
                    + "\n\t 3) Ingresar Matriz B"
                    + "\n\t 4) Encriptar"
                    + "\n\t x) Regresar al menu principal");
            menu_encriptar = Byte.valueOf(leer.nextLine());
            switch (menu_encriptar) {
                case 1:
                    matriz_m = leerMensaje();
                    break;
                case 2:
                    matriz_a = cargarMatriz();
                    break;
                case 3:
                    matriz_b = cargarMatriz();
                    break;
                case 4:
                    encriptarMensaje(matriz_m,matriz_a,matriz_b); 
                    break;
                default:
                    menu_encriptar = 0;
                    break;
            }

        } while (menu_encriptar != 0);
    }

    public static int[][] leerMensaje() {
        System.out.println("Por favor introduzca su mensaje:\n");
        String entrada_mensaje = leer.nextLine();
        entrada_mensaje = entrada_mensaje.toLowerCase();
        char matriz_tmp[] = entrada_mensaje.toCharArray();
        //Mensaje esta en una matriz de tamanio 3xN
        int vector_ret[][] = new int[3][(int) Math.ceil(matriz_tmp.length / 3.0)];
        //rellenar la matriz 
        for (int i = 0; i < matriz_tmp.length; i++) {
            //tiene que estar dentro de 97 y 122, 32
            if (!((matriz_tmp[i] >= 97 && matriz_tmp[i] <= 122) || matriz_tmp[i] == 32)) {
                System.out.println("Existe un caracter erroneo en la entrada");
                return null;
            }

            if (matriz_tmp[i] == 32) {
                vector_ret[i % 3][(int) Math.floor(i / 3.0)] = 27;
            } else if (matriz_tmp[i] < 111) {
                vector_ret[i % 3][(int) Math.floor(i / 3.0)] = matriz_tmp[i] - 97;
            } else {
                vector_ret[i % 3][(int) Math.floor(i / 3.0)] = matriz_tmp[i] - 96;
            }
        }

        if (matriz_tmp.length % 3 == 2) {
            vector_ret[2][vector_ret[0].length - 1] = 27;
        } else if (matriz_tmp.length % 3 == 1) {
            vector_ret[2][vector_ret[0].length - 1] = vector_ret[1][vector_ret[0].length - 1] = 27;
        }

        for (int i = 0; i < vector_ret.length; i++) {
            for (int j = 0; j < vector_ret[0].length; j++) {
                System.out.print("|" + vector_ret[i][j] + "|");
            }
            System.out.println("");
        }

        return vector_ret;

    }

    public static int[][] cargarMatriz() {
        System.out.println("Usuario por favor, deme la direccion del archivo:");
        File nuevo = new File(leer.nextLine());
        try {
            FileReader archivo = new FileReader(nuevo);
            BufferedReader mi_buffer = new BufferedReader(archivo);
            String linea = mi_buffer.readLine();
            //a como b, 3x3 y 3xn 
            int vector_ret[][] = new int[3][];
            for (int i = 0; linea != null && i < 3; i++) {
                String vec_tmp[] = linea.split(",");
                vector_ret[i] = new int[vec_tmp.length];
                for (int j = 0; j < vec_tmp.length; j++) {
                    vector_ret[i][j] = Integer.valueOf(vec_tmp[j]);
                }
                linea = mi_buffer.readLine();
            }

            for (int[] vector_ret1 : vector_ret) {
                for (int j = 0; j < vector_ret[0].length; j++) {
                    System.out.print("|" + vector_ret1[j] + "|");
                }
                System.out.println("");
            }
            
            return vector_ret; 
        } catch (Exception e) {
            System.out.println("Disculpe, algo malo paso y tenga mas detalles: ");
            System.out.println(e.getMessage());
        }
        return null; 
    }
    
    public static void encriptarMensaje(int matriz_m[][], int matriz_a[][], int matriz_b[][]){
        int matriz_ret[][] = new int[matriz_a.length][matriz_m[0].length]; 
        //llevar a cabo la multiplicacion de cada uno// ya esta a*m  
        for(int i=0; i<matriz_ret.length;i++){
            for(int j=0; j<matriz_ret[0].length;j++){
                int valor_resultado = 0; 
                for(int z=0;z<matriz_m.length;z++){
                    valor_resultado+=(matriz_a[i][z]*matriz_m[z][j]); 
                }
                matriz_ret[i][j] = valor_resultado; 
            }
        }
        
        for(int i=0; i<matriz_ret.length;i++){
            for(int j=0; j<matriz_ret[0].length;j++){
                matriz_ret[i][j] += matriz_b[i][j]; 
            }
        }
        
        for (int i = 0; i < matriz_ret.length; i++) {
            for (int j = 0; j < matriz_ret[0].length; j++) {
                System.out.print("|" + matriz_ret[i][j] + "|");
            }
        }

        return; 
    }
    
}
