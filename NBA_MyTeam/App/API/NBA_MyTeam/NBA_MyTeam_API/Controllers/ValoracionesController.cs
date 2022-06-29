using NBA_MyTeam_BL.Gestoras;
using NBA_MyTeam_BL.Listados;
using NBA_MyTeam_Entities.Intermedias;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace NBA_MyTeam_API.Controllers
{
    public class ValoracionesController : ApiController
    {

        // GET: api/Valoraciones
        public IEnumerable<ClsValoracion> Get(int idAlineacion)
        {

            List<ClsValoracion> listadoValoraciones;
            ClsListadosValoracionesBL clsListadosValoracionesBL = new ClsListadosValoracionesBL();

            try
            {
                listadoValoraciones = clsListadosValoracionesBL.getListadoValoracionesAlineacionBL(idAlineacion);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            if (listadoValoraciones == null || listadoValoraciones.Count == 0)
            {
                throw new HttpResponseException(HttpStatusCode.NoContent);
            }

            return listadoValoraciones;

        }

        // POST: api/Valoraciones
        public int Post(String nickUsuario, int idAlineacion)
        {

            int filasAfectadas;
            ClsGestoraValoracionesBL clsGestoraValoracionesBL = new ClsGestoraValoracionesBL();

            try
            {
                filasAfectadas = clsGestoraValoracionesBL.insertValoracionBL(nickUsuario, idAlineacion);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

        // PUT: api/Valoraciones
        public int Put([FromBody] ClsValoracion valoracion)
        {

            int filasAfectadas;
            ClsGestoraValoracionesBL clsGestoraValoracionesBL = new ClsGestoraValoracionesBL();

            try
            {
                filasAfectadas = clsGestoraValoracionesBL.updateValoracionBL(valoracion);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

        // DELETE: api/Valoraciones
        public int Delete(String nickUsuario, int idAlineacion)
        {

            int filasAfectadas;
            ClsGestoraValoracionesBL clsGestoraValoracionesBL = new ClsGestoraValoracionesBL();

            try
            {
                filasAfectadas = clsGestoraValoracionesBL.deleteValoracionBL(nickUsuario, idAlineacion);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

    }
}
