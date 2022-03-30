package MyGame.Tiles;

import MyGame.Graphics.Assets;

import java.awt.image.BufferedImage;

import static MyGame.Graphics.Assets.*;

/*! \class public class GhostTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip fantoma.
 */
public class GhostTile extends Tile {

    // vector de tipul BUfferedImage folosit pentru a stoca tipurile fantomei+abilitatile acestora(mai multe BufferedImage) si al folosi la apelarea constructorului clasei

    private static BufferedImage ghost[]={ghost_fly1, ghost_fly2, ghost_fly3, ghost_fly4, ghost_fly5, ghost_fly6,ghost_dead1, ghost_dead2, ghost_dead3, ghost_dead4, ghost_dead5, ghost_dead6, ghost_dead7, ghost_shoot1,  ghost_shoot2,  ghost_shoot3,  ghost_shoot4,  ghost_shoot5,  ghost_shoot6};

    /*! \fn public GhostTile(int id, int index)
       \brief Constructorul de initializare al clasei

       \param id = Id-ul dalei util in desenarea hartii.
       \param index = Indexul imaginii(BufferedImage) din vectorul declarat anterior in clasa
    */

    public GhostTile(int id, int index)
    {

        /// Apel al constructorului clasei de baza

        super(ghost[index], id);
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