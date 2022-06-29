using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_Entities.Intermedias
{
    public class ClsJugadorClub
    {

        #region Atributos

        private int idJugador;
        private String nickUsuario;
        private DateTime fechaIncorporacion;

        #endregion


        #region Constructores

        //Sin parámetros
        public ClsJugadorClub()
        {
            idJugador = 1;
            nickUsuario = "default";
            fechaIncorporacion = DateTime.Now;
        }

        //Con parámetros
        public ClsJugadorClub(int idJugador, String nickUsuario, DateTime fechaIncorporacion)
        {
            if(idJugador > 0) { this.idJugador = idJugador; } else { this.idJugador = 1; }
            if(!String.IsNullOrEmpty(nickUsuario) && nickUsuario.Length <= 20) { this.nickUsuario = nickUsuario; } else { this.nickUsuario = "default"; }
            if(DateTime.Compare(fechaIncorporacion, DateTime.Now) <= 0) { this.fechaIncorporacion = fechaIncorporacion; } else { this.fechaIncorporacion = DateTime.Now; }
        }

        #endregion


        #region Propiedades

        public int IdJugador
        {
            get { return idJugador; }
            set { if(value > 0) { this.idJugador = value; } else { this.idJugador = 1; } }
        }


        public String NickUsuario
        {
            get { return nickUsuario; }
            set { if(!String.IsNullOrEmpty(value) && value.Length <= 20) { this.nickUsuario = value; } else { this.nickUsuario = "default"; } }
        }

        public DateTime FechaIncorporacion
        {
            get { return fechaIncorporacion; }
            set { if(DateTime.Compare(value, DateTime.Now.AddHours(2)) <= 0) { this.fechaIncorporacion = value; } else { this.fechaIncorporacion = DateTime.Now; } }
        }

        #endregion

    }
}
