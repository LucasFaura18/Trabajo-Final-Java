-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-04-2025 a las 10:08:33
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectoprogramacion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actores`
--

CREATE TABLE `actores` (
  `CodAct` int(11) NOT NULL,
  `NomAct` varchar(150) NOT NULL,
  `ApeAct` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `actores`
--

INSERT INTO `actores` (`CodAct`, `NomAct`, `ApeAct`) VALUES
(1, 'Leonardo', 'DiCaprio'),
(2, 'Scarlett', 'Johansson'),
(3, 'Robert', 'Downey Jr.'),
(4, 'Natalie', 'Portman'),
(5, 'Santiago', 'Segura'),
(6, 'Jhonny', 'Deep');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `participa`
--

CREATE TABLE `participa` (
  `CodPel` int(11) NOT NULL,
  `CodPer` int(11) NOT NULL,
  `Rol` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `participa`
--

INSERT INTO `participa` (`CodPel`, `CodPer`, `Rol`) VALUES
(1, 1, 'Protagonista'),
(2, 2, 'Protagonista'),
(2, 3, 'Protagonista'),
(4, 4, 'Protagonista'),
(6, 5, 'Protagonista'),
(7, 6, 'Protagonista');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

CREATE TABLE `peliculas` (
  `CodPel` int(11) NOT NULL,
  `TitPel` varchar(150) NOT NULL,
  `GenPel` varchar(150) NOT NULL,
  `duracion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`CodPel`, `TitPel`, `GenPel`, `duracion`) VALUES
(1, 'Inception', 'Ciencia Ficción', 148),
(2, 'Avengers: Endgame', 'Acción', 181),
(3, 'Lucy', 'Ciencia Ficción', 89),
(4, 'Black Swan', 'Drama', 108),
(5, 'Harry Potter', 'Fantasia', 137),
(6, 'Torrente 5', 'accion', 105),
(7, 'Piratas del Caribe', 'Accion', 120);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personajes`
--

CREATE TABLE `personajes` (
  `CodPer` int(11) NOT NULL,
  `NomPer` varchar(150) NOT NULL,
  `GenPer` varchar(150) NOT NULL,
  `CodAct` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personajes`
--

INSERT INTO `personajes` (`CodPer`, `NomPer`, `GenPer`, `CodAct`) VALUES
(1, 'Dom Cobb', 'Masculino', 1),
(2, 'Natasha Romanoff', 'Femenino', 2),
(3, 'Tony Stark', 'Masculino', 3),
(4, 'Nina Sayers', 'Femenino', 4),
(5, 'Torrente', 'Masculino', 5),
(6, 'Jack Sparrow', 'Masculino', 6);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actores`
--
ALTER TABLE `actores`
  ADD PRIMARY KEY (`CodAct`),
  ADD KEY `NomAct` (`NomAct`),
  ADD KEY `ApeAct` (`ApeAct`);

--
-- Indices de la tabla `participa`
--
ALTER TABLE `participa`
  ADD PRIMARY KEY (`CodPel`,`CodPer`),
  ADD KEY `CodPer` (`CodPer`);

--
-- Indices de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD PRIMARY KEY (`CodPel`),
  ADD KEY `TitPel` (`TitPel`),
  ADD KEY `GenPel` (`GenPel`),
  ADD KEY `duracion` (`duracion`);

--
-- Indices de la tabla `personajes`
--
ALTER TABLE `personajes`
  ADD PRIMARY KEY (`CodPer`),
  ADD KEY `CodAct` (`CodAct`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actores`
--
ALTER TABLE `actores`
  MODIFY `CodAct` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  MODIFY `CodPel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `personajes`
--
ALTER TABLE `personajes`
  MODIFY `CodPer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `participa`
--
ALTER TABLE `participa`
  ADD CONSTRAINT `participa_ibfk_1` FOREIGN KEY (`CodPel`) REFERENCES `peliculas` (`CodPel`),
  ADD CONSTRAINT `participa_ibfk_2` FOREIGN KEY (`CodPer`) REFERENCES `personajes` (`CodPer`);

--
-- Filtros para la tabla `personajes`
--
ALTER TABLE `personajes`
  ADD CONSTRAINT `personajes_ibfk_1` FOREIGN KEY (`CodAct`) REFERENCES `actores` (`CodAct`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
