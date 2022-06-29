using NBA_MyTeam_DAL.Connection;
using NBA_MyTeam_Entities.Basicas;
using NBA_MyTeam_Entities.Enums;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_DAL.Listados
{
    public class ClsListadosJugadoresDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresNombreDAL(String nombreJugador)
        /// Propósito: obtener un listado con los jugadores cuyo nombre coincida con el pasado como parámetro.
        /// Precondiciones: "nombreJugador" debe ser distinto de null y no debe estar vacío.
        /// Entradas: el nombre del jugador.
        /// Salidas: el listado de jugadores o null en caso de error.
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// <param name="nombreJugador"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresNombreDAL(String nombreJugador)
        {

            //Declaraciones e inicializaciones
            List<ClsJugador> listadoJugadores = new List<ClsJugador>();
            clsMyConnection clsConnection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsJugador jugador;

            //Añadimos los parámetros
            command.Parameters.Add("@nombre", System.Data.SqlDbType.VarChar).Value = nombreJugador;

            //Instanciamos un objeto conexion
            clsConnection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = clsConnection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT ID, Nombre, Edad, Nacionalidad, Altura, Peso, Dorsal, Posicion, ValoracionGeneral, Imagen, IDEquipo FROM Jugadores WHERE Nombre LIKE @nombre + '%'";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                //Leemos los datos
                if (lector.HasRows)
                {
                    while (lector.Read())
                    {
                        jugador = new ClsJugador();
                        jugador.Id = (int) lector["ID"];
                        jugador.Nombre = (String) lector["Nombre"];
                        if (lector["Edad"] != System.DBNull.Value)
                        { jugador.Edad = (byte) lector["Edad"]; }
                        if (lector["Nacionalidad"] != System.DBNull.Value)
                        { jugador.Nacionalidad = (String) lector["Nacionalidad"]; }
                        if (lector["Altura"] != System.DBNull.Value)
                        { jugador.Altura = (Decimal) lector["Altura"]; }
                        if (lector["Peso"] != System.DBNull.Value)
                        { jugador.Peso = (byte) lector["Peso"]; }
                        if (lector["Dorsal"] != System.DBNull.Value)
                        { jugador.Dorsal = (byte) lector["Dorsal"]; }
                        jugador.Posicion = (EnumPosiciones) Enum.Parse(typeof(EnumPosiciones), (String) lector["Posicion"]);
                        jugador.ValoracionGeneral = (byte) lector["ValoracionGeneral"];
                        jugador.Imagen = (String) lector["Imagen"];
                        jugador.IdEquipo = (int) lector["IDEquipo"];

                        listadoJugadores.Add(jugador);
                    }
                }

                //Cerramos el lector
                lector.Close();
            }
            catch (SqlException e)
            {
                throw e;
            }
            finally
            {
                //Cerramos la conexión
                clsConnection.closeConnection(ref sqlConnection);
            }

            return listadoJugadores;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresEquipoDAL(int idEquipo)
        /// Propósito: obtener un listado con los jugadores cuyo equipo coincida con el pasado como parámetro.
        /// Precondiciones: "idEquipo" debe ser mayor que 0.
        /// Entradas: el equipo al que pertenece el jugador.
        /// Salidas: el listado de jugadores o null en caso de error.
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// /// <param name="idEquipo"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresEquipoDAL(int idEquipo)
        {

            //Declaraciones e inicializaciones
            List<ClsJugador> listadoJugadores = new List<ClsJugador>();
            clsMyConnection clsConnection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsJugador jugador;

            //Añadimos los parámetros
            command.Parameters.Add("@idEquipo", System.Data.SqlDbType.Int).Value = idEquipo;

            //Instanciamos un objeto conexion
            clsConnection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = clsConnection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT ID, Nombre, Edad, Nacionalidad, Altura, Peso, Dorsal, Posicion, ValoracionGeneral, Imagen, IDEquipo FROM Jugadores WHERE IDEquipo = @idEquipo";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                //Leemos los datos
                if (lector.HasRows)
                {
                    while (lector.Read())
                    {
                        jugador = new ClsJugador();
                        jugador.Id = (int) lector["ID"];
                        jugador.Nombre = (String) lector["Nombre"];
                        if (lector["Edad"] != System.DBNull.Value)
                        { jugador.Edad = (byte) lector["Edad"]; }
                        if (lector["Nacionalidad"] != System.DBNull.Value)
                        { jugador.Nacionalidad = (String) lector["Nacionalidad"]; }
                        if (lector["Altura"] != System.DBNull.Value)
                        { jugador.Altura = (Decimal) lector["Altura"]; }
                        if (lector["Peso"] != System.DBNull.Value)
                        { jugador.Peso = (byte) lector["Peso"]; }
                        if (lector["Dorsal"] != System.DBNull.Value)
                        { jugador.Dorsal = (byte) lector["Dorsal"]; }
                        jugador.Posicion = (EnumPosiciones) Enum.Parse(typeof(EnumPosiciones), (String) lector["Posicion"]);
                        jugador.ValoracionGeneral = (byte) lector["ValoracionGeneral"];
                        jugador.Imagen = (String) lector["Imagen"];
                        jugador.IdEquipo = (int) lector["IDEquipo"];

                        listadoJugadores.Add(jugador);
                    }
                }

                //Cerramos el lector
                lector.Close();
            }
            catch (SqlException e)
            {
                throw e;
            }
            finally
            {
                //Cerramos la conexión
                clsConnection.closeConnection(ref sqlConnection);
            }

            return listadoJugadores;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresPosicionDAL(EnumPosiciones posicion)
        /// Propósito: obtener un listado de jugadores que compartan la misma posición, la cual es pasada como parámetro.
        /// Precondiciones: "posicion" debe ser un valor definido en el enum correspondiente.
        /// Entradas: la posición.
        /// Salidas: el listado de jugadores o null en caso de error.
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// <param name="posicion"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresPosicionDAL(EnumPosiciones posicion)
        {

            //Declaraciones e inicializaciones
            List<ClsJugador> listadoJugadores = new List<ClsJugador>();
            clsMyConnection clsConnection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsJugador jugador;

            //Añadimos los parámetros
            command.Parameters.Add("@posicion", System.Data.SqlDbType.VarChar).Value = Enum.GetName(posicion.GetType(), posicion);

            //Instanciamos un objeto conexion
            clsConnection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = clsConnection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT ID, Nombre, Edad, Nacionalidad, Altura, Peso, Dorsal, Posicion, ValoracionGeneral, Imagen, IDEquipo FROM Jugadores WHERE Posicion = @posicion";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                //Leemos los datos
                if (lector.HasRows)
                {
                    while (lector.Read())
                    {
                        jugador = new ClsJugador();
                        jugador.Id = (int) lector["ID"];
                        jugador.Nombre = (String) lector["Nombre"];
                        if (lector["Edad"] != System.DBNull.Value)
                        { jugador.Edad = (byte) lector["Edad"]; }
                        if (lector["Nacionalidad"] != System.DBNull.Value)
                        { jugador.Nacionalidad = (String) lector["Nacionalidad"]; }
                        if (lector["Altura"] != System.DBNull.Value)
                        { jugador.Altura = (Decimal) lector["Altura"]; }
                        if (lector["Peso"] != System.DBNull.Value)
                        { jugador.Peso = (byte) lector["Peso"]; }
                        if (lector["Dorsal"] != System.DBNull.Value)
                        { jugador.Dorsal = (byte) lector["Dorsal"]; }
                        jugador.Posicion = (EnumPosiciones) Enum.Parse(typeof(EnumPosiciones), (String) lector["Posicion"]);
                        jugador.ValoracionGeneral = (byte) lector["ValoracionGeneral"];
                        jugador.Imagen = (String) lector["Imagen"];
                        jugador.IdEquipo = (int) lector["IDEquipo"];

                        listadoJugadores.Add(jugador);
                    }
                }

                //Cerramos el lector
                lector.Close();
            }
            catch (SqlException e)
            {
                throw e;
            }
            finally
            {
                //Cerramos la conexión
                clsConnection.closeConnection(ref sqlConnection);
            }

            return listadoJugadores;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresValoracionDAL(int valoracionMinima, int valoracionMaxima)
        /// Propósito: obtener un listado con los jugadores cuya valoración general se encuentre en el rango definido por los parámetros.
        /// Precondiciones: tanto "valoracionMinima" como "valoracionMaxima" deben ser valores comprendidos entre 70 y 99 (ambos incluidos).
        /// Además, "valoracionMinima" debe ser un valor menor que "valoracionMaxima".
        /// Entradas: la valoración mínima y la valoración máxima que comprenden el margen de valoración de los jugadores.
        /// Salidas: el listado de jugadores o null en caso de error.
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// /// <param name="valoracionMinima"></param>
        /// /// <param name="valoracionMaxima"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresValoracionDAL(int valoracionMinima, int valoracionMaxima)
        {

            //Declaraciones e inicializaciones
            List<ClsJugador> listadoJugadores = new List<ClsJugador>();
            clsMyConnection clsConnection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsJugador jugador;

            //Añadimos los parámetros
            command.Parameters.Add("@valoracionMinima", System.Data.SqlDbType.Int).Value = valoracionMinima;
            command.Parameters.Add("@valoracionMaxima", System.Data.SqlDbType.Int).Value = valoracionMaxima;

            //Instanciamos un objeto conexion
            clsConnection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = clsConnection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT ID, Nombre, Edad, Nacionalidad, Altura, Peso, Dorsal, Posicion, ValoracionGeneral, Imagen, IDEquipo FROM Jugadores WHERE ValoracionGeneral BETWEEN @valoracionMinima AND @valoracionMaxima";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                //Leemos los datos
                if (lector.HasRows)
                {
                    while (lector.Read())
                    {
                        jugador = new ClsJugador();
                        jugador.Id = (int)lector["ID"];
                        jugador.Nombre = (String)lector["Nombre"];
                        if (lector["Edad"] != System.DBNull.Value)
                        { jugador.Edad = (byte)lector["Edad"]; }
                        if (lector["Nacionalidad"] != System.DBNull.Value)
                        { jugador.Nacionalidad = (String)lector["Nacionalidad"]; }
                        if (lector["Altura"] != System.DBNull.Value)
                        { jugador.Altura = (Decimal)lector["Altura"]; }
                        if (lector["Peso"] != System.DBNull.Value)
                        { jugador.Peso = (byte)lector["Peso"]; }
                        if (lector["Dorsal"] != System.DBNull.Value)
                        { jugador.Dorsal = (byte)lector["Dorsal"]; }
                        jugador.Posicion = (EnumPosiciones)Enum.Parse(typeof(EnumPosiciones), (String)lector["Posicion"]);
                        jugador.ValoracionGeneral = (byte)lector["ValoracionGeneral"];
                        jugador.Imagen = (String)lector["Imagen"];
                        jugador.IdEquipo = (int)lector["IDEquipo"];

                        listadoJugadores.Add(jugador);
                    }
                }

                //Cerramos el lector
                lector.Close();
            }
            catch (SqlException e)
            {
                throw e;
            }
            finally
            {
                //Cerramos la conexión
                clsConnection.closeConnection(ref sqlConnection);
            }

            return listadoJugadores;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public ClsJugador getJugadorDAL(int idJugador)
        /// Propósito: obtener un determinado jugador (con todos sus datos) a partir de su id.
        /// Precondiciones: "idJugador" debe ser mayor que 0.
        /// Entradas: el id del jugador.
        /// Salidas: el jugador (si existe en la BBDD) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el jugador asociado al nombre de la función.
        /// </summary>
        /// <param name="idJugador"></param>
        /// <returns></returns>
        public ClsJugador getJugadorDAL(int idJugador)
        {

            //Declaraciones e inicializaciones
            clsMyConnection connection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsJugador jugador = null;

            //Añadimos los parámetros
            command.Parameters.Add("@id", System.Data.SqlDbType.Int).Value = idJugador;

            //Instanciamos un objeto conexión
            connection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT ID, Nombre, Edad, Nacionalidad, Altura, Peso, Dorsal, Posicion, ValoracionGeneral, Imagen, IDEquipo FROM Jugadores WHERE ID = @id";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                if (lector.HasRows)
                {
                    lector.Read();

                    jugador = new ClsJugador();
                    jugador.Id = (int) lector["ID"];
                    jugador.Nombre = (String) lector["Nombre"];
                    if (lector["Edad"] != System.DBNull.Value)
                    { jugador.Edad = (byte) lector["Edad"]; }
                    if (lector["Nacionalidad"] != System.DBNull.Value)
                    { jugador.Nacionalidad = (String) lector["Nacionalidad"]; }
                    if (lector["Altura"] != System.DBNull.Value)
                    { jugador.Altura = (Decimal) lector["Altura"]; }
                    if (lector["Peso"] != System.DBNull.Value)
                    { jugador.Peso = (byte) lector["Peso"]; }
                    if (lector["Dorsal"] != System.DBNull.Value)
                    { jugador.Dorsal = (byte) lector["Dorsal"]; }
                    jugador.Posicion = (EnumPosiciones) Enum.Parse(typeof(EnumPosiciones), (String) lector["Posicion"]);
                    jugador.ValoracionGeneral = (byte) lector["ValoracionGeneral"];
                    jugador.Imagen = (String) lector["Imagen"];
                    jugador.IdEquipo = (int) lector["IDEquipo"];
                }

                //Cerramos el elector
                lector.Close();
            }
            catch (SqlException e)
            {
                throw e;
            }
            finally
            {
                //Cerramos la conexión
                connection.closeConnection(ref sqlConnection);
            }

            return jugador;

        }

    }
}
