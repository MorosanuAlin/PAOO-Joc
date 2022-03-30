package MyGame.Tiles;


import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 50;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

    /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
    /// o singura data in memorie
    public static Tile ghost_fly1_Tile       = new GhostTile(1,0);     /*!< Dala de tip ghost_fly*/
    public static Tile ghost_fly2_Tile       = new GhostTile(2,1);     /*!< Dala de tip ghost_fly*/
    public static Tile ghost_fly3_Tile       = new GhostTile(3,2);     /*!< Dala de tip ghost_fly*/
    public static Tile ghost_fly4_Tile       = new GhostTile(4,3);     /*!< Dala de tip ghost_fly*/
    public static Tile ghost_fly5_Tile       = new GhostTile(5,4);     /*!< Dala de tip ghost_fly*/
    public static Tile ghost_fly6_Tile       = new GhostTile(6,5);     /*!< Dala de tip ghost_fly*/
    public static Tile ghost_dead1_Tile      = new GhostTile(7,6);     /*!< Dala de tip ghost_dead*/
    public static Tile ghost_dead2_Tile      = new GhostTile(8,7);     /*!< Dala de tip ghost_dead*/
    public static Tile ghost_dead3_Tile      = new GhostTile(9,8);     /*!< Dala de tip ghost_dead*/
    public static Tile ghost_dead4_Tile      = new GhostTile(10,9);    /*!< Dala de tip ghost_dead*/
    public static Tile ghost_dead5_Tile      = new GhostTile(11,10);   /*!< Dala de tip ghost_dead*/
    public static Tile ghost_dead6_Tile      = new GhostTile(12,11);   /*!< Dala de tip ghost_dead*/
    public static Tile ghost_dead7_Tile      = new GhostTile(13,12);   /*!< Dala de tip ghost_dead*/
    public static Tile ghost_shoot1_Tile     = new GhostTile(14,13);   /*!< Dala de tip ghost_shoot*/
    public static Tile ghost_shoot2_Tile     = new GhostTile(15,14);   /*!< Dala de tip ghost_shoot*/
    public static Tile ghost_shoot3_Tile     = new GhostTile(16,15);   /*!< Dala de tip ghost_shoot*/
    public static Tile ghost_shoot4_Tile     = new GhostTile(17,16);   /*!< Dala de tip ghost_shoot*/
    public static Tile ghost_shoot5_Tile     = new GhostTile(18,17);   /*!< Dala de tip ghost_shoot*/
    public static Tile ghost_shoot6_Tile     = new GhostTile(19,18);   /*!< Dala de tip ghost_shoot*/
    public static Tile heart_Tile            = new ItemsTile(20,0);    /*!< Dala de tip item*/
    public static Tile task_notes_Tile       = new ItemsTile(21,1);    /*!< Dala de tip item*/
    public static Tile disc_Tile             = new ItemsTile(22,2);    /*!< Dala de tip item*/
    public static Tile bed_Tile              = new ItemsTile(23,3);    /*!< Dala de tip item*/
    public static Tile glass_Tile            = new ItemsTile(24,4);    /*!< Dala de tip item*/
    public static Tile maid_Tile             = new MaidTile(25);                /*!< Dala de tip maid*/
    public static Tile princess1_Tile        = new PrincessTile(26,0); /*!< Dala de tip princess*/
    public static Tile princess2_Tile        = new PrincessTile(27,1); /*!< Dala de tip princess*/
    public static Tile princess3_Tile        = new PrincessTile(28,2); /*!< Dala de tip princess*/
    public static Tile prince1_Tile          = new PrinceTile(29,0);   /*!< Dala de tip prince*/
    public static Tile prince2_Tile          = new PrinceTile(30,1);   /*!< Dala de tip prince*/
    public static Tile prince3_Tile          = new PrinceTile(31,2);   /*!< Dala de tip prince*/
    public static Tile sink1_Tile            = new SinkTile(32,0);     /*!< Dala de tip sink*/
    public static Tile sink2_Tile            = new SinkTile(33,1);     /*!< Dala de tip sink*/
    public static Tile sink3_Tile            = new SinkTile(34,2);     /*!< Dala de tip sink*/
    public static Tile sink4_Tile            = new SinkTile(35,3);     /*!< Dala de tip sink*/
    public static Tile wall_Tile             = new WallTile(36,0);     /*!< Dala de tip wall*/
    public static Tile wall_side_Tile        = new WallTile(37,1);     /*!< Dala de tip wall*/
    public static Tile wall_door_Tile        = new WallTile(38,2);     /*!< Dala de tip wall*/
    public static Tile water_shoot1_Tile     = new WaterTile(39,0);    /*!< Dala de tip water_shoot*/
    public static Tile water_shoot2_Tile     = new WaterTile(40,1);    /*!< Dala de tip water_shoot*/
    public static Tile water_shoot3_Tile     = new WaterTile(41,2);    /*!< Dala de tip water_shoot*/
    public static Tile water_shoot4_Tile     = new WaterTile(42,3);    /*!< Dala de tip water_shoot*/
    public static Tile water_shoot5_Tile     = new WaterTile(43,4);    /*!< Dala de tip water_shoot*/
    public static Tile water_shoot6_Tile     = new WaterTile(44,5);    /*!< Dala de tip water_shoot*/

    public static final int TILE_WIDTH  = 48;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 40;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
        /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
}

