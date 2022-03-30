package MyGame;


import MyGame.GameWindow.GameWindow;
import MyGame.Graphics.Assets;
import MyGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferStrategy;

/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie).

    In mod obisnuit aceasta clasa trebuie sa contina urmatoarele:
        - public Game();            //constructor
        - private void init();      //metoda privata de initializare
        - private void update();    //metoda privata de actualizare a elementelor jocului
        - private void draw();      //metoda privata de desenare a tablei de joc
        - public run();             //metoda publica ce va fi apelata de noul fir de executie
        - public synchronized void start(); //metoda publica de pornire a jocului
        - public synchronized void stop()   //metoda publica de oprire a jocului
 */


public class Game implements Runnable
{
    private GameWindow      wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean         runState;   /*!< Flag ce starea firului de executie.*/
    private Thread          gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy  bs;         /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/


    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************


    private Graphics        g;          /*!< Referinta catre un context grafic.*/


    private Tile tile; /*!< variabila membra temporara. Este folosita in aceasta etapa doar pentru a desena ceva pe ecran.*/

    /*! \fn public Game(String title, int width, int height)
        \brief Constructor de initializare al clasei Game.

        Acest constructor primeste ca parametri titlul ferestrei, latimea si inaltimea
        acesteia avand in vedere ca fereastra va fi construita/creata in cadrul clasei Game.

        \param title Titlul ferestrei.
        \param width Latimea ferestrei in pixeli.
        \param height Inaltimea ferestrei in pixeli.
     */


    public Game(String title, int width, int height)
    {
        /// Obiectul GameWindow este creat insa fereastra nu este construita
        /// Acest lucru va fi realizat in metoda init() prin apelul
        /// functiei BuildGameWindow();

        wnd = new GameWindow(title, width, height);
        /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)

        runState = false;
    }

    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    private void InitGame()
    {
        wnd = new GameWindow("Schelet Proiect Game", 800, 600);

        /// Este construita fereastra grafica.
        wnd.BuildGameWindow();

        /// Se incarca toate elementele grafice (dale)
        Assets.Init();
    }

    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */

    public void run()
    {
        /// Initializeaza obiectul game
        InitGame();

        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState == true)
        {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();

            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {
                /// Actualizeaza pozitiile elementelor
                Update();
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                oldTime = curentTime;

            }
        }

    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */

    public synchronized void StartGame()
    {
        if(runState == false)
        {
            /// Se actualizeaza flagul de stare a threadului
            runState = true;

            /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
            /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);

            /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }
        else
        {
            /// Thread-ul este creat si pornit deja
            return;
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */

    public synchronized void StopGame()
    {
        if(runState == true)
        {
            /// Actualizare stare thread
            runState = false;
            /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try
            {
                /// Metoda join() pune un thread in asteptare pana cand un altul isi termina executie.
                /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
        else
        {
            /// Thread-ul este oprit deja.
            return;
        }
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */

    private void Update()
    {

    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */

    private void Draw()
    {
        /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();
        /// Verific daca buffer strategy a fost construit sau nu
        if(bs == null)
        {
            /// Se executa doar la primul apel al metodei Draw()
            try
            {
                /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }

        /// Se obtine contextul grafic curent in care se poate desena.
        g = bs.getDrawGraphics();

        /// Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        /// operatie de desenare
        //Tile.ghost_fly1_Tile  .Draw(g, 1 * Tile.TILE_WIDTH, 1 * Tile.TILE_WIDTH);
        //Tile.ghost_fly2_Tile  .Draw(g, 2 * Tile.TILE_WIDTH, 2 * Tile.TILE_WIDTH);
        //Tile.ghost_fly3_Tile  .Draw(g, 3 * Tile.TILE_WIDTH, 3 * Tile.TILE_WIDTH);
        //Tile.ghost_fly4_Tile  .Draw(g, 4 * Tile.TILE_WIDTH, 4 * Tile.TILE_WIDTH);
        //Tile.ghost_fly5_Tile  .Draw(g, 5 * Tile.TILE_WIDTH, 5 * Tile.TILE_WIDTH);
        //Tile.ghost_fly6_Tile  .Draw(g, 6 * Tile.TILE_WIDTH, 6 * Tile.TILE_WIDTH);
        //Tile.ghost_dead1_Tile .Draw(g, 7 * Tile.TILE_WIDTH, 7 * Tile.TILE_WIDTH);
        //Tile.ghost_dead2_Tile .Draw(g, 8 * Tile.TILE_WIDTH, 8 * Tile.TILE_WIDTH);
        //Tile.ghost_dead3_Tile .Draw(g, 9 * Tile.TILE_WIDTH, 9 * Tile.TILE_WIDTH);
        //Tile.ghost_dead4_Tile .Draw(g, 10 * Tile.TILE_WIDTH, 10 * Tile.TILE_WIDTH);
        //Tile.ghost_dead5_Tile .Draw(g, 11 * Tile.TILE_WIDTH, 11* Tile.TILE_WIDTH);
        //Tile.ghost_dead6_Tile .Draw(g, 12 * Tile.TILE_WIDTH, 12 * Tile.TILE_WIDTH);
        //Tile.ghost_dead7_Tile .Draw(g, 13 * Tile.TILE_WIDTH, 13 * Tile.TILE_WIDTH);
        //Tile.ghost_shoot1_Tile.Draw(g, 14 * Tile.TILE_WIDTH, 14 * Tile.TILE_WIDTH);
        //Tile.ghost_shoot2_Tile.Draw(g, 15 * Tile.TILE_WIDTH, 15 * Tile.TILE_WIDTH);
        //Tile.ghost_shoot3_Tile.Draw(g, 16 * Tile.TILE_WIDTH, 16 * Tile.TILE_WIDTH);
        //Tile.ghost_shoot4_Tile.Draw(g, 17 * Tile.TILE_WIDTH, 17 * Tile.TILE_WIDTH);
        //Tile.ghost_shoot5_Tile.Draw(g, 18 * Tile.TILE_WIDTH, 18 * Tile.TILE_WIDTH);
        //Tile.ghost_shoot6_Tile.Draw(g, 19 * Tile.TILE_WIDTH, 19 * Tile.TILE_WIDTH);
        //Tile.heart_Tile       .Draw(g, 20 * Tile.TILE_WIDTH, 20 * Tile.TILE_WIDTH);
        //Tile.task_notes_Tile  .Draw(g, 21 * Tile.TILE_WIDTH, 21 * Tile.TILE_WIDTH);
        //Tile.disc_Tile        .Draw(g, 22 * Tile.TILE_WIDTH, 22 * Tile.TILE_WIDTH);
        //Tile.bed_Tile         .Draw(g, 23 * Tile.TILE_WIDTH, 23 * Tile.TILE_WIDTH);
        //Tile.glass_Tile       .Draw(g, 24 * Tile.TILE_WIDTH, 24 * Tile.TILE_WIDTH);
        //Tile.maid_Tile        .Draw(g, 25 * Tile.TILE_WIDTH, 25 * Tile.TILE_WIDTH);
        //Tile.princess1_Tile   .Draw(g, 26 * Tile.TILE_WIDTH, 26 * Tile.TILE_WIDTH);
        //Tile.princess2_Tile   .Draw(g, 27 * Tile.TILE_WIDTH, 27 * Tile.TILE_WIDTH);
        //Tile.princess3_Tile   .Draw(g, 28 * Tile.TILE_WIDTH, 28 * Tile.TILE_WIDTH);
        //Tile.prince1_Tile     .Draw(g, 29 * Tile.TILE_WIDTH, 29 * Tile.TILE_WIDTH);
        //Tile.prince2_Tile     .Draw(g, 30 * Tile.TILE_WIDTH, 30 * Tile.TILE_WIDTH);
        //Tile.prince3_Tile     .Draw(g, 31 * Tile.TILE_WIDTH, 31 * Tile.TILE_WIDTH);
        //Tile.sink1_Tile       .Draw(g, 32 * Tile.TILE_WIDTH, 32 * Tile.TILE_WIDTH);
        //Tile.sink2_Tile       .Draw(g, 33 * Tile.TILE_WIDTH, 33 * Tile.TILE_WIDTH);
        //Tile.sink3_Tile       .Draw(g, 34 * Tile.TILE_WIDTH, 34 * Tile.TILE_WIDTH);
        //Tile.sink4_Tile       .Draw(g, 35 * Tile.TILE_WIDTH, 35 * Tile.TILE_WIDTH);
        //Tile.wall_Tile        .Draw(g, 36 * Tile.TILE_WIDTH, 36 * Tile.TILE_WIDTH);
        //Tile.wall_side_Tile   .Draw(g, 37 * Tile.TILE_WIDTH, 37 * Tile.TILE_WIDTH);
        //Tile.wall_door_Tile   .Draw(g, 38 * Tile.TILE_WIDTH, 38 * Tile.TILE_WIDTH);
        //Tile.water_shoot1_Tile.Draw(g, 39 * Tile.TILE_WIDTH, 39 * Tile.TILE_WIDTH);
        //Tile.water_shoot2_Tile.Draw(g, 40 * Tile.TILE_WIDTH, 40 * Tile.TILE_WIDTH);
        //Tile.water_shoot3_Tile.Draw(g, 41 * Tile.TILE_WIDTH, 41 * Tile.TILE_WIDTH);
        //Tile.water_shoot4_Tile.Draw(g, 42 * Tile.TILE_WIDTH, 42 * Tile.TILE_WIDTH);
        //Tile.water_shoot5_Tile.Draw(g, 43 * Tile.TILE_WIDTH, 43 * Tile.TILE_WIDTH);
        //Tile.water_shoot6_Tile.Draw(g, 44 * Tile.TILE_WIDTH, 44 * Tile.TILE_WIDTH);
        //
        Tile.maid_Tile.Draw(g , 0 *Tile.TILE_WIDTH,0);
        
        g.drawRect(1 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);


        // end operatie de desenare
        /// Se afiseaza pe ecran
        bs.show();

        /// Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        Tile.maid_Tile.Draw(g , 1 *Tile.TILE_WIDTH,0);

        g.drawRect(1 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);


        // end operatie de desenare
        /// Se afiseaza pe ecran
        bs.show();


        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }
}