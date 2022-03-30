package MyGame.Tiles;

import MyGame.Graphics.Assets;

import java.awt.image.BufferedImage;

import static MyGame.Graphics.Assets.*;

/*! \class public class SinkTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip robinet.
 */
public class SinkTile extends Tile {

    // vector de tipul BUfferedImage folosit pentru a stoca tipurile robinetului(mai multe BufferedImage) si al folosi la apelarea constructorului clasei

    private static BufferedImage sink[]={sink1, sink2, sink3, sink4};

    /*! \fn public PrincessTile(int id, int index)
       \brief Constructorul de initializare al clasei

       \param id = Id-ul dalei util in desenarea hartii.
       \param index = Indexul imaginii(BufferedImage) din vectorul declarat anterior in clasa
    */

    public SinkTile(int id, int index)
    {

        /// Apel al constructorului clasei de baza

        super(sink[index], id);
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