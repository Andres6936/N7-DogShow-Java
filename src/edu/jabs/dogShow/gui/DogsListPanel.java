package edu.jabs.dogShow.gui;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import edu.jabs.dogShow.domain.*;

/**
 * Panel to interact with the dogs list.
 */
public class DogsListPanel extends JPanel implements ListSelectionListener
{
    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Reference to the principal Class of the GUI
     */
    private DogShowGUI principal;

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------

    /**
     * Shown list
     */
    private JList dogsList;

    /**
     * Displacement component to contain the graphical list
     */
    private JScrollPane scroll;

    // -----------------------------------------------------------------
    // Builders
    // -----------------------------------------------------------------

    /**
     * Builds the panel and initializes its components
     * @param principalWindow reference to the main class of the interface - principalWindow != null
     */
    public DogsListPanel( DogShowGUI principalWindow )
    {
        principal = principalWindow;

        setLayout( new BorderLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 4, 3, 3, 3 ), new TitledBorder( "Dogs of the Show" ) ) );

        dogsList = new JList( );
        dogsList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        dogsList.addListSelectionListener( this );

        scroll = new JScrollPane( dogsList );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setBorder( new CompoundBorder( new EmptyBorder( 3, 3, 3, 3 ), new LineBorder( Color.BLACK, 1 ) ) );

        add( scroll, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the dogs list
     * @param newList list with the dogs that must be shown
     */
    public void refreshList( ArrayList newList )
    {
        dogsList.setListData( newList.toArray( ) );
        dogsList.setSelectedIndex( 0 );
    }

    /**
     * Selects an element in the list
     * @param selected position of the element that must be selected
     */
    public void select( int selected )
    {
        dogsList.setSelectedIndex( selected );
        dogsList.ensureIndexIsVisible( selected );
    }

    /**
     *Changes the actual dog's info, according to the new selected dog
     * @param e is the element changing item
     */
    public void valueChanged( ListSelectionEvent e )
    {
        if( dogsList.getSelectedValue( ) != null )
        {
            Dog d = ( Dog )dogsList.getSelectedValue( );
            principal.watchData( d );
        }
    }
}