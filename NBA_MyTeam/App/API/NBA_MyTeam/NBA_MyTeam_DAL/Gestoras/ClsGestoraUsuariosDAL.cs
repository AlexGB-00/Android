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
    public class ClsGestoraUsuariosDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int insertUsuarioDAL(ClsUsuario nuevoUsuario)
        /// Propósito: insertar un nuevo usuario en la BBDD con los datos del objeto "nuevoUsuario" pasado como parámetro.
        /// Precondiciones: "nuevoUsuario" debe ser distinto de null.
        /// Entradas: el usuario a insertar.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nuevoUsuario"></param>
        /// <returns></returns>
        public int insertUsuarioDAL(ClsUsuario nuevoUsuario)
        {

            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@nick", System.Data.SqlDbType.VarChar).Value = nuevoUsuario.Nick;
            command.Parameters.Add("@correo", System.Data.SqlDbType.VarChar).Value = nuevoUsuario.CorreoElectronico;
            command.Parameters.Add("@contrasenha", System.Data.SqlDbType.VarChar).Value = nuevoUsuario.Contrasenha;
            command.Parameters.Add("@nombre", System.Data.SqlDbType.VarChar).Value = nuevoUsuario.NombreCompleto;
            command.Parameters.Add("@imagen", System.Data.SqlDbType.VarChar).Value = nuevoUsuario.Imagen;
            
            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "INSERT INTO Usuarios (Nick, CorreoElectronico, Contrasenha, NombreCompleto, Imagen) VALUES " +
                    "(@nick, @correo, @contrasenha, @nombre, @imagen)";
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
        /// Prototipo: public int updateUsuarioDAL(ClsUsuario usuario)
        /// Propósito: actualizar un determinado usuario de la BBDD, sustituyendo sus actuales datos por los del objeto "usuario" pasado como parámetro.
        /// Precondiciones: "usuario" debe ser distinto de null.
        /// Entradas: el usuario con los datos a actualizar.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="usuario"></param>
        /// <returns></returns>
        public int updateUsuarioDAL(ClsUsuario usuario)
        {

            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@nick", System.Data.SqlDbType.VarChar).Value = usuario.Nick;
            command.Parameters.Add("@correo", System.Data.SqlDbType.VarChar).Value = usuario.CorreoElectronico;
            command.Parameters.Add("@contrasenha", System.Data.SqlDbType.VarChar).Value = usuario.Contrasenha;
            command.Parameters.Add("@nombre", System.Data.SqlDbType.VarChar).Value = usuario.NombreCompleto;
            command.Parameters.Add("@imagen", System.Data.SqlDbType.VarChar).Value = usuario.Imagen;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "UPDATE Usuarios SET Nick = @nick, CorreoElectronico = @correo, Contrasenha = @contrasenha," +
                    " NombreCompleto = @nombre, Imagen = @imagen WHERE Nick = @nick";
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
        /// Prototipo: public int deleteUsuarioDAL(String nickUsuario)
        /// Propósito: eliminar un determinado usuario de la BBDD, cuyo nick es pasado como parámetro.
        /// Precondiciones: "nickUsuario" no debe ser una cadena vacía y debe distinto de null.
        /// Entradas: el nick del usuario.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <returns></returns>
        public int deleteUsuarioDAL(String nickUsuario)
        {

            //Declaraciones
            int filasAfectadas = 0;
            SqlConnection sqlConnection = new SqlConnection();
            clsMyConnection connection;
            SqlCommand command = new SqlCommand();

            //Instanciamos un objeto conexion
            connection = new clsMyConnection();

            //Añadimos los parámetros
            command.Parameters.Add("@nick", System.Data.SqlDbType.VarChar).Value = nickUsuario;

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "DELETE FROM Usuarios WHERE Nick = @nick";
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
