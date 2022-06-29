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
    public class EquiposController : ApiController
    {
       
        // GET: api/Equipos/{id}
        public ClsEquipo Get(int id)
        {

            ClsEquipo equipo;
            ClsListadosEquiposBL clsListadosEquiposBL = new ClsListadosEquiposBL();

            try
            {
                equipo = clsListadosEquiposBL.getEquipoBL(id);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            if (equipo == null)
            {
                throw new HttpResponseException(HttpStatusCode.NoContent);
            }

            return equipo;

        }

    }
}
