using NBA_MyTeam_BL.Gestoras;
using NBA_MyTeam_BL.Listados;
using NBA_MyTeam_Entities.Basicas;
using NBA_MyTeam_Entities.Intermedias;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace NBA_MyTeam_API.Controllers
{
    public class JugadoresAlineacionesController : ApiController
    {

        // GET: api/JugadoresAlineaciones
        public IEnumerable<ClsJugador> Get(int idAlineacion)
        {

            List<ClsJugador> listadoJugadores;
            ClsListadosJugadoresAlineacionesBL clsListadosJugadoresAlineacionesBL = new ClsListadosJugadoresAlineacionesBL();

            try
            {
                listadoJugadores = clsListadosJugadoresAlineacionesBL.getListadoJugadoresAlineacionBL(idAlineacion);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            if (listadoJugadores == null || listadoJugadores.Count == 0)
            {
                throw new HttpResponseException(HttpStatusCode.NoContent);
            }

            return listadoJugadores;
        }

        // POST: api/JugadoresAlineaciones
        public int Post([FromBody] ClsJugadorAlineacion jugadorAlineacion)
        {

            int filasAfectadas;
            ClsGestoraJugadoresAlineacionesBL clsGestoraJugadoresAlineacionesBL = new ClsGestoraJugadoresAlineacionesBL();

            try
            {
                filasAfectadas = clsGestoraJugadoresAlineacionesBL.insertJugadorAlineacionBL(jugadorAlineacion);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

        // PUT: api/JugadoresAlineaciones
        public int Put([FromBody] ClsJugadorAlineacion jugadorAlineacion, int idJugadorSustituto)
        {

            int filasAfectadas;
            ClsGestoraJugadoresAlineacionesBL clsGestoraJugadoresAlineaciones = new ClsGestoraJugadoresAlineacionesBL();

            try
            {
                filasAfectadas = clsGestoraJugadoresAlineaciones.updateJugadorAlineacionBL(jugadorAlineacion, idJugadorSustituto);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

        // DELETE: api/JugadoresAlineaciones
        public int Delete(int idJugador, int idAlineacion)
        {

            int filasAfectadas;
            ClsGestoraJugadoresAlineacionesBL clsGestoraJugadoresAlineaciones = new ClsGestoraJugadoresAlineacionesBL();

            try
            {
                filasAfectadas = clsGestoraJugadoresAlineaciones.deleteJugadorAlineacionBL(idJugador, idAlineacion);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

    }
}
