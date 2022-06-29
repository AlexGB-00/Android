using NBA_MyTeam_Entities.Enums;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_Entities.Basicas
{  
    public class ClsJugador
    {

        #region Atributos

        private int id;
        private String nombre;
        private byte edad;
        private String nacionalidad;
        private Decimal altura;
        private byte peso;
        private byte dorsal;
        private EnumPosiciones posicion;
        private byte valoracionGeneral;
        private String imagen;
        private int idEquipo;

        #endregion


        #region Constructores

        //Sin parámetros
        public ClsJugador()
        {
            nombre = "default";
            edad = 19;
            nacionalidad = null;
            altura = new Decimal(1.50);
            peso = 50;
            dorsal = 0;
            posicion = EnumPosiciones.BASE;
            valoracionGeneral = 70;
            imagen = "default";
            idEquipo = 1;
        }

        //Con parámetros
        public ClsJugador(String nombre, byte edad, String nacionalidad, Decimal altura, byte peso, byte dorsal, EnumPosiciones posicion, byte valoracionGeneral, String imagen, int idEquipo) 
        {
            if(!String.IsNullOrEmpty(nombre) && nombre.Length <= 40) { this.nombre = nombre; } else { this.nombre = "default"; }
            if(edad >= 19) { this.edad = edad; } else { this.edad = 19; }
            if(String.IsNullOrEmpty(nacionalidad)) { this.nacionalidad = nacionalidad; } else if(nacionalidad.Length <= 30) { this.nacionalidad = nacionalidad; }
            if(Decimal.Compare(altura, new Decimal(1.50)) >= 0) { this.altura = altura; } else { this.altura = new Decimal(1.50); }
            if(peso >= 50) { this.peso = peso; } else { this.peso = 50; }
            if(dorsal >= 0 && dorsal <= 99) { this.dorsal = dorsal; } else { this.dorsal = 0; }
            this.posicion = posicion;
            if(valoracionGeneral >= 70 && valoracionGeneral <= 99) { this.valoracionGeneral = valoracionGeneral; } else { this.valoracionGeneral = 70; }
            if(!String.IsNullOrEmpty(imagen) && imagen.Length <= 150) { this.imagen = imagen; } else { this.imagen = "default"; }
            if(idEquipo > 0) { this.idEquipo = idEquipo; } else { this.idEquipo = 1; }
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

        public byte Edad
        {
            get { return edad; }
            set { if(value >= 19) { this.edad = value; } else { this.edad = 19; } }
        }

        public String Nacionalidad
        {
            get { return nacionalidad; }
            set { if(String.IsNullOrEmpty(value)) { this.nacionalidad = value; } else if (value.Length <= 30) { this.nacionalidad = value; } }
        }

        public Decimal Altura
        {
            get { return altura; }
            set { if(Decimal.Compare(value, new Decimal(1.50)) >= 0) { this.altura = value; } else { this.altura = new Decimal(1.50); } }
        }

        public byte Peso
        {
            get { return peso; }
            set { if(value >= 50) { this.peso = value; } else { this.peso = 50; } }
        }

        public byte Dorsal
        {
            get { return dorsal; }
            set { if(value >= 0 && value <= 99) { this.dorsal = value; } else { this.dorsal = 0; } }
        }

        public EnumPosiciones Posicion
        {
            get { return posicion; }
            set { this.posicion = value; }
        }

        public byte ValoracionGeneral
        {
            get { return valoracionGeneral; }
            set { if(value >= 70 && value <= 99) { this.valoracionGeneral = value; } else { this.valoracionGeneral = 70; } }
        }

        public String Imagen
        {
            get { return imagen; }
            set { if(!String.IsNullOrEmpty(value) && value.Length <= 150) { this.imagen = value; } else { this.imagen = "default"; } }
        }

        public int IdEquipo
        {
            get { return idEquipo; }
            set { if(value > 0) { this.idEquipo = value; } else { this.idEquipo = 1; } }
        }

        #endregion

    }
}
