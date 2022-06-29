using NBA_MyTeam_DAL.Gestoras;
using NBA_MyTeam_Entities.Basicas;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NBA_MyTeam_BL.Gestoras
{
    public class ClsGestoraUsuariosBL
    {

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int insertUsuarioBL(ClsUsuario nuevoUsuario)
        /// Propósito: insertar un nuevo usuario en la BBDD con los datos del objeto "nuevoUsuario" pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "nuevoUsuario" debe ser distinto de null.
        /// Entradas: el usuario a insertar.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nuevoUsuario"></param>
        /// <returns></returns>
        public int insertUsuarioBL(ClsUsuario nuevoUsuario)
        {

            int filasAfectadas;

            ClsGestoraUsuariosDAL clsGestoraUsuariosDAL = new ClsGestoraUsuariosDAL();

            try
            {
                filasAfectadas = clsGestoraUsuariosDAL.insertUsuarioDAL(nuevoUsuario);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int updateUsuarioBL(ClsUsuario usuario)
        /// Propósito: actualizar un determinado usuario de la BBDD, sustituyendo sus actuales datos por los del objeto "usuario" pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL.
        /// Precondiciones: "usuario" debe ser distinto de null.
        /// Entradas: el usuario con los datos a actualizar.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="usuario"></param>
        /// <returns></returns>
        public int updateUsuarioBL(ClsUsuario usuario)
        {

            int filasAfectadas;

            ClsGestoraUsuariosDAL clsGestoraUsuariosDAL = new ClsGestoraUsuariosDAL();

            try
            {
                filasAfectadas = clsGestoraUsuariosDAL.updateUsuarioDAL(usuario);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

        /// <summary>
        /// ESTUDIO INTERFAZ
        /// Prototipo: public int deleteUsuarioBL(String nickUsuario)
        /// Propósito: eliminar un determinado usuario de la BBDD, cuyo nick es pasado como parámetro.
        /// Para ello hará uso de la llamada a la capa DAL. 
        /// Precondiciones: "nickUsuario" no debe ser una cadena vacía y debe distinto de null.
        /// Entradas: el nick del usuario.
        /// Salidas: el número de filas afectadas por la instrucción.
        /// Postcondiciones: se devuelve el número de filas afectadas asociado al nombre de la función.
        /// </summary>
        /// <param name="nickUsuario"></param>
        /// <returns></returns>
        public int deleteUsuarioBL(String nickUsuario)
        {

            int filasAfectadas;

            ClsGestoraUsuariosDAL clsGestoraUsuariosDAL = new ClsGestoraUsuariosDAL();

            try
            {
                filasAfectadas = clsGestoraUsuariosDAL.deleteUsuarioDAL(nickUsuario);
            }
            catch (SqlException e)
            {
                throw e;
            }

            return filasAfectadas;

        }

    }
}
