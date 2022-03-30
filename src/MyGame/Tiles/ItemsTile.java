package MyGame.Tiles;

import MyGame.Graphics.Assets;

import java.awt.image.BufferedImage;

import static MyGame.Graphics.Assets.*;

/*! \class public class ItemsTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip items.
 */
public class ItemsTile extends Tile {

    // vector de tipul BUfferedImage folosit pentru a stoca tipurile items(mai multe BufferedImage) si al folosi la apelarea constructorului clasei

    private static BufferedImage items[]={heart, task_notes, disc, bed, glass};

    /*! \fn public PrincessTile(int id, int index)
       \brief Constructorul de initializare al clasei

       \param id = Id-ul dalei util in desenarea hartii.
       \param index = Indexul imaginii(BufferedImage) din vectorul declarat anterior in clasa
    */

    public ItemsTile(int id, int index)
    {

        /// Apel al constructorului clasei de baza

        super(items[index], id);
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