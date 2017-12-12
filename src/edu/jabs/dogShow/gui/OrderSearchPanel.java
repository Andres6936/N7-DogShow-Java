package edu.jabs.dogShow.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * This is the panel where the buttons to make the orders by various criteria and searches.
 */
public class OrderSearchPanel extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String SEARCH = "Search";

    private static final String ORDER_BY_POINTS = "OrderPoints";

    private static final String ORDERN_BY_AGE = "OrderAge";

    private static final String ORDER_BY_RACE = "Orderrace";

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Reference to the principal interface class
     */
    private DogShowGUI principal;

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------
    /**
     * Button to order by race
     */
    private JButton btnOrderRace;

    /**
     * Button to order by points
     */
    private JButton btnOrderPoints;

    /**
     * Button to order by age
     */
    private JButton btnOrderAge;

    /**
     * Button to establish a search
     */
    private JButton btnSearch;

    // -----------------------------------------------------------------
    // Builders
    // -----------------------------------------------------------------

    /**
     * Builds the panel and initializes its components
     * @param principalWindow reference to the principal class in the interface - principalWindow != null
     */
    public OrderSearchPanel( DogShowGUI principalWindow )
    {
        principal = principalWindow;

        setPreferredSize( new Dimension( 200, 0 ) );
        setBorder( new CompoundBorder( new EmptyBorder( 4, 3, 3, 3 ), new TitledBorder( "Search and Ordering" ) ) );
        setLayout( new GridBagLayout( ) );

        btnOrderRace = new JButton( "Order by Race" );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets( 0, 0, 10, 0 );
        gbc.fill = GridBagConstraints.BOTH;
        btnOrderRace.setActionCommand( ORDER_BY_RACE );
        btnOrderRace.addActionListener( this );
        add( btnOrderRace, gbc );

        btnOrderPoints = new JButton( "Order by Points" );
        btnOrderPoints.setActionCommand( ORDER_BY_POINTS );
        btnOrderPoints.addActionListener( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets( 0, 0, 10, 0 );
        gbc.fill = GridBagConstraints.BOTH;
        add( btnOrderPoints, gbc );

        btnOrderAge = new JButton( "Order by Age" );
        btnOrderAge.setActionCommand( ORDERN_BY_AGE );
        btnOrderAge.addActionListener( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets( 0, 0, 10, 0 );
        gbc.fill = GridBagConstraints.BOTH;
        add( btnOrderAge, gbc );

        btnSearch = new JButton( "Search Dog" );
        btnSearch.setActionCommand( SEARCH );
        btnSearch.addActionListener( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets( 0, 0, 10, 0 );
        gbc.fill = GridBagConstraints.BOTH;
        add( btnSearch, gbc );
    }

    /**
     * Executes an action taking into account the Button that was pressed
     * @param event is the button's click event
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );

        if( ORDER_BY_RACE.equals( command ) )
        {
            principal.orderByRace( );
        }
        else if( ORDER_BY_POINTS.equals( command ) )
        {
            principal.orderByPoints( );
        }
        else if( ORDERN_BY_AGE.equals( command ) )
        {
            principal.orderByAge( );
        }
        else if( SEARCH.equals( command ) )
        {
            principal.search( );
        }
    }
}