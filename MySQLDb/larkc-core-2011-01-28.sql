# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.0.24a-community-nt
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2011-01-28 10:16:15
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for larkc
CREATE DATABASE IF NOT EXISTS `larkc` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `larkc`;


# Dumping structure for table larkc.metrics
CREATE TABLE IF NOT EXISTS `metrics` (
  `idMetric` int(10) unsigned NOT NULL auto_increment,
  `Name` varchar(45) default NULL,
  PRIMARY KEY  (`idMetric`),
  UNIQUE KEY `idMetric_UNIQUE` (`idMetric`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='\n';

# Dumping data for table larkc.metrics: ~18 rows (approximately)
/*!40000 ALTER TABLE `metrics` DISABLE KEYS */;
INSERT INTO `metrics` (`idMetric`, `Name`) VALUES
	(1, 'QueryTimestamp'),
	(2, 'QueryContent'),
	(3, 'QueryNamespaceNb'),
	(4, 'QueryNamespaceKeys'),
	(5, 'QueryNamespaceValues'),
	(6, 'QueryVariablesNb'),
	(7, 'QueryDataSetSourcesNb'),
	(8, 'QueryResultOrderingNb'),
	(9, 'QueryResultLimitNb'),
	(10, 'QueryResultOffsetNb'),
	(11, 'QuerySizeInTriples'),
	(12, 'WorkflowDurationFromPlatform'),
	(13, 'QueryTotalResponseTimeFromClient'),
	(14, 'QueryCompletionStatus'),
	(16, 'PluginTotalExecutionTime'),
	(20, 'WorkflowPluginNb'),
	(21, 'PluginThreadsStartedNB'),
	(22, 'WorkflowDuration');
/*!40000 ALTER TABLE `metrics` ENABLE KEYS */;


# Dumping structure for table larkc.metrics_mtypes
CREATE TABLE IF NOT EXISTS `metrics_mtypes` (
  `idMetrics_MTypes` int(10) unsigned NOT NULL auto_increment,
  `fk_idMetric` varchar(45) NOT NULL,
  `fk_idType` varchar(45) NOT NULL,
  PRIMARY KEY  (`idMetrics_MTypes`,`fk_idMetric`,`fk_idType`),
  UNIQUE KEY `idMetrics_MTypes_UNIQUE` (`idMetrics_MTypes`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.metrics_mtypes: ~19 rows (approximately)
/*!40000 ALTER TABLE `metrics_mtypes` DISABLE KEYS */;
INSERT INTO `metrics_mtypes` (`idMetrics_MTypes`, `fk_idMetric`, `fk_idType`) VALUES
	(1, '1', '1'),
	(2, '2', '1'),
	(3, '3', '1'),
	(4, '4', '1'),
	(5, '5', '1'),
	(6, '6', '1'),
	(7, '7', '1'),
	(8, '8', '1'),
	(9, '9', '1'),
	(10, '10', '1'),
	(11, '11', '1'),
	(12, '12', '4'),
	(13, '13', '4'),
	(14, '14', '1'),
	(15, '15', '1'),
	(16, '16', '1'),
	(20, '20', '1'),
	(21, '21', '1'),
	(22, '22', '1');
/*!40000 ALTER TABLE `metrics_mtypes` ENABLE KEYS */;


# Dumping structure for table larkc.mtypes
CREATE TABLE IF NOT EXISTS `mtypes` (
  `idType` int(10) unsigned zerofill NOT NULL auto_increment,
  `Name` text,
  PRIMARY KEY  (`idType`),
  UNIQUE KEY `idMType` (`idType`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.mtypes: ~4 rows (approximately)
/*!40000 ALTER TABLE `mtypes` DISABLE KEYS */;
INSERT INTO `mtypes` (`idType`, `Name`) VALUES
	(0000000001, 'Atomic'),
	(0000000002, 'Compound'),
	(0000000003, 'Generic'),
	(0000000004, 'Temporary');
/*!40000 ALTER TABLE `mtypes` ENABLE KEYS */;


# Dumping structure for table larkc.platforms
CREATE TABLE IF NOT EXISTS `platforms` (
  `idPlatform` int(11) NOT NULL auto_increment,
  `PlatformAddress` varchar(45) default NULL,
  PRIMARY KEY  (`idPlatform`),
  UNIQUE KEY `idPlatform_UNIQUE` (`idPlatform`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.platforms: ~1 rows (approximately)
/*!40000 ALTER TABLE `platforms` DISABLE KEYS */;
INSERT INTO `platforms` (`idPlatform`, `PlatformAddress`) VALUES
	(1, 'larkc-localhost');
/*!40000 ALTER TABLE `platforms` ENABLE KEYS */;


# Dumping structure for table larkc.platforms_metrics
CREATE TABLE IF NOT EXISTS `platforms_metrics` (
  `Platforms_idPlatform` int(11) NOT NULL,
  `Metrics_idMetric` int(10) unsigned NOT NULL,
  `Value` varchar(45) default NULL,
  PRIMARY KEY  (`Platforms_idPlatform`,`Metrics_idMetric`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.platforms_metrics: ~0 rows (approximately)
/*!40000 ALTER TABLE `platforms_metrics` DISABLE KEYS */;
/*!40000 ALTER TABLE `platforms_metrics` ENABLE KEYS */;


# Dumping structure for table larkc.platforms_workflows
CREATE TABLE IF NOT EXISTS `platforms_workflows` (
  `Platforms_idPlatform` int(11) NOT NULL,
  `Workflows_idWorkflow` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Platforms_idPlatform`,`Workflows_idWorkflow`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.platforms_workflows: ~0 rows (approximately)
/*!40000 ALTER TABLE `platforms_workflows` DISABLE KEYS */;
/*!40000 ALTER TABLE `platforms_workflows` ENABLE KEYS */;


# Dumping structure for table larkc.plugins
CREATE TABLE IF NOT EXISTS `plugins` (
  `idPlugin` int(10) unsigned NOT NULL auto_increment,
  `Name` varchar(45) default NULL,
  PRIMARY KEY  (`idPlugin`),
  UNIQUE KEY `idPlugin_UNIQUE` (`idPlugin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.plugins: ~6 rows (approximately)
/*!40000 ALTER TABLE `plugins` DISABLE KEYS */;
INSERT INTO `plugins` (`idPlugin`, `Name`) VALUES
	(1, 'GrowingDataSetSelecter'),
	(2, 'SimpleAnytimeDecider'),
	(3, 'SindiceTriplePatternIdentifier'),
	(4, 'SparqlQueryEvaluationReasoner'),
	(5, 'SPARQLToTriplePatternQueryTransformer'),
	(6, NULL);
/*!40000 ALTER TABLE `plugins` ENABLE KEYS */;


# Dumping structure for table larkc.plugins_metrics
CREATE TABLE IF NOT EXISTS `plugins_metrics` (
  `Plugins_idPlugin` int(10) unsigned NOT NULL,
  `Metrics_idMetric` int(10) unsigned NOT NULL,
  `Value` varchar(45) default NULL,
  PRIMARY KEY  (`Plugins_idPlugin`,`Metrics_idMetric`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.plugins_metrics: ~6 rows (approximately)
/*!40000 ALTER TABLE `plugins_metrics` DISABLE KEYS */;
INSERT INTO `plugins_metrics` (`Plugins_idPlugin`, `Metrics_idMetric`, `Value`) VALUES
	(1, 16, '11468'),
	(2, 16, '11484'),
	(2, 21, '7'),
	(3, 16, '11468'),
	(4, 16, '11468'),
	(5, 16, '11468');
/*!40000 ALTER TABLE `plugins_metrics` ENABLE KEYS */;


# Dumping structure for table larkc.properties
CREATE TABLE IF NOT EXISTS `properties` (
  `idProperty` int(10) unsigned NOT NULL auto_increment,
  PRIMARY KEY  (`idProperty`),
  UNIQUE KEY `idProperty_UNIQUE` (`idProperty`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.properties: ~0 rows (approximately)
/*!40000 ALTER TABLE `properties` DISABLE KEYS */;
/*!40000 ALTER TABLE `properties` ENABLE KEYS */;


# Dumping structure for table larkc.queries
CREATE TABLE IF NOT EXISTS `queries` (
  `idQuery` int(11) NOT NULL auto_increment,
  `Context` text,
  PRIMARY KEY  (`idQuery`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.queries: ~1 rows (approximately)
/*!40000 ALTER TABLE `queries` DISABLE KEYS */;
INSERT INTO `queries` (`idQuery`, `Context`) VALUES
	(1, 'PREFIX foaf: <http://xmlns.com/foaf/0.1/>   SELECT ?name1   WHERE {   ?person1 foaf:knows ?person2 .   ?person1 foaf:name  ?name1 .   ?person2 foaf:name  "Raluca Brehar" .       }');
/*!40000 ALTER TABLE `queries` ENABLE KEYS */;


# Dumping structure for table larkc.queries_metrics
CREATE TABLE IF NOT EXISTS `queries_metrics` (
  `Queries_idQuery` int(11) NOT NULL,
  `Metrics_idMetric` int(10) unsigned NOT NULL,
  `Value` text,
  PRIMARY KEY  (`Queries_idQuery`,`Metrics_idMetric`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.queries_metrics: ~13 rows (approximately)
/*!40000 ALTER TABLE `queries_metrics` DISABLE KEYS */;
INSERT INTO `queries_metrics` (`Queries_idQuery`, `Metrics_idMetric`, `Value`) VALUES
	(1, 1, '2011-01-18 14:50:37'),
	(1, 2, 'PREFIX foaf: <http://xmlns.com/foaf/0.1/>   SELECT ?name1   WHERE {   ?person1 foaf:knows ?person2 .   ?person1 foaf:name  ?name1 .   ?'),
	(1, 3, '1'),
	(1, 4, '[foaf]'),
	(1, 5, '[http://xmlns.com/foaf/0.1/]'),
	(1, 6, '3'),
	(1, 7, '0'),
	(1, 8, '0'),
	(1, 9, '0'),
	(1, 10, '0'),
	(1, 11, '3'),
	(1, 13, '11609'),
	(1, 14, 'SuccessfulQuery');
/*!40000 ALTER TABLE `queries_metrics` ENABLE KEYS */;


# Dumping structure for table larkc.queries_workflows
CREATE TABLE IF NOT EXISTS `queries_workflows` (
  `Queries_idQuery` int(11) NOT NULL,
  `Workflows_idWorkflow` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Queries_idQuery`,`Workflows_idWorkflow`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.queries_workflows: ~1 rows (approximately)
/*!40000 ALTER TABLE `queries_workflows` DISABLE KEYS */;
INSERT INTO `queries_workflows` (`Queries_idQuery`, `Workflows_idWorkflow`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `queries_workflows` ENABLE KEYS */;


# Dumping structure for table larkc.workflows
CREATE TABLE IF NOT EXISTS `workflows` (
  `idWorkflow` int(10) unsigned NOT NULL auto_increment,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY  (`idWorkflow`),
  UNIQUE KEY `idWorkflow_UNIQUE` (`idWorkflow`),
  UNIQUE KEY `Name_UNIQUE` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.workflows: ~1 rows (approximately)
/*!40000 ALTER TABLE `workflows` DISABLE KEYS */;
INSERT INTO `workflows` (`idWorkflow`, `Name`) VALUES
	(1, 'SPARQL for Web');
/*!40000 ALTER TABLE `workflows` ENABLE KEYS */;


# Dumping structure for table larkc.workflows_metrics
CREATE TABLE IF NOT EXISTS `workflows_metrics` (
  `Workflows_idWorkflow` int(10) unsigned NOT NULL,
  `Metrics_idMetric` int(10) unsigned NOT NULL,
  `Value` varchar(45) default NULL,
  PRIMARY KEY  (`Workflows_idWorkflow`,`Metrics_idMetric`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.workflows_metrics: ~3 rows (approximately)
/*!40000 ALTER TABLE `workflows_metrics` DISABLE KEYS */;
INSERT INTO `workflows_metrics` (`Workflows_idWorkflow`, `Metrics_idMetric`, `Value`) VALUES
	(1, 12, '11484'),
	(1, 20, '9'),
	(1, 22, '11484');
/*!40000 ALTER TABLE `workflows_metrics` ENABLE KEYS */;


# Dumping structure for table larkc.workflows_plugins
CREATE TABLE IF NOT EXISTS `workflows_plugins` (
  `Workflows_idWorkflow` int(10) unsigned NOT NULL,
  `Plugins_idPlugin` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Workflows_idWorkflow`,`Plugins_idPlugin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table larkc.workflows_plugins: ~5 rows (approximately)
/*!40000 ALTER TABLE `workflows_plugins` DISABLE KEYS */;
INSERT INTO `workflows_plugins` (`Workflows_idWorkflow`, `Plugins_idPlugin`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5);
/*!40000 ALTER TABLE `workflows_plugins` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
