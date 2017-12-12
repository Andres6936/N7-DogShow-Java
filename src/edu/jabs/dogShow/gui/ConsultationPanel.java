package edu.jabs.dogShow.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel in which the buttons that consult over the dog show are located
 *
 */
public class ConsultationPanel extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String WINNER = "Winner";

    private static final String LESS_POINTS = "Less Points";

    private static final String MAJOR_AGE = "Major age";

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Reference to the principal GUI class
     */
    private DogShowGUI principal;

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------

    /**
     * Button to show the winner's name
     */
    private JButton btnWinner;

    /**
     * Button to show the dog with less points
     */
    private JButton btnLessPoints;

    /**
     * Button to show the oldest dog
     */
    private JButton btnMajorAge;

    // -----------------------------------------------------------------
    // Builders
    // -----------------------------------------------------------------
    /**
     * Builds the panel and initializes its components
     * @param princWindow referene to the principal GUI class - princWindow != null
     */
    public ConsultationPanel( DogShowGUI princWindow )
    {
        principal = princWindow;
        setLayout( new GridLayout( 4, 1, 10, 10 ) );
        setBorder( new CompoundBorder( new EmptyBorder( 4, 3, 3, 3 ), new TitledBorder( "Consultas Exposición" ) ) );

        // Winner
        btnWinner = new JButton( "Winner" );
        btnWinner.addActionListener( this );
        btnWinner.setActionCommand( WINNER );
        add( btnWinner );

        // Less Points
        btnLessPoints = new JButton( "Minor Score" );
        btnLessPoints.addActionListener( this );
        btnLessPoints.setActionCommand( LESS_POINTS );
        add( btnLessPoints );

        // Major Age
        btnMajorAge = new JButton( "Oldest Dog" );
        btnMajorAge.addActionListener( this );
        btnMajorAge.setActionCommand( MAJOR_AGE );
        add( btnMajorAge );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Executes an action according to the pressed button.
     * @param event event associated to the click
     */
    public void actionPerformed( ActionEvent event )
    {
        String comando = event.getActionCommand( );

        if( WINNER.equals( comando ) )
        {
            principal.searchWinner( );
        }
        else if( LESS_POINTS.equals( comando ) )
        {
            principal.searchMinorScore( );
        }
        else if( MAJOR_AGE.equals( comando ) )
        {
            principal.searchOldestDog( );
        }
    }
}