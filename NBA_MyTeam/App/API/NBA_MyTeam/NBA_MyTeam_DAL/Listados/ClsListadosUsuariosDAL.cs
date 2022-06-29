using NBA_MyTeam_DAL.Connection;
using NBA_MyTeam_Entities.Basicas;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_DAL.Listados
{
    public class ClsListadosUsuariosDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public ClsUsuario comprobarUsuarioExistenteDAL(String usuario, String contrasenha, bool loginCorreo)
        /// -> "loginCorreo" tomará el valor true (login con correo electrónico) o el valor false (login con nick).
        /// Propósito: comprobar si existe un determinado usuario con tales credenciales en la BBDD.
        /// Precondiciones: tanto "usuario" como "contrasenha" no deben ser cadenas vacías y deben ser distintos de null.
        /// Entradas: el usuario y la contraseña junto con la variable "loginCorreo" que determinará la forma de comprobación.
        /// Salidas: el usuario (si existe en la BBDD) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el usuario asociado al nombre de la función.
        /// </summary>
        /// <param name="usuario"></param>
        /// <param name="contrasenha"></param>
        /// <param name="loginCorreo"></param>
        /// <returns></returns>
        public ClsUsuario comprobarUsuarioExistenteDAL(String usuario, String contrasenha, bool loginCorreo)
        {

            //Declaraciones e inicializaciones
            clsMyConnection connection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsUsuario usuarioRegistrado = null;
            String campoAux;

            //Comprobamos el método de login
            if (loginCorreo) {
                campoAux = "CorreoElectronico";
            }
            else {
                campoAux = "Nick";
            }

            //Añadimos los parámetros
            command.Parameters.Add("@usuario", System.Data.SqlDbType.VarChar).Value = usuario;
            command.Parameters.Add("@contrasenha", System.Data.SqlDbType.VarChar).Value = contrasenha;

            //Instanciamos un objeto conexión
            connection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT Nick, CorreoElectronico, Contrasenha, NombreCompleto, Imagen FROM Usuarios WHERE " + campoAux + " = @usuario AND Contrasenha = @contrasenha";

                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                //Leemos los datos
                if (lector.HasRows)
                {
                    lector.Read();

                    usuarioRegistrado = new ClsUsuario();
                    usuarioRegistrado.Nick = (String) lector["Nick"];
                    usuarioRegistrado.CorreoElectronico = (String) lector["CorreoElectronico"];
                    usuarioRegistrado.Contrasenha = (String) lector["Contrasenha"];
                    if (lector["NombreCompleto"] != System.DBNull.Value)
                    { usuarioRegistrado.NombreCompleto = (String) lector["NombreCompleto"]; }
                    if (lector["Imagen"] != System.DBNull.Value)
                    { usuarioRegistrado.Imagen = (String)lector["Imagen"]; }
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

            return usuarioRegistrado;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public String comprobarIdentificadorUsuarioExistenteDAL(String usuario, bool metodoCorreo) 
        /// Propósito: comprobar si existe un usuario con un determinado nick o correo electrónico en la BBDD.
        /// Precondiciones: "usuario" no debe ser una cadena vacía y debe ser distinto de null.
        /// Entradas: el "usuario" que representará el identificador (o bien el nick, o bien el correo) con el que se desea comprobar la existencia de dicho usuario y, por otro lado,
        /// "metodoCorreo" que indicará si el primer parámetro representa el nick o el correo electrónico del usuario.
        /// Salidas: una cadena de texto (String) que representará el nick del usuario en caso de que exista. En caso contrario, devolverá null.
        /// Postcondiciones: se devuelve el nick del usuario asociado al nombre de la función en caso de que exista.
        /// </summary>
        /// <param name="usuario"></param>
        /// <param name="metodoCorreo"></param>
        /// <returns></returns>
        public String comprobarIdentificadorUsuarioExistenteDAL(String usuario, bool metodoCorreo)
        {

            //Declaraciones e inicializaciones
            clsMyConnection connection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            String nickUsuario = null;
            String campoAux;

            //Comprobamos el método de login
            if (metodoCorreo)
            {
                campoAux = "CorreoElectronico";
            }
            else
            {
                campoAux = "Nick";
            }

            //Añadimos los parámetros
            command.Parameters.Add("@usuario", System.Data.SqlDbType.VarChar).Value = usuario;

            //Instanciamos un objeto conexión
            connection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT Nick FROM Usuarios WHERE " + campoAux + " = @usuario";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                //Leemos los datos
                if (lector.HasRows)
                {
                    lector.Read();

                    nickUsuario = (String) lector["Nick"];
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

            return nickUsuario;

        }

    }
}
