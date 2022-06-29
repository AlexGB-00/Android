using NBA_MyTeam_BL.Listados;
using NBA_MyTeam_Entities.Basicas;
using NBA_MyTeam_Entities.Enums;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace NBA_MyTeam_API.Controllers
{
    public class JugadoresController : ApiController
    {

        // GET: api/Jugadores
        public IEnumerable<ClsJugador> Get(String nombre)
        {

            List<ClsJugador> listadoJugadores;
            ClsListadosJugadoresBL clsListadosJugadoresBL = new ClsListadosJugadoresBL();

            try
            {
                listadoJugadores = clsListadosJugadoresBL.getListadoJugadoresNombreBL(nombre);
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

        // GET: api/Jugadores
        public IEnumerable<ClsJugador> Get(EnumPosiciones posicion)
        {

            List<ClsJugador> listadoJugadores;
            ClsListadosJugadoresBL clsListadosJugadoresBL = new ClsListadosJugadoresBL();

            //Comprobar si castea posicion a String

            try
            {
                listadoJugadores = clsListadosJugadoresBL.getListadoJugadoresPosicionBL(posicion);
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

        // GET: api/Jugadores
        public IEnumerable<ClsJugador> Get(int valoracionMinima, int valoracionMaxima)
        {

            List<ClsJugador> listadoJugadores;
            ClsListadosJugadoresBL clsListadosJugadoresBL = new ClsListadosJugadoresBL();

            //Comprobar si castea posicion a String

            try
            {
                listadoJugadores = clsListadosJugadoresBL.getListadoJugadoresValoracionBL(valoracionMinima, valoracionMaxima);
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


        // GET: api/Jugadores/{id}
        public ClsJugador Get(int id)
        {

            ClsJugador jugador;
            ClsListadosJugadoresBL clsListadosJugadoresBL = new ClsListadosJugadoresBL();

            try
            {
                jugador = clsListadosJugadoresBL.getJugadorBL(id);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            if (jugador == null)
            {
                throw new HttpResponseException(HttpStatusCode.NoContent);
            }

            return jugador;

        }

    }
}
