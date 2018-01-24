package game_Controller;

//Begriffe und Abkürzungen / Glossar:
//(bps = Bilder pro Sekunde, Part = Thread, X)

public class Clock 
{
	private int bps = 60;
	private int bZähler = 0;
	private boolean läuft = false;		// Müssen geregelt werden.
	private boolean pausiert = false;	// Müssen geregelt werden
	
	public Clock() 
	{
		
	}
	
	//Startet einen neuen Part. Führt die Methode spielRing() aus.
	public void laufenderSpielRing() 
	{
		Thread ring = new Thread()
	      {
			
			
			
	         public void run()
	         {
	            spielRing();
	         }
	         
	         
	         
	      };
	      
	      ring.start();
	}
	
	//Nur in einem anderen Thread ausführen! penis
	public void spielRing()
	{
		
	      final double wiederholrate = 30.0;
	      
	      
	      //Schätzt ein wie viel Zeit benötigt wird um die angepeilten bps zu erreichen!
	      final double zeitZwischenAktualisierungen = 1000000000 / wiederholrate;
	      final int maxAktualisierungen = 1;
	      
	      
	      
	      double lastUpdateTime = System.nanoTime();
	      
	      
	      
	      //Einfacher Weg um die bps festzustellen.
	      int lastSecondTime = (int) (lastUpdateTime / 1000000000);
	      
	      
	      
	      //Hauptkonstrukt
	      while (läuft)
	      {
	         double now = System.nanoTime();
	         int updateCount = 0;
	         
	         if (!pausiert)
	         {
	        	 
	        	 
	        	 
	        	//Soviele Aktualisierungen wie nötig machen. (Wie viele das sein werden bereits in Zeile 41 festgelegt(1 um visuelle Fehler zu vermeiden))
	            while( now - lastUpdateTime > zeitZwischenAktualisierungen && updateCount < maxAktualisierungen )
	            {
	               aktualisierung();
	               lastUpdateTime += zeitZwischenAktualisierungen;
	               updateCount++;
	            }
	         
	            
	            
	            //Aktualisierung der bereits erstellten Bilder.
	            int thisSecond = (int) (lastUpdateTime / 1000000000);
	            if (thisSecond > lastSecondTime)
	            {
	               //System.out.println("NEW SECOND " + thisSecond + " " + bZähler);
	               bps = bZähler;
	               bZähler = 0;
	               lastSecondTime = thisSecond;
	            }
	         
	         }
	      }
	      
	}
	
	//Integration der Aktualisierungen der Spielmechanik!
	public void aktualisierung() 
	{
		
	}

}
