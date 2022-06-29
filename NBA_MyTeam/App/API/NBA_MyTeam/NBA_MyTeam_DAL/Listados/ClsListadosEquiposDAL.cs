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
    public class ClsListadosEquiposDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public ClsEquipo getEquipoDAL(int idEquipo)
        /// Propósito: obtener un determinado equipo (con todos sus datos) a partir del id pasado como parámetro. 
        /// Precondiciones: "idEquipo" debe ser mayor que 0.
        /// Entradas: el id del equipo.
        /// Salidas: el equipo (si existe en la BBDD) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el equipo asociado al nombre de la función.
        /// </summary>
        /// <param name="idEquipo"></param>
        /// <returns></returns>
        public ClsEquipo getEquipoDAL(int idEquipo)
        {

            //Declaraciones e inicializaciones
            clsMyConnection connection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsEquipo equipo = null;

            //Añadimos los parámetros
            command.Parameters.Add("@id", System.Data.SqlDbType.Int).Value = idEquipo;

            //Instanciamos un objeto conexión
            connection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = connection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT ID, Nombre, Conferencia, Ciudad, AnhoFundacion, NumeroTitulos, Estadio, NombreEntrenador, Imagen FROM Equipos WHERE ID = @id";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                if (lector.HasRows)
                {
                    lector.Read();

                    equipo = new ClsEquipo();
                    equipo.Id = (int) lector["ID"];
                    equipo.Nombre = (String) lector["Nombre"];
                    if (lector["Conferencia"] != System.DBNull.Value)
                    { equipo.Conferencia = (EnumConferencias) Enum.Parse(typeof(EnumConferencias), (String) lector["Conferencia"]); }
                    if (lector["Ciudad"] != System.DBNull.Value)
                    { equipo.Ciudad = (String) lector["Ciudad"]; }
                    if (lector["AnhoFundacion"] != System.DBNull.Value)
                    { equipo.AnhoFundacion = (short) lector["AnhoFundacion"]; }
                    if (lector["NumeroTitulos"] != System.DBNull.Value)
                    { equipo.NumeroTitulos = (byte) lector["NumeroTitulos"]; }
                    if (lector["Estadio"] != System.DBNull.Value)
                    { equipo.Estadio = (String) lector["Estadio"]; }
                    if (lector["NombreEntrenador"] != System.DBNull.Value)
                    { equipo.NombreEntrenador = (String) lector["NombreEntrenador"]; }
                    equipo.Imagen = (String) lector["Imagen"];
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

            return equipo;

        }

    }
}
