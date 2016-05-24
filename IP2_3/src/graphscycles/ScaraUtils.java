package graphscycles;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import buildingParts.Coordinates;

/**
 *
 * @author Tudor
 */
public class ScaraUtils{
   
    /* Verifica daca centrul unei scari b se afla in raza cu centrul altei scari a*/
    public static boolean isInCircle(Coordinates a, Coordinates b, float radius)
    {
        
        if ( ((b.getX() - a.getX())*(b.getX() - a.getX()) + (b.getY() - a.getY())*(b.getY() - a.getY())) <= radius*radius )
            return true;
        else return false;
    }
}
