using NBA_MyTeam_Entities.Enums;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_Entities.Basicas
{
    public class ClsAlineacion
    {

        #region Atributos

        private int id;
        private String nombre;
        private EnumSistemas sistema;
        private byte valoracionMedia;
        private DateTime fechaCreacion;
        private bool finalizada;
        private String nickUsuario;

        #endregion


        #region Constructores

        //Sin parámetros
        public ClsAlineacion()
        {
            nombre = "default";
            sistema = EnumSistemas.DOS_DOS_UNO;
            valoracionMedia = 0;
            fechaCreacion = DateTime.Now;
            finalizada = false;
            nickUsuario = "default";
        }

        //Con parámetros
        public ClsAlineacion(String nombre, EnumSistemas sistema, byte valoracionMedia, DateTime fechaCreacion, bool finalizada, String nickUsuario)
        {
            if(!String.IsNullOrEmpty(nombre) && nombre.Length <= 20) { this.nombre = nombre; } else { this.nombre = "default"; }
            this.sistema = sistema;
            if(valoracionMedia >= 0 && valoracionMedia <= 99) { this.valoracionMedia = valoracionMedia; } else { this.valoracionMedia = 0; }
            if(DateTime.Compare(fechaCreacion, DateTime.Now) <= 0) { this.fechaCreacion = fechaCreacion; } else { this.fechaCreacion = DateTime.Now; }
            this.finalizada = finalizada;
            if(!String.IsNullOrEmpty(nickUsuario) && nickUsuario.Length >= 3 && nickUsuario.Length <= 20) { this.nickUsuario = nickUsuario; } else { this.nickUsuario = "default"; }
        }

        #endregion


        #region Propiedades

        public int Id
        {
            get { return id; }
            set { if(value > 0) { this.id = value; } }
        }

        public String Nombre
        {
            get { return nombre; }
            set { if(!String.IsNullOrEmpty(value) && value.Length <= 20) { this.nombre = value; } else { this.nombre = "default"; } }
        }

        public EnumSistemas Sistema
        {
            get { return sistema; }
            set { this.sistema = value; }
        }

        public byte ValoracionMedia
        {
            get { return valoracionMedia; }
            set { if(value >= 0 && value <= 99) { this.valoracionMedia = value; } else { this.valoracionMedia = 0; } }
        }

        public DateTime FechaCreacion
        {
            get { return fechaCreacion; }
            set { if(DateTime.Compare(value, DateTime.Now) <= 0) { this.fechaCreacion = value; } else { this.fechaCreacion = DateTime.Now; } }
        }

        public bool Finalizada
        {
            get { return finalizada; }
            set { this.finalizada = value; }
        }

        public String NickUsuario
        {
            get { return nickUsuario; }
            set { if(!String.IsNullOrEmpty(value) && value.Length >= 3 && value.Length <= 20) { this.nickUsuario = value; } else { this.nickUsuario = "default"; } }
        }

        #endregion

    }
}
