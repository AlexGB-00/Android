using NBA_MyTeam_BL.Gestoras;
using NBA_MyTeam_BL.Listados;
using NBA_MyTeam_Entities.Basicas;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace NBA_MyTeam_API.Controllers
{
    public class AlineacionesController : ApiController
    {

        // GET: api/Alineaciones
        public IEnumerable<ClsAlineacion> Get(String nickUsuario)
        {

            List<ClsAlineacion> listadoAlineaciones;
            ClsListadosAlineacionesBL clsListadosAlineacionesBL = new ClsListadosAlineacionesBL();

            try
            {
                listadoAlineaciones = clsListadosAlineacionesBL.getListadoAlineacionesUsuarioBL(nickUsuario);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            if (listadoAlineaciones == null || listadoAlineaciones.Count == 0)
            {
                throw new HttpResponseException(HttpStatusCode.NoContent);
            }

            return listadoAlineaciones;

        }

        // GET: api/Alineaciones/{id}
        public ClsAlineacion Get(int id)
        {

            ClsAlineacion alineacion;
            ClsListadosAlineacionesBL clsListadosAlineacionesBL = new ClsListadosAlineacionesBL();

            try
            {
                alineacion = clsListadosAlineacionesBL.getAlineacionBL(id);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            if (alineacion == null)
            {
                throw new HttpResponseException(HttpStatusCode.NoContent);
            }

            return alineacion;

        }

        // POST: api/Alineaciones
        public int Post([FromBody] ClsAlineacion alineacion)
        {

            int filasAfectadas;
            ClsGestoraAlineacionesBL clsGestoraAlineacionesBL = new ClsGestoraAlineacionesBL();

            try
            {
                filasAfectadas = clsGestoraAlineacionesBL.insertAlineacionBL(alineacion);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

        // PUT: api/Alineaciones
        public int Put([FromBody] ClsAlineacion alineacion)
        {

            int filasAfectadas;
            ClsGestoraAlineacionesBL clsGestoraAlineacionesBL = new ClsGestoraAlineacionesBL();

            try
            {
                filasAfectadas = clsGestoraAlineacionesBL.updateAlineacionBL(alineacion);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

        // DELETE: api/Alineaciones/{id}
        public int Delete(int id)
        {

            int filasAfectadas;
            ClsGestoraAlineacionesBL clsGestoraAlineacionesBL = new ClsGestoraAlineacionesBL();

            try
            {
                filasAfectadas = clsGestoraAlineacionesBL.deleteAlineacionBL(id);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

    }
}
