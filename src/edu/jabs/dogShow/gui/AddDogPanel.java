package edu.jabs.dogShow.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel to which the dogs are going to be added
 */
public class AddDogPanel extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ADD = "AddDog";

    private static final String SEARCH = "SearchForImage";

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
     * Field for the path to the dog's image
     */
    private JTextField txtImage;

    /**
     * Field for the dog's name
     */
    private JTextField txtName;

    /**
     * Field for the dog's race
     */
    private JTextField txtRace;

    /**
     * Field for the dog's age
     */
    private JTextField txtAge;

    /**
     * Field for the dog's points
     */
    private JTextField txtPoints;

    /**
     * Label for the dog's image
     */
    private JLabel labelImage;

    /**
     * Label for the dog's name
     */
    private JLabel labelName;

    /**
     * Label for the dog's race
     */
    private JLabel labelRace;

    /**
     * Label for the dogs points
     */
    private JLabel labelPoints;

    /**
     * Label for the dog's age
     */
    private JLabel labelAge;

    /**
     * Button used to add a dog
     */
    private JButton btnAdd;

    /**
     * Button used to examine the disk looking for the dog's image
     */
    private JButton btnExamine;

    // -----------------------------------------------------------------
    // Builders
    // -----------------------------------------------------------------

    /**
     * Builds the panel and initializes its components
     * @param iec reference to the principal class in the GUI
     */
    public AddDogPanel( DogShowGUI iec )
    {
        principal = iec;

        setLayout( new GridBagLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 4, 3, 3, 3 ), new TitledBorder( "Add a Dog" ) ) );

        JPanel dataPanel = new JPanel( new GridBagLayout( ) );

        // Name
        labelName = new JLabel( "Name: " );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        dataPanel.add( labelName, gbc );
        txtName = new JTextField( "" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;

        dataPanel.add( txtName, gbc );

        // Race
        labelRace = new JLabel( "Race: " );
        labelRace.setBorder( new EmptyBorder( 0, 5, 0, 0 ) );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.EAST;

        dataPanel.add( labelRace, gbc );
        txtRace = new JTextField( "" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        dataPanel.add( txtRace, gbc );

        // Age
        labelAge = new JLabel( "Age: " );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 1;
        dataPanel.add( labelAge, gbc );
        txtAge = new JTextField( "" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        dataPanel.add( txtAge, gbc );

        // Points
        labelPoints = new JLabel( "Points: " );
        labelPoints.setBorder( new EmptyBorder( 0, 5, 0, 0 ) );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 1;
        dataPanel.add( labelPoints, gbc );
        txtPoints = new JTextField( "" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.ipadx = 160;
        dataPanel.add( txtPoints, gbc );

        // Image
        labelImage = new JLabel( "Image: " );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 2;
        dataPanel.add( labelImage, gbc );
        txtImage = new JTextField( "" );
        btnExamine = new JButton( "Examine" );
        btnExamine.setActionCommand( SEARCH );
        btnExamine.addActionListener( this );

        // Add Button
        JPanel panelBoton = new JPanel( );
        btnAdd = new JButton( "Add a Dog" );
        btnAdd.setActionCommand( ADD );
        btnAdd.addActionListener( this );
        panelBoton.add( btnAdd );

        JPanel imagePanel = new JPanel( new GridLayout( ) );

        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        imagePanel.add( txtImage );
        gbc = new GridBagConstraints( );
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        imagePanel.add( btnExamine );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 2;
        dataPanel.add( imagePanel, gbc );

        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add( dataPanel, gbc );

        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add( panelBoton, gbc );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Method executed when you click a button
     * @param event event of the click said before
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );

        if( ADD.equals( command ) )
        {
            try
            {
                String name = txtName.getText( );
                String race = txtRace.getText( );
                String image = txtImage.getText( );
                int points = Integer.parseInt( txtPoints.getText( ) );
                int age = Integer.parseInt( txtAge.getText( ) );

                if( name == null || name.equals( "" ) )
                {
                    JOptionPane.showMessageDialog( this, "You should input the name of the dog", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else if( race == null || race.equals( "" ) )
                {
                    JOptionPane.showMessageDialog( this, "You should input hte race of the dog", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else if( image == null || image.equals( "" ) )
                {
                    JOptionPane.showMessageDialog( this, "You should input the image of the dog", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    principal.addDog( name, race, image, points, age );

                    txtName.setText( "" );
                    txtRace.setText( "" );
                    txtImage.setText( "" );
                    txtPoints.setText( "" );
                    txtAge.setText( "" );
                }
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "The age and points fields must be numerical", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( SEARCH.equals( command ) )
        {
            JFileChooser fc = new JFileChooser( "./data" );
            fc.setDialogTitle( "Search for dog's image" );
            fc.setMultiSelectionEnabled( false );

            int resultado = fc.showOpenDialog( this );
            if( resultado == JFileChooser.APPROVE_OPTION )
            {
                String imagen = fc.getSelectedFile( ).getAbsolutePath( );
                txtImage.setText( imagen );
            }
        }

    }

}