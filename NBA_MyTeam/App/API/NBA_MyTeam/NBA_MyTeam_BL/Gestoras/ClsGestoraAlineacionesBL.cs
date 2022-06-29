using NBA_MyTeam_DAL.Gestoras;
using NBA_MyTeam_Entities.Basicas;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_BL.Gestoras
{
    public class ClsGestoraAlineacionesBL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int insertAlineacionBL(ClsAlineacion nuevaAlineacion)
        /// Propósito: insertar una nueva alineación en la BBDD a partir de los datos del objeto "nuevaAlineacion" pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL. 
        /// Precondiciones: "alineacion" debe ser distinta de null.
        /// Entradas: la alineación a insertar.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nuevaAlineacion"></param>
        /// <returns></returns>
        public int insertAlineacionBL(ClsAlineacion nuevaAlineacion)
        {

            int filasAfectadas;

            ClsGestoraAlineacionesDAL clsGestoraAlineacionesDAL = new ClsGestoraAlineacionesDAL();

            try
            {
                filasAfectadas = clsGestoraAlineacionesDAL.insertAlineacionDAL(nuevaAlineacion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int updateAlineacionBL(ClsAlineacion alineacion)
        /// Propósito: actualizar una determinada alineación de la BBDD, sustituyendo sus actuales datos por los del objeto "alineacion" pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL. 
        /// Precondiciones: "alineacion" debe ser distinto de null.
        /// Entradas: la alineación con los datos a actualizar.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="alineacion"></param>
        /// <returns></returns>
        public int updateAlineacionBL(ClsAlineacion alineacion)
        {

            int filasAfectadas;

            ClsGestoraAlineacionesDAL clsGestoraAlineacionesDAL = new ClsGestoraAlineacionesDAL();

            try
            {
                filasAfectadas = clsGestoraAlineacionesDAL.updateAlineacionDAL(alineacion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int deleteAlineacionBL(int idAlineacion)
        /// Propósito: eliminar una determinada alineación de la BBDD, cuyo id es pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL. 
        /// Precondiciones: "idAlineacion" debe ser mayor que 0.
        /// Entradas: el id de la alineacion.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public int deleteAlineacionBL(int idAlineacion)
        {

            int filasAfectadas;

            ClsGestoraAlineacionesDAL clsGestoraAlineacionesDAL = new ClsGestoraAlineacionesDAL();

            try
            {
                filasAfectadas = clsGestoraAlineacionesDAL.deleteAlineacionDAL(idAlineacion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

    }
}
