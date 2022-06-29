using NBA_MyTeam_BL.Gestoras;
using NBA_MyTeam_BL.Listados;
using NBA_MyTeam_Entities.Basicas;
using NBA_MyTeam_Entities.Enums;
using NBA_MyTeam_Entities.Intermedias;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace NBA_MyTeam_API.Controllers
{   
    public class JugadoresClubsController : ApiController
    {
        // GET: api/JugadoresClubs
        public IEnumerable<ClsJugador> Get(String nickUsuario)
        {

            List<ClsJugador> listadoJugadores;
            ClsListadosJugadoresClubsBL clsListadosJugadoresClubsBL = new ClsListadosJugadoresClubsBL();

            try
            {
                listadoJugadores = clsListadosJugadoresClubsBL.getListadoJugadoresUsuarioBL(nickUsuario);
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

        // GET: api/JugadoresClubs
        public IEnumerable<ClsJugador> Get(String nickUsuario, EnumPosiciones posicion)
        {

            List<ClsJugador> listadoJugadores;
            ClsListadosJugadoresClubsBL clsListadosJugadoresClubsBL = new ClsListadosJugadoresClubsBL();

            try
            {
                listadoJugadores = clsListadosJugadoresClubsBL.getListadoJugadoresUsuarioPorPosicionBL(nickUsuario, posicion);
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

        // GET: api/JugadoresClubs
        public IEnumerable<ClsJugador> Get(int idEquipo)
        {

            List<ClsJugador> listadoJugadores;
            ClsListadosJugadoresBL clsListadosJugadoresBL = new ClsListadosJugadoresBL();

            try
            {
                listadoJugadores = clsListadosJugadoresBL.getListadoJugadoresEquipoBL(idEquipo);
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

        // POST: api/JugadoresClubs
        public int Post([FromBody]ClsJugadorClub jugadorClub)
        {

            int filasAfectadas;
            ClsGestoraJugadoresClubsBL clsGestoraJugadoresClubsBL = new ClsGestoraJugadoresClubsBL();

            try
            {
                filasAfectadas = clsGestoraJugadoresClubsBL.insertJugadorClubBL(jugadorClub);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

        // DELETE: api/JugadoresClubs
        public int Delete(int idJugador, String nickUsuario)
        {

            int filasAfectadas;
            ClsGestoraJugadoresClubsBL clsGestoraJugadoresClubsBL = new ClsGestoraJugadoresClubsBL();

            try
            {
                filasAfectadas = clsGestoraJugadoresClubsBL.deleteJugadorClubBL(idJugador, nickUsuario);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

    }
}
