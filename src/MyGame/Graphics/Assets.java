package MyGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage heart;
    public static BufferedImage task_notes;
    public static BufferedImage glass;
    public static BufferedImage disc;
    public static BufferedImage bed;
    public static BufferedImage Wall;
    public static BufferedImage Wall_Side;
    public static BufferedImage Wall_Door;
    public static BufferedImage sink1;
    public static BufferedImage sink2;
    public static BufferedImage sink3;
    public static BufferedImage sink4;
    public static BufferedImage ghost_fly1;
    public static BufferedImage ghost_fly2;
    public static BufferedImage ghost_fly3;
    public static BufferedImage ghost_fly4;
    public static BufferedImage ghost_fly5;
    public static BufferedImage ghost_fly6;
    public static BufferedImage ghost_dead1;
    public static BufferedImage ghost_dead2;
    public static BufferedImage ghost_dead3;
    public static BufferedImage ghost_dead4;
    public static BufferedImage ghost_dead5;
    public static BufferedImage ghost_dead6;
    public static BufferedImage ghost_dead7;
    public static BufferedImage ghost_shoot1;
    public static BufferedImage ghost_shoot2;
    public static BufferedImage ghost_shoot3;
    public static BufferedImage ghost_shoot4;
    public static BufferedImage ghost_shoot5;
    public static BufferedImage ghost_shoot6;
    public static BufferedImage water_shoot1;
    public static BufferedImage water_shoot2;
    public static BufferedImage water_shoot3;
    public static BufferedImage water_shoot4;
    public static BufferedImage water_shoot5;
    public static BufferedImage water_shoot6;
    public static BufferedImage maid;
    public static BufferedImage Princess1;
    public static BufferedImage Princess2;
    public static BufferedImage Princess3;
    public static BufferedImage Prince1;
    public static BufferedImage Prince2;
    public static BufferedImage Prince3;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/all.png"));

        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        heart = sheet.crop(0, 1);
        task_notes = sheet.crop(0, 0);
        glass = sheet.crop(0,0);
        disc = sheet.crop(3, 0);
        bed = sheet.crop(0, 1);
        Wall = sheet.crop(3, 1);
        Wall_Side = sheet.crop(3, 1);
        Wall_Door = sheet.crop(3, 1);
        sink1 = sheet.crop(1, 0);
        sink2 = sheet.crop(1, 0);
        sink3 = sheet.crop(1, 0);
        sink4 = sheet.crop(1, 0);
        water_shoot1 = sheet.crop(2, 0);
        water_shoot2 = sheet.crop(2, 0);
        water_shoot3 = sheet.crop(2, 0);
        water_shoot4 = sheet.crop(2, 0);
        water_shoot5 = sheet.crop(2, 0);
        water_shoot6 = sheet.crop(2, 0);
        ghost_fly1= sheet.crop(1, 1);
        ghost_fly2= sheet.crop(1, 1);
        ghost_fly3= sheet.crop(1, 1);
        ghost_fly4= sheet.crop(1, 1);
        ghost_fly5= sheet.crop(1, 1);
        ghost_fly6= sheet.crop(1, 1);
        ghost_dead1 = sheet.crop(2, 1);
        ghost_dead2 = sheet.crop(2, 1);
        ghost_dead3 = sheet.crop(2, 1);
        ghost_dead4 = sheet.crop(2, 1);
        ghost_dead5 = sheet.crop(2, 1);
        ghost_dead6 = sheet.crop(2, 1);
        ghost_dead7 = sheet.crop(2, 1);
        ghost_shoot1 = sheet.crop(2, 1);
        ghost_shoot2 = sheet.crop(2, 1);
        ghost_shoot3 = sheet.crop(2, 1);
        ghost_shoot4 = sheet.crop(2, 1);
        ghost_shoot5 = sheet.crop(2, 1);
        ghost_shoot6 = sheet.crop(2, 1);
        maid = sheet.crop(0, 0);
        Princess1 = sheet.crop(0, 0);
        Princess2 = sheet.crop(0, 0);
        Princess3 = sheet.crop(0, 0);
        Prince1 = sheet.crop(0, 0);
        Prince2 = sheet.crop(0, 3);
        Prince3 = sheet.crop(1, 3);
    }
}