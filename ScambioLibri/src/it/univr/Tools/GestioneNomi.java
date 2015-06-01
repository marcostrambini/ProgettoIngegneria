package it.univr.Tools;



public class GestioneNomi {
	
	static String[] nomi = {"Agnese", "Alba","Alice","Anita","Alessandra","Anna","Arianna","Ambra",
		"Azzurra","Angelica","Annalisa","Aria","Astrid","Ambrosia","Adelaide","Alexandra","Aurora","Barbara","Beatrice",
		"Benedetta","Camilla","Candida","Carola","Cassandra","Cassiopea","Caterina","Celeste","Chantal","Clara",
		"Clarissa","Claudia","Clementina","Cloe","Dalila","Daniela","Deborah","Delfina","Desiree","Denise","Dharma",
		"Diana","Diletta","Domitilla","Dorotea","Donatella","Edy","Edith","Elena","Eleonora","Elettra","Elisabetta",
		"Elisabeth","Eloisa","Elaine","Emily","Erica","Ester","Evelyne","Fanny","Flora","Gabriella","Gaia","Giada",
		"Ginevra","Gioia","Giulia","Gloria","Helena","Ines","Irene","Iris","Isabella","Jasmine","Jessica","Jillian",
		"Layla","Leila","Letizia","Liliana","Lizabeth","Lorena","Lority","Luna","Maia","Manuela","Marianna","Marilu",
		"Martina","Melania","Melissa","Micaela","Micol","Miranda","Miriam","Monica","Morena","Morgana","Nabila",
		"Nadia","Naomi","Nicole","Nicoletta","Ninfa","Nives","Olimpia","Ornella","Ottavia","Paola","Penelope","Priscilla",
		"Rebecca","Rossella","Sabina","Sabrina","Sara","Serena","Shana","Sharon","Shirley","Sibilla","Simona","Soledad",
		"Stella","Susanna","Teodora","Teresa","Valeria","Valerie","Vanessa","Venere","Vera","Veronica","Viola","Vittoria",
		"Viviana","Wendy","Yasmine","Zoe","Aaron","Adam","Abraham","Agostino","Alessandro","Alessio","Amedeo",
		"Antonio","Bartolomeo","Battista","Benedetto","Bernardo","Cesare","Claudio","Corrado","Cristopher",
		"Daniele","Davide","Domenico","Edoardo","Efrem","Emanuele","Emiliano","Enrico","Ermes","Eros",
		"Ettore","Fabrizio","Federico","Fernando","Filippo","Francesco","Gabriele","Giacomo","Gianluca",
		"Gioele","Giordano","Giosue'","Giovanni","Gregorio","Hector","Igor","Isacco","Ismaele","Ivan","JacK",
		"Jacopo","Jonathan","Joseph","Joshua","Leandro","Leonardo","Lorenzo","Loris","Luca","Manuele",
		"Marco","Mariano","Martino","Massimiliano","Massimo","Mathias","Matteo","Mattia","Maurizio",
		"Michele","Michelangelo","Moreno","Moris","Mose'","Narciso","Nathan","Nicholas","Nicola","Nicol",
		"Noah","Oscar","Paolo","Peter","Raffaele","Renato","Riccardo","Roberto","Romeo","Rossano",
		"Roy","Salvatore","Samuele","Samuel","Sasha","Saverio","Sebastiano","Sebastian","Serafino","Silvano",
		"Silvestro","Simone","Sirio","Stefano","Teodoro","Thierry","Thomas","Tommaso","Umberto",
		"Ulisse","Uribe","Valerio","Vincenzo","Vittorio","William"	};



	
	static String[] cognomi = {"Agostini","Aiello","Albanese","Amato","Antonelli","Arena","Baldi","Barbieri",
		"Barone","Basile","Battaglia","Bellini","Benedetti","Bernardi","Bianchi","Bianco","Brambilla",
		"Bruni","Bruno","Calabrese","Caputo","Carbone","Caruso","Castelli","Catalano","Cattaneo","Cavallo",
		"Ceccarelli","Cirillo","Colombo","Conte","Conti","Coppola","Costa","Costantini","De Angelis",
		"De Luca","De Rosa","De Santis","De Simone","Di Stefano","Donati","Esposito","Fabbri","Farina",
		"Ferrante","Ferrara","Ferrari","Ferraro","Ferrero","Ferretti","Ferri","Ferro","Fiore","Fontana",
		"Franco","Fumagalli","Fusco","Galli","Gallo","Gargiulo","Garofalo","Gatti","Gentile","Giordano",
		"Giorgi","Giuliani","Grassi","Grasso","Greco","Grimaldi","Guerra","Guidi","Leone","Lombardi","Lombardo",
		"Longo","Lorusso","Mancini","Marchetti","Marchi","Mariani","Marini","Marino","Marra","Martinelli",
		"Martini","Martino","Mazza","Mele","Meloni","Messina","Milani","Monaco","Montanari","Monti",
		"Morelli","Moretti","Moro","Napolitano","Neri","Olivieri","Orlando","Pace","Pagano","Palmieri",
		"Palumbo","Parisi","Pastore","Pellegrini","Pellegrino","Pepe","Perrone","Piazza","Piccolo",
		"Pinna","Piras","Poli","Pozzi","Proietti","Ricci","Ricciardi","Rinaldi","Riva","Rizzi",
		"Rizzo","Romano","Romeo","Rossetti","Rossi","Ruggeri","Ruggiero","Russo","Sala","Sanna",
		"Santini","Santoro","Sartori","Serra","Silvestri","Sorrentino","Strambini","Testa","Valente","Valentini",
		"Villa","Villani","Vitale","Vitali","Volpe","Zanetti"};

	static String[] indirizzi = {"Via ADALBERTO CATENA Milano","Via ADALBERTO CATENA Milano","Viale ADAMO MICKIEWICZ Milano",
		"Via AGNELLO Milano","Via ALESSANDRO MANZONI Milano","Viale ALESSANDRO PETOFI Milano","Viale ALESSANDRO PUSKIN Milano",
		"Via ALESSANDRO VOLTA Milano","Via ANCONA Milano","Via ANDEGARI Milano","Via ANDREA APPIANI Milano","Via ANFITEATRO Milano",
		"Via Degli ANGIOLI Milano","Via Dell' ANNUNCIATA Milano","Via ANTONIO BERETTA Milano","Largo ANTONIO GHIRINGHELLI Milano",
		"Largo ANTONIO GREPPI Milano","Via ARCO Milano","Via ARRIGO BOITO Milano","Sito Dei BAGATTI VALSECCHI Milano","Via BAGUTTA Milano",
		"Via BAGUTTINO Milano","Viale BARBARO DI SAN GIORGIO RAMIRO Milano","Piazza BELGIOIOSO Milano","Via BETTINO RICASOLI Milano",
		"Via BIGLI Milano","Via BORGONUOVO Milano","Via BORGOSPESSO Milano","Largo BORTOLO BELOTTI Milano","Via BOSCHETTI Milano",
		"Via Dei BOSSI Milano","Piazzetta Di BRERA Milano","Via BRERA Milano","Via BROLETTO Milano","Foro BUONAPARTE Milano",
		"Via CARLO CATTANEO Milano","Piazza CARLO MIRABELLO Milano","Via CARLO PORTA Milano","Piazza Del CARMINE Milano",
		"Via Del CARMINE Milano","Via CASE ROTTE Milano","Via CASTELFIDARDO Milano","Piazza CASTELLO Milano",
		"Via CAVALIERI BONAVENTURA Milano","Via Dei CAVALIERI DEL SANTO SEPOLCRO Milano","Piazza CAVOUR Milano","Via CERNAIA Milano",
		"Via CESARE MANGILI Milano","Via Dei CHIOSTRI Milano","Via CIOVASSINO Milano","Via CIOVASSO Milano","Vicolo CIOVASSO Milano",
		"Galleria CIRO FONTANA Milano","Largo CLAUDIO TREVES Milano","Via CLERICI Milano","Viale CONGRESSO CISPADANO Milano",
		"Via CROCE ROSSA Milano","Via CUSANI Milano","Via DANIELE MANIN Milano","Via DELIO TESSA Milano","Largo DE SABATA VICTOR Milano",
		"Viale EMILIO ALEMAGNA Milano","Viale EMILIO ZOLA Milano","Viale ENRICO CONNEAU Milano","Piazzetta ENRICO CUCCIA Milano",
		"Viale ENRICO IBSEN Milano","Via Delle ERBE Milano","Via EUGENIO BALZAN Milano","Viale EUGENIO BEAUHARNAIS Milano","Via FARINE Milano",
		"Via FATEBENEFRATELLI Milano","Via FATEBENESORELLE Milano","Viale FEDERICO SCHILLER Milano","Piazza FILIPPO MEDA Milano",
		"Via FILIPPO TURATI Milano","Via FILODRAMMATICI Milano","Via FIORI CHIARI Milano","Via FIORI OSCURI Milano","Vicolo FIORI Milano",
		"Viale FRANCESCO CRISPI Milano","Via FRATELLI GABBA Milano","Via Del GALLO Milano","Via GASTONE PISONI Milano",
		"Viale GEROLAMO GADIO Milano","Via GEROLAMO MORONE Milano","Via GESU' Milano","Corso GIACOMO MATTEOTTI Milano",
		"Via GIACOMO PUCCINI Milano","Via GIAN DOMENICO ROMAGNOSI Milano","Via Dei GIARDINI Milano","Vicolo GIARDINO Milano",
		"Piazzetta GIORDANO DELL'AMORE Milano","Viale GIOVANNI BATTISTA RACINE Milano","Via GIOVANNI BERCHET Milano","Via GIOVANNI LANZA Milano",
		"Passaggio GIOVANNI MALAGODI Milano","Viale GIOVANNI MILTON Milano","Via GIULIANOVA Milano","Via GIUSEPPE BRENTANO Milano",
		"Corso GIUSEPPE GARIBALDI Milano","Via GIUSEPPE MARCORA Milano","Via GIUSEPPE MENGONI Milano","Via GIUSEPPE PARINI Milano",
		"Via GIUSEPPE POZZONE Milano","Via GIUSEPPE SACCHI Milano","Via GIUSEPPE VERDI Milano","Via GOITO Milano",
		"Viale GUGLIELMO SHAKESPEARE Milano","Largo GUIDO DONEGANI Milano","Via IGINIO UGO TARCHETTI Milano","Largo LA FOPPA Milano",
		"Via LANDOLFO Milano","Via LAURA SOLERA MANTEGAZZA Milano","Via Del LAURO Milano","Viale LEGIONE LOMBARDA Milano","Via LEGNANO Milano",
		"Piazza Del LIBERTY Milano","Largo LINO MONTAGNA Milano","Via LOVANIO Milano","Via LUCA BELTRAMI Milano","Via LUCHINO VISCONTI Milano",
		"Via LUIGI ALBERTINI Milano","Viale LUIGI CAMOENS Milano","Via LUIGI ILLICA Milano","Via LUIGI ROSSARI Milano","Via MADONNINA Milano",
		"Viale MALTA Milano","Via MARCO DE MARCHI Milano","Via MARCO FORMENTINI Milano","Via MARCO MINGHETTI Milano","Piazzale MARENGO Milano",
		"Largo MARIA CALLAS Milano","Via MARINA Milano","Via MARINO TOMMASO Milano","Via MARSALA Milano","Piazzetta MAURILIO BOSSI Milano",
		"Via MELONE Milano","Via MERCATO Milano","Sottopassaggio METRO' CAIROLI Milano","Sottopassaggio METRO' DUOMO Milano",
		"Sottopassaggio METRO SANTA RADEGONDA Milano","Viale MICHELE CERVANTES Milano","Via MILAZZO Milano","Viale MOLIERE Milano",
		"Via MONTEBELLO Milano","Via MONTE DI PIETA' Milano","Via MONTENAPOLEONE Milano","Via Della MOSCOVA Milano","Via OMENONI Milano",
		"Via Dell' ORSO Milano","Via PALERMO Milano","Via PALESTRO Milano","Piazza PAOLO FERRARI Milano","Largo PAOLO GRASSI Milano",
		"Piazza PAOLO VI Milano","Via PIERRE E MARIE CURIE Milano","Via PIETRO PALEOCAPA Milano","Via PIETRO VERRI Milano",
		"Via PINAMONTE DA VIMERCATE Milano","Via PONTACCIO Milano","Via PONTE VETERO Milano","Via PONTIDA Milano","Via PORRONE BASSANO Milano",
		"Bastioni Di PORTA NUOVA Milano","Corso Di PORTA NUOVA Milano","Via Di PORTA TENAGLIA Milano","Bastioni Di PORTA VOLTA Milano",
		"Via PRINCIPE AMEDEO Milano","Piazzale PRINCIPESSA CLOTILDE Milano","Via QUINTINO SELLA Milano","Largo RAFFAELE MATTIOLI Milano",
		"Via RAGAZZI DEL '99 Milano","Via RENZO BERTONI Milano","Via RIVOLI Milano","Piazzale RODOLFO MORANDI Milano","Via ROVELLO Milano",
		"Galleria SALA DEI LONGOBARDI Milano","Via SAN CARPOFORO Milano","Via SAN DALMAZIO Milano","Via SANDRO SANDRI Milano",
		"Piazza SAN FEDELE Milano","Via SAN FERMO Milano","Via SAN GIOVANNI SUL MURO Milano","Vicolo SAN GIOVANNI SUL MURO Milano",
		"Piazza SAN MARCO Milano","Via SAN MARCO Milano","Via SAN PAOLO Milano","Via SAN PIETRO ALL'ORTO Milano","Via SAN PRIMO Milano",
		"Via SAN PROSPERO Milano","Via SAN PROTASO Milano","Via SAN RAFFAELE Milano","Piazza SAN SIMPLICIANO Milano","Via SAN SIMPLICIANO Milano",
		"Passaggio SANTA MARGHERITA Milano","Via SANTA MARGHERITA Milano","Via SANT'ANDREA Milano","Piazza SANT'ANGELO Milano",
		"Via SANTA RADEGONDA Milano","Piazza SANT'ERASMO Milano","Via SAN TOMASO Milano","Via SANTO SPIRITO Milano","Piazza Della SCALA Milano",
		"Via SENATO Milano","Portici SETTENTRIONALE DUOMO Milano","Via SILVIO PELLICO Milano","Via SOLFERINO Milano",
		"Via Della SPIGA Milano","Piazza STATI UNITI D'AMERICA Milano","Via STATUTO Milano","Via STEFANO JACINI Milano","Via TIVOLI Milano",
		"Via TOMMASO DA CAZZANIGA Milano","Via TOMMASO GROSSI Milano","Via UGO FOSCOLO Milano","Via ULRICO HOEPLI Milano",
		"Via VARESE Milano","Via VASTO Milano","Via Del VECCHIO POLITECNICO Milano","Bastioni Di Porta VENEZIA Milano","Corso VENEZIA Milano",
		"Galleria VITTORIO EMANUELE II Milano","Viale WOLFANGO GOETHE Milano"
	};

	static String getNome(){
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * nomi.length);
		return nomi[id];
	};
	

	static String getCognome(){
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * cognomi.length);
		return cognomi[id];
	};
	
	static String getIndirizzo(){
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * indirizzi.length);
		return indirizzi[id];
	}
	
	static String getMail(String nome, String cognome){
		String [] domini = {"mail.it","mail.com","gmail.com","libero.it","tiscali.it","hotmail.it","alice.it","outlook.com","jegjeghede.org"};
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * domini.length);
		
		return nome.substring(0,1).toLowerCase()+"."+cognome.toLowerCase()+"@"+domini[id];
	}
	
	
	static String getPassword(){
		return "password";
	}
	
	
	
	
}
