using NBA_MyTeam_DAL.Listados;
using NBA_MyTeam_Entities.Basicas;
using NBA_MyTeam_Entities.Enums;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_BL.Listados
{
    public class ClsListadosJugadoresBL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresNombreBL(String nombreJugador)
        /// Propósito: obtener un listado con los jugadores cuyo nombre coincida con el pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "nombreJugador" debe ser distinto de null y no debe estar vacío.
        /// Entradas: el nombre del jugador.
        /// Salidas: el listado de jugadores o null en caso de error.
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// <param name="nombreJugador"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresNombreBL(String nombreJugador)
        {

            List<ClsJugador> listadoJugadores;

            ClsListadosJugadoresDAL clsListadosJugadoresDAL = new ClsListadosJugadoresDAL();

            try
            {
                listadoJugadores = clsListadosJugadoresDAL.getListadoJugadoresNombreDAL(nombreJugador);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return listadoJugadores;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresEquipoBL(int idEquipo)
        /// Propósito: obtener un listado con los jugadores cuyo equipo coincida con el pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "idEquipo" debe ser mayor que 0.
        /// Entradas: el equipo al que pertenece el jugador.
        /// Salidas: el listado de jugadores o null en caso de error.
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// <param name="idEquipo"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresEquipoBL(int idEquipo)
        {

            List<ClsJugador> listadoJugadores;

            ClsListadosJugadoresDAL clsListadosJugadoresDAL = new ClsListadosJugadoresDAL();

            try
            {
                listadoJugadores = clsListadosJugadoresDAL.getListadoJugadoresEquipoDAL(idEquipo);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return listadoJugadores;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresPosicionDAL(EnumPosiciones posicion)
        /// Propósito: obtener un listado de jugadores que compartan la misma posición, la cual es pasada como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "posicion" debe ser un valor definido en el enum correspondiente.
        /// Entradas: la posición.
        /// Salidas: el listado de jugadores o null en caso de error.
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// <param name="posicion"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresPosicionBL(EnumPosiciones posicion)
        {

            List<ClsJugador> listadoJugadores;

            ClsListadosJugadoresDAL clsListadosJugadoresDAL = new ClsListadosJugadoresDAL();

            try
            {
                listadoJugadores = clsListadosJugadoresDAL.getListadoJugadoresPosicionDAL(posicion);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return listadoJugadores;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public List<ClsJugador> getListadoJugadoresValoracionBL(int valoracionMinima, int valoracionMaxima)
        /// Propósito: obtener un listado con los jugadores cuya valoración general se encuentre en el rango definido por los parámetros.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: tanto "valoracionMinima" como "valoracionMaxima" deben ser valores comprendidos entre 70 y 99 (ambos incluidos).
        /// Además, "valoracionMinima" debe ser un valor menor que "valoracionMaxima".
        /// Entradas: la valoración mínima y la valoración máxima que comprenden el margen de valoración de los jugadores.
        /// Salidas: el listado de jugadores o null en caso de error.
        /// Postcondiciones: se devuelve el listado de jugadores asociado al nombre de la función.
        /// </summary>
        /// /// <param name="valoracionMinima"></param>
        /// /// <param name="valoracionMaxima"></param>
        /// <returns></returns>
        public List<ClsJugador> getListadoJugadoresValoracionBL(int valoracionMinima, int valoracionMaxima)
        {

            List<ClsJugador> listadoJugadores;

            ClsListadosJugadoresDAL clsListadosJugadoresDAL = new ClsListadosJugadoresDAL();

            try
            {
                listadoJugadores = clsListadosJugadoresDAL.getListadoJugadoresValoracionDAL(valoracionMinima, valoracionMaxima);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return listadoJugadores;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public ClsJugador getJugadorDAL(int idJugador)
        /// Propósito: obtener un determinado jugador (con todos sus datos) a partir de su id.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "idJugador" debe ser mayor que 0.
        /// Entradas: el id del jugador.
        /// Salidas: el jugador (si existe en la BBDD) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el jugador asociado al nombre de la función.
        /// </summary>
        /// <param name="idJugador"></param>
        /// <returns></returns>
        public ClsJugador getJugadorBL(int idJugador)
        {

            ClsJugador jugador;

            ClsListadosJugadoresDAL clsListadosJugadoresDAL = new ClsListadosJugadoresDAL();

            try
            {
                jugador = clsListadosJugadoresDAL.getJugadorDAL(idJugador);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return jugador;

        }

    }
}
