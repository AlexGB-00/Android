using NBA_MyTeam_DAL.Listados;
using NBA_MyTeam_Entities.Basicas;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_BL.Listados
{
    public class ClsListadosJugadoresAlineacionesBL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresAlineacionDAL(int idAlineacion)
        /// Propósito: obtener un listado con los jugadores que se encuentran incluidos dentro de una determinada alineación, cuyo id es pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "idAlineacion" debe ser mayor que 0.
        /// Entradas: el id de la alineación.
        /// Salidas: el listado de jugadores de la alineación en cuestión (si existe) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresAlineacionBL(int idAlineacion)
        {

            List<ClsJugador> listadoJugadores;

            ClsListadosJugadoresAlineacionesDAL clsListadosJugadoresAlineacionesDAL = new ClsListadosJugadoresAlineacionesDAL();

            try
            {
                listadoJugadores = clsListadosJugadoresAlineacionesDAL.getListadoJugadoresAlineacionDAL(idAlineacion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return listadoJugadores;

        }

    }
}
