package MyGame.Tiles;

import MyGame.Graphics.Assets;

import java.awt.image.BufferedImage;

import static MyGame.Graphics.Assets.*;

/*! \class public class PrinceTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip print.
 */
public class PrinceTile extends Tile {

    // vector de tipul BUfferedImage folosit pentru a stoca tipurile printului(mai multe BufferedImage) si al folosi la apelarea constructorului clasei

    private static BufferedImage prince[] = {Prince1, Prince2, Prince3};

    /*! \fn public PrincessTile(int id, int index)
       \brief Constructorul de initializare al clasei

       \param id = Id-ul dalei util in desenarea hartii.
       \param index = Indexul imaginii(BufferedImage) din vectorul declarat anterior in clasa
    */

    public PrinceTile(int id,int index)
    {
        /// Apel al constructorului clasei de baza
        super(prince[index], id);
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
