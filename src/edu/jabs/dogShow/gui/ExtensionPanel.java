package edu.jabs.dogShow.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel that containst the Extension Buttons
 */
public class ExtensionPanel extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Command for Button 1
     */
    private final String OPTION_1 = "option 1";

    /**
     * Command for Button 2
     */
    private final String OPTION_2 = "option 2";

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Reference to the application interface
     */
    private DogShowGUI principal;

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------

    /**
     * Button 1
     */
    private JButton btnOption1;

    /**
     * Button 2
     */
    private JButton btnOption2;

    // -----------------------------------------------------------------
    // Builders
    // -----------------------------------------------------------------

    /**
     * Builds the panel with a reference to the principal window of the application.
     * @param iec reference to the principal window - ie!=null
     */
    public ExtensionPanel( DogShowGUI iec )
    {
        principal = iec;
        initialize( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Initializes the components of the panel. <br>
     * <b>post: </b> The components were initialized and located in the panel.
     */
    private void initialize( )
    {
        setBorder( new TitledBorder( "Extension Points" ) );

        setLayout( new FlowLayout( ) );
        btnOption1 = new JButton( "Opción 1" );
        btnOption1.setActionCommand( OPTION_1 );
        btnOption1.addActionListener( this );

        btnOption2 = new JButton( "Opción 2" );
        btnOption2.setActionCommand( OPTION_2 );
        btnOption2.addActionListener( this );

        add( btnOption1 );
        add( btnOption2 );
    }

    /**
     * Method called when a button is pressed.
     * @param event is the click event of the button
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );
        if( OPTION_1.equals( command ) )
        {
            principal.funcReqOption1( );
        }
        else if( OPTION_2.equals( command ) )
        {
            principal.funcReqoption2( );
        }
    }

}