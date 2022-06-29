using NBA_MyTeam_DAL.Gestoras;
using NBA_MyTeam_Entities.Intermedias;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_BL.Gestoras
{
    public class ClsGestoraJugadoresClubsBL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int insertJugadorClubBL(ClsJugadorClub nuevoJugadorClub)
        /// Propósito: añadir un nuevo jugador a la plantilla de un determinado usuario, a partir de los datos del objeto "nuevoJugadorClub" pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL. 
        /// Precondiciones: "nuevoJugadorClub" debe ser distinto de null.
        /// Entradas: el objeto jugadorClub.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nuevoJugadorClub"></param>
        /// <returns></returns>
        public int insertJugadorClubBL(ClsJugadorClub nuevoJugadorClub)
        {

            int filasAfectadas;

            ClsGestoraJugadoresClubsDAL clsGestoraJugadoresClubsDAL = new ClsGestoraJugadoresClubsDAL();

            try
            {
                filasAfectadas = clsGestoraJugadoresClubsDAL.insertJugadorClubDAL(nuevoJugadorClub);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int deleteJugadorClubBL(int idJugador, String nickUsuario)
        /// Propósito: eliminar un determinado jugador de la plantilla de un determinado usuario.
        /// Para ello hará uso de la llamada a la capa DAL. 
        /// Precondiciones: "idJugador" debe ser mayor que 0 y "nickUsuario" debe ser distinto de null y no debe estar vacío.
        /// Entradas: el id del jugador y el nick del usuario.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="idJugador"></param>
        /// <param name="nickUsuario"></param>
        /// <returns></returns>
        public int deleteJugadorClubBL(int idJugador, String nickUsuario)
        {

            int filasAfectadas;

            ClsGestoraJugadoresClubsDAL clsGestoraJugadoresClubsDAL = new ClsGestoraJugadoresClubsDAL();

            try
            {
                filasAfectadas = clsGestoraJugadoresClubsDAL.deleteJugadorClubDAL(idJugador, nickUsuario);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

    }
}
