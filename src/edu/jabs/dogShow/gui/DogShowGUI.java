package edu.jabs.dogShow.gui;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import edu.jabs.dogShow.domain.*;

/**
 * Principal class of the Graphical User Interface
 */
public class DogShowGUI extends JFrame
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * File's path with the dogs information
     */
    public static final String DOGS_FILE = "./data/dogs.txt";

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Reference to the Dogs Show
     */
    private DogShow show;

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------

    /**
     * Panel where the list of dogs is shown
     */
    private DogsListPanel listPanel;

    /**
     * Panel where the dogs data is shown
     */
    private DogDataPanel dataPanel;

    /**
     * Panel where the data needed to add a dog is input
     */
    private AddDogPanel addPanel;

    /**
     * Panel where the Extension Methods Buttons are found
     */
    private ExtensionPanel extensionPanel;

    /**
     * Panel where the Ordering and Search buttons are found
     */
    private OrderSearchPanel orderAndSearchPanel;

    /**
     * Consultaion Panel
     */
    private ConsultationPanel consultationPanel;

    // -----------------------------------------------------------------
    // Builders
    // -----------------------------------------------------------------

    /**
     * Builds the interface and initializes all of its components
     * @param dogsFile name of the file that contains the dogs data
     */
    public DogShowGUI( String dogsFile )
    {
        show = new DogShow( );
        chargeDogs( dogsFile );

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "Dogs Show" );
        setSize( new Dimension( 700, 550 ) );
        setResizable( false );

        setLayout( new GridBagLayout( ) );

        JPanel superiorPanel = new JPanel( );
        superiorPanel.setLayout( new GridBagLayout( ) );

        listPanel = new DogsListPanel( this );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 175;
        superiorPanel.add( listPanel, gbc );

        dataPanel = new DogDataPanel( );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 45;
        gbc.ipady = 40;
        superiorPanel.add( dataPanel, gbc );

        orderAndSearchPanel = new OrderSearchPanel( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        gbc.ipady = 100;
        superiorPanel.add( orderAndSearchPanel, gbc );

        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        getContentPane( ).add( superiorPanel, gbc );

        JPanel centralPanel = new JPanel( );
        centralPanel.setLayout( new GridBagLayout( ) );

        addPanel = new AddDogPanel( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 35;
        gbc.ipadx = 20;
        centralPanel.add( addPanel, gbc );

        consultationPanel = new ConsultationPanel( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 85;
        centralPanel.add( consultationPanel, gbc );

        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        getContentPane( ).add( centralPanel, gbc );

        extensionPanel = new ExtensionPanel( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        getContentPane( ).add( extensionPanel, gbc );
        updateList( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the shown list
     */
    private void updateList( )
    {
        listPanel.refreshList( show.getDogs( ) );
    }

    /**
     * Orders the dogs by race, and updates the list
     */
    public void orderByRace( )
    {
        show.orderByRace( );
        dataPanel.cleanData( );
        updateList( );
    }

    /**
     * Orders the dogs by points and updates the list
     */
    public void orderByPoints( )
    {
        show.orderByPoints( );
        dataPanel.cleanData( );
        updateList( );
    }

    /**
     * Orders the dogs by age and updates the list
     */
    public void orderByAge( )
    {
        show.orderByAge( );
        dataPanel.cleanData( );
        updateList( );
    }

    /**
     * Finds a dog using the name and when it's found in the list, it selects it and displays its data.
     */
    public void search( )
    {
        String searchedName = JOptionPane.showInputDialog( this, "Dog's name" );
        if( searchedName != null )
        {
            int position = show.searchForDog( searchedName );

            if( position != -1 )
            {
                updateList( );
                listPanel.select( position );
                Dog d = ( Dog )show.getDogs( ).get( position );
                watchData( d );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "The dog wasn't found" );
            }
        }
    }

    /**
     * Shows the data of a dog in the corresponging panel
     * @param dogg dog whose data is wanted - dogg != null
     */
    public void watchData( Dog dogg )
    {
        dataPanel.showData( dogg );
    }

    /**
     * Add a new dog.
     * @param pName dog's name - pName != null
     * @param pRace dog's race - prace != null
     * @param pImage Path to the dog's image - pImage != null
     * @param pHeight dog's height - pHeight > 0
     * @param pAge dog's age in months - pAge > 0
     */
    public void addDog( String pName, String pRace, String pImage, int pHeight, int pAge )
    {

        boolean added = show.addDog( pName, pRace, pImage, pHeight, pAge );
        if( !added )
            JOptionPane.showMessageDialog( this, "The dog wasn't added because its name " + pName + " is already used by another dog", "Error", JOptionPane.ERROR_MESSAGE );
        else
        {
            updateList( );
            listPanel.select( show.getDogs( ).size( ) - 1 );
        }

    }

    /**
     * Charges the initial show dogs from a file
     * @param file name of the file that contains the dogs data - file!=null
     */
    private void chargeDogs( String file )
    {

        try
        {
            FileInputStream fis = new FileInputStream( new File( file ) );
            Properties props = new Properties( );
            props.load( fis );

            // Charges the dogs
            String data;
            String name;
            String race;
            String image;
            int points;
            int age;
            String aux;
            data = "total.dogs";
            aux = props.getProperty( data );
            int cuantity = Integer.parseInt( aux );

            for( int cont = 1; cont <= cuantity; cont++ )
            {
                // Charges a dog
                data = "dog" + cont + ".name";
                name = props.getProperty( data );

                data = "dog" + cont + ".race";
                race = props.getProperty( data );

                data = "dog" + cont + ".race";
                race = props.getProperty( data );

                data = "dog" + cont + ".image";
                image = props.getProperty( data );

                data = "dog" + cont + ".points";
                aux = props.getProperty( data );
                points = Integer.parseInt( aux );

                data = "dog" + cont + ".age";
                aux = props.getProperty( data );
                age = Integer.parseInt( aux );

                // The dog is charged only if the data is correct
                if( name != null && race != null && image != null && points >= 0 && age > 0 )
                    show.addDog( name, race, image, points, age );
                fis.close( );
            }
        }
        catch( FileNotFoundException e )
        {
            e.printStackTrace( );
        }
        catch( IOException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Looks for the winner dog and show it complete data.
     *
     */
    public void searchWinner( )
    {
        int position = show.searchDogMajorScore( );

        updateList( );
        if( position != -1 )
        {
            listPanel.select( position );
            Dog d = ( Dog )show.getDogs( ).get( position );
            watchData( d );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "There are not dogs registered in the show" );
        }
    }

    /**
     * Looks for the dog with best score and show its data
     *
     */
    public void searchMinorScore( )
    {
        int position = show.searchDogMinorScore( );

        updateList( );
        if( position != -1 )
        {
            listPanel.select( position );
            Dog d = ( Dog )show.getDogs( ).get( position );
            watchData( d );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "There are not dogs registered in the show" );
        }
    }

    /**
     * Looks for the oldest dog and shows its data.
     *
     */
    public void searchOldestDog( )
    {
        int position = show.searchDogHighestAge( );

        updateList( );
        if( position != -1 )
        {
            listPanel.select( position );
            Dog d = ( Dog )show.getDogs( ).get( position );
            watchData( d );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "There are not dogs registered in this show" );
        }
    }

    // -----------------------------------------------------------------
    // Extension Points
    // -----------------------------------------------------------------

    /**
     * Extension Method 1
     */
    public void funcReqOption1( )
    {
        String answer = show.metodo1( );
        JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Extension Method 2
     */
    public void funcReqoption2( )
    {
        String answer = show.metodo2( );
        JOptionPane.showMessageDialog( this, answer, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Executes the application
     * @param args are execution parameters of the application. Mustn't be used.
     */
    public static void main( String[] args )
    {
        DogShowGUI iec = new DogShowGUI( DOGS_FILE );
        iec.setVisible( true );
    }
}