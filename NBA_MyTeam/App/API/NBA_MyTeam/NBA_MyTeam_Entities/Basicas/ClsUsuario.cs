using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_Entities.Basicas
{  
    public class ClsUsuario
    {

        #region Atributos

        private String nick;
        private String correoElectronico;
        private String contrasenha;
        private String nombreCompleto;
        private String imagen;

        #endregion


        #region Constructores

        //Sin parámetros
        public ClsUsuario()
        {
            nick = "default";
            correoElectronico = "default@gmail.com";
            contrasenha = "Default123";
            nombreCompleto = null;
            imagen = null;
        }

        //Con parámetros
        public ClsUsuario(String nick, String correoElectronico, String contrasenha, String nombreCompleto, String imagen)
        {
            if(!String.IsNullOrEmpty(nick) && nick.Length >= 3 && nick.Length <= 20) { this.nick = nick; } else { this.nick = "default"; }
            if(!String.IsNullOrEmpty(correoElectronico) && correoElectronico.Length <= 45) { this.correoElectronico = correoElectronico; } else { this.correoElectronico = "default@gmail.com"; }
            if(!String.IsNullOrEmpty(contrasenha) && contrasenha.Length >= 8 && contrasenha.Length <= 32) { this.contrasenha = contrasenha; } else { this.contrasenha = "Default123"; }
            if(String.IsNullOrEmpty(nombreCompleto)) { this.nombreCompleto = nombreCompleto; } else if(nombreCompleto.Length <= 40) { this.nombreCompleto = nombreCompleto; }
            if(String.IsNullOrEmpty(imagen)) { this.imagen = imagen; } else if(imagen.Length <= 150) { this.imagen = imagen; }
        }

        #endregion


        #region Propiedades

        public String Nick 
        {   
            get { return nick; }
            set { if(!String.IsNullOrEmpty(value) && value.Length >= 3 && value.Length <= 20) { this.nick = value; } else { this.nick = "default"; } }
        }

        public String CorreoElectronico 
        {
            get { return correoElectronico; }
            set { if(!String.IsNullOrEmpty(value) && value.Length <= 45) { this.correoElectronico = value; } else { this.correoElectronico = "default@gmail.com"; } }
        }

        public String Contrasenha 
        { 
            get { return contrasenha; }
            set { if(!String.IsNullOrEmpty(value) && value.Length >= 8 && value.Length <= 32) { this.contrasenha = value; } else { this.contrasenha = "Default123"; } }
        }

        public String NombreCompleto
        { 
            get { return nombreCompleto; }
            set { if(String.IsNullOrEmpty(value)) { this.nombreCompleto = value; } else if (value.Length <= 40) { this.nombreCompleto = value; } }
        }

        public String Imagen 
        {
            get { return imagen; }
            set { if(String.IsNullOrEmpty(value)) { this.imagen = value; } else if (value.Length <= 150) { this.imagen = value; } }
        }

        #endregion

    }
}
