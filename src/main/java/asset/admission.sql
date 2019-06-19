-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mer. 19 juin 2019 à 15:20
-- Version du serveur :  10.1.37-MariaDB
-- Version de PHP :  7.3.0

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
-- Base de données :  `admission`
--

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(151),
(151),
(151);

-- --------------------------------------------------------

--
-- Structure de la table `note_concours`
--

CREATE TABLE `note_concours` (
  `id` bigint(20) NOT NULL,
  `note_ecrit` double NOT NULL,
  `note_oral` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `note_concours`
--

INSERT INTO `note_concours` (`id`, `note_ecrit`, `note_oral`) VALUES
(122, 2.534, 12),
(124, 0, 0),
(126, 2.27, 9),
(128, 2.072, 3),
(130, 2.734, 8),
(132, 3.008, 16),
(134, 2.47, 17),
(136, 2.668, 15),
(138, 3.008, 14),
(140, 2.404, 10);

-- --------------------------------------------------------

--
-- Structure de la table `note_module_concours`
--

CREATE TABLE `note_module_concours` (
  `id` bigint(20) NOT NULL,
  `note` double NOT NULL,
  `ref_module_concours` varchar(255) DEFAULT NULL,
  `retenue_ecrit` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `note_module_concours`
--

INSERT INTO `note_module_concours` (`id`, `note`, `ref_module_concours`, `retenue_ecrit`) VALUES
(141, 11.35, '8', 125),
(142, 12.67, '8', 121),
(143, 10.36, '8', 127),
(144, 15.04, '8', 131),
(145, 12.02, '8', 139),
(146, 12.35, '8', 133),
(147, 13.34, '8', 135),
(148, 13.67, '8', 129),
(149, 15.04, '8', 137),
(150, 0, '8', 123);

-- --------------------------------------------------------

--
-- Structure de la table `retenue_ecrit`
--

CREATE TABLE `retenue_ecrit` (
  `id` bigint(20) NOT NULL,
  `admis` bit(1) NOT NULL,
  `note_preselection` double DEFAULT NULL,
  `preselectione` bit(1) NOT NULL,
  `ref_candidat` varchar(255) DEFAULT NULL,
  `ref_concours` varchar(255) DEFAULT NULL,
  `retenue_oral` bit(1) NOT NULL,
  `note_concours` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `retenue_ecrit`
--

INSERT INTO `retenue_ecrit` (`id`, `admis`, `note_preselection`, `preselectione`, `ref_candidat`, `ref_concours`, `retenue_oral`, `note_concours`) VALUES
(121, b'0', 15.25, b'1', 'g-3514578', 'Concours d\'acces a la premiere annee du au cycle d\'ingenieur IRISI', b'1', 122),
(123, b'0', 15.125, b'1', 'g-8465132', 'Concours d\'acces a la premiere annee du au cycle d\'ingenieur IRISI', b'0', 124),
(125, b'0', 15, b'1', 'b-1248532', 'Concours d\'acces a la premiere annee du au cycle d\'ingenieur IRISI', b'0', 126),
(127, b'0', 15, b'1', 'a-9864531', 'Concours d\'acces a la premiere annee du au cycle d\'ingenieur IRISI', b'0', 128),
(129, b'0', 14.25, b'1', 'g-9621351', 'Concours d\'acces a la premiere annee du au cycle d\'ingenieur IRISI', b'1', 130),
(131, b'1', 14.25, b'1', 'g-1923548', 'Concours d\'acces a la premiere annee du au cycle d\'ingenieur IRISI', b'1', 132),
(133, b'1', 10, b'1', 'g-5465125', 'Concours d\'acces a la premiere annee du au cycle d\'ingenieur IRISI', b'1', 134),
(135, b'1', 11.5, b'1', 'g-9239988', 'Concours d\'acces a la premiere annee du au cycle d\'ingenieur IRISI', b'1', 136),
(137, b'1', 12.25, b'1', 'g-8627121', 'Concours d\'acces a la premiere annee du au cycle d\'ingenieur IRISI', b'1', 138),
(139, b'0', 12.75, b'1', 'g-5345683', 'Concours d\'acces a la premiere annee du au cycle d\'ingenieur IRISI', b'1', 140);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `note_concours`
--
ALTER TABLE `note_concours`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `note_module_concours`
--
ALTER TABLE `note_module_concours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd222xlw4oderkbc3dr16dqkgc` (`retenue_ecrit`);

--
-- Index pour la table `retenue_ecrit`
--
ALTER TABLE `retenue_ecrit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmiap9jsd6qam8i1abc15la3p4` (`note_concours`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
