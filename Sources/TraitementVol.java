/** 
 * Projet d'algorithmique PARAPENTE
 * 
 * Classe Java principale permettant de recuperer les donnees de vol et d'afficher les statistiques.
 * 
 * @author   Dominguez Mikael , Badot-Bertrand Corentin
 * @version  Beta
 */
public class TraitementVol
{
	private static Vol vol;

	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args)
	{
		// Affichage du titre PARAPENTE et de la reference Github
		System.out.println("\n ___  _   ___    _   ___ ___ _  _ _____ ___ ");
		System.out.println("| _ \\/_\\ | _ \\  /_\\ | _ \\ __| \\| |_   _| __|");
		System.out.println("|  _/ _ \\|   / / _ \\|  _/ _|| .` | | | | _| ");
		System.out.println("|_|/_/ \\_\\_|_\\/_/ \\_\\_| |___|_|\\_| |_| |___|\n");
		
		System.out.println("\nAnalyse de ton vol\n==================\n");
		vol = chargerVol();
		int choix;
		do {
			choix = lireChoix();
			switch (choix) {
			case 1:
				epreuve1();
				break;
            case 3:
				epreuve3();
				break;
            case 4:
            	afficherEpreuve4();
            	break;
            case 5:
				afficherEpreuve5();
				break;
			case 6:
				afficherEpreuve6();
				break;
			case 7:
				afficherEpreuve7();
				break;
			case 8:
				afficherEpreuve8();
				break;
			case 10:
				afficherEpreuve10();
				break;
			default :
				System.out.println("Cette option n'existe pas, choissisez en une autre.");
				break;
			}
		}while(choix!=0);
		System.out.println("Au revoir!\n");
	}

	/**
	 * Affiche un menu a l'ecran avec toutes les epreuves disponible ainsi
	 * que l'option pour quitter.
	 * L'utilisateur peut choisir une option dans le menu.
	 * 
	 * @return       le numero du menu que l'utilisateur a choisi
	 */
	private static int lireChoix()
	{
		String [] listeEpreuves =  {"Quitter le programme",
								  "Rester le plus longtemps dans l’air", 
		                          "Aller le plus loin possible",
		                          "Parcourir la plus longue distance",
		                          "Parcourir la plus longue distance sur un temps imparti",
		                          "S’approcher le plus pres possible d’une cible",
		                          "Atteindre plusieurs cibles",
		                          "Suivre un parcours",
		                          "Faire du sur place"};
		System.out.println("\nFais ton choix \n==============\n");
	
		for(int compteurMenu=0; compteurMenu < listeEpreuves.length; compteurMenu++)
		{
			int nombreMenu = compteurMenu;
			System.out.println("#" + nombreMenu + " " + listeEpreuves[compteurMenu]);
		}
		
		System.out.print("\nTon choix : ");
		int choix = Utilitaires.lireUnEntierComprisEntre(0, 10);
		
		return choix;
	}
	
	/**
	 * Demande a l'utilisateur d'entrer la latitude et longitude d'une coordonnees
	 * et renvoie ensuite un nouvel objet Coordonnees.
	 * 
	 * @return    objet Coordonnees avec latitude et longitude
	 */
	public static Coordonnees lireCoordonnees()
	{
		System.out.print("\nEntrez la latitude de la cible: ");
		int latitude = Utilitaires.lireUnEntierComprisEntre(-400, 400);
		
		System.out.print("Entrez la longitude de la cible: ");
		int longitude = Utilitaires.lireUnEntierComprisEntre(-400, 400);
		
		Coordonnees cibleDefinie = new Coordonnees(latitude, longitude);
		
		return cibleDefinie;
	}
	
	/**
	 * Affiche toutes les coordonnees d'un parcours specifique.
	 * Pour ce faire, la methode a besoin d'une liste de coordonnees.
	 * 
	 * @param    parcours   un tableau de coordonnees a afficher
	 */
	public static void afficherParcours(Coordonnees[] parcours)
	{
		for(int compteur=0; compteur < parcours.length; compteur++)
		{
			if(parcours[compteur] != null)
				System.out.println( parcours[compteur] );
		}
	}
	
	/**
	 * Demande un ensemble de coordonnees a l'utilisateur pour en suite
	 * creer un tableau avec toutes ses coordonnees.
	 * 
	 * @return     tableau de coordonnees
	 */
	public static Coordonnees[] creerParcours()
	{
		System.out.print("\nNombre de cibles a declarer: ");
		int nombreDeCibles = Utilitaires.lireUnEntierComprisEntre(1,20);
		
		Coordonnees[] cibles = new Coordonnees[nombreDeCibles];
		int compteur = 0;
		
		while( compteur < nombreDeCibles )
		{	
			cibles[compteur] = TraitementVol.lireCoordonnees();
			compteur++;
		}
		
		return cibles;
	}
	
	public static void epreuve1(){
		System.out.println("\nTon vol a dure "+vol.duree()+ " unites temps.");
	}
   
    public static void epreuve3(){
		System.out.println("\nLa distance du vol est de "+vol.distance()+ " km.");
	}
	
	/**
	 * Affiche les resultats de l'epreuve 4.
	 * Dans cette epreuve, le programme analyse les donnees de vol pour calculer la plus longue
	 * distance d'une jonction sur n unites de temps.
	 * Par exemple, si nous avons  D --- 3km --- P --- 7km --- P --- 2km ---A  sur 2 unites de temps
	 * Le programme vas trouver la jonction de 3 et 7 kilometres.
	 * Ensuite il vas afficher la distance et les coordonnees de la jonction.
	 */
    public static void afficherEpreuve4()
    {
		System.out.print("\nUnites de temps: ");
		int unites = Utilitaires.lireUnEntierComprisEntre(1,50);
		
		// Trouver la plus grande distance avec n unites
		int pointDebutDistance = vol.distanceAvecTemps(unites);
		
		System.out.println("\nPoint debut: " + pointDebutDistance);
		
		// Afficher la taille (en kilometres) de cette distance
		System.out.println("\nLa taille de la distance est " + vol.tailleDistance(pointDebutDistance, unites) + " kilometres.");
		
		// Afficher les coordonnees
		Coordonnees[] parcours = vol.coordonneesParcours(pointDebutDistance, unites);
    	System.out.println("\nVoici les coordonnees:");
    	afficherParcours(parcours);
    }
    
    /**
     * Affiche les resultats de l'epreuve 5.
     * La methode demande a l'utilisateur d'entrer les coordonnees d'une cible.
     * Apres, le programme renvoie quel point du parcours etait le plus proche de la cible
     * et sa distance par rapport a la cible en kilometres.
     */
    public static void afficherEpreuve5()
    {		
		Coordonnees cible = TraitementVol.lireCoordonnees();
		
		Coordonnees pointPlusProche = vol.pointPlusProche(cible);
		System.out.println("\nLe point le plus proche de la cible est...");
		System.out.println(pointPlusProche);
		
		System.out.println("\nEt sa distance par rapport a la cible est " + vol.distanceCible(pointPlusProche, cible) + " kilometres.");
	}
	
	/**
	 * Affiche les resultats de l'epreuve 6.
	 * Dans cette epreuve, l'utilisateur doit declarer un ensemble de cibles.
	 * Le programme calcule alors le nombre de cibles atteintes pendant le vol.
	 * Si toutes les cibles ont etes atteintes, le programme renvoie le temps qu'il a fallu pour le faire.
	 * 
	 * #BUGFIX : Renvoyer un message d'erreur si aucune cible n'a ete atteinte
	 */
	public static void afficherEpreuve6()
	{
		Coordonnees[] cibles = TraitementVol.creerParcours();
		
		System.out.println("\nVoici les cibles atteintes:");
		Coordonnees[] ciblesAtteintes = vol.ciblesAtteintes(cibles);
		afficherParcours(ciblesAtteintes);
		
		if( vol.nombreDeCibles(ciblesAtteintes) == cibles.length )
		{
			System.out.println("La duree est " + vol.dureeVersCible(ciblesAtteintes[cibles.length-1]) );
		}
	}
	
	/**
	 * Affiche les resultats de l'epreuve 7.
	 * L'utilisateur definit un ensemble de cibles en ordre precis et le vol
	 * doit passer par ces points dans l'ordre.
	 * Toute cible pas faite dans l'ordre sera ignoree.
	 * Si toutes les cibles ont etes atteintes, le programme affiche
	 * le temps du vol qui a ete necessaire.
	 */
	public static void afficherEpreuve7()
	{
		Coordonnees[] ciblesEnOrdre = TraitementVol.creerParcours();
		
		/*Coordonnees[] ciblesEnOrdre = { new Coordonnees(109,50),
										new Coordonnees(118,159),
										new Coordonnees(56,159),
										new Coordonnees(38,87),
										new Coordonnees(-90,81) };*/
		
		int nombreCiblesAtteintes = vol.ciblesAtteintesEnOrdre(ciblesEnOrdre);
		System.out.println("\nTu a reussi a atteindre " + nombreCiblesAtteintes + " cibles.\n");
		
		if(nombreCiblesAtteintes == ciblesEnOrdre.length)
			System.out.println("Tu l'as fait en " + vol.dureeVersCible( ciblesEnOrdre[nombreCiblesAtteintes-1] ) + " unites de temps.");
	}
	
	/**
	 * Affiche les resultats de l'epreuve 8.
	 * En fonction d'une cible et d'un rayon fournis par l'utilisateur,
	 * la methode calcule le plus long survol en unites de temps.
	 * #CONSEIL : Utiliser 4 comme perimetre
	 */
	public static void afficherEpreuve8()
	{
		Coordonnees cibleRef = TraitementVol.lireCoordonnees();
		
		System.out.print("\nPerimetre autour de la cible: ");
		double perimetre = Utilitaires.lireUnEntierStrictementPositif();
		
		System.out.print("\nLe plus long survol est de " + vol.dureePlusLongSurvol(cibleRef, perimetre) + " unites de temps.\n");
	}

	/**
	 * Construit un vol sur base des donnees du fichier dont le nom est
	 * encode au clavier
	 * 
	 * @return parcours cree sur base des donnees du fichier
	 */
	private static Vol chargerVol() {
		System.out.print("Introduis le nom du fichier : ");
		String nomFichier = scanner.next();
		Coordonnees[] tableCoordonnees = new Coordonnees[4];
		Fichier fichier = new Fichier(nomFichier);
		int nombreCoordonnees = 0;
		Date date = null;
		String pilote = null;
		try {
			// ouverture fichier
			fichier.ouvrirEnLecture();

			// lecture Date, Pilote
			date = (Date) fichier.lireObjet();
			pilote = (String) fichier.lireObjet();

			// lecture des Coordonnees
			while (true) { // on quitte cette repetitive lorsque EOF rencontree
				Coordonnees coordonnee = (Coordonnees) fichier.lireObjet();
				if(nombreCoordonnees==tableCoordonnees.length){
					Coordonnees[]temp = new Coordonnees[tableCoordonnees.length*2];
					for (int i = 0; i < tableCoordonnees.length; i++) {
						temp[i]=tableCoordonnees[i];	
					}
					tableCoordonnees=temp;
				}
				tableCoordonnees[nombreCoordonnees] = coordonnee;
				nombreCoordonnees++;
			}
		} catch (java.io.EOFException ex) { // fin du fichier rencontree
			Coordonnees[] tableCoordonnees2 = new Coordonnees[nombreCoordonnees];
			for (int i = 0; i < nombreCoordonnees; i++) {
				tableCoordonnees2[i] = tableCoordonnees[i];
			}
			return new Vol(date, pilote, tableCoordonnees2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // quoi qu'il arrive, il doit essayer de fermer le fichier.
			try {
				fichier.fermer();
			} catch (java.io.IOException ex) { // si erreur lors de la fermeture
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}

}
