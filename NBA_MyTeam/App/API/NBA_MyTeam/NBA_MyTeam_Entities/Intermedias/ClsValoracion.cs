using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_Entities.Intermedias
{
    public class ClsValoracion
    {

        #region Atributos

        private String nickUsuario;
        private int idAlineacion;
        private byte rating;
        private String descripcion;

        #endregion


        #region Constructores

        //Sin parámetros
        public ClsValoracion()
        {
            nickUsuario = "default";
            idAlineacion = 1;
            rating = 0;
            descripcion = null;
        }

        //Con parámetros
        public ClsValoracion(String nickUsuario, int idAlineacion, byte rating, String descripcion)
        {
           if(!String.IsNullOrEmpty(nickUsuario) && nickUsuario.Length <= 20) { this.nickUsuario = nickUsuario; } else { this.nickUsuario = "default"; }
           if(idAlineacion > 0) { this.idAlineacion = idAlineacion; } else { this.idAlineacion = 1; }
           if(rating >= 0 && rating <= 10) { this.rating = rating; } else { this.rating = 0; }
           if(String.IsNullOrEmpty(descripcion)) { this.descripcion = descripcion; } else if(descripcion.Length <= 250) { this.descripcion = descripcion; }
        }

        #endregion


        #region Propiedades

        public String NickUsuario
        {
            get { return nickUsuario; }
            set { if(!String.IsNullOrEmpty(value) && value.Length <= 20) { this.nickUsuario = value; } else { this.nickUsuario = "default"; } }
        }

        public int IdAlineacion
        {
            get { return idAlineacion; }
            set { if(value > 0) { this.idAlineacion = value; } else { this.idAlineacion = 1; } }
        }

        public byte Rating
        {
            get { return rating; }
            set { if(value >= 0 && value <= 10) { this.rating = value; } else { this.rating = 0; } }
        }

        public String Descripcion
        {
            get { return descripcion; }
            set { if(String.IsNullOrEmpty(value)) { this.descripcion = value; } else if (value.Length <= 250) { this.descripcion = value; } }
        }

        #endregion

    }
}
