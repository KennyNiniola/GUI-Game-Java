import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel.*;
import java.awt.event.*;
import java.awt.Color.*;;
import java.util.*;
/**
 * I have been tasked to create a game called chasing-bombs-fkan2. 
 * The aim of the game is for a player to click on a square and hopefully avoid a bomb that is hidden under the square. 
 *
 * Faizah Niniola
 * 30/04/2020
 */public class GUI extends JFrame 
{
    private JFrame container; // The Frame contaner
    int ROW = 1;
    int COL = 3;
    private JPanel threepanels = new JPanel(new GridLayout(ROW,COL)); // The three panels
    private JPanel secpanel = new JPanel();// 2nd panel
    private JPanel thirpanel = new JPanel();//3rd panel
    int ROWS = 2;
    int COLS = 5;
    private JPanel grid = new JPanel(new GridLayout(ROWS,COLS)); // The game grid
    private JPanel [] bombpane = new JPanel[10];// The game grid margin
    
    int bomb = 0;
    int point = 0;
    int clicked = 0;
    int moves = 0;
    int move = 0;
    private boolean clicks[] = new boolean[10];//number of clicks clicked by the user
    private boolean isRUNNING = false;

    private JButton PTGButton = new JButton("Play the Game"); // Play buttons
    private JButton ExitButton = new JButton("Exit");
    private JButton EasyButton = new JButton("Easy");
    private JButton IntermediateButton = new JButton("Intermediate");
    private JButton DifficultButton = new JButton("Difficult");
    public JLabel points = new JLabel("");

    private Color colour1 = Color.MAGENTA; // The panel colours
    private Color colour2 = Color.WHITE;

    /**
     * Constructor for objects of class Gui
     */
    public GUI()
    {
        // JFrame in which all other containers and 
        // components should be populated.
        super("Chasing Bombs Fkan2");
        setSize(1000,1000);
        setVisible(true);
        add(threepanels);
        makeFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
      * The creation of frame
      */
    public void makeFrame()
    {
        secpanel.add(PTGButton);// Adding play buttons
        secpanel.add(ExitButton);
        secpanel.add(points);
        
        thirpanel.add(EasyButton);
        thirpanel.add(IntermediateButton);
        thirpanel.add(DifficultButton);
        threepanels.add(grid);
        threepanels.add(secpanel);
        threepanels.add(thirpanel);
        
        grid.setBackground(colour1);// Adding background colour
        secpanel.setBackground(colour2);
        thirpanel.setBackground(colour1);
        points.setForeground(Color.BLACK);// Adding text colour
                      
        threepanels.setVisible(true); // Making the panels visible
        bombmargin();
        
        /**
          * This methods purpose is to change the selected panels colour if bomb is selected.
          */
        PTGButton.addActionListener(new ActionListener()//the buttons that play the game 
            {
                public void actionPerformed(ActionEvent e)
                {
                    if(isRUNNING)
                    {
                        moves = move;
                    }
                    isRUNNING = true; // game running
                    point = 0;// default point count
                    for(int x = 0; x < 10; x++)
                    {
                        bombpane[x].setBackground(colour1);//changing colour of the selected panel
                        clicks[x] = false;// not clicking bomb
                    }
                }
            });

        /**
          * This method allows the user to change the game into the easy setting.
          */   
        EasyButton.addMouseListener(new MouseAdapter()//make the game easy
            {
                public void mousePressed(MouseEvent e)
                {
                    if(!isRUNNING)
                    {
                        moves = 5;
                    }
                    else
                    {
                        move = 5;
                    }
                }
            });

         /**
          * This method allows the user to change the game into the intermediate setting.
          */   
        IntermediateButton.addMouseListener(new MouseAdapter() // make the game intermediate
            {
                public void mousePressed(MouseEvent e)
                {
                    if(!isRUNNING)
                    {
                        moves = 7;
                    }
                    else
                    {
                        move = 7;
                    }                
                }
            });
            
         /**
          * This method allows the user to change the game into the difficult setting.
          */
        DifficultButton.addMouseListener(new MouseAdapter() //make the game difficult
            {
                public void mousePressed(MouseEvent e)
                {
                    if(!isRUNNING)
                    {
                        moves = 9;
                    }
                    else
                    {
                        move = 9;
                    }
                }
            });

           /**
          * This method allows the user to exit the game.
          */    
        ExitButton.addActionListener(new ActionListener()// exit game
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });
    }

    /**
     *Game stops and winning message appears.
     */
    public void Win()
    {
        points.setText("You've won! You're score is" + point + " points!");      
        isRUNNING = false;        
    }

    /**
     *Game stops and losing message appears.
     */
    public void Lose()
    {
        points.setText("You've lost! You're score " + point + " points!");      
        isRUNNING = false;
    }
    
    /**
     * This method creates the games margin for the game panels, 
     * bomb and allows the user to win or lose the game if bomb is hit or !hit.
     */
    public void bombmargin()
    {
        for(int x = 0; x < 10; x++) {
            bombpane[x] = new JPanel();
            grid.add(bombpane[x]);// Add bomb to grid board
            bombpane[x].setBackground(colour1);//bomb panel background colour
            bombpane[x].setBorder(BorderFactory.createLineBorder(Color.WHITE));//game panel margin

            bombpane[x].addMouseListener(new MouseAdapter()
                {
                    public void mousePressed(MouseEvent e)
                    {
                        if (isRUNNING)
                        {
                            JPanel clicked = (JPanel)e.getSource();
                            int index = Arrays.asList(bombpane).indexOf(clicked); 

                            if (index == bomb)
                            {
                                Lose();
                            }
                            else if (!clicks[index])
                            {
                                clicked.setBackground(colour2);  // Change colour  
                                point++;// Increase point for each panel selected
                                clicks[index] = true;
                                points.setText("Points: " + point);
                                if(moves == point)
                                {
                                    Win();
                                }                            
                            }
                        }

                    }
                });
                
                    Random random = new Random();// Random int generator for bomb
                    bomb = random.nextInt(10);
                    
                    points.setText("Points: "+ point );  

        }
    }
}
