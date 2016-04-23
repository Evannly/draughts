package draughts;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class GameOfDraughtsFrame extends JFrame implements ActionListener{
    
    public GameOfDraughtsFrame()
    {
	setTitle("Draughts");
        setSize(800,700);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
            	System.exit(0);
            }
        } );
        
        /***
	* Add the menu bar
	***/

	JMenuBar mbar=new JMenuBar();
	setJMenuBar(mbar);

	JMenu GameMenu=new JMenu("Play");

        NewGameItem=new JMenuItem("New Game");
        NewGameItem.addActionListener(this);
        GameMenu.add(NewGameItem);
        GameMenu.addSeparator();

        NextMoveItem=new JMenuItem("Next Move");
        NextMoveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                                              InputEvent.CTRL_MASK));
        NextMoveItem.addActionListener(this);
       	GameMenu.add(NextMoveItem);
        GameMenu.addSeparator();

        ExitItem=new JMenuItem("Exit");
       	ExitItem.addActionListener(this);
        GameMenu.add(ExitItem);
        GameMenu.addSeparator();

        mbar.add(GameMenu);

        JMenu SelectWhitePlayerMenu=new JMenu("Select White Player");

       	BasicWhiteItem=new JMenuItem("Basic");
        BasicWhiteItem.addActionListener(this);
        SelectWhitePlayerMenu.add(BasicWhiteItem);
        SelectWhitePlayerMenu.addSeparator();

        AdvancedWhiteItem=new JMenuItem("Advanced");
        AdvancedWhiteItem.addActionListener(this);
        SelectWhitePlayerMenu.add(AdvancedWhiteItem);
        SelectWhitePlayerMenu.addSeparator();

        mbar.add(SelectWhitePlayerMenu);

        JMenu SelectBlackPlayerMenu=new JMenu("Select Black Player");

        BasicBlackItem=new JMenuItem("Basic");
        BasicBlackItem.addActionListener(this);
        SelectBlackPlayerMenu.add(BasicBlackItem);
        SelectBlackPlayerMenu.addSeparator();

        AdvancedBlackItem=new JMenuItem("Advanced");
        AdvancedBlackItem.addActionListener(this);
        SelectBlackPlayerMenu.add(AdvancedBlackItem);
        SelectBlackPlayerMenu.addSeparator();

        mbar.add(SelectBlackPlayerMenu);


	/***
	* Set up the board
	***/

	board=new DraughtBoard();

	/***
	* Add the board drawing area
	* panel
	***/

	Container contentPane=getContentPane();

	boardPanel=new DraughtBoardPanel(board);

	contentPane.add(boardPanel,"Center");

	boardPanel.draw(0,0);
    }


    public void actionPerformed(ActionEvent evt)
    {

        Object source=evt.getSource();
        if (source==NewGameItem)
        {
            /***
            * Reset board for a new game
            ***/

            board.setBoard();
            nextMoveColor=DraughtBoard.WHITE;	// White has first move
            endOfGame=false;
            boardPanel.draw(0,0);

        }
        else if ((source==NextMoveItem)&&(!endOfGame))
        {
            /***
            * Move the next piece
            ***/

            if (nextMoveColor==DraughtBoard.WHITE)
            {
                whitePlayer.movePiece(board);
                nextMoveColor=DraughtBoard.BLACK;
            }
            else
            {
                blackPlayer.movePiece(board);
                nextMoveColor=DraughtBoard.WHITE;
            }

            int ws=board.getWhiteScore(); int bs=board.getBlackScore();
            boardPanel.draw(ws,bs);	// re-draw the board

            if ((board.getWhitePiecesLeft()==0)||(board.getBlackPiecesLeft()==0))
            {
                endOfGame=true;

                System.out.println("End of Game");

                if (ws>bs)
                    JOptionPane.showMessageDialog(this,"White wins! "," ",JOptionPane.PLAIN_MESSAGE);
                else if (ws<bs)
                    JOptionPane.showMessageDialog(this,"Black wins! "," ",JOptionPane.PLAIN_MESSAGE);
                else if (ws==bs)
                    JOptionPane.showMessageDialog(this,"Drawn Game! "," ",JOptionPane.PLAIN_MESSAGE);

            }
        }
        else if (source==ExitItem)
        {
            /***
            * Quit the game
            ***/

            System.exit(0);
        }
        else if (source==BasicWhiteItem)
        {
            System.out.println("Not implemented");
            /* Plug in basic white player here */
        }
        else if (source==AdvancedWhiteItem)
        {
            System.out.println("Not implemented");
            /* Plug in advanced white player here */
        }
        else if (source==BasicBlackItem)
        {
            System.out.println("Not implemented");
            /* Plug in basic black player here */
        }
        else if (source==AdvancedBlackItem)
        {
            System.out.println("Not implemented");
            /* Plug in advanced black player here */
        }
    }

    private JMenuItem NewGameItem,NextMoveItem,ExitItem;

    private JMenuItem BasicWhiteItem,BasicBlackItem,AdvancedWhiteItem,AdvancedBlackItem;

    private DraughtBoard board;

    private DraughtBoardPanel boardPanel;

    private Player whitePlayer=new Player();

    private Player blackPlayer=new Player();

    private char nextMoveColor=DraughtBoard.WHITE;

    private boolean endOfGame=false;

}
