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
    public class ClsListadosAlineacionesBL
    {


        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsAlineacion> getListadoAlineacionesUsuarioDAL(String nickUsuario)
        /// Propósito: obtener un listado de alineaciones pertenecientes a un determinado usuario, cuyo nick será pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "nickUsuario" debe ser distinto de null y no debe estar vacío.
        /// Entradas: el nick del usuario.
        /// Salidas: el listado de alineaciones del usuario en cuestión (si existe) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el listado de alineaciones asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <returns></returns>
        public List<ClsAlineacion> getListadoAlineacionesUsuarioBL(String nickUsuario)
        {

            List<ClsAlineacion> listadoAlineaciones;

            ClsListadosAlineacionesDAL clsListadosAlineacionesDAL = new ClsListadosAlineacionesDAL();

            try
            {
                listadoAlineaciones = clsListadosAlineacionesDAL.getListadoAlineacionesUsuarioDAL(nickUsuario);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return listadoAlineaciones;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public ClsAlineacion getAlineacionBL(int idAlineacion)
        /// Propósito: obtener una determinada alineación (con todos sus datos) a partir del id pasado como parámetro. 
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "idAlineacion" debe ser mayor que 0.
        /// Entradas: el id de la alineación.
        /// Salidas: la alineación (si existe en la BBDD) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve la alineación asociada al nombre de la función.
        /// </summary>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public ClsAlineacion getAlineacionBL(int idAlineacion)
        {

            ClsAlineacion alineacion;

            ClsListadosAlineacionesDAL clsListadosAlineacionesDAL = new ClsListadosAlineacionesDAL();

            try
            {
                alineacion = clsListadosAlineacionesDAL.getAlineacionDAL(idAlineacion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return alineacion;

        }

    }
}
