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
    public class ClsGestoraValoracionesDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int insertValoracionDAL(String nickUsuario, int idAlineacion)
        /// Propósito: insertar una nuevo registro en la tabla "Valoraciones", a partir de los datos pasados como parámetro.
        /// Esta operación representa la acción de compartir una alineación con otro usuario, relacionando de esa manera dicha alineación con el nuevo usuario.
        /// Precondiciones: "nickUsuario" debe ser distinto de null y no debe estar vacío y "idAlineacion" debe ser mayor que 0.
        /// Entradas: el nick del usuario y el id de la alineación.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public int insertValoracionDAL(String nickUsuario, int idAlineacion)
        {

            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@nickUsuario", System.Data.SqlDbType.VarChar).Value = nickUsuario;
            command.Parameters.Add("@idAlineacion", System.Data.SqlDbType.Int).Value = idAlineacion;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "INSERT INTO Valoraciones (NickUsuario, IDAlineacion) VALUES (@nickUsuario, @idAlineacion)";
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
        /// Prototipo: public int updateValoracionDAL(ClsValoracion valoracion)
        /// Propósito: actualizar una determinada valoración, cuyos nuevos datos vendrán dados por el objeto "valoracion" pasado como parámetro.
        /// Precondiciones: "valoracion" debe ser distinto de null.
        /// Entradas: el objeto "valoracion" que contiene la valoración (rating y descripción), el usuario y la alineacion a la que se asigna.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="valoracion"></param>
        /// <returns></returns>
        public int updateValoracionDAL(ClsValoracion valoracion)
        {

            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@nickUsuario", System.Data.SqlDbType.VarChar).Value = valoracion.NickUsuario;
            command.Parameters.Add("@idAlineacion", System.Data.SqlDbType.Int).Value = valoracion.IdAlineacion;
            command.Parameters.Add("@rating", System.Data.SqlDbType.TinyInt).Value = valoracion.Rating;
            command.Parameters.Add("@descripcion", System.Data.SqlDbType.VarChar).Value = valoracion.Descripcion;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "UPDATE Valoraciones SET Rating = @rating, Descripcion = @descripcion" +
                    " WHERE NickUsuario = @nickUsuario AND IDAlineacion = @idAlineacion";
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
        /// Prototipo: public int deleteValoracionDAL(String nickUsuario, int idAlineacion)
        /// Propósito: eliminar una determinada valoración de una determinada alineación.
        /// Precondiciones: "nickUsuario" debe ser distinto de null y no debe estar vacío y "idAlineacion" debe ser mayor que 0.
        /// Entradas: el nick del usuario y el id de la alineación.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public int deleteValoracionDAL(String nickUsuario, int idAlineacion)
        {

            //Declaraciones
            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@nickUsuario", System.Data.SqlDbType.VarChar).Value = nickUsuario;
            command.Parameters.Add("@idAlineacion", System.Data.SqlDbType.Int).Value = idAlineacion;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "DELETE FROM Valoraciones WHERE NickUsuario = @nickUsuario AND IDAlineacion = @idAlineacion";
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
