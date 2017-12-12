package edu.jabs.dogShow.gui;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;

import edu.jabs.dogShow.domain.*;

/**
 * Panel where the dog's data is shown
 */
public class DogDataPanel extends JPanel
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * This constant indicates the height that you must have an individual image
     */
    private static final int HEIGHT = 200;

    /**
     * This constant indicates the width you must have an individual image
     */
    private static final int WIDTH = 200;
    // -----------------------------------------------------------------
    // GUI FIELDS
    // -----------------------------------------------------------------

    /**
     * Es la etiqueta donde se muestra la imagen del perro
     */
    private JLabel lblImage;

    /**
     * Es la etiqueta para el nombre del perro
     */
    private JLabel lblName;

    /**
     * Es la etiqueta para la raza del perro
     */
    private JLabel lblRace;

    /**
     * Es la etiqueta donde se muestra los puntos del perro
     */
    private JLabel lblPoints;

    /**
     * Es la etiqueeta para la edad del perro
     */
    private JLabel lblAge;

    /**
     * Es el campo para el nombre del perro
     */
    private JTextField txtName;

    /**
     * Es el campo para la raza del perro
     */
    private JTextField txtRace;

    /**
     * Es el campo para la edad del perro
     */
    private JTextField txtAge;

    /**
     * Es el campo para la altura del perro
     */
    private JTextField txtHeight;

    // -----------------------------------------------------------------
    // Builders
    // -----------------------------------------------------------------

    /**
     * Buids the panel and initializes its components
     */
    public DogDataPanel( )
    {
        setLayout( new BorderLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 4, 3, 3, 3 ), new TitledBorder( "Dog Data" ) ) );

        JPanel panelImagen = new JPanel( );
        lblImage = new JLabel( );
        lblImage.setBorder( new LineBorder( Color.BLACK, 1 ) );
        lblImage.setMinimumSize( new Dimension( 230, 153 ) );
        lblImage.setMaximumSize( new Dimension( 230, 153 ) );

        panelImagen.add( lblImage );
        add( panelImagen, BorderLayout.CENTER );

        JPanel panelDatosTexto = new JPanel( new GridLayout( 2, 4 ) );

        lblName = new JLabel( "Name: " );
        txtName = new JTextField( );
        txtName.setEnabled( false );
        panelDatosTexto.add( lblName );
        panelDatosTexto.add( txtName );

        lblRace = new JLabel( "Race: " );
        lblRace.setBorder( new EmptyBorder( 0, 5, 0, 0 ) );
        txtRace = new JTextField( );
        txtRace.setEnabled( false );
        panelDatosTexto.add( lblRace );
        panelDatosTexto.add( txtRace );

        lblAge = new JLabel( "Age: " );
        txtAge = new JTextField( );
        txtAge.setEnabled( false );
        panelDatosTexto.add( lblAge );
        panelDatosTexto.add( txtAge );

        lblPoints = new JLabel( "Points: " );
        lblPoints.setBorder( new EmptyBorder( 0, 5, 0, 0 ) );
        txtHeight = new JTextField( );
        txtHeight.setEnabled( false );
        panelDatosTexto.add( lblPoints );
        panelDatosTexto.add( txtHeight );

        add( panelDatosTexto, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Show the dog's data in the fields
     * @param theDog dog whose data wants to be shown - theDog!= null
     */
    public void showData( Dog theDog )
    {
        try
        {
            String image = theDog.getImage( );
            BufferedImage bImage = ImageIO.read( new File( image ) );
            Image theImage = bImage.getScaledInstance( ( int ) ( WIDTH * 0.85 ), ( int ) ( HEIGHT * 0.85 ), Image.SCALE_AREA_AVERAGING );
            lblImage.setIcon( new ImageIcon( theImage ) );

            txtName.setText( theDog.getName( ) );
            txtRace.setText( theDog.getRace( ) );
            txtHeight.setText( "" + theDog.getPoints( ) );
            txtAge.setText( "" + theDog.getAge( ) + " months" );

            validate( );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog(this, "Error charging the dog's image "+theDog.getName(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Cleans all the fields.
     */
    public void cleanData( )
    {
        lblImage.setIcon( null );
        txtName.setText( "" );
        txtRace.setText( "" );
        txtHeight.setText( "" );
        txtAge.setText( "" );
    }
}