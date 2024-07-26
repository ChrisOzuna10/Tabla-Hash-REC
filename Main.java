import models.HashTable;
import models.Bussines;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashTable<String, Bussines> hashTable;
        hashTable = new HashTable<>();

        Scanner scanner = new Scanner(System.in);
        boolean conectado = true;
        String line;
        do {
            String eleccion;
            System.out.println("Elige una opcion:");
            System.out.println("1. Imprimir los datos");
            System.out.println("2. Guardar los datos en la tabla hash");
            System.out.println("3. Agregar un nuevo dato");
            System.out.println("4. Encontrar un dato por ID");
            System.out.println("5. Salir");
            eleccion = scanner.next();
            switch (eleccion){
                case "1":
                    for (String key : hashTable.keySet()) {
                        Bussines business = hashTable.obtener(key);
                        System.out.println("ID: " + business.getId() + ", Nombre: " + business.getName() + ", Dirección: " + business.getAddress() + ", Ciudad: " + business.getCity() + ", Estado: " + business.getState());
                    }
                    break;
                case "2":
                    long inicio = System.nanoTime();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader("bussines.csv"));
                        while ((line = br.readLine()) != null) {
                            String[] businessData = line.split(",");
                            Bussines business1 = new Bussines(businessData[0], businessData[1], businessData[2], businessData[3], businessData[4]);
                            hashTable.agregar(business1.getId(), business1);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    long fin = System.nanoTime();
                    System.out.println("Tiempo transcurrido para insertar todos los datos: " + (fin - inicio)/1000000 + " milisegundos / ms");

                    break;
                case  "3":
                    System.out.println("Ingrese ID:");
                    String id = scanner.next();
                    scanner.nextLine();
                    System.out.println("Ingrese Nombre:");
                    String name = scanner.nextLine();
                    System.out.println("Ingrese Dirección:");
                    String address = scanner.nextLine();
                    System.out.println("Ingrese Ciudad:");
                    String city = scanner.nextLine();
                    System.out.println("Ingrese Estado:");
                    String state = scanner.nextLine();

                    long inicioAdd = System.nanoTime();

                    Bussines business = new Bussines(id, name, address, city, state);

                    long finAdd = System.nanoTime();

                    System.out.println("Tiempo transcurrido para insertar un nuevo dato: " + (finAdd - inicioAdd) + " nanosegundos");

                    hashTable.agregar(id, business);
                    System.out.println("Agregado con éxito!");
                    break;
                case "4":
                    System.out.println("Ingrese ID para buscar:");
                    String idEncontrar = scanner.next();

                    long inicioBusqueda = System.nanoTime();
                    Bussines businessToFind = hashTable.obtener(idEncontrar);
                    long finBusqueda = System.nanoTime();

                    System.out.println("Tiempo total de la busqueda: " + (finBusqueda - inicioBusqueda) + " nanosegundos");
                    if (businessToFind != null) {
                        System.out.println("ID: " + businessToFind.getId() + ", Nombre: " + businessToFind.getName() + ", Dirección: " + businessToFind.getAddress() + ", Ciudad: " + businessToFind.getCity() + ", Estado: " + businessToFind.getState());
                    } else {
                        System.out.println("No se encontraron los datos");
                    }
                    break;
                case "5":
                    conectado = false;
                    break;
                default:
                    System.out.println("Error.");
            }
        }while (conectado);
    }
}