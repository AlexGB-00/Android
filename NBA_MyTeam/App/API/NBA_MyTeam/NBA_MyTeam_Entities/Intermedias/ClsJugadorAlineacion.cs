using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_Entities.Intermedias
{
    public class ClsJugadorAlineacion
    {

        #region Atributos

        private int idJugador;
        private int idAlineacion;

        #endregion


        #region Constructores

        //Sin parámetros
        public ClsJugadorAlineacion()
        {
            idJugador = 1;
            idAlineacion = 1;
        }

        //Con parámetros
        public ClsJugadorAlineacion(int idJugador, int idAlineacion)
        {
            if(idJugador > 0) { this.idJugador = idJugador; } else { this.idJugador = 1; }
            if(idAlineacion > 0) { this.idAlineacion = idAlineacion; } else { this.idAlineacion = 1; }
        }

        #endregion


        #region Propiedades

        public int IdJugador
        {
            get { return idJugador; }
            set { if(value > 0) { this.idJugador = value; } else { this.idJugador = 1; } }
        }

        public int IdAlineacion
        {
            get { return idAlineacion; }
            set { if(value > 0) { this.idAlineacion = value; } else { this.idAlineacion = 1; } }
        }

        #endregion

    }
}
