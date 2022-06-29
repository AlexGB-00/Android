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
    public class ClsListadosAlineacionesDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsAlineacion> getListadoAlineacionesUsuarioDAL(String nickUsuario)
        /// Propósito: obtener un listado de alineaciones pertenecientes a un determinado usuario, cuyo nick será pasado como parámetro.
        /// Precondiciones: "nick" debe ser distinto de null y no debe estar vacío.
        /// Entradas: el nick del usuario.
        /// Salidas: el listado de alineaciones del usuario en cuestión (si existe) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el listado de alineaciones asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <returns></returns>
        public List<ClsAlineacion> getListadoAlineacionesUsuarioDAL(String nickUsuario) {

            //Declaraciones e inicializaciones
            List<ClsAlineacion> listadoAlineaciones = new List<ClsAlineacion>();
            clsMyConnection clsConnection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsAlineacion alineacion;

            //Añadimos los parámetros
            command.Parameters.Add("@nick", System.Data.SqlDbType.VarChar).Value = nickUsuario;

            //Instanciamos un objeto conexion
            clsConnection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = clsConnection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT ID, Nombre, Sistema, ValoracionMedia, FechaCreacion, Finalizada, NickUsuario FROM Alineaciones " +
                    "WHERE NickUsuario = @nick OR ID IN (SELECT IDAlineacion FROM Valoraciones WHERE NickUsuario = @nick)";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                //Leemos los datos
                if (lector.HasRows)
                {
                    while (lector.Read())
                    {
                        alineacion = new ClsAlineacion();
                        alineacion.Id = (int) lector["ID"];
                        alineacion.Nombre = (String) lector["Nombre"];
                        alineacion.Sistema = (EnumSistemas) Enum.Parse(typeof(EnumSistemas), (String) lector["Sistema"]);
                        if (lector["ValoracionMedia"] != System.DBNull.Value)
                        { alineacion.ValoracionMedia = (byte) lector["ValoracionMedia"]; }
                        if (lector["FechaCreacion"] != System.DBNull.Value)
                        { alineacion.FechaCreacion = (DateTime) lector["FechaCreacion"]; }
                        if (lector["Finalizada"] != System.DBNull.Value)
                        { alineacion.Finalizada = (bool) lector["Finalizada"]; }
                        alineacion.NickUsuario = (String) lector["NickUsuario"];

                        listadoAlineaciones.Add(alineacion);
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

            return listadoAlineaciones;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public ClsAlineacion getAlineacionDAL(int idAlineacion)
        /// Propósito: obtener una determinada alineación (con todos sus datos) a partir del id pasado como parámetro. 
        /// Precondiciones: "idAlineacion" debe ser mayor que 0.
        /// Entradas: el id de la alineación.
        /// Salidas: la alineación (si existe en la BBDD) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve la alineación asociada al nombre de la función.
        /// </summary>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public ClsAlineacion getAlineacionDAL(int idAlineacion)
        {

            //Declaraciones e inicializaciones
            clsMyConnection connection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsAlineacion alineacion = null;

            //Añadimos los parámetros
            command.Parameters.Add("@id", System.Data.SqlDbType.Int).Value = idAlineacion;

            //Instanciamos un objeto conexión
            connection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT ID, Nombre, Sistema, ValoracionMedia, FechaCreacion, Finalizada, NickUsuario FROM Alineaciones WHERE ID = @id";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                if (lector.HasRows)
                {
                    lector.Read();

                    alineacion = new ClsAlineacion();
                    alineacion.Id = (int)lector["ID"];
                    alineacion.Nombre = (String)lector["Nombre"];
                    alineacion.Sistema = (EnumSistemas)Enum.Parse(typeof(EnumSistemas), (String)lector["Sistema"]);
                    if (lector["ValoracionMedia"] != System.DBNull.Value)
                    { alineacion.ValoracionMedia = (byte)lector["ValoracionMedia"]; }
                    if (lector["FechaCreacion"] != System.DBNull.Value)
                    { alineacion.FechaCreacion = (DateTime)lector["FechaCreacion"]; }
                    if (lector["Finalizada"] != System.DBNull.Value)
                    { alineacion.Finalizada = (bool)lector["Finalizada"]; }
                    alineacion.NickUsuario = (String)lector["NickUsuario"];
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

            return alineacion;

        }

    }
}
