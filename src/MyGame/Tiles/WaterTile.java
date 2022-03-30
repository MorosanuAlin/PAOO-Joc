package MyGame.Tiles;

import MyGame.Graphics.Assets;

import java.awt.image.BufferedImage;

import static MyGame.Graphics.Assets.*;

/*! \class public class WaterTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip apa.
 */
public class WaterTile extends Tile {

    // vector de tipul BUfferedImage folosit pentru a stoca tipurile atacului cu apa(mai multe BufferedImage) si al folosi la apelarea constructorului clasei

    private static BufferedImage water[]={water_shoot1, water_shoot2, water_shoot3, water_shoot4, water_shoot5, water_shoot6};

    /*! \fn public WaterTile(int id, int index)
       \brief Constructorul de initializare al clasei

       \param id = Id-ul dalei util in desenarea hartii.
       \param index = Indexul imaginii(BufferedImage) din vectorul declarat anterior in clasa
    */

    public WaterTile(int id, int index)
    {

        /// Apel al constructorului clasei de baza

        super(water[index], id);
    }


    /*! \fn public boolean IsSolid()
        \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
     */
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}