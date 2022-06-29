using NBA_MyTeam_DAL.Listados;
using NBA_MyTeam_Entities.Basicas;
using NBA_MyTeam_Entities.Enums;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_BL.Listados
{
    public class ClsListadosJugadoresClubsBL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresUsuarioDAL(String nickUsuario)
        /// Propósito: obtener un listado con los jugadores que se encuentran dentro del "club" de un determinado usuario, cuyo nick es pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "nickUsuario" no debe ser una cadena vacía y debe ser distinto de null.
        /// Entradas: el nick del usuario.
        /// Salidas: el listado de jugadores del usuario en cuestión (si existe) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresUsuarioBL(String nickUsuario)
        {

            List<ClsJugador> listadoJugadores;

            ClsListadosJugadoresClubsDAL clsListadosJugadoresClubsDAL = new ClsListadosJugadoresClubsDAL();

            try
            {
                listadoJugadores = clsListadosJugadoresClubsDAL.getListadoJugadoresUsuarioDAL(nickUsuario);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return listadoJugadores;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresUsuarioPorPosicionBL(String nickUsuario, EnumPosiciones posicion)
        /// Propósito: obtener un listado con los jugadores que se encuentran dentro del "club" de un determinado usuario, cuyo nick es pasado como parámetro,
        /// y que además su posición coincida con la pasada como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "nickUsuario" no debe ser una cadena vacía y debe ser distinto de null. Además, "posicion" debe ser un valor definido
        /// en el enum correspondiente.
        /// Entradas: el nick del usuario y la pocición.
        /// Salidas: el listado de jugadores del usuario en cuestión que pertenezcan a tal posición (si existen) o null (en caso de que no existan).
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <param name="posicion"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresUsuarioPorPosicionBL(String nickUsuario, EnumPosiciones posicion)
        {

            List<ClsJugador> listadoJugadores;

            ClsListadosJugadoresClubsDAL clsListadosJugadoresClubsDAL = new ClsListadosJugadoresClubsDAL();

            try
            {
                listadoJugadores = clsListadosJugadoresClubsDAL.getListadoJugadoresUsuarioPorPosicionDAL(nickUsuario, posicion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return listadoJugadores;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public DateTime? getUltimaFechaIncorporacionUsuarioBL(String nickUsuario)
        /// Propósito: obtener la fecha de incorporación más reciente de un determinado usuario, el cual vendrá dado por el parámetro "nickUsuario".
        /// Es decir, la fecha en la que este usuario ha abierto el último sobre.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "nickUsuario" no debe ser una cadena vacía y debe ser distinto de null.
        /// Entradas: el nick del usuario.
        /// Salidas: la fecha correspondiente a la fecha de incorporación más reciente o "null" en caso de que no exista ningún registro.
        /// Postcondiciones: se devuelve la fecha asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <returns></returns>
        public DateTime? getUltimaFechaIncorporacionUsuarioBL(String nickUsuario)
        {

            DateTime? ultimaFecha;

            ClsListadosJugadoresClubsDAL clsListadosJugadoresClubsDAL = new ClsListadosJugadoresClubsDAL();

            try
            {
                ultimaFecha = clsListadosJugadoresClubsDAL.getUltimaFechaIncorporacionUsuarioDAL(nickUsuario);
            } 
            catch (SqlException e)
            {
                throw e;
            }

            return ultimaFecha;

        }

    }
}
