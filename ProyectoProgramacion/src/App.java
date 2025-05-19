import peliculas.Pelicula;
import peliculas.PeliculasCRUD;
import personajes.Personaje;
import personajes.PersonajesCRUD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import actores.Actor;
import actores.ActorCRUD;
import connection.ConnectionPool;
import participa.Participa;
import participa.ParticipaCRUD;

public class App {
    public static void listarPeliculas(PeliculasCRUD crud) {
        try {
            ArrayList<Pelicula> peliculas = crud.requestAll();
            if (peliculas.size() == 0) {
                System.out.println("No hay peliculas en la base de datos");
            } else{
                System.out.println("----------------------------------------------------------------------------------");
                for (Pelicula pelicula : peliculas) {
                    System.out.println(pelicula);
                }
                System.out.println("----------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las peliculas");
            e.printStackTrace();
        }
    }

    public static void listarActores(ActorCRUD crud) {
        try {
            ArrayList<Actor> actores = crud.requestAll();
            if (actores.size() == 0) {
                System.out.println("No hay actores en la base de datos");
            } else{
                System.out.println("-------------------------------------------------------");
                for (Actor actor : actores) {
                    System.out.println(actor);
                }
                System.out.println("-------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los actores");
            e.printStackTrace();
        }
    }

    public static void listarPersonajes(PersonajesCRUD crud){
        try {
            ArrayList<Personaje> personajes = crud.requestAll();
            if (personajes.size() == 0) {
                System.out.println("No hay personajes en la base de datos");
            } else{
                System.out.println("---------------------------------------------------------------------");
                for (Personaje personaje : personajes) {
                    System.out.println(personaje);
                }
                System.out.println("---------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los personajes");
            e.printStackTrace();
        }
    }

    public static void listarParticipaciones(ParticipaCRUD crud){
        try {
            ArrayList<Participa> participaciones = crud.requestAll();
            if (participaciones.size() == 0) {
                System.out.println("No hay participaciones en la base de datos");
            } else{
                System.out.println("-------------------------------------------");
                for (Participa participa : participaciones) {
                    System.out.println(participa);
                }
                System.out.println("-------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las participaciones");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/proyectoprogramacion";
        String usuario = "lucas";
        String clave = "1234";

        ConnectionPool pool = new ConnectionPool(url, usuario, clave);
        PeliculasCRUD peliculasCRUD = new PeliculasCRUD(pool.getConnection());
        ActorCRUD actorCRUD = new ActorCRUD(pool.getConnection());
        PersonajesCRUD personajesCRUD = new PersonajesCRUD(pool.getConnection());
        ParticipaCRUD participaCRUD = new ParticipaCRUD(pool.getConnection());

        String NomAct, ApeAct, Rol, TitPel, GenPel, NomPer, GenPer, rutaExport;
        long CodAct, CodPel, CodPer, duracion;
        boolean salir = false;
        while (!salir) {
            try {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("1. Editar Actores");
                System.out.println("2. Editar Peliculas");
                System.out.println("3. Editar Personajes");
                System.out.println("4. Editar Participaciones");
                System.out.println("5. Salir");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
                int opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                    boolean salir2 = false;
                        while (!salir2) {
                            try {
                                System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
                                System.out.println("1. Listar Actores");
                                System.out.println("2. Insertar Actor");
                                System.out.println("3. Actualizar Actor");
                                System.out.println("4. Eliminar Actor");
                                System.out.println("5. Visualizar un Actor");
                                System.out.println("6. Salir");
                                System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
                                int opcionActores = sc.nextInt();
                                sc.nextLine();
                                switch (opcionActores) {
                                    case 1:
                                        listarActores(actorCRUD);
                                        break;
                                    case 2:
                                        System.out.println("Ingrese el nombre del actor");
                                        NomAct = sc.nextLine();
                                        System.out.println("Ingrese el apellido del actor");
                                        ApeAct = sc.nextLine();
                                        try {
                                            CodAct = actorCRUD.create(new Actor(0, NomAct, ApeAct));
                                            System.out.println("Se ha insertado el actor con el codigo: " + CodAct);
                                        } catch (SQLException e) {
                                            if (e.getErrorCode() == 1062) {
                                                System.out.println("Ya existe un actor con ese nombre y apellido");
                                            } else {
                                                System.out.println("Error al insertar el actor");
                                                e.printStackTrace();
                                            }
                                        }
                                        break;
                                    case 3: 
                                        listarActores(actorCRUD);
                                        System.out.println("Ingrese el codigo del actor a actualizar");
                                        CodAct = sc.nextLong();
                                        sc.nextLine(); 
                                        System.out.println("Ingrese el nombre del actor");
                                        NomAct = sc.nextLine();
                                        System.out.println("Ingrese el apellido del actor");
                                        ApeAct = sc.nextLine();
                                        try {
                                            int rowAffected = actorCRUD.update(new Actor(CodAct, NomAct, ApeAct));
                                            if (rowAffected == 1) {
                                                System.out.println("Se ha actualizado el actor");
                                            } else {
                                                System.out.println("No se ha actualizado ningún actor");
                                            }
                                        } catch (SQLException e) {
                                            System.out.println("Error al actualizar el actor");
                                            System.out.println("Ocurrió una excepcion "+e.getMessage());
                                        }
                                        break;
                                    case 4:
                                        listarActores(actorCRUD);
                                        System.out.println("Ingrese el codigo del actor a eliminar");
                                        CodAct = sc.nextLong();
                                        sc.nextLine();
                                        try {
                                            actorCRUD.delete(CodAct);
                                            System.out.println("Se ha eliminado el actor");
                                        } catch (SQLException e) {
                                            System.out.println("Error al eliminar el actor");
                                            System.out.println("Ocurrió una excepcion "+e.getMessage());
                                        }
                                        break;
                                    case 5:
                                        listarActores(actorCRUD);
                                        System.out.println("Ingrese el codigo del actor a visualizar");
                                        CodAct = sc.nextLong();
                                        sc.nextLine();
                                        Actor actor = actorCRUD.requestById(CodAct);
                                        if (actor != null) {
                                            System.out.println(actor);
                                        } else {
                                            System.out.println("No se ha encontrado el actor");
                                        }
                                        break;
                                    case 6:
                                        salir2 = true;
                                        break;
                                    default:
                                        break;
                                } 
                            } catch (Exception e) {
                                e.printStackTrace();
                            } 
                        }
                        break;
                    case 2:
                        boolean salir3 = false;
                        while (!salir3) {
                            try {
                                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                System.out.println("1. Listar Peliculas");
                                System.out.println("2. Insertar Pelicula");
                                System.out.println("3. Actualizar Pelicula");
                                System.out.println("4. Eliminar Pelicula");
                                System.out.println("5. Visualizar una Pelicula");
                                System.out.println("6. Salir");
                                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                int opcionPeliculas = sc.nextInt();
                                sc.nextLine();
                                switch (opcionPeliculas) {
                                    case 1:
                                        listarPeliculas(peliculasCRUD);
                                        break;
                                    case 2:
                                        System.out.println("Ingrese el titulo de la pelicula");
                                        TitPel = sc.nextLine();
                                        System.out.println("Ingrese el genero de la pelicula");
                                        GenPel = sc.nextLine();
                                        System.out.println("Ingrese la duracion de la pelicula en minutos");
                                        duracion = sc.nextLong();
                                        sc.nextLine();
                                        try {
                                            CodPel = peliculasCRUD.create(new Pelicula(0, TitPel, GenPel, duracion));
                                            System.out.println("Se ha insertado la pelicula con el codigo: " + CodPel);
                                        } catch (SQLException e) {
                                            if (e.getErrorCode() == 1062) {
                                                System.out.println("Ya existe un actor con ese nombre y apellido");
                                            } else {
                                                System.out.println("Error al insertar el actor");
                                                e.printStackTrace();
                                            }
                                        }
                                        break;
                                    case 3:
                                        listarPeliculas(peliculasCRUD);
                                        System.out.println("Ingrese el codigo de la pelicula a actualizar");
                                        CodPel = sc.nextLong();
                                        sc.nextLine();
                                        System.out.println("Ingrese el titulo de la pelicula");
                                        TitPel = sc.nextLine();
                                        System.out.println("Ingrese el genero de la pelicula");
                                        GenPel = sc.nextLine();
                                        System.out.println("Ingrese la duracion de la pelicula en minutos");
                                        duracion = sc.nextLong();
                                        sc.nextLine();
                                        try {
                                            int rowAffected = peliculasCRUD.update(new Pelicula(CodPel, TitPel, GenPel, duracion));
                                            if (rowAffected == 1) {
                                                System.out.println("Se ha actualizado la pelicula");
                                            } else {
                                                System.out.println("No se ha actualizado ninguna pelicula");
                                            }
                                        } catch (SQLException e) {
                                            System.out.println("Error al actualizar la pelicula");
                                            System.out.println("Ocurrió una excepcion "+e.getMessage());
                                        }
                                        break;
                                    case 4:
                                        listarPeliculas(peliculasCRUD);
                                        System.out.println("Ingrese el codigo de la pelicula a eliminar");
                                        CodPel = sc.nextLong();
                                        sc.nextLine();
                                        try {
                                            peliculasCRUD.delete(CodPel);
                                            System.out.println("Se ha eliminado la pelicula");
                                        } catch (SQLException e) {
                                            System.out.println("Error al eliminar la pelicula");
                                            System.out.println("Ocurrió una excepcion "+e.getMessage());
                                        }
                                        break;
                                    case 5:
                                        listarPeliculas(peliculasCRUD);
                                        System.out.println("Ingrese el codigo de la pelicula a visualizar");
                                        CodPel = sc.nextLong();
                                        sc.nextLine();
                                        Pelicula pelicula = peliculasCRUD.requestById(CodPel);
                                        if (pelicula != null) {
                                            System.out.println(pelicula);
                                        } else {
                                            System.out.println("No se ha encontrado la pelicula");
                                        }
                                        break;  
                                    case 6:
                                        salir3 = true;
                                        break;
                                    default:
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 3:
                        boolean salir4 = false;
                        while (!salir4) {
                            try {
                                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                System.out.println("1. Listar Personajes");
                                System.out.println("2. Insertar Personaje");
                                System.out.println("3. Actualizar Personaje");
                                System.out.println("4. Eliminar Personaje");
                                System.out.println("5. Visualizar un Personaje");
                                System.out.println("6. Exportar CSV");
                                System.out.println("7. Salir");
                                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                int opcionPersonajes = sc.nextInt();
                                sc.nextLine();
                                switch (opcionPersonajes) {
                                case 1:
                                    listarPersonajes(personajesCRUD);
                                    break;
                                case 2:
                                    System.out.println("Ingrese el nombre del personaje");
                                    NomPer = sc.nextLine();
                                    System.out.println("Ingrese el genero del personaje");
                                    GenPer = sc.nextLine();
                                    listarActores(actorCRUD);
                                    System.out.println("Ingrese el codigo del actor");
                                    CodAct = sc.nextLong();
                                    Actor actor = actorCRUD.requestById(CodAct);
                                    if(actor == null){
                                        System.out.println("Error el CodAct del actor no existe en la BBDD");
                                        break;
                                    }
                                    try {
                                        CodPer = personajesCRUD.create(new Personaje(0, NomPer, GenPer, CodAct));
                                        System.out.println("Se ha insertado el personaje con el codigo: " + CodPer);
                                    } catch (SQLException e) {
                                        if (e.getErrorCode() == 1062) {
                                            System.out.println("Ya existe un personaje con ese nombre y genero");
                                        } else {
                                            System.out.println("Error al insertar el personaje");
                                            e.printStackTrace();
                                        }
                                    }
                                    break;
                                case 3:
                                    listarPersonajes(personajesCRUD);
                                    System.out.println("Ingrese el codigo del personaje a actualizar");
                                    CodPer = sc.nextLong();
                                    sc.nextLine();
                                    System.out.println("Ingrese el nombre del personaje");
                                    NomPer = sc.nextLine();
                                    System.out.println("Ingrese el genero del personaje");
                                    GenPer = sc.nextLine();
                                    listarActores(actorCRUD);
                                    System.out.println("Ingrese el codigo del actor");
                                    CodAct = sc.nextLong();
                                    Actor actor2 = actorCRUD.requestById(CodAct);
                                    if(actor2 == null){
                                        System.out.println("Error el CodAct del actor no existe en la BBDD");
                                        break;
                                    }
                                    try {
                                        int rowAffected = personajesCRUD.update(new Personaje(CodPer, NomPer, GenPer, CodAct));
                                        if (rowAffected == 1) {
                                            System.out.println("Se ha actualizado el personaje");
                                        } else {
                                            System.out.println("No se ha actualizado ningún personaje");
                                        }
                                    } catch (SQLException e) {
                                        System.out.println("Error al actualizar el personaje");
                                        System.out.println("Ocurrió una excepcion "+e.getMessage());
                                    }
                                    break;
                                case 4:
                                    listarPersonajes(personajesCRUD);
                                    System.out.println("Ingrese el codigo del personaje a eliminar");
                                    CodPer = sc.nextLong();
                                    try {
                                        personajesCRUD.delete(CodPer);
                                        System.out.println("Se ha eliminado el personaje");
                                    } catch (SQLException e) {
                                        System.out.println("Error al eliminar el personaje");
                                        System.out.println("Ocurrió una excepcion "+e.getMessage());
                                    }
                                    break;
                                case 5:
                                    listarPersonajes(personajesCRUD);
                                    System.out.println("Ingrese el codigo del personaje a visualizar");
                                    CodPer = sc.nextLong();
                                    Personaje personaje = personajesCRUD.requestById(CodPer);
                                    if (personaje != null) {
                                        System.out.println(personaje);
                                    } else {
                                        System.out.println("No se ha encontrado el personaje");
                                    }
                                    break;
                                case 6:
                                    System.out.println("Ingrese la ruta del archivo CSV");
                                    rutaExport = sc.nextLine();
                                    personajesCRUD.exportToCSV(rutaExport);
                                    break;
                                case 7:
                                    salir4 = true;
                                    break;
                                default:
                                    break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        
                        break;
                    case 4:
                        boolean salir5 = false;
                        while (!salir5) {
                            try {
                                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                System.out.println("1. Listar Participaciones");
                                System.out.println("2. Insertar Participacion");
                                System.out.println("3. Actualizar Participacion");
                                System.out.println("4. Eliminar Participacion");
                                System.out.println("5. Visualizar una Participacion");
                                System.out.println("6. Salir");
                                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                int opcionParticipaciones = sc.nextInt();
                                sc.nextLine();
                                switch (opcionParticipaciones) {
                                    case 1:
                                        listarParticipaciones(participaCRUD);
                                        break;
                                    case 2:
                                        listarPeliculas(peliculasCRUD);
                                        System.out.println("Introduzca el Codigo de la Pelicula: ");
                                        CodPel = Long.parseLong(sc.nextLine());
                                        Pelicula pelicula = peliculasCRUD.requestById(CodPel);
                                        if(pelicula == null){
                                            System.out.println("Error el CodPel de la pelicula no existe en la BBDD");
                                            break;
                                        }
                                        listarPersonajes(personajesCRUD);
                                        System.out.println("Introduzca el CodPer del Personaje: ");
                                        CodPer = Long.parseLong(sc.nextLine());
                                        Personaje personaje = personajesCRUD.requestById(CodPer);
                                        if(personaje == null){
                                            System.out.println("Error el CodPer del personaje no existe en la BBDD");
                                            break;
                                        }
                                        System.out.println("Introduzca el Rol del Personaje: ");
                                        Rol = sc.nextLine();
                                        try {
                                            participaCRUD.create(new Participa(CodPel, CodPer, Rol));    
                                            System.out.println("Participación creada correctamente");
                                        } catch (SQLException e) {
                                            if(e.getErrorCode() == 1062){
                                                System.out.println("La participación ya existe");
                                            }
                                            else{
                                                System.out.println("No se ha podido crear la participación");
                                                System.out.println("Ocurrió una excepción: "+ e.getMessage());
                                            }
                                        }
                                        break;
                                    case 3:
                                        listarParticipaciones(participaCRUD);
                                        System.out.println("Introduzca el Codigo de la Pelicula: ");
                                        CodPel = Long.parseLong(sc.nextLine());
                                        Pelicula pelicula2 = peliculasCRUD.requestById(CodPel);
                                        if(pelicula2 == null){
                                            System.out.println("Error el CodPel de la pelicula no existe en la BBDD");
                                            break;
                                        }
                                        listarPersonajes(personajesCRUD);
                                        System.out.println("Introduzca el CodPer del Personaje: ");
                                        CodPer = Long.parseLong(sc.nextLine());
                                        Personaje personaje2 = personajesCRUD.requestById(CodPer);
                                        if(personaje2 == null){
                                            System.out.println("Error el CodPer del personaje no existe en la BBDD");
                                            break;
                                        }
                                        System.out.println("Introduzca el Rol del Personaje: ");
                                        Rol = sc.nextLine();
                                        try {
                                            String mensaje = participaCRUD.update(new Participa(CodPel, CodPer, Rol));  
                                            System.out.println(mensaje);
                                        } catch (SQLException e) {
                                            if(e.getErrorCode() == 1062){
                                                System.out.println("La participación ya existe");
                                            } else {
                                                System.out.println("No se ha podido actualizar la participación");
                                                System.out.println("Ocurrió una excepción: " + e.getMessage());
                                            }
                                        }
                                        break;
                                    case 4:
                                        listarParticipaciones(participaCRUD);
                                        System.out.println("Introduzca el Codigo de la Pelicula: ");
                                        CodPel = Long.parseLong(sc.nextLine());
                                        Pelicula pelicula3 = peliculasCRUD.requestById(CodPel);
                                        if(pelicula3 == null){
                                            System.out.println("Error el CodPel de la pelicula no existe en la BBDD");
                                            break;
                                        }
                                        try {
                                            String mensaje = participaCRUD.delete(CodPel); 
                                            System.out.println(mensaje);  
                                        } catch (SQLException e) {
                                            System.out.println("Error: " + e.getMessage());
                                        }
                                        break;
                                    case 5:
                                        listarParticipaciones(participaCRUD);
                                        System.out.println("Introduzca el Codigo de la Pelicula: ");
                                        CodPel = Long.parseLong(sc.nextLine());
                                        Pelicula pelicula4 = peliculasCRUD.requestById(CodPel);
                                        if(pelicula4 == null){
                                            System.out.println("Error el CodPel de la pelicula no existe en la BBDD");
                                            break;
                                        }
                                        try {
                                            Participa participa = participaCRUD.requestByCodPel(CodPel);
                                            if(participa != null){
                                                System.out.println(participa);
                                            }
                                            else{
                                                System.out.println("No existe la participación");
                                            }
                                        } catch (SQLException e) {
                                            System.out.println("No se ha podido encontrar la participación");
                                            System.out.println("Ocurrió una excepción: "+ e.getMessage());
                                        }
                                        break;
                                    case 6:
                                        salir5 = true;
                                        break;  
                                    default:
                                        break;
                                    }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sc.close();
    }
}
