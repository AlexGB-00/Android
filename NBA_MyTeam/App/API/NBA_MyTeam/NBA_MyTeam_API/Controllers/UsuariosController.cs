using NBA_MyTeam_BL.Gestoras;
using NBA_MyTeam_BL.Listados;
using NBA_MyTeam_Entities.Basicas;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace NBA_MyTeam_API.Controllers
{
    public class UsuariosController : ApiController
    {

        // GET: api/Usuarios
        public ClsUsuario Get(String nombreUsuario, String contrasenha, bool loginCorreo)
        {
            
            ClsUsuario usuario;
            ClsListadosUsuariosBL clsListadosUsuariosBL = new ClsListadosUsuariosBL();

            try
            {
                usuario = clsListadosUsuariosBL.comprobarUsuarioExistenteBL(nombreUsuario, contrasenha, loginCorreo);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            if (usuario == null)
            {
                throw new HttpResponseException(HttpStatusCode.NoContent);
            }

            return usuario;

        }

        // GET: api/Usuarios
        public String Get(String usuario, bool metodoCorreo)
        {
            String nickUsuario;
            ClsListadosUsuariosBL clsListadosUsuariosBL = new ClsListadosUsuariosBL();

            try
            {
                nickUsuario = clsListadosUsuariosBL.comprobarIdentificadorUsuarioExistenteBL(usuario, metodoCorreo);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            if (nickUsuario == null)
            {
                throw new HttpResponseException(HttpStatusCode.NoContent);
            }

            return nickUsuario;
        }

        // GET: api/Usuarios
        public DateTime? Get(String nickUsuario)
        {

            DateTime? ultimaFecha;
            ClsListadosJugadoresClubsBL clsListadosJugadoresClubsBL = new ClsListadosJugadoresClubsBL();

            try
            {
                ultimaFecha = clsListadosJugadoresClubsBL.getUltimaFechaIncorporacionUsuarioBL(nickUsuario);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            if (ultimaFecha == null)
            {
                throw new HttpResponseException(HttpStatusCode.NoContent);
            }

            return ultimaFecha;

        }

        // POST: api/Usuarios
        public int Post([FromBody] ClsUsuario usuario)
        {

            int filasAfectadas;
            ClsGestoraUsuariosBL clsGestoraUsuariosBL = new ClsGestoraUsuariosBL();

            try
            {
                filasAfectadas = clsGestoraUsuariosBL.insertUsuarioBL(usuario);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }

        // PUT: api/Usuarios
        public int Put([FromBody] ClsUsuario usuario)
        {

            int filasAfectadas;
            ClsGestoraUsuariosBL clsGestoraUsuariosBL = new ClsGestoraUsuariosBL();

            try
            {
                filasAfectadas = clsGestoraUsuariosBL.updateUsuarioBL(usuario);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }
            
        // DELETE: api/Usuarios/{nick}
        public int Delete(String nick)
        {
            
            int filasAfectadas;
            ClsGestoraUsuariosBL clsGestoraUsuariosBL = new ClsGestoraUsuariosBL();

            try
            {
                filasAfectadas = clsGestoraUsuariosBL.deleteUsuarioBL(nick);
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }

            return filasAfectadas;

        }
       
    }
}
