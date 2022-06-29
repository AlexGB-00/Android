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
    public class ClsListadosUsuariosBL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public ClsUsuario comprobarUsuarioExistenteBL(String usuario, String contrasenha, bool loginCorreo)
        /// -> "loginCorreo" tomará el valor true (login con correo electrónico) o el valor false (login con nick).
        /// Propósito: comprobar si existe un determinado usuario con tales credenciales en la BBDD.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: tanto "usuario" como "contrasenha" no deben ser cadenas vacías y deben ser distintos de null.
        /// Entradas: el usuario y la contraseña junto con la variable "loginCorreo" que determinará la forma de comprobación.
        /// Salidas: el usuario (si existe en la BBDD) o null (en caso de que no exista).
        /// Postcondiciones: se devuelve el usuario asociado al nombre de la función.
        /// </summary>
        /// <param name="usuario"></param>
        /// <param name="contrasenha"></param>
        /// <param name="loginCorreo"></param>
        /// <returns></returns>
        public ClsUsuario comprobarUsuarioExistenteBL(String usuario, String contrasenha, bool loginCorreo)
        {

            ClsUsuario usuarioRegistrado;

            ClsListadosUsuariosDAL clsListadosUsuariosDAL = new ClsListadosUsuariosDAL();

            try
            {
                usuarioRegistrado = clsListadosUsuariosDAL.comprobarUsuarioExistenteDAL(usuario, contrasenha, loginCorreo);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return usuarioRegistrado;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public String comprobarIdentificadorUsuarioExistenteBL(String usuario, bool metodoCorreo) 
        /// Propósito: comprobar si existe un usuario con un determinado nick o correo electrónico en la BBDD.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "usuario" no debe ser una cadena vacía y debe ser distinto de null.
        /// Entradas: el "usuario" que representará el identificador (o bien el nick, o bien el correo) con el que se desea comprobar la existencia de dicho usuario y, por otro lado,
        /// "metodoCorreo" que indicará si el primer parámetro representa el nick o el correo electrónico del usuario.
        /// Salidas: una cadena de texto (String) que representará el nick del usuario en caso de que exista. En caso contrario, devolverá null.
        /// Postcondiciones: se devuelve el nick del usuario asociado al nombre de la función en caso de que exista.
        /// </summary>
        /// <param name="usuario"></param>
        /// <param name="metodoCorreo"></param>
        /// <returns></returns>
        public String comprobarIdentificadorUsuarioExistenteBL(String usuario, bool metodoCorreo)
        {

            String nickUsuario;

            ClsListadosUsuariosDAL clsListadosUsuariosDAL = new ClsListadosUsuariosDAL();

            try
            {
                nickUsuario = clsListadosUsuariosDAL.comprobarIdentificadorUsuarioExistenteDAL(usuario, metodoCorreo);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return nickUsuario;

        }

    }
}
