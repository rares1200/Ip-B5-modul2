/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphscycles;

import static java.lang.Math.abs;

import buildingParts.Coordinates;

import static java.lang.Math.abs;

/**
 *
 * @author Vero
 */

public class Scara {
    Coordinates colt, coltOpus, origine;
    float lungime, latime;
    
    public Scara(Coordinates colt, Coordinates coltOpus)
    {   
        float coltX = colt.getX();
        float coltY = colt.getY();
        float coltOpusX = coltOpus.getX();
        float coltOpusY = coltOpus.getY();
        
        
        this.colt = colt;
        this.coltOpus = coltOpus;
        
        this.lungime = abs(coltOpus.getX() - colt.getX());
        this.latime = abs(coltOpus.getY() - colt.getY());
        
        
        if(coltX < coltOpusX)
        {
            if(coltY < coltOpusY)
                origine = new Coordinates(coltX + lungime/2, coltY + latime/2);
            else 
                origine = new Coordinates(coltX + lungime/2, coltY - latime/2);
        }
        else
        {
            if(coltY < coltOpusY)
                origine = new Coordinates(coltX - lungime/2, coltY + latime/2);
            else 
                origine = new Coordinates(coltX - lungime/2, coltY - latime/2);
        }
            
    }
    public Coordinates getOrigine() 
    {   
        return origine;
    }
    public float getLatime()
    {
        return latime;
    }
    public float getLungime()
    {
        return lungime;
    }
    
    /*metoda care returneaza adevarat daca punctul de origine al scarii primite
    ca argument se afla in interiorul cercului cu originea in centrul scarii noastre
    */
    public boolean sePotriveste(Scara altaScara)
    {   
        float radius = 2; //sau radius = this.latime/2
        return ScaraUtils.isInCircle(this.origine,altaScara.getOrigine(),radius);
       
    }
    
}
