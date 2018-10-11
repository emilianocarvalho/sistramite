-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 10-Out-2018 às 19:05
-- Versão do servidor: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistramite`
--
CREATE DATABASE IF NOT EXISTS `sistramite` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sistramite`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `auto`
--

DROP TABLE IF EXISTS `auto`;
CREATE TABLE IF NOT EXISTS `auto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_lavratura` datetime DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `tipo_de_infracao` varchar(255) DEFAULT NULL,
  `tipo_de_reclamacao` varchar(255) DEFAULT NULL,
  `empresa_id` int(11) DEFAULT NULL,
  `fiscal_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfxfs5sxs4iitvtv1tgxnbltil` (`empresa_id`),
  KEY `FKdhicu0s690a8bps0noqg5812j` (`fiscal_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `auto`:
--

--
-- Extraindo dados da tabela `auto`
--

INSERT INTO `auto` (`id`, `data_lavratura`, `numero`, `tipo_de_infracao`, `tipo_de_reclamacao`, `empresa_id`, `fiscal_id`) VALUES
(1, '2017-09-30 13:32:00', 25625, 'Auto de Apreensão', 'Reclamação', 5, 3),
(2, '2017-10-01 12:50:00', 58962, 'Auto de Constatação', 'Reclamação', 6, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidade`
--

DROP TABLE IF EXISTS `cidade`;
CREATE TABLE IF NOT EXISTS `cidade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkworrwk40xj58kevvh3evi500` (`estado_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `cidade`:
--

--
-- Extraindo dados da tabela `cidade`
--

INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES
(1, 'João Pessoa', 1),
(2, 'Aracajú', 2),
(3, 'Bayeux', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

DROP TABLE IF EXISTS `endereco`;
CREATE TABLE IF NOT EXISTS `endereco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `cidade_id` int(11) DEFAULT NULL,
  `pessoa_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8b1kcb3wucapb8dejshyn5fsx` (`cidade_id`),
  KEY `FKn1l2g0b74rqd9ywu29sva9sy9` (`pessoa_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `endereco`:
--

--
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`id`, `bairro`, `cep`, `complemento`, `logradouro`, `numero`, `cidade_id`, `pessoa_id`) VALUES
(1, 'Geisel', '58075400', 'casa', 'Rua Juscelino Kubitscheck', '692', 1, 1),
(2, 'J. Planalto', '58088010', 'casa', 'Rua Eng. Ávidos', '1209', 1, 2),
(3, 'Funcionarios II', '58079070', 'casa', 'Rua Ladislau', '86', 1, 3),
(4, 'Imaculada', '58309250', 'casa', 'Rua Tomé de Souza', '299', 3, 4),
(5, 'Salgado Filho', '49020490', 'casa', 'Rua Urquiza Leal', '900', 2, 5),
(6, 'Grotão', '58079786', 'casa', 'Rua Recife', '89', 1, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `estado`
--

DROP TABLE IF EXISTS `estado`;
CREATE TABLE IF NOT EXISTS `estado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `estado`:
--

--
-- Extraindo dados da tabela `estado`
--

INSERT INTO `estado` (`id`, `nome`) VALUES
(1, 'Paraíba'),
(2, 'Sergipe');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
CREATE TABLE IF NOT EXISTS `pessoa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mc87q8fpvldpdyfo9o5633o5l` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `pessoa`:
--

--
-- Extraindo dados da tabela `pessoa`
--

INSERT INTO `pessoa` (`id`, `email`, `nome`) VALUES
(1, 'maria@procon.pb.gov.br', 'Maria'),
(2, 'darcio@procon.pb.gov.br', 'Darcio'),
(3, 'nataluan@procon.pb.gov.br', 'Nataluan'),
(4, 'santana@procon.pb.gov.br', 'Santana'),
(5, 'sac@americanas.com', 'Americanas'),
(6, 'sac@carrefour.com', 'Carrefour');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa_autos`
--

DROP TABLE IF EXISTS `pessoa_autos`;
CREATE TABLE IF NOT EXISTS `pessoa_autos` (
  `pessoa_id` int(11) NOT NULL,
  `autos_id` int(11) NOT NULL,
  UNIQUE KEY `UK_q8gvhhcdqq9qrkrj1cm563b9n` (`autos_id`),
  KEY `FKq88apwq22ohp43be1j0q36791` (`pessoa_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `pessoa_autos`:
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa_fisica`
--

DROP TABLE IF EXISTS `pessoa_fisica`;
CREATE TABLE IF NOT EXISTS `pessoa_fisica` (
  `cpf` varchar(255) DEFAULT NULL,
  `funcao` varchar(255) DEFAULT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `pessoa_fisica`:
--

--
-- Extraindo dados da tabela `pessoa_fisica`
--

INSERT INTO `pessoa_fisica` (`cpf`, `funcao`, `matricula`, `id`) VALUES
('21482109042', 'Fiscal', '1759842', 3),
('40690425040', 'Fiscal', '885421', 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa_juridica`
--

DROP TABLE IF EXISTS `pessoa_juridica`;
CREATE TABLE IF NOT EXISTS `pessoa_juridica` (
  `cnpj` varchar(255) DEFAULT NULL,
  `razao_social` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `pessoa_juridica`:
--

--
-- Extraindo dados da tabela `pessoa_juridica`
--

INSERT INTO `pessoa_juridica` (`cnpj`, `razao_social`, `id`) VALUES
('76293655000167', 'Lojas Americanas LTDA', 5),
('60046057000153', 'Carrefour LTDA', 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa_tramites`
--

DROP TABLE IF EXISTS `pessoa_tramites`;
CREATE TABLE IF NOT EXISTS `pessoa_tramites` (
  `pessoa_id` int(11) NOT NULL,
  `tramites_id` int(11) NOT NULL,
  UNIQUE KEY `UK_tkgt771e2s4ct8oc96nr1fkd` (`tramites_id`),
  KEY `FK81dl22m9lfdnneh9d6tkrvdlo` (`pessoa_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `pessoa_tramites`:
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `setor`
--

DROP TABLE IF EXISTS `setor`;
CREATE TABLE IF NOT EXISTS `setor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `setor`:
--

--
-- Extraindo dados da tabela `setor`
--

INSERT INTO `setor` (`id`, `nome`) VALUES
(1, 'Divida Ativa'),
(2, 'Cartório'),
(3, 'Fiscalização'),
(4, 'Financeiro'),
(5, 'Administração'),
(6, 'Chefia de Gabinete'),
(7, 'T.I.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `telefone`
--

DROP TABLE IF EXISTS `telefone`;
CREATE TABLE IF NOT EXISTS `telefone` (
  `pessoa_id` int(11) NOT NULL,
  `telefones` varchar(255) DEFAULT NULL,
  KEY `FKdfopyen4k14hhqgi17u5ct0h3` (`pessoa_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `telefone`:
--

--
-- Extraindo dados da tabela `telefone`
--

INSERT INTO `telefone` (`pessoa_id`, `telefones`) VALUES
(1, '988532053'),
(1, '32334286'),
(2, '988567422'),
(2, '32334286'),
(3, '988556699'),
(3, '32241960'),
(5, '32335689'),
(5, '986007619');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tramite`
--

DROP TABLE IF EXISTS `tramite`;
CREATE TABLE IF NOT EXISTS `tramite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_movimentacao` datetime DEFAULT NULL,
  `auto_id` int(11) DEFAULT NULL,
  `setor_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4ehm56pr0bo3oij5hisghxtw` (`auto_id`),
  KEY `FKt91m7aojn26l5hs52ecs42l5r` (`setor_id`),
  KEY `FKjirf6579axp0axjttgljx5kkx` (`usuario_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `tramite`:
--

--
-- Extraindo dados da tabela `tramite`
--

INSERT INTO `tramite` (`id`, `data_movimentacao`, `auto_id`, `setor_id`, `usuario_id`) VALUES
(1, '2018-09-10 11:10:00', 1, 1, 1),
(2, '2018-09-10 11:20:00', 2, 1, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `login` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- RELATIONSHIPS FOR TABLE `usuario`:
--

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`login`, `senha`, `id`) VALUES
('Maria', '123456', 1),
('Darcio', '123', 2);


--
-- Metadata
--
USE `phpmyadmin`;

--
-- Metadata for table auto
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table cidade
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table endereco
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table estado
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table pessoa
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table pessoa_autos
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table pessoa_fisica
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table pessoa_juridica
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table pessoa_tramites
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table setor
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table telefone
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table tramite
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for table usuario
--
-- Error reading data for table phpmyadmin.pma__column_info: #1100 - Tabela 'pma__column_info' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__table_uiprefs: #1100 - Tabela 'pma__table_uiprefs' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__tracking: #1100 - Tabela 'pma__tracking' não foi travada com LOCK TABLES

--
-- Metadata for database sistramite
--
-- Error reading data for table phpmyadmin.pma__bookmark: #1100 - Tabela 'pma__bookmark' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__relation: #1100 - Tabela 'pma__relation' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__savedsearches: #1100 - Tabela 'pma__savedsearches' não foi travada com LOCK TABLES
-- Error reading data for table phpmyadmin.pma__central_columns: #1100 - Tabela 'pma__central_columns' não foi travada com LOCK TABLES
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
