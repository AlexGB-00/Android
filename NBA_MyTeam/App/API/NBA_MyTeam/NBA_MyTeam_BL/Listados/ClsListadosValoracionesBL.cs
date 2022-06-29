using NBA_MyTeam_DAL.Listados;
using NBA_MyTeam_Entities.Intermedias;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_BL.Listados
{
    public class ClsListadosValoracionesBL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsValoracion> getListadoValoracionesAlineacionDAL(int idAlineacion)
        /// Propósito: obtener un listado con las valoraciones de una determinada alineación, cuyo id es pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "idAlineacion" debe ser mayor que 0.
        /// Entradas: el id de la alineación.
        /// Salidas: el listado de valoraciones de la alineación en cuestión (si existe) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el listado de valoraciones asociado al nombre de la función.
        /// </summary>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public List<ClsValoracion> getListadoValoracionesAlineacionBL(int idAlineacion)
        {

            List<ClsValoracion> listadoValoraciones;

            ClsListadosValoracionesDAL clsListadoValoracionesDAL = new ClsListadosValoracionesDAL();

            try
            {
                listadoValoraciones = clsListadoValoracionesDAL.getListadoValoracionesAlineacionDAL(idAlineacion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return listadoValoraciones;

        }

    }
}
