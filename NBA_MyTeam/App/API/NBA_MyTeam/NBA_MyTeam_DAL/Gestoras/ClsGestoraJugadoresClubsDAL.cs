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
    public class ClsGestoraJugadoresClubsDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int insertJugadorClubDAL(ClsJugadorClub nuevoJugadorClub)
        /// Propósito: añadir un nuevo jugador a la plantilla de un determinado usuario, a partir de los datos del objeto "nuevoJugadorClub" pasado como parámetro.
        /// Precondiciones: "nuevoJugadorClub" debe ser distinto de null.
        /// Entradas: el objeto nuevoJugadorClub.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nuevoJugadorClub"></param>
        /// <returns></returns>
        public int insertJugadorClubDAL(ClsJugadorClub nuevoJugadorClub)
        {

            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@idJugador", System.Data.SqlDbType.Int).Value = nuevoJugadorClub.IdJugador;
            command.Parameters.Add("@nickUsuario", System.Data.SqlDbType.VarChar).Value = nuevoJugadorClub.NickUsuario;
            command.Parameters.Add("@fechaIncorporacion", System.Data.SqlDbType.DateTime).Value = nuevoJugadorClub.FechaIncorporacion;
            
            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "INSERT INTO JugadoresClubs (IDJugador, NickUsuario, FechaIncorporacion) VALUES (@idJugador, @nickUsuario, @fechaIncorporacion)";
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
        /// Prototipo: public int deleteJugadorClubDAL(int idJugador, String nickUsuario)
        /// Propósito: eliminar un determinado jugador de la plantilla de un determinado usuario.
        /// Precondiciones: "idJugador" debe ser mayor que 0 y "nickUsuario" debe ser distinto de null y no debe estar vacío.
        /// Entradas: el id del jugador y el nick del usuario.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="idJugador"></param>
        /// <param name="nickUsuario"></param>
        /// <returns></returns>
        public int deleteJugadorClubDAL(int idJugador, String nickUsuario)
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
            command.Parameters.Add("@nickUsuario", System.Data.SqlDbType.VarChar).Value = nickUsuario;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "DELETE FROM JugadoresCLubs WHERE IDJugador = @idJugador AND NickUsuario = @nickUsuario";
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
