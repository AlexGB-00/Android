
USE agata
GO

-- Estructura tablas

-- Tabla Usuarios
CREATE TABLE Usuarios (

	Nick VARCHAR(20) NOT NULL,
	CorreoElectronico VARCHAR(45) NOT NULL,
	Contrasenha VARCHAR(32) NOT NULL,
	NombreCompleto VARCHAR(40) NULL,
	Imagen VARCHAR(150) NULL,

	CONSTRAINT PKUsuarios PRIMARY KEY(Nick),
	CONSTRAINT UQCorreoElectronico UNIQUE(CorreoElectronico),
	CONSTRAINT CKNick CHECK(DATALENGTH(Nick) >= 3),
	CONSTRAINT CKCorreoElectronico CHECK(CorreoElectronico LIKE '%@%.%'),
	CONSTRAINT CKConstrasenha CHECK(DATALENGTH(Contrasenha) >= 8)

)
GO

-- Tabla Alineaciones
CREATE TABLE Alineaciones (

	ID INT NOT NULL IDENTITY(1,1),
	Nombre VARCHAR(20) NOT NULL,
	Sistema VARCHAR(12) NOT NULL DEFAULT 'DOS_DOS_UNO',
	ValoracionMedia TINYINT NULL DEFAULT 0,
	FechaCreacion DATE NULL DEFAULT CURRENT_TIMESTAMP,		
	Finalizada BIT NULL DEFAULT 0,
	NickUsuario VARCHAR(20) NOT NULL,

	CONSTRAINT PKAlineaciones PRIMARY KEY(ID),
	CONSTRAINT FKAlineacionesUsuarios FOREIGN KEY(NickUsuario) REFERENCES Usuarios(Nick) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT CKSistema CHECK (Sistema IN ('DOS_DOS_UNO', 'UNO_DOS_DOS', 'DOS_UNO_DOS', 'UNO_TRES_UNO', 'TRES_UNO_UNO')),	
	CONSTRAINT CKValoracionMedia CHECK(ValoracionMedia BETWEEN 0 AND 99),
	CONSTRAINT CKFechaCreacion CHECK(FechaCreacion <= CURRENT_TIMESTAMP)

)
GO

-- Tabla Equipos
CREATE TABLE Equipos (

	ID INT NOT NULL IDENTITY(1,1),
	Nombre VARCHAR(40) NOT NULL,
	Conferencia VARCHAR(5) NULL,
	Ciudad VARCHAR(30) NULL,
	AnhoFundacion SMALLINT NULL,
	NumeroTitulos TINYINT NULL,
	Estadio VARCHAR(30) NULL,
	NombreEntrenador VARCHAR(25) NULL,
	Imagen VARCHAR(150) NOT NULL,

	CONSTRAINT PKEquipos PRIMARY KEY(ID),
	CONSTRAINT UQNombreEquipo UNIQUE(Nombre),
	CONSTRAINT UQImagenEquipo UNIQUE(Imagen),
	CONSTRAINT CKConferencia CHECK(Conferencia IN ('ESTE', 'OESTE')),
	CONSTRAINT CKAnhoFundacion CHECK(AnhoFundacion BETWEEN 1946 AND YEAR(CURRENT_TIMESTAMP)),
	CONSTRAINT CKNumeroTitulos CHECK(NumeroTitulos >= 0)

)
GO

-- Tabla Jugadores(Cartas)
CREATE TABLE Jugadores (

	ID INT NOT NULL IDENTITY(1,1),
	Nombre VARCHAR(40) NOT NULL,
	Edad TINYINT NULL,
	Nacionalidad VARCHAR(30) NULL,
	Altura DECIMAL(3,2) NULL,
	Peso TINYINT NULL,
	Dorsal TINYINT NULL,
	Posicion VARCHAR(9) NOT NULL,
	ValoracionGeneral TINYINT NOT NULL,
	Imagen VARCHAR(150) NOT NULL,
	IDEquipo INT NOT NULL,

	CONSTRAINT PKJugadores PRIMARY KEY(ID),
	CONSTRAINT FKJugadoresEquipos FOREIGN KEY(IDEquipo) REFERENCES Equipos(ID) ON DELETE NO ACTION ON UPDATE CASCADE,
	CONSTRAINT CKAltura CHECK(Altura >= 1.50),
	CONSTRAINT CKPeso CHECK(Peso >= 50),
	CONSTRAINT CKEdad CHECK(Edad >= 19),
	CONSTRAINT CKPosicion CHECK(Posicion IN ('BASE', 'ESCOLTA', 'ALERO', 'ALA_PIVOT', 'PIVOT')),
	CONSTRAINT CKValoracion CHECK(ValoracionGeneral BETWEEN 70 AND 99),
	CONSTRAINT CKDorsal CHECK(Dorsal BETWEEN 0 AND 99)

)
GO

-- Tabla Valoraciones
CREATE TABLE Valoraciones (

	NickUsuario VARCHAR(20) NOT NULL,
	IDAlineacion INT NOT NULL,
	Rating TINYINT NULL,
	Descripcion VARCHAR(250) NULL,

	CONSTRAINT PKValoraciones PRIMARY KEY(NickUsuario, IDAlineacion),
	CONSTRAINT FKValoracionesUsuarios FOREIGN KEY(NickUsuario) REFERENCES Usuarios(Nick) ON DELETE NO ACTION ON UPDATE CASCADE,
	CONSTRAINT FKValoracionesAlineaciones FOREIGN KEY(IDAlineacion) REFERENCES Alineaciones(ID) ON DELETE CASCADE ON UPDATE NO ACTION,
	CONSTRAINT CKRating CHECK(Rating BETWEEN 0 AND 10)

)
GO

-- Tabla JugadoresClubs
CREATE TABLE JugadoresClubs (

	IDJugador INT NOT NULL,
	NickUsuario VARCHAR(20) NOT NULL,
	FechaIncorporacion DATETIME NOT NULL,

	CONSTRAINT PKUsuariosJugadores PRIMARY KEY(IDJugador, NickUsuario),
	CONSTRAINT FKUsuariosJugadoresJugadores FOREIGN KEY(IDJugador) REFERENCES Jugadores(ID) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FKUsuariosJugadoresUsuarios FOREIGN KEY(NickUsuario) REFERENCES Usuarios(Nick) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT CKFechaIncorporacion CHECK (FechaIncorporacion <= DATEADD(HOUR, 2, CURRENT_TIMESTAMP))

)
GO

-- Tabla JugadoresAlineaciones
CREATE TABLE JugadoresAlineaciones (

	IDJugador INT NOT NULL,
	IDAlineacion INT NOT NULL,

	CONSTRAINT PKAlineacionesJugadores PRIMARY KEY(IDJugador, IDAlineacion),
	CONSTRAINT FKAlineacionesJugadoresJugadores FOREIGN KEY(IDJugador) REFERENCES Jugadores(ID) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FKAlineacionesJugadoresAlineaciones FOREIGN KEY(IDAlineacion) REFERENCES Alineaciones(ID) ON DELETE CASCADE ON UPDATE CASCADE

)
GO


--Funciones

--  Estudio Interfaz
--	 Prototipo: CREATE FUNCTION ObtenerPosicionAlternativa (@posicionActual VARCHAR(9)) RETURNS VARCHAR(9).
--	 Propósito: obtener la posición alternativa a la pasada como parámetro.
--	 Precondiciones: la posición debe ser una posición definida (BASE, ESCOLTA, ALERO, ALA_PIVOT, PIVOT).
--   Entradas: la posición.
--   Salidas: la nueva posición.
--   Postcondiciones: se devuelve la nueva posición alternativa asociada al nombre.

CREATE FUNCTION ObtenerPosicionAlternativa (@posicionActual VARCHAR(9)) RETURNS VARCHAR(9) AS

	BEGIN
		
		DECLARE @nuevaPosicion VARCHAR(9)

		SET @nuevaPosicion = (CASE @posicionActual
									WHEN 'BASE' THEN 'ESCOLTA'
									WHEN 'ESCOLTA' THEN 'BASE'
									WHEN 'ALERO' THEN 'ESCOLTA'
									WHEN 'ALA_PIVOT' THEN 'PIVOT'
									WHEN 'PIVOT' THEN 'ALA_PIVOT'
							  END)

		RETURN @nuevaPosicion

	END

GO

PRINT 'Resultado: ' + [dbo].ObtenerPosicionAlternativa('ALERO')
GO

--  Estudio Interfaz
--	 Prototipo: CREATE FUNCTION CalcularValoracionMedia (@idAlineacion INT) RETURNS TINYINT.
--	 Propósito: calcular la valoración media de una alineación determinada a partir de la valoración de sus jugadores.
--   La valoración media resultante entre los jugadores será dividada por el número de jugadores que tiene una alineación (5).
--	 Precondiciones: el id de la alineación debe ser mayor que 0.
--   Entradas: el id de la alineación.
--   Salidas: la valoración media de la alineación.
--   Postcondiciones: se devuelve la valoración media asociada al nombre.

CREATE FUNCTION CalcularValoracionMedia (@idAlineacion INT) RETURNS TINYINT AS

	BEGIN

		DECLARE @valoracionMedia TINYINT

		SET @valoracionMedia = (SELECT SUM(ValoracionGeneral)/5 AS ValoracionMedia FROM Jugadores AS J 
		INNER JOIN JugadoresAlineaciones AS JA ON J.ID = JA.IDJugador 
		WHERE JA.IDAlineacion = @idAlineacion)

		RETURN @valoracionMedia

	END

GO

--Pruebas
PRINT @@trancount
BEGIN TRANSACTION 

INSERT INTO Alineaciones (Nombre, NickUsuario) VALUES ('Prueba', 'UsuPrueba')
INSERT INTO JugadoresAlineaciones (IDJugador, IDAlineacion) VALUES (1, 1)
INSERT INTO JugadoresAlineaciones (IDJugador, IDAlineacion) VALUES (3, 1)


ROLLBACK

SELECT * FROM Equipos
SELECT * FROM Usuarios
SELECT * FROM Alineaciones
SELECT * FROM Jugadores
SELECT * FROM JugadoresAlineaciones

DECLARE @Res TINYINT 
SET @Res = [dbo].CalcularValoracionMedia(5)
PRINT @Res
GO


-- Procedimientos

--Usuario prueba
INSERT INTO Usuarios (Nick, CorreoElectronico, Contrasenha, NombreCompleto, Imagen) VALUES ('UsuPrueba', 'usuprueba@gmail.com', 'prueba123', 'Usuario Prueba', 'https://es.global.nba.com/media/img/teams/00/logos/GSW_logo.svg')
GO	

--  Estudio Interfaz
--	 Prototipo: CREATE PROCEDURE PoblarEquipos.
--	 Propósito: grabar en la tabla Equipos todos los equipos existentes en la NBA.
--	 Precondiciones: ninguna.
--   Entradas: ninguna.
--   Salidas: ninguna.
--   Postcondiciones: ninguna.

CREATE PROCEDURE PoblarEquipos AS

	BEGIN

		INSERT INTO Equipos (Nombre, Conferencia, Ciudad, AnhoFundacion, NumeroTitulos, Estadio, NombreEntrenador, Imagen) VALUES 
		('Atlanta Hawks', 'ESTE', 'Atlanta', 1946, 1, 'State Farm Arena', 'Nate McMillan', 'https://es.global.nba.com/media/img/teams/00/logos/ATL_logo.svg'),
		('Boston Celtics', 'ESTE', 'Boston', 1946, 17, 'TD Garden', 'Brad Stevens', 'https://es.global.nba.com/media/img/teams/00/logos/BOS_logo.svg'),
		('Brooklyn Nets', 'ESTE', 'New York', 1967, 0, 'Barclays Center', 'Steve Nash', 'https://es.global.nba.com/media/img/teams/00/logos/BKN_logo.svg'),
		('Charlotte Hornets', 'ESTE', 'Charlotte', 1988, 1, 'Spectrum Center', 'James Borrego', 'https://es.global.nba.com/media/img/teams/00/logos/CHA_logo.svg'),
		('Chicago Bulls', 'ESTE', 'Chicago', 1966, 6, 'United Center', 'Billy Donovan', 'https://es.global.nba.com/media/img/teams/00/logos/CHI_logo.svg'),
		('Cleveland Cavaliers ', 'ESTE', 'Cleveland', 1970, 1, 'Rocket Mortgage FiledHouse', 'J.B.Bickerstaff', 'https://es.global.nba.com/media/img/teams/00/logos/CLE_logo.svg'),
		('Detroit Pistons', 'ESTE', 'Detroit', 1946, 3, 'Little Caesars Arena', 'Dwane Casey', 'https://es.global.nba.com/media/img/teams/00/logos/DET_logo.svg'), 
		('Indiana Pacers', 'ESTE', 'Indianapolis', 1967, 0, 'Barkers Life FieldHouse', 'Nate Bjorkgren', 'https://es.global.nba.com/media/img/teams/00/logos/IND_logo.svg'),
		('Miami Heat', 'ESTE', 'Miami', 1988, 3, 'American Airlines Arena', 'Erik Spoelstra', 'https://es.global.nba.com/media/img/teams/00/logos/MIA_logo.svg'),
		('Milwaukee Bucks', 'ESTE', 'Milwaukee', 1968, 1, 'Fiserv Forum', 'Mike BudenHolzer', 'https://es.global.nba.com/media/img/teams/00/logos/MIL_logo.svg'),
		('New York Knicks', 'ESTE', 'New York', 1946, 2, 'Madison Square Garden', 'Tom Thibodeau', 'https://es.global.nba.com/media/img/teams/00/logos/NYK_logo.svg'),
		('Orlando Magic', 'ESTE', 'Orlando', 1989, 0, 'Amway Center', 'Steve Clifford', 'https://es.global.nba.com/media/img/teams/00/logos/ORL_logo.svg'),
		('Philadelphia 76ers', 'ESTE', 'Philadelphia', 1946, 3, 'Wells Fargo Center', 'Doc Rivers', 'https://es.global.nba.com/media/img/teams/00/logos/PHI_logo.svg'),
		('Toronto Raptors', 'ESTE', 'Toronto', 1995, 1, 'Scotiabank Arena', 'Nick Nurse', 'https://es.global.nba.com/media/img/teams/00/logos/TOR_logo.svg'),
		('Washington Wizards', 'ESTE', 'Washington', 1961, 1, 'Capital One Arena', 'Scott Brooks', 'https://es.global.nba.com/media/img/teams/00/logos/WAS_logo.svg'), 
		('Dallas Mavericks', 'OESTE', 'Dallas', 1980, 1, 'American Airlines Center', 'Rick Carlisle', 'https://es.global.nba.com/media/img/teams/00/logos/DAL_logo.svg'),
		('Denver Nuggets', 'OESTE', 'Denver', 1967, 0, 'Ball Arena', 'Michael Malone', 'https://es.global.nba.com/media/img/teams/00/logos/DEN_logo.svg'),
		('Golden State Warriors', 'OESTE', 'San Francisco', 1946, 6, 'Chase Center', 'Steve Kerr', 'https://es.global.nba.com/media/img/teams/00/logos/GSW_logo.svg'),
		('Houston Rockets', 'OESTE', 'Houston', 1967, 2, 'Toyota Center', 'Stephen Silas', 'https://es.global.nba.com/media/img/teams/00/logos/HOU_logo.svg'),
		('Los Angeles Clippers', 'OESTE', 'Los Angeles', 1970, 0, 'Staples Center', 'Tyronn Lue', 'https://es.global.nba.com/media/img/teams/00/logos/LAC_logo.svg'),
		('Los Angeles Lakers', 'OESTE', 'Los Angeles', 1947, 17, 'Staples Center', 'Frank Vogel', 'https://es.global.nba.com/media/img/teams/00/logos/LAL_logo.svg'), 
		('Memphis Grizzlies', 'OESTE', 'Menphis', 1995, 0, 'FedExForum', 'Taylor Jenkins', 'https://es.global.nba.com/media/img/teams/00/logos/MEM_logo.svg'),
		('Minnesota Timberwolves', 'OESTE', 'Minneapolis', 1989, 0, 'Target Center', 'Chris Finch', 'https://es.global.nba.com/media/img/teams/00/logos/MIN_logo.svg'),
		('New Orleans Pelicans', 'OESTE', 'New Orleans', 1988, 0, 'Smoothie King Center', 'Stan Van Gundy', 'https://es.global.nba.com/media/img/teams/00/logos/NOP_logo.svg'),
		('Oklahoma City Thunder', 'OESTE', 'Oklahoma', 2008, 1, 'Chesapeake Energy Arena', 'Mark Daigneault', 'https://es.global.nba.com/media/img/teams/00/logos/OKC_logo.svg'),
		('Phoenix Suns', 'OESTE', 'Phoenix', 1968, 0, 'Phoenix Suns Arena', 'Monty Williams', 'https://es.global.nba.com/media/img/teams/00/logos/PHX_logo.svg'),
		('Portland Trail Blazers', 'OESTE', 'Portland', 1970, 1, 'Moda Center', 'Terry Stotts', 'https://es.global.nba.com/media/img/teams/00/logos/POR_logo.svg'),
		('Sacramento Kings', 'OESTE', 'Sacramento', 1946, 1, 'Golden 1 Center', 'Luke Walton', 'https://es.global.nba.com/media/img/teams/00/logos/SAC_logo.svg'),
		('San Antonio Spurs', 'OESTE', 'San Antonio', 1967, 5, 'AT&T Center', 'Gregg Popovich', 'https://es.global.nba.com/media/img/teams/00/logos/SAS_logo.svg'), 
		('Utah Jazz', 'OESTE', 'Salt Lake City', 1974, 0, 'Vivint Arena', 'Quin Snyder', 'https://es.global.nba.com/media/img/teams/00/logos/UTA_logo.svg')
	
	END

GO

EXECUTE PoblarEquipos
GO

--  Estudio Interfaz
--	 Prototipo: CREATE PROCEDURE PoblarJugadores.
--	 Propósito: grabar en la tabla Jugadores una serie de jugadores pertenecientes a distintos equipos de la NBA.
--	 Precondiciones: ninguna.
--   Entradas: ninguna.
--   Salidas: ninguna.
--   Postcondiciones: ninguna.

CREATE PROCEDURE PoblarJugadores AS

	BEGIN

		INSERT INTO Jugadores (Nombre, Edad, Nacionalidad, Altura, Peso, Dorsal, Posicion, ValoracionGeneral, Imagen, IDEquipo) VALUES 
		('Trae Young', 22, 'Estados Unidos', 1.85, 82, 11, 'BASE', 91, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629027.png', 1),
		('Clint Capela', 26, 'Suiza', 2.08, 109, 15, 'PIVOT', 87, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203991.png', 1),
		('Jaylen Brown', 24, 'Estados Unidos', 1.98, 101, 7, 'ALERO', 90, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1627759.png', 2),
		('Jayson Tatum', 23, 'Estados Unidos', 2.03, 95, 0, 'ALA_PIVOT', 91, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1628369.png', 2),
		('James Harden', 31, 'Estados Unidos', 1.96, 100, 13, 'BASE', 92, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/201935.png', 3), 
		('Kevin Durant', 32, 'Estados Unidos', 2.08, 109, 7, 'ALA_PIVOT', 93, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/201142.png', 3),	
		('LaMelo Ball', 19, 'Estados Unidos', 2.01, 86, 2, 'BASE', 86, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1630163.png', 4),
		('Jalen McDaniels', 23, 'Estados Unidos', 2.06, 88, 6, 'ALA_PIVOT', 85, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629667.png', 4),
		('Zach LaVine', 26, 'Estados Unidos', 1.98, 91, 8, 'ESCOLTA', 88, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203897.png', 5), 
		('Nikola Vucevic', 30, 'Montenegro', 2.11, 118, 9, 'PIVOT', 87, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/202696.png', 5),
		('Larry Nance Jr', 28, 'Estados Unidos', 2.01, 111, 22, 'ALA_PIVOT', 85, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1626204.png', 6), 
		('Kevin Love', 32, 'Estados Unidos', 2.03, 114, 0, 'ALA_PIVOT', 85, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/201567.png', 6),
		('Hamidou Diallo', 22, 'Estados Unidos', 1.96, 92, 6, 'ESCOLTA', 85, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1628977.png', 7), 
		('Mason Plumlee', 31, 'Estados Unidos', 2.11, 115, 24, 'PIVOT', 84, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203486.png', 7),
		('Domantas Sabonis', 24, 'Lituania', 2.11, 109, 11, 'ALA_PIVOT', 86, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1627734.png', 8), 
		('Myles Turner', 25, 'Estados Unidos', 2.11, 113, 33, 'PIVOT', 85, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1626167.png', 8),
		('Tyler Herro', 21, 'Estados Unidos', 1.96, 88, 14, 'ESCOLTA', 87, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629639.png', 9), 
		('Jimmy Butler', 31, 'Estados Unidos', 2.01, 104, 22,'ALERO', 89, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/202710.png', 9),
		('Khris Middleton', 29, 'Estados Unidos', 2.01, 101, 22, 'ALERO', 90, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203114.png', 10), 
		('Giannis Antetokounmpo', 26, 'Grecia', 2.11, 110, 34,'ALA_PIVOT', 94, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203507.png', 10),
		('Derrick Rose', 32, 'Estados Unidos', 1.88, 91, 4, 'BASE', 84, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/201565.png', 11),
		('Julius Randle', 26, 'Estados Unidos', 2.03, 113, 30, 'ALA_PIVOT', 84, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203944.png', 11),
		('Markelle Fultz', 22, 'Estados Unidos', 1.91, 95, 20, 'BASE', 83, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1628365.png', 12), 
		('Gary Harris', 26, 'Estados Unidos', 1.93, 95,  14, 'ESCOLTA', 82, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203914.png', 12), 
		('Ben Simmons', 24, 'Australia', 2.07, 109, 25, 'BASE', 87, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1627732.png', 13),
		('Joel Embiid', 27, 'Camerún', 2.13, 127, 21, 'PIVOT', 90, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203954.png', 13),
		('Kyle Lowry', 35, 'Estados Unidos', 1.83, 89, 7, 'BASE', 89, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/200768.png', 14),
		('Fred VanVleet', 27, 'Estados Unidos', 1.85, 89, 23, 'ESCOLTA', 86, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1627832.png', 14),
		('Russell Westbrook', 32, 'Estados Unidos', 1.91, 91, 4, 'BASE', 90, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/201566.png', 15), 
		('Bradley Beal', 27, 'Estados Unidos', 1.91, 94, 3, 'ESCOLTA', 89, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203078.png', 15), 
		('Luka Doncic', 22, 'Eslovenia', 2.01, 104, 77, 'BASE', 92, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629029.png', 16),
		('Kristaps Porzingis', 25, 'Letonia', 2.21, 109, 6, 'PIVOT', 90, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/204001.png', 16),
		('Jamal Murray', 24, 'Canadá', 1.93, 98, 27, 'BASE', 90, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1627750.png', 17), 
		('Nikola Jokic', 26, 'Serbia', 2.13, 129, 15, 'PIVOT', 93, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203999.png', 17),
		('Stephen Curry', 33, 'Estados Unidos', 1.91, 84, 30, 'BASE', 93, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/201939.png', 18), 
		('Klay Thompson', 31, 'Estados Unidos', 1.98, 98, 11, 'ESCOLTA', 90, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/202691.png', 18),
		('John Wall', 30, 'Estados Unidos', 1.93, 95, 1, 'BASE', 89, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/202322.png', 19), 
		('Christian Wood', 25, 'Estados Unidos', 2.08, 97, 35, 'PIVOT', 88, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1626174.png', 19),
		('Paul George', 30, 'Estados Unidos', 2.03, 100, 13, 'ESCOLTA', 90, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/202331.png', 20),
		('Kawhi Leonard', 29, 'Estados Unidos', 2.01, 102, 84, 'ALERO', 92, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/202695.png', 20), 
		('LeBron James', 36, 'Estados Unidos', 2.03, 113, 23, 'ALERO', 94, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/2544.png', 21), 
		('Anthony Davis', 28, 'Estados Unidos', 2.08, 115, 3, 'ALA_PIVOT', 92, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203076.png', 21),
		('Ja Morant', 21, 'Estados Unidos', 1.91, 79, 12, 'BASE', 88, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629630.png', 22), 
		('Jonas Valanciunas', 28, 'Lituania', 2.11, 120, 17, 'PIVOT', 86, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/202685.png', 22),
		('D''Angelo Russell', 25, 'Estados Unidos', 1.93, 88, 0, 'BASE', 87, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1626156.png', 23),
		('Ricky Rubio', 30, 'España', 1.91, 86, 9, 'BASE', 85, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/201937.png', 23),
		('Brandon Ingram', 23, 'Estados Unidos', 2.01, 86, 14, 'ALERO', 87, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1627742.png', 24),
		('Zion Williamson', 20, 'Estados Unidos', 1.98, 129, 1,'ALA_PIVOT', 89, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629627.png', 24),
		('Darius Bazley', 20, 'Estados Unidos', 2.03, 94, 7, 'ALA_PIVOT', 83, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629647.png', 25),
		('Moses Brown', 21, 'Estados Unidos', 2.18, 111, 9, 'PIVOT', 84, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1629650.png', 25),
		('Chris Paul', 35, 'Estados Unidos', 1.85, 79, 3, 'BASE', 87, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/101108.png', 26),
		('Devin Booker', 24, 'Estados Unidos', 1.96, 93, 1, 'ESCOLTA', 88, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1626164.png', 26),
		('Damian Lillard', 30, 'Estados Unidos', 1.88, 88, 0, 'BASE', 91, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203081.png', 27),
		('C.J.McCollum', 29, 'Estados Unidos', 1.91, 86, 3, 'ESCOLTA', 88, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203468.png', 27), 
		('De''Aaron Fox', 23, 'Estados Unidos', 1.91, 84, 5, 'BASE',  89, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1628368.png', 28), 
		('Harrison Barnes', 28, 'Estados Unidos', 2.03, 102, 40, 'ALA_PIVOT', 86, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203084.png', 28), 
		('Derrick White', 26, 'Estados Unidos', 1.93, 86, 4, 'ESCOLTA', 85, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1628401.png', 29),
		('DeMar DeRozan', 31, 'Estados Unidos', 1.98, 100, 10, 'ALERO', 89, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/201942.png', 29),
		('Donovan Mitchell', 24, 'Estados Unidos', 1.85, 98, 45, 'ESCOLTA', 89, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/1628378.png', 30),
		('Rudy Gobert', 28, 'Francia', 2.16, 117, 27, 'PIVOT', 89, 'https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/203497.png', 30)

		DECLARE cJugadores INSENSITIVE CURSOR FOR SELECT Nombre, Edad, Nacionalidad, Altura, Peso, Dorsal, Posicion, ValoracionGeneral, Imagen, IDEquipo FROM Jugadores
		DECLARE @nombre VARCHAR(40)
		DECLARE @nacionalidad VARCHAR(30)
		DECLARE @altura DECIMAL(3,2)
		DECLARE @edad TINYINT, @peso TINYINT, @dorsal TINYINT, @valoracionGeneral TINYINT
		DECLARE @posicion VARCHAR(9)
		DECLARE @imagen VARCHAR(150)
		DECLARE @idEquipo INT

		OPEN cJugadores

		FETCH NEXT FROM cJugadores INTO @nombre, @edad, @nacionalidad, @altura, @peso, @dorsal, @posicion, @valoracionGeneral, @imagen, @idEquipo

		WHILE @@FETCH_STATUS = 0
			
			BEGIN

				INSERT INTO Jugadores (Nombre, Edad, Nacionalidad, Altura, Peso, Dorsal, Posicion, ValoracionGeneral, Imagen, IDEquipo) VALUES 
				(@nombre, @edad, @nacionalidad, @altura, @peso, @dorsal, [dbo].ObtenerPosicionAlternativa(@posicion), (@valoracionGeneral - 3), @imagen, @idEquipo)

				FETCH NEXT FROM cJugadores INTO @nombre, @edad, @nacionalidad, @altura, @peso, @dorsal, @posicion, @valoracionGeneral, @imagen, @idEquipo

			END

		CLOSE cJugadores
		DEALLOCATE cJugadores

	END

GO

--Pruebas

PRINT @@trancount
BEGIN TRANSACTION

SELECT * FROM Jugadores
DELETE FROM Jugadores
DELETE FROM JugadoresAlineaciones
DELETE FROM Alineaciones
EXECUTE PoblarJugadores
GO

ROLLBACK
GO

--Excepciones
--EXECUTE sys.sp_addmessage @msgnum = 70000, @severity = 16, @msgtext = N'You can not modify a closed lineup', @lang = 'us_english', @replace = 'replace';
--EXECUTE sys.sp_addmessage @msgnum = 70000, @severity = 16, @msgtext = N'No se puede modificar una alineacion finalizada o cerrada', @lang = 'spanish', @replace = 'replace';
--GO


--Triggers

--Trigger que controla que no se puedan modificar alineaciones ya cerradas o finalizadas.
CREATE TRIGGER ModificarAlineacion ON Alineaciones AFTER UPDATE AS

	BEGIN

		IF EXISTS (SELECT * FROM deleted WHERE Finalizada = 1)

			BEGIN 

				ROLLBACK
				DECLARE @Message NVARCHAR(255) = 'You can not modify a closed lineup';
				THROW 70000, @Message, 1

			END

	END

GO

--Trigger que controla que no se puedan insertar jugadores en una alineación ya cerrada o finalizada.
CREATE TRIGGER ModificarJugadorAlineacionIns ON JugadoresAlineaciones AFTER INSERT, UPDATE AS

	BEGIN
	
		IF EXISTS (SELECT * FROM inserted AS I INNER JOIN Alineaciones AS A ON I.IDAlineacion = A.ID WHERE A.Finalizada = 1)

			BEGIN 

				ROLLBACK
				DECLARE @Message NVARCHAR(255) = 'You can not modify a closed lineup';
				THROW 70000, @Message, 1

			END

	END

GO

--Trigger que controla que no se puedan retirar jugadores en una alineación ya cerrada o finalizada.
CREATE TRIGGER ModificarJugadorAlineacionDel ON JugadoresAlineaciones AFTER DELETE AS

	BEGIN

		IF EXISTS (SELECT * FROM deleted AS D INNER JOIN Alineaciones AS A ON D.IDAlineacion = A.ID WHERE A.Finalizada = 1)

			BEGIN 

				ROLLBACK
				DECLARE @Message NVARCHAR(255) = 'You can not modify a closed lineup';
				THROW 70000, @Message, 1

			END

	END

GO

--Trigger que calcula automáticamente la valoración media de una alineación cada vez que se inserte un nuevo jugador en alguna de ellas.
CREATE TRIGGER ActualizarValoracionMediaAlineacionIns ON JugadoresAlineaciones AFTER INSERT, UPDATE AS

	BEGIN

		DECLARE cJugadoresAlineaciones CURSOR FOR SELECT IDAlineacion FROM inserted
		DECLARE @valoracionMedia TINYINT
		DECLARE @alineacion INT
		DECLARE @alineacionAux INT
		
		SET @alineacionAux = 0

		OPEN cJugadoresAlineaciones

		FETCH NEXT FROM cJugadoresAlineaciones INTO @alineacion

		WHILE @@FETCH_STATUS = 0

			BEGIN

				IF(@alineacion != @alineacionAux)

					BEGIN

						SET @valoracionMedia = [dbo].CalcularValoracionMedia(@alineacion)
		
						UPDATE Alineaciones
						SET ValoracionMedia = @valoracionMedia
						WHERE ID = @alineacion

						SET @alineacionAux = @alineacion

					END

				FETCH NEXT FROM cJugadoresAlineaciones INTO @alineacion

			END

		CLOSE cJugadoresAlineaciones
		DEALLOCATE cJugadoresAlineaciones	

	END

GO

--Trigger que calcula automáticamente la valoración media de una alineación cada vez que se retire un jugador de alguna de ellas.
CREATE TRIGGER ActualizarValoracionMediaAlineacionDel ON JugadoresAlineaciones AFTER DELETE AS

	BEGIN

		DECLARE cJugadoresAlineaciones CURSOR FOR SELECT IDAlineacion FROM deleted
		DECLARE @valoracionMedia TINYINT
		DECLARE @alineacion INT
		DECLARE @alineacionAux INT
		
		SET @alineacionAux = 0

		OPEN cJugadoresAlineaciones

		FETCH NEXT FROM cJugadoresAlineaciones INTO @alineacion

		WHILE @@FETCH_STATUS = 0

			BEGIN

				IF(@alineacion != @alineacionAux)

					BEGIN

						SET @valoracionMedia = [dbo].CalcularValoracionMedia(@alineacion)
		
						UPDATE Alineaciones
						SET ValoracionMedia = @valoracionMedia
						WHERE ID = @alineacion

						SET @alineacionAux = @alineacion

					END

				FETCH NEXT FROM cJugadoresAlineaciones INTO @alineacion

			END

		CLOSE cJugadoresAlineaciones
		DEALLOCATE cJugadoresAlineaciones	

	END

GO

--En cada DELETE solo se borra un registro --> DELETE FROM JugadoresCLubs WHERE IDJugador = @idJugador AND NickUsuario = @nickUsuario
--Trigger que se encarga de eliminar, para un determinado usuario, los registros de "JugadoresAlineaciones" cuyo "IDJugador" coincida con el "IDJugador" de los registros que se acaban de eliminar de la tabla "JugadoresClubs".
--CREATE TRIGGER EliminarJugadoresAlineaciones ON JugadoresClubs AFTER DELETE AS

--	BEGIN

--		DECLARE @jugador INT
--		DECLARE @usuario VARCHAR(20)

--		SET @jugador = (SELECT IDJugador FROM deleted) 
--		SET @usuario = (SELECT NickUsuario FROM deleted)

--		DELETE FROM JugadoresAlineaciones WHERE IDJugador = @jugador AND IDAlineacion IN (SELECT A.ID FROM Alineaciones AS A 
--																							INNER JOIN JugadoresAlineaciones AS JA ON A.ID = JA.IDAlineacion
--																							WHERE A.NickUsuario = @usuario AND JA.IDJugador = @jugador AND A.Finalizada = 0)

--	END

--GO


--Pruebas

BEGIN TRANSACTION

INSERT INTO Alineaciones (Nombre, NickUsuario) VALUES ('Prueba5', 'UsuPrueba')
SELECT * FROM Alineaciones
INSERT INTO Usuarios (Nick, CorreoElectronico, Contrasenha, NombreCompleto, Imagen) VALUES ('UsuPrueba', 'usuprueba@gmail.com', 'prueba123', 'Usuario Prueba', 'https://es.global.nba.com/media/img/teams/00/logos/GSW_logo.svg')
SELECT * FROM Usuarios
UPDATE Alineaciones SET Nombre = 'd' WHERE ID = 1 

SELECT * FROM Jugadores
INSERT INTO JugadoresAlineaciones (IDJugador, IDAlineacion) VALUES (10, 2)
SELECT * FROM JugadoresAlineaciones
DELETE FROM JugadoresAlineaciones WHERE IDJugador = 10 AND IDAlineacion = 2
UPDATE JugadoresAlineaciones SET IDJugador = 6 WHERE IDJugador = 2 AND IDAlineacion = 1

ROLLBACK

		
		