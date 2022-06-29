using NBA_MyTeam_DAL.Connection;
using NBA_MyTeam_Entities.Basicas;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_DAL.Gestoras
{
    public class ClsGestoraAlineacionesDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int insertAlineacionDAL(ClsAlineacion nuevaAlineacion)
        /// Propósito: insertar una nueva alineación en la BBDD a partir de los datos del objeto "nuevaAlineacion" pasado como parámetro.
        /// Precondiciones: "nuevaAlineacion" debe ser distinta de null.
        /// Entradas: la alineación a insertar.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nuevaAlineacion"></param>
        /// <returns></returns>
        public int insertAlineacionDAL(ClsAlineacion nuevaAlineacion)
        {

            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();
            
            //Añadimos los parámetros
            command.Parameters.Add("@nombre", System.Data.SqlDbType.VarChar).Value = nuevaAlineacion.Nombre;
            command.Parameters.Add("@sistema", System.Data.SqlDbType.VarChar).Value = nuevaAlineacion.Sistema;
            command.Parameters.Add("@valoracionMedia", System.Data.SqlDbType.TinyInt).Value = nuevaAlineacion.ValoracionMedia;
            command.Parameters.Add("@fechaCreacion", System.Data.SqlDbType.Date).Value = nuevaAlineacion.FechaCreacion;
            command.Parameters.Add("@finalizada", System.Data.SqlDbType.Bit).Value = nuevaAlineacion.Finalizada;
            command.Parameters.Add("@nickUsuario", System.Data.SqlDbType.VarChar).Value = nuevaAlineacion.NickUsuario;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "INSERT INTO Alineaciones (Nombre, Sistema, ValoracionMedia, FechaCreacion, Finalizada, NickUsuario) VALUES " +
                    "(@nombre, @sistema, @valoracionMedia, @fechaCreacion, @finalizada, @nickUsuario)";
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
        /// Prototipo: public int updateAlineacionDAL(ClsAlineacion alineacion)
        /// Propósito: actualizar una determinada alineación de la BBDD, sustituyendo sus actuales datos por los del objeto "alineacion" pasado como parámetro.
        /// Precondiciones: "alineacion" debe ser distinto de null.
        /// Entradas: la alineación con los datos a actualizar.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="alineacion"></param>
        /// <returns></returns>
        public int updateAlineacionDAL(ClsAlineacion alineacion)
        {

            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@id", System.Data.SqlDbType.Int).Value = alineacion.Id;
            command.Parameters.Add("@nombre", System.Data.SqlDbType.VarChar).Value = alineacion.Nombre;
            command.Parameters.Add("@sistema", System.Data.SqlDbType.VarChar).Value = alineacion.Sistema;
            command.Parameters.Add("@valoracionMedia", System.Data.SqlDbType.TinyInt).Value = alineacion.ValoracionMedia;
            command.Parameters.Add("@fechaCreacion", System.Data.SqlDbType.Date).Value = alineacion.FechaCreacion;
            command.Parameters.Add("@finalizada", System.Data.SqlDbType.Bit).Value = alineacion.Finalizada;
            command.Parameters.Add("@nickUsuario", System.Data.SqlDbType.VarChar).Value = alineacion.NickUsuario;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "UPDATE Alineaciones SET Nombre = @nombre, Sistema = @sistema, ValoracionMedia = @valoracionMedia," +
                    " FechaCreacion = @fechaCreacion, Finalizada = @finalizada, NickUsuario = @nickUsuario WHERE ID = @id";
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
        /// Prototipo: public int deleteAlineacionDAL(int idAlineacion)
        /// Propósito: eliminar una determinada alineación de la BBDD, cuyo id es pasado como parámetro.
        /// Precondiciones: "idAlineacion" debe ser mayor que 0.
        /// Entradas: el id de la alineacion.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public int deleteAlineacionDAL(int idAlineacion)
        {

            //Declaraciones
            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@id", System.Data.SqlDbType.Int).Value = idAlineacion;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "DELETE FROM Alineaciones WHERE ID = @id";
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
