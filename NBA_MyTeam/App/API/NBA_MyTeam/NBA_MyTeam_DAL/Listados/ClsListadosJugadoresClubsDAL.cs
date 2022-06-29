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
    public class ClsListadosJugadoresClubsDAL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresUsuarioDAL(String nickUsuario)
        /// Propósito: obtener un listado con los jugadores que se encuentran dentro del "club" de un determinado usuario, cuyo nick es pasado como parámetro.
        /// Precondiciones: "nickUsuario" no debe ser una cadena vacía y debe ser distinto de null.
        /// Entradas: el nick del usuario.
        /// Salidas: el listado de jugadores del usuario en cuestión (si existe) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresUsuarioDAL(String nickUsuario)
        {

            //Declaraciones e inicializaciones
            List<ClsJugador> listadoJugadores = new List<ClsJugador>();
            clsMyConnection clsConnection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsJugador jugador;

            //Añadimos los parámetros
            command.Parameters.Add("@nick", System.Data.SqlDbType.VarChar).Value = nickUsuario;

            //Instanciamos un objeto conexion
            clsConnection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = clsConnection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT J.ID, J.Nombre, J.Edad, J.Nacionalidad, J.Altura, J.Peso, J.Dorsal, J.Posicion, J.ValoracionGeneral, J.Imagen, J.IDEquipo FROM Jugadores AS J INNER JOIN JugadoresClubs AS JC ON J.ID = JC.IDJugador WHERE JC.NickUsuario = @nick";
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
                        { jugador.Peso = (byte) lector["Peso"]; }
                        if (lector["Dorsal"] != System.DBNull.Value)
                        { jugador.Dorsal = (byte) lector["Dorsal"]; }
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


        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresUsuarioPorPosicionDAL(String nickUsuario, EnumPosiciones posicion)
        /// Propósito: obtener un listado con los jugadores que se encuentran dentro del "club" de un determinado usuario, cuyo nick es pasado como parámetro,
        /// y que además su posición coincida con la pasada como parámetro.
        /// Precondiciones: "nickUsuario" no debe ser una cadena vacía y debe ser distinto de null. Además, "posicion" debe ser un valor definido
        /// en el enum correspondiente.
        /// Entradas: el nick del usuario y la pocición.
        /// Salidas: el listado de jugadores del usuario en cuestión que pertenezcan a tal posición (si existen) o null (en caso de que no existan).
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <param name="posicion"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresUsuarioPorPosicionDAL(String nickUsuario, EnumPosiciones posicion)
        {

            //Declaraciones e inicializaciones
            List<ClsJugador> listadoJugadores = new List<ClsJugador>();
            clsMyConnection clsConnection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;
            ClsJugador jugador;

            //Añadimos los parámetros
            command.Parameters.Add("@nick", System.Data.SqlDbType.VarChar).Value = nickUsuario;
            command.Parameters.Add("@posicion", System.Data.SqlDbType.VarChar).Value = Enum.GetName(posicion.GetType(), posicion);

            //Instanciamos un objeto conexion
            clsConnection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = clsConnection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT J.ID, J.Nombre, J.Edad, J.Nacionalidad, J.Altura, J.Peso, J.Dorsal, J.Posicion, J.ValoracionGeneral, J.Imagen, J.IDEquipo FROM Jugadores AS J INNER JOIN JugadoresClubs AS JC ON J.ID = JC.IDJugador WHERE JC.NickUsuario = @nick AND J.Posicion = @posicion";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                //Leemos los datos
                if (lector.HasRows)
                {
                    while (lector.Read())
                    {
                        jugador = new ClsJugador();
                        jugador.Id = (int)lector["ID"];
                        jugador.Nombre = (String)lector["Nombre"];
                        if (lector["Edad"] != System.DBNull.Value)
                        { jugador.Edad = (byte)lector["Edad"]; }
                        if (lector["Nacionalidad"] != System.DBNull.Value)
                        { jugador.Nacionalidad = (String)lector["Nacionalidad"]; }
                        if (lector["Altura"] != System.DBNull.Value)
                        { jugador.Altura = (Decimal)lector["Altura"]; }
                        if (lector["Peso"] != System.DBNull.Value)
                        { jugador.Peso = (byte)lector["Peso"]; }
                        if (lector["Dorsal"] != System.DBNull.Value)
                        { jugador.Dorsal = (byte)lector["Dorsal"]; }
                        jugador.Posicion = (EnumPosiciones)Enum.Parse(typeof(EnumPosiciones), (String)lector["Posicion"]);
                        jugador.ValoracionGeneral = (byte)lector["ValoracionGeneral"];
                        jugador.Imagen = (String)lector["Imagen"];
                        jugador.IdEquipo = (int)lector["IDEquipo"];

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

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public DateTime? getUltimaFechaIncorporacionUsuarioDAL(String nickUsuario)
        /// Propósito: obtener la fecha de incorporación más reciente de un determinado usuario, el cual vendrá dado por el parámetro "nickUsuario".
        /// Es decir, la fecha en la que este usuario ha abierto el último sobre.
        /// Precondiciones: "nickUsuario" no debe ser una cadena vacía y debe ser distinto de null.
        /// Entradas: el nick del usuario.
        /// Salidas: la fecha correspondiente a la fecha de incorporación más reciente o "null" en caso de que no exista ningún registro.
        /// Postcondiciones: se devuelve la fecha asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <returns></returns>
        public DateTime? getUltimaFechaIncorporacionUsuarioDAL(String nickUsuario)
        {

            //Declaraciones e inicializaciones
            DateTime? ultimaFecha = null;
            clsMyConnection clsConnection;
            SqlConnection sqlConnection = new SqlConnection();
            SqlCommand command = new SqlCommand();
            SqlDataReader lector;

            //Añadimos los parámetros
            command.Parameters.Add("@nick", System.Data.SqlDbType.VarChar).Value = nickUsuario;

            //Instanciamos un objeto conexion
            clsConnection = new clsMyConnection();

            try
            {
                //Obtenemos la conexión abierta
                sqlConnection = clsConnection.getConnection();

                //Definimos el comando
                command.CommandText = "SELECT TOP 1 FechaIncorporacion FROM JugadoresClubs WHERE NickUsuario = @nick ORDER BY FechaIncorporacion Desc";
                command.Connection = sqlConnection;
                lector = command.ExecuteReader();

                //Leemos los datos
                if (lector.HasRows)
                {
                    lector.Read();

                    ultimaFecha = (DateTime)lector["FechaIncorporacion"];
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

            return ultimaFecha;

        }

    }
}
