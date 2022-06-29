using NBA_MyTeam_DAL.Connection;
using NBA_MyTeam_Entities.Intermedias;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_DAL.Listados
{
    public class ClsListadosValoracionesDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsValoracion> getListadoValoracionesAlineacionDAL(int idAlineacion)
        /// Propósito: obtener un listado con las valoraciones de una determinada alineación, cuyo id es pasado como parámetro.
        /// Precondiciones: "idAlineación" debe ser mayor que 0.
        /// Entradas: el id de la alineación.
        /// Salidas: el listado de valoraciones de la alineación en cuestión (si existe) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el listado de valoraciones asociado al nombre de la función.
        /// </summary>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public List<ClsValoracion> getListadoValoracionesAlineacionDAL(int idAlineacion)
        {

            //Declaraciones e inicializaciones
            List<ClsValoracion> listadoValoraciones = new List<ClsValoracion>();
            clsMyConnection clsConnection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsValoracion valoracion;

            //Añadimos los parámetros
            command.Parameters.Add("@idAlineacion", System.Data.SqlDbType.Int).Value = idAlineacion;

            //Instanciamos un objeto conexion
            clsConnection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = clsConnection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT NickUsuario, IDAlineacion, Rating, Descripcion FROM Valoraciones WHERE IDAlineacion = @idAlineacion";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                //Leemos los datos
                if (lector.HasRows)
                {
                    while (lector.Read())
                    {
                        valoracion = new ClsValoracion();
                        valoracion.NickUsuario = (String) lector["NickUsuario"];
                        valoracion.IdAlineacion = (int) lector["IDAlineacion"];
                        valoracion.Rating = (byte) lector["Rating"];
                        if (lector["Descripcion"] != System.DBNull.Value)
                        { valoracion.Descripcion = (String)lector["Descripcion"]; }
                      
                        listadoValoraciones.Add(valoracion);
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

            return listadoValoraciones;

        }

    }
}
