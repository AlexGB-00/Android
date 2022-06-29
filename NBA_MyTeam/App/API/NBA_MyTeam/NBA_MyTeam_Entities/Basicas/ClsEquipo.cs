using NBA_MyTeam_Entities.Enums;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_Entities.Basicas
{
    public class ClsEquipo
    {

        #region Atributos

        private int id;
        private String nombre;
        private EnumConferencias conferencia;
        private String ciudad;
        private short anhoFundacion;
        private byte numeroTitulos;
        private String estadio;
        private String nombreEntrenador;
        private String imagen;

        #endregion


        #region Constructores

        //Sin parámetros
        public ClsEquipo()
        {
            nombre = "default";
            conferencia = EnumConferencias.ESTE;
            ciudad = null;
            anhoFundacion = 1946;
            numeroTitulos = 0;
            estadio = null;
            nombreEntrenador = null;
            imagen = "default";
        }

        //Con parámetros
        public ClsEquipo(String nombre, EnumConferencias conferencia, String ciudad, short anhoFundacion, byte numeroTitulos, String estadio, String nombreEntrenador, String imagen)
        {
            if(!String.IsNullOrEmpty(nombre) && nombre.Length <= 40) { this.nombre = nombre; } else { this.nombre = "default"; }
            this.conferencia = conferencia;
            if(String.IsNullOrEmpty(ciudad)) { this.ciudad = ciudad; } else if(ciudad.Length <= 30) { this.ciudad = ciudad; }
            if(anhoFundacion >= 1946 && anhoFundacion <= DateTime.Now.Year) { this.anhoFundacion = anhoFundacion; } else { this.anhoFundacion = 1946; }
            if(numeroTitulos >= 0) { this.numeroTitulos = numeroTitulos; } else { this.numeroTitulos = 0; }
            if(String.IsNullOrEmpty(estadio)) { this.estadio = estadio; } else if(estadio.Length <= 30) { this.estadio = estadio; }
            if(String.IsNullOrEmpty(nombreEntrenador)) { this.nombreEntrenador = nombreEntrenador; } else if(nombreEntrenador.Length <= 25) { this.nombreEntrenador = nombreEntrenador; }
            if(!String.IsNullOrEmpty(imagen) && imagen.Length <= 150) { this.imagen = imagen; } else { this.imagen = "default"; }
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
            set { if(!String.IsNullOrEmpty(value) && value.Length <= 40) { this.nombre = value; } else { this.nombre = "default"; } }
        }

        public EnumConferencias Conferencia
        {
            get { return conferencia; }
            set { this.conferencia = value; }
        }

        public String Ciudad
        {
            get { return ciudad; }
            set { if(String.IsNullOrEmpty(value)) { this.ciudad = value; } else if (value.Length <= 30) { this.ciudad = value; } }
        }

        public short AnhoFundacion
        {
            get { return anhoFundacion; }
            set { if(value >= 1946 && value <= DateTime.Now.Year) { this.anhoFundacion = value; } else { this.anhoFundacion = 1946; } }
        }

        public byte NumeroTitulos
        {
            get { return numeroTitulos; }
            set { if(value >= 0) { this.numeroTitulos = value; } else { this.numeroTitulos = 0; } }
        }

        public String Estadio
        {
            get { return estadio; }
            set { if(String.IsNullOrEmpty(value)) { this.estadio = value; } else if (value.Length <= 30) { this.estadio = value; } }
        }

        public String NombreEntrenador
        {
            get { return nombreEntrenador; }
            set { if(String.IsNullOrEmpty(value)) { this.nombreEntrenador = value; } else if (value.Length <= 25) { this.nombreEntrenador = value; } }
        }

        public String Imagen
        {
            get { return imagen; }
            set { if(!String.IsNullOrEmpty(value) && value.Length <= 150) { this.imagen = value; } else { this.imagen = "default"; } }
        }

        #endregion

    }
}
