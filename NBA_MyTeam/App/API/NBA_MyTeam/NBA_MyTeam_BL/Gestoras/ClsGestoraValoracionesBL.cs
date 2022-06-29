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
    public class ClsGestoraValoracionesBL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int insertValoracionBL(String nickUsuario, int idAlineacion)
        /// Propósito: insertar una nuevo registro en la tabla "Valoraciones", a partir de los datos pasados como parámetro.
        /// Esta operación representa la acción de compartir una alineación con otro usuario, relacionando de esa manera dicha alineación con el nuevo usuario.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "nickUsuario" debe ser distinto de null y no debe estar vacío y "idAlineacion" debe ser mayor que 0.
        /// Entradas: el nick del usuario y el id de la alineación.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public int insertValoracionBL(String nickUsuario, int idAlineacion)
        {

            int filasAfectadas;

            ClsGestoraValoracionesDAL clsGestoraValoracionesDAL = new ClsGestoraValoracionesDAL();

            try
            {
                filasAfectadas = clsGestoraValoracionesDAL.insertValoracionDAL(nickUsuario, idAlineacion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int updateValoracionBL(ClsValoracion valoracion)
        /// Propósito: actualizar una determinada valoración, cuyos nuevos datos vendrán dados por el objeto "valoracion" pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "valoracion" debe ser distinto de null.
        /// Entradas: el objeto valoracion que contiene la valoración (rating y descripción), el usuario y la alineacion a la que se asigna.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="valoracion"></param>
        /// <returns></returns>
        public int updateValoracionBL(ClsValoracion valoracion)
        {

            int filasAfectadas;

            ClsGestoraValoracionesDAL clsGestoraValoracionesDAL = new ClsGestoraValoracionesDAL();

            try
            {
                filasAfectadas = clsGestoraValoracionesDAL.updateValoracionDAL(valoracion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int deleteValoracionBL(String nickUsuario, int idAlineacion)
        /// Propósito: eliminar una determinada valoración de una determinada alineación, lo cual vendrá dado por el objeto "valoracion" pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "nickUsuario" debe ser distinto de null y no debe estar vacío y "idAlineacion" debe ser mayor que 0.
        /// Entradas: el nick del usuario y el id de la alineación.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <param name="idAlineacion"></param>
        /// <returns></returns>
        public int deleteValoracionBL(String nickUsuario, int idAlineacion)
        {

            int filasAfectadas;

            ClsGestoraValoracionesDAL clsGestoraValoracionesDAL = new ClsGestoraValoracionesDAL();

            try
            {
                filasAfectadas = clsGestoraValoracionesDAL.deleteValoracionDAL(nickUsuario, idAlineacion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

    }
}
