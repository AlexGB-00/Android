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
    public class ClsGestoraJugadoresAlineacionesBL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int insertJugadorAlineacionBL(ClsJugadorAlineacion nuevoJugadorAlineacion)
        /// Propósito: insertar un nuevo jugador en una determinada alineación, a partir de los datos del objeto "nuevoJugadorAlineacion" pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL. 
        /// Precondiciones: "nuevoJugadorAlineacion" debe ser distinto de null.
        /// Entradas: el objeto jugadorAlienacion que contiene el jugador a insertar junto con la alineación correspondiente.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nuevoJugadorAlineacion"></param>
        /// <returns></returns>
        public int insertJugadorAlineacionBL(ClsJugadorAlineacion nuevoJugadorAlineacion)
        {

            int filasAfectadas;

            ClsGestoraJugadoresAlineacionesDAL clsGestoraJugadoresAlineacionesDAL = new ClsGestoraJugadoresAlineacionesDAL();

            try
            {
                filasAfectadas = clsGestoraJugadoresAlineacionesDAL.insertJugadorAlineacionDAL(nuevoJugadorAlineacion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int updateJugadorAlineacionBL(ClsJugadorAlineacion jugadorAlineacion, idJugadorSustituto)
        /// Propósito: reemplazar un determinado jugador dentro de una determinada alineación (lo cual vendrá dado por el objeto "jugadorAlineacion") por otro, el cual
        /// vendrá indicado mediante el parámetro "idJugadorSustituto".
        /// Para ello hará uso de la llamada a la capa DAL. 
        /// Precondiciones: "jugadorAlineacion" debe ser distinto de null y "idJugadorSustituto" debe ser mayor que 0.
        /// Entradas: el objeto "jugadorAlineacion" que contiene el jugador a reemplazar junto con la alineación correspondiente y el id del jugador sustituto.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="jugadorAlineacion"></param>
        /// <param name="idJugadorSustituto"></param>
        /// <returns></returns>
        public int updateJugadorAlineacionBL(ClsJugadorAlineacion jugadorAlineacion, int idJugadorSustituto)
        {

            int filasAfectadas;

            ClsGestoraJugadoresAlineacionesDAL clsGestoraJugadoresAlineacionesDAL = new ClsGestoraJugadoresAlineacionesDAL();

            try
            {
                filasAfectadas = clsGestoraJugadoresAlineacionesDAL.updateJugadorAlineacionDAL(jugadorAlineacion, idJugadorSustituto);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int deleteJugadorAlineacionBL(int idJugador, int idAlineacion)
        /// Propósito: eliminar un determinado jugador de una determinada alineación.
        /// Para ello hará uso de la llamada a la capa DAL. 
        /// Precondiciones: tanto "idJugador" como "idAlineacion" deben ser mayores que 0. 
        /// Entradas: el id del jugador y el id de la alineación.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="idJugador"></param>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public int deleteJugadorAlineacionBL(int idJugador, int idAlineacion)
        {

            int filasAfectadas;

            ClsGestoraJugadoresAlineacionesDAL clsGestoraJugadoresAlineacionesDAL = new ClsGestoraJugadoresAlineacionesDAL();

            try
            {
                filasAfectadas = clsGestoraJugadoresAlineacionesDAL.deleteJugadorAlineacionDAL(idJugador, idAlineacion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

    }
}
