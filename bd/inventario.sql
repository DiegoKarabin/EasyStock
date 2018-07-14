-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 17-07-2018 a las 04:46:04
-- Versión del servidor: 5.5.16
-- Versión de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `inventario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bitacora_compras`
--

CREATE TABLE IF NOT EXISTS `bitacora_compras` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_proveedor` varchar(12) NOT NULL,
  `codigo_producto` varchar(5) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `codigo_producto` (`codigo_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bitacora_movimientos`
--

CREATE TABLE IF NOT EXISTS `bitacora_movimientos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(50) NOT NULL,
  `accion` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cabecera_cot`
--

CREATE TABLE IF NOT EXISTS `cabecera_cot` (
  `codigo` varchar(10) NOT NULL,
  `id_cliente` varchar(12) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `monto` double NOT NULL,
  `estado` enum('E','V','C') NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `id_cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE IF NOT EXISTS `categorias` (
  `codigo` varchar(2) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`codigo`, `nombre`) VALUES
('AC', 'Accesorios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `id` varchar(12) NOT NULL,
  `tipo_doc` enum('V','E') NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE IF NOT EXISTS `productos` (
  `codigo` varchar(5) NOT NULL,
  `cod_categoria` varchar(2) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` text NOT NULL,
  `precio_unitario` double NOT NULL,
  `cantidad` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`),
  KEY `categoria` (`cod_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_cot`
--

CREATE TABLE IF NOT EXISTS `productos_cot` (
  `codigo_cot` varchar(10) NOT NULL,
  `cod_producto` varchar(5) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`codigo_cot`,`cod_producto`),
  KEY `cod_producto` (`cod_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE IF NOT EXISTS `proveedores` (
  `id` varchar(12) NOT NULL,
  `tipo_doc` enum('V','E','J') NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id`, `tipo_doc`, `nombre`, `direccion`, `telefono`, `estado`) VALUES
('12345678', 'V', 'un proveedor', 'es venezolano', '0251-1234567', 0),
('9876543', 'J', 'una empresa C.A', 'zona industrial', '0215-7894561', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `username` varchar(50) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `es_admin` tinyint(1) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`username`, `clave`, `es_admin`, `estado`) VALUES
('admin', '1234', 1, 1),
('caja1', 'caja1', 1, 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bitacora_compras`
--
ALTER TABLE `bitacora_compras`
  ADD CONSTRAINT `bitacora_compras_ibfk_3` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `bitacora_compras_ibfk_4` FOREIGN KEY (`codigo_producto`) REFERENCES `productos` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `bitacora_movimientos`
--
ALTER TABLE `bitacora_movimientos`
  ADD CONSTRAINT `bitacora_movimientos_ibfk_1` FOREIGN KEY (`username`) REFERENCES `usuarios` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cabecera_cot`
--
ALTER TABLE `cabecera_cot`
  ADD CONSTRAINT `cabecera_cot_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`cod_categoria`) REFERENCES `categorias` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `productos_cot`
--
ALTER TABLE `productos_cot`
  ADD CONSTRAINT `productos_cot_ibfk_2` FOREIGN KEY (`codigo_cot`) REFERENCES `cabecera_cot` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `productos_cot_ibfk_3` FOREIGN KEY (`cod_producto`) REFERENCES `productos` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
