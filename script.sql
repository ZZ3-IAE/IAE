CREATE TABLE `medecins` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(64) DEFAULT NULL,
  `prenom` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `patients` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(64) DEFAULT NULL,
  `prenom` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `creneaux` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `debut` datetime NOT NULL,
  `fin` datetime NOT NULL,
  `medecin` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_medecin_numero` (`medecin`),
  CONSTRAINT `fk_medecin_numero` FOREIGN KEY (`medecin`) REFERENCES `medecins` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `rdv` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `patient` smallint(5) unsigned NOT NULL,
  `creneau` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `patient` (`patient`),
  KEY `creneau` (`creneau`),
  CONSTRAINT `rdv_ibfk_1` FOREIGN KEY (`patient`) REFERENCES `patients` (`id`),
  CONSTRAINT `rdv_ibfk_2` FOREIGN KEY (`creneau`) REFERENCES `creneaux` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
