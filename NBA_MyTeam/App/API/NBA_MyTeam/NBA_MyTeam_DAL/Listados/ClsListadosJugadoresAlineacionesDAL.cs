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
    public class ClsListadosJugadoresAlineacionesDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresAlineacionDAL(int idAlineacion)
        /// Propósito: obtener un listado con los jugadores que se encuentran incluidos dentro de una determinada alineación, cuyo id es pasado como parámetro.
        /// Precondiciones: "idAlineación" debe ser mayor que 0.
        /// Entradas: el id de la alineación.
        /// Salidas: el listado de jugadores de la alineación en cuestión (si existe) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresAlineacionDAL(int idAlineacion)
        {

            //Declaraciones e inicializaciones
            List<ClsJugador> listadoJugadores = new List<ClsJugador>();
            clsMyConnection clsConnection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsJugador jugador;

            //Añadimos los parámetros
            command.Parameters.Add("@idAlineacion", System.Data.SqlDbType.Int).Value = idAlineacion;

            //Instanciamos un objeto conexion
            clsConnection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = clsConnection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT J.ID, J.Nombre, J.Edad, J.Nacionalidad, J.Altura, J.Peso, J.Dorsal, J.Posicion, J.ValoracionGeneral, J.Imagen, J.IDEquipo FROM Jugadores AS J INNER JOIN JugadoresAlineaciones AS JA ON J.ID = JA.IDJugador WHERE JA.IDAlineacion = @idAlineacion";
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
                        { jugador.Peso = (byte)lector["Peso"]; }
                        if (lector["Dorsal"] != System.DBNull.Value)
                        { jugador.Dorsal = (byte)lector["Dorsal"]; }
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

    }
}
