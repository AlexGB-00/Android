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
    public class ClsListadosEquiposBL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public ClsEquipo getEquipoDAL(int idEquipo)
        /// Propósito: obtener un determinado equipo (con todos sus datos) a partir del id pasado como parámetro. 
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "idEquipo" debe ser mayor que 0.
        /// Entradas: el id del equipo.
        /// Salidas: el equipo (si existe en la BBDD) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el equipo asociado al nombre de la función.
        /// </summary>
        /// <param name="idEquipo"></param>
        /// <returns></returns>
        public ClsEquipo getEquipoBL(int idEquipo)
        {

            ClsEquipo equipo;

            ClsListadosEquiposDAL clsListadosEquiposDAL = new ClsListadosEquiposDAL();

            try
            {
                equipo = clsListadosEquiposDAL.getEquipoDAL(idEquipo);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return equipo;

        }

    }
}
