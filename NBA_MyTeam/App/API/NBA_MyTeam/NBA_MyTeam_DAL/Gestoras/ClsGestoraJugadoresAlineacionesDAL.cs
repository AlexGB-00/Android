using NBA_MyTeam_DAL.Connection;
using NBA_MyTeam_Entities.Intermedias;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_DAL.Gestoras
{
    public class ClsGestoraJugadoresAlineacionesDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int insertJugadorAlineacionDAL(ClsJugadorAlineacion nuevoJugadorAlineacion)
        /// Propósito: insertar un nuevo jugador en una determinada alineación, a partir de los datos del objeto "nuevoJugadorAlineacion" pasado como parámetro.
        /// Precondiciones: "nuevoJugadorAlineacion" debe ser distinto de null.
        /// Entradas: el objeto nuevoJugadorAlienacion que contiene el jugador a insertar junto con la alineación correspondiente.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nuevoJugadorAlineacion"></param>
        /// <returns></returns>
        public int insertJugadorAlineacionDAL(ClsJugadorAlineacion nuevoJugadorAlineacion)
        {

            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@idJugador", System.Data.SqlDbType.Int).Value = nuevoJugadorAlineacion.IdJugador;
            command.Parameters.Add("@idAlineacion", System.Data.SqlDbType.Int).Value = nuevoJugadorAlineacion.IdAlineacion;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "INSERT INTO JugadoresAlineaciones (IDJugador, IDAlineacion) VALUES (@idJugador, @idAlineacion)";
                command.Connection = sqlConnection;
                filasAfectadas = command.ExecuteNonQuery();
            }
            catch (SqlException e)
            {
                throw e;
            }
            finally
            {
                connection.closeConnection(ref sqlConnection);
            }

            return filasAfectadas;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int updateJugadorAlineacionDAL(ClsJugadorAlineacion jugadorAlineacion, idJugadorSustituto)
        /// Propósito: reemplazar un determinado jugador dentro de una determinada alineación (lo cual vendrá dado por el objeto "jugadorAlineacion") por otro, el cual
        /// vendrá indicado mediante el parámetro "idJugadorSustituto".
        /// Precondiciones: "jugadorAlineacion" debe ser distinto de null y "idJugadorSustituto" debe ser mayor que 0.
        /// Entradas: el objeto "jugadorAlineacion" que contiene el jugador a reemplazar junto con la alineación correspondiente y el id del jugador sustituto.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="jugadorAlineacion"></param>
        /// <param name="idJugadorSustituto"></param>
        /// <returns></returns>
        public int updateJugadorAlineacionDAL(ClsJugadorAlineacion jugadorAlineacion, int idJugadorSustituto)
        {

            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@idJugador", System.Data.SqlDbType.Int).Value = jugadorAlineacion.IdJugador;
            command.Parameters.Add("@idAlineacion", System.Data.SqlDbType.Int).Value = jugadorAlineacion.IdAlineacion;
            command.Parameters.Add("@idJugadorSustituto", System.Data.SqlDbType.Int).Value = idJugadorSustituto;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "UPDATE JugadoresAlineaciones SET IDJugador = @idJugadorSustituto " +
                    "WHERE IDJugador = @idJugador AND IDAlineacion = @idAlineacion";
                command.Connection = sqlConnection;
                filasAfectadas = command.ExecuteNonQuery();
            }
            catch (SqlException e)
            {
                throw e;
            }
            finally
            {
                connection.closeConnection(ref sqlConnection);
            }

            return filasAfectadas;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int deleteJugadorAlineacionDAL(int idJugador, int idAlineacion)
        /// Propósito: eliminar un determinado jugador de una determinada alineación.
        /// Precondiciones: tanto "idJugador" como "idAlineacion" deben ser mayores que 0. 
        /// Entradas: el id del jugador y el id de la alineación.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="idJugador"></param>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public int deleteJugadorAlineacionDAL(int idJugador, int idAlineacion)
        {

            //Declaraciones
            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@idJugador", System.Data.SqlDbType.Int).Value = idJugador;
            command.Parameters.Add("@idAlineacion", System.Data.SqlDbType.Int).Value = idAlineacion;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "DELETE FROM JugadoresAlineaciones WHERE IDJugador = @idJugador AND IDAlineacion = @idAlineacion";
                command.Connection = sqlConnection;
                filasAfectadas = command.ExecuteNonQuery();
            }
            catch (SqlException e)
            {
                throw e;
            }
            finally
            {
                connection.closeConnection(ref sqlConnection);
            }

            return filasAfectadas;

        }

    }
}
