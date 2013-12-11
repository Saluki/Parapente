/**
 * Projet d'algorithmique PARAPENTE
 * 
 * Classe permettant de memoriser et traiter les coordonnees d'un vol
 * 
 * @author   Dominguez Mikael , Badot-Bertrand Corentin
 * @version  Beta
 */
public class Vol
{
	private Date date; // date du parcours
	private String pilote; // nom et prenom du pilote
	private Coordonnees[] tableCoordonnees;


	public Vol(Date date, String pilote, Coordonnees[] tableCoordonnees) {
		this.date = date;
		this.pilote = pilote;
		this.tableCoordonnees = tableCoordonnees;
	}

	/**
	 * Cette methode renvoie la duree du vol.
	 * Une unite de temps correspond au temps ecoule entre 2 mesures de position du gps.
	 * @return    la duree en unites de temps
	 */
	public int duree() {
		return this.tableCoordonnees.length-1;
	}
	
	/**
	 * Cette methode renvoie la duree du vol jusqu'a la distance la plus eloignee
	 * L'unitÃ© de temps correspond Ã  l'indice de la coordonnee
	 * @return la duree jusqu'au point max
	 */
	public int dureePointMax() {
		return indiceDistanceMax();
	}
	
	private double arrondir(double distance)
	{
		return Math.round(distance*10000.0)/10000.0;
	}
   
   /**
	 * Cette methode renvoie les coordonee de la table a l'indice choisi.
	 * 
	 * @return les coordonees
	 */
	public Coordonnees retourCoordonnees(int indice) {
		return this.tableCoordonnees[indice] ;
	}
   
   /**
	 * Cette methode renvoie les coordonnees du point de depart .
	 * On utilise la methodes : retourCoordonnee a l'indice 0 
	 * @return les coordonnees du point de depart 
	 */
	public Coordonnees pointDeDepart() {
		return this.retourCoordonnees(0) ;
	}
   
   
   /**
	 * Cette methode renvoie les coordonnees du lieux le plus eloigne .
	 * On utilise deux methodes : retourCoordonnee et indiceDistanceMax 
	 * @return les coordonnees les plus eloignes
	 */
	public Coordonnees pointLePlusLoin() {
		return this.retourCoordonnees(this.indiceDistanceMax()) ;
	}

	/**
	 * Cette methode calcule la distance parcourue. Cette distance sera
	 * obtenue en additionnant les distances des segments du vol memorise.
	 * 
	 * @return la distance parcourue
	 */
	public double distance() {
		double distance = 0;
      for ( int i = 1 ; i < this.tableCoordonnees.length ; i++ ){
        distance = distance + this.tableCoordonnees[i].distance(this.tableCoordonnees[i-1]) ;
      }
		return distance;
	}
   
    /**
	 * Cette methode calcule la distance parcourue jusqu'Ã  l'indice de la coordonnÃ©e voulu. Cette 
	 * distance sera obtenue en additionnant les distances des segments du vol jusqu'Ã  l'indice demandÃ©.
	 * 
	 * @return la distance parcourue
	 */
	public double distanceIndice(int indice) {
	  double distance = 0;
      for ( int i = 1 ; i < indice+1 ; i++ ){
        distance = distance + this.tableCoordonnees[i].distance(this.tableCoordonnees[i-1]) ;
      }
		return distance;
	}
   
   /**
	 * Cette methode calcule la distance la plus eloigne par rapport au point d'origine. Cette distance sera
	 * obtenue en comparant tout les segements des coordonnees par rapport au point de depart et 
	 * d'enregister le segment le plus grand .
     *
	 * @return la distance la plus longue par rapport au point de depart
	 */
   public double distanceMax()
   {
		//double distanceMax = 0;
	   return 0.00;
   }
   
   /**
	 * Cette methode calcule la distance la plus Ã©loignÃ© par rapport au point d'origine. Cette distance sera
	 * obtenue en comparant tout les segements des coordonÃ©es par rapport au point de dÃ©part . Ensuite nous 
	 * enregistrons l'indice du segment le plus grand .
    *
	 * @return l'indice de la distance la plus longue Ã  vol d'oiseau par rapport au point de dÃ©part
	 */
   public int indiceDistanceMax() {
		double distanceMax = 0;
      int indiceMax = 0 ;
     
      for ( int i = 0 ; i < this.tableCoordonnees.length ; i++ ){
         if (  this.tableCoordonnees[0].distance(this.tableCoordonnees[i])> distanceMax ){  
            distanceMax = this.tableCoordonnees[0].distance(this.tableCoordonnees[i]) ;
            indiceMax = i ;
           
         }
      }
      return indiceMax ;
      
	}
   
   
   /**
	 * Cette methode renvoie la distance parcourue pour atteindre le plus le plus eloigne .
	 * On utilise deux mÃ©thodes : distanceIndice et indiceDistanceMax 
	 * @return distance parcourue jusqu'au point le plus eloigne 
	 */
	public double distanceParcourueAuPointMax() {
		return arrondir( this.distanceIndice(this.indiceDistanceMax())) ;
	}
   
   /**
     * Calcule la plus longue distance d'une intersection de n unites de temps.
     * Cette methode a ete concue pour l'epreuve 4.
     * 
     * @param    unites   le nombre d'unites de temps
     * @return            l'emplacement du coordonnees de debut dans la table de vol
     */
	public int distanceAvecTemps(int unites)
	{
		int pointDebutMax = 0; 
		double distanceMax = 0;
		
		int pointFinal = this.tableCoordonnees.length - unites;
		
		for(int compteur=0; compteur < pointFinal; compteur++)
		{
			double distanceTotal = 0;
			
			for(int compteurSegments = 0; compteurSegments < unites; compteurSegments++)
			{
				distanceTotal += this.tableCoordonnees[compteur + compteurSegments].distance(this.tableCoordonnees[compteur + compteurSegments + 1]);
			}
						
			if(distanceTotal >= distanceMax)
			{
				distanceMax = distanceTotal;
				pointDebutMax = compteur;
			}
		}
		
		return pointDebutMax;
	}
	
	/**
	 * Calcule la distance d'une intersection du vol en kilometres
	 * a partir d'un point de depart sur une distance de n unites de temps.
	 * Cette methode a ete concue pour l'epreuve 4.
	 * 
	 * @param    pointDebut   les coordonnees du point de debut de l'intersection
	 * @param    unites       la distance de l'intersection en unites
	 * @return                la distance de l'intersection en kilometres
	 */ 
	public double tailleDistance(int pointDebut, int unites)
	{
		double distance = 0;
		
		for(int compteur=0; compteur < unites; compteur++)
		{
			distance += this.tableCoordonnees[pointDebut + compteur].distance(this.tableCoordonnees[pointDebut + compteur + 1]);
		}
				
		return arrondir( distance );
	}
	
	/**
	 * Retourne toutes les coordonnees d'une intersection a partir d'un point
	 * sur une distance de n unites de temps.
	 * Cette methode a ete concue pour l'epreuve 4.
	 * #BUGFIX : Voir si on doit aussi retourner les coordonnees de fin
	 * 
	 * @param    pointDebut   les coordonnees du point de debut de l'intersection
	 * @param    unites       la distance de l'intersection en unites
	 * @return                toutes les coordonnees de l'intersection
	 */
	public Coordonnees[] coordonneesParcours(int pointDebut, int unites)
	{
		Coordonnees[] parcoursActuel = new Coordonnees[unites];
		
		for(int compteur=0; compteur < unites; compteur++)
		{
			parcoursActuel[compteur] = this.tableCoordonnees[pointDebut + compteur];
		}
		
		return parcoursActuel;
	}
	
	/**
	 * Retourne le point d'un trajet le plus proche d'une cible.
	 * Pour cela, la methode va comparer point par point la distance avec la cible.
	 * Cette methode a ete concue pour l'epreuve 5.
	 * 
	 * @param    cible   la cible sur laquelle on doit calculer la distance
	 * @return           les coordonnees du point le plus proche
	 */
	public Coordonnees pointPlusProche(Coordonnees cible)
	{
		Coordonnees pointPlusProche = new Coordonnees(0,0);
		double plusPetiteDistance = 500;
		
		for(int compteur=0; compteur < this.tableCoordonnees.length; compteur++)
		{
			double distanceActuelle = this.tableCoordonnees[compteur].distance( cible );
			
			if( distanceActuelle < plusPetiteDistance )
			{
				plusPetiteDistance = distanceActuelle;
				pointPlusProche = this.tableCoordonnees[compteur];
			}
		}
		
		return pointPlusProche;
	}
	
	/**
	 * Renvoie la distance en kilometres entre deux point.
	 * Cette methode a ete concue pour l'epreuve 5.
	 * 
	 * @param    pointAlpha   les coordonnees du premier point
	 * @param    pointBeta    les coordonnees du deuxieme point
	 * @return                la distance arrondie en kilometres
	 */
	public double distanceCible(Coordonnees pointAlpha, Coordonnees pointBeta)
	{
		return arrondir( pointAlpha.distance( pointBeta ) );
	}
	
	/**
	 * Calcule le nombre de cibles atteintes pendant un trajet et renvoie les cibles atteintes.
	 * Pour cela, la methode se base sur une liste de cibles predefinies.
	 * Cette methode a ete concue pour l'epreuve 6.
	 * 
	 * @param    tableCibles   un tableau de coordonnees avec des cibles
	 * @return                 un tableau avec les cibles atteintes.
	 */
	public Coordonnees[] ciblesAtteintes(Coordonnees[] tableCibles)
	{
		int nbCiblesAtteintes = 0;
		Coordonnees[] ciblesAtteintes = new Coordonnees[ tableCibles.length ];
		
		for(int compteur=0; compteur < this.tableCoordonnees.length; compteur++)
		{
			for(int compteurCibles=0; compteurCibles < tableCibles.length; compteurCibles++)
			{
				if( this.tableCoordonnees[compteur].equals( tableCibles[compteurCibles] ) )
				{
					// Si il trouve une cible atteinte
					ciblesAtteintes[ nbCiblesAtteintes ] = tableCibles[compteurCibles];
					nbCiblesAtteintes++;
					break;
				}
			}
		}
		
		return ciblesAtteintes;
	}
	
	/**
	 * Renvoie la duree du vol avant d'avoir atteint une cible.
	 * Si une cible se trouve a 3 unites de temps du depart, il renverra 3.
	 * Cette methode a ete concue pour l'epreuve 6.
	 * #BUGFIX : Si la cible n'est pas sur leparcours, la methode doit renvoyer 0
	 * 
	 * @param    cible   la cible qui devrait se trouver sur le parcours
	 * @return           le nombre d'unites de temps a partir du depart jusqu'a la cible
	 */
	public int dureeVersCible(Coordonnees cible)
	{
		int unitesDeTemps = 0;
		
		while( !this.tableCoordonnees[unitesDeTemps].equals(cible) && unitesDeTemps < this.tableCoordonnees.length)
		{
			unitesDeTemps++;
		}
		
		return unitesDeTemps;
	}
	
	/**
	 * Renvoie le nombre de coordonnees presents dans une table.
	 * Par exemple, une table avec 20 emplacements et seulement 5 coordonnees.
	 * Cette methode a ete concue pour l'epreuve 6.
	 * 
	 * @param    cibles	  une table de coordonnees
	 * @return            le nombre de coordonnees dans la table
	 */
	public int nombreDeCibles(Coordonnees[] cibles)
	{
		int compteurCibles=0;
		
		while(compteurCibles < cibles.length)
		{
			if( cibles[compteurCibles] == null )
				break;
				
			compteurCibles++;
		}

		return compteurCibles;
	}
	
	/**
	 * Renvoie le nombre de cibles qui ont etes atteintes dans l'ordre
	 * sur base d'un tableau de cibles.
	 * Cette methode a ete concue pour l'epreuve 7.
	 * 
	 * @param    ciblesEnOrdre   une table de coordonnees avec les cibles
	 * @return                   le nombre de cibles atteintes
	 */
	public int ciblesAtteintesEnOrdre(Coordonnees[] ciblesEnOrdre)
	{
		int pointeurCible = 0;
		
		for(int compteur=0; compteur < this.tableCoordonnees.length; compteur++)
		{
			if(this.tableCoordonnees[compteur].equals(ciblesEnOrdre[pointeurCible])) // Si la cible actuelle est egale au lieu actuel
				pointeurCible++;
				
			if(pointeurCible == ciblesEnOrdre.length) // Si toutes cibles sont passes
				break;
		}
		
		return pointeurCible;
	}
	
	/**
	 * Renvoie le plus long survol d'une zone basee sur une cible et un perimetre.
	 * Le survol est exprime en unites de temps.
	 * 
	 * @param    cibleDeReference   les coordonnees de la cible de reference
	 * @param    perimetre          le perimetre en kilometres
	 * @return                      le temps de survol en unites de temps
	 */
	public int dureePlusLongSurvol(Coordonnees cibleDeReference, double perimetre){
   
      int pointsMax=0, sommePoints = 0;
   		
   		for(int compteur=0; compteur < this.tableCoordonnees.length; compteur++)
   		{	
   			double distanceEntrePoints = distanceCible(cibleDeReference, this.tableCoordonnees[compteur]);
   			
   			if( distanceEntrePoints <= perimetre)	// Si le point est dans le perimetre
   			{
   				sommePoints++;
               if ( compteur == this.tableCoordonnees.length-1 ) pointsMax = pointsMax + sommePoints-1 ;
   			}
   			else	// Sinon, remet le compteur a 0
   			{
               if (  sommePoints > 1 ) pointsMax = pointsMax + sommePoints-1 ;   
   				
   				sommePoints = 0;
   			}
   			
   		}
   		
   		return pointsMax ; // Unites de temps = nb de points
   }
   
   /**
	 * Cette methode verifie le nombre de fois qu'un vol croise les segments de couple d'obstacle ( les portes ).  
	 * Ces portes doivent etre franchies dans l'ordre .  
    * Un nombre de point sera calculer selon le nombre de portes franchient 
	 * 
	 * @return le nombre de traversee reussi
	 */
   public int traverseePortes( Coordonnees[] portes) {
   int point = 0 ;
   int j = 0 ;
   boolean croiser = false ;
     
      for ( int i = 0 ; i < portes.length-1 ; i++ ){
         
         croiser = false ;
         
         while ( j < this.tableCoordonnees.length-1 && croiser == false ) {
         
            if (  Coordonnees.segmentsCroises(portes[i], portes[i+1], this.tableCoordonnees[j], this.tableCoordonnees[j+1])) {
               point++ ;
               croiser = true ;     
            }
            j++ ;
         }
         i++ ;  //pour passer directement aux coordonnee de la porte suivante    
      }
      return point ;
      
	}


}
