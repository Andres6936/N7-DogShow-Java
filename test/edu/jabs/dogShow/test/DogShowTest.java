package edu.jabs.dogShow.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import junit.framework.TestCase;

import edu.jabs.dogShow.domain.Dog;
import edu.jabs.dogShow.domain.DogShow;

/**
 * It's the class to prove the methods of the class DogShow
 */
public class DogShowTest extends TestCase
{
    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Field that will be suited to execute the proves
     */
    private DogShow show;

    /**
     * Number of dogs in the show
     */
    private int cuantity;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Creates a show using the file dogs1.txt
     */
    private void setupStage1( )
    {
        show = new DogShow( );
        chargeDogs( "./test/data/dogs1.txt" );

    }

    /**
     * Creates a show using the file perros2.txt
     */
    private void setupStage2( )
    {
        show = new DogShow( );
        chargeDogs( "./test/data/dogs2.txt" );

    }

    /**
     * Creates an empty show
     */
    private void setupStage3( )
    {
        show = new DogShow( );
        cuantity = 0;
    }

    /**
     * Verifies the method addDog by adding in a correct way a dog. <br>
     * <b> Methods to prove: </b> <br>
     * addDog, searchForDog, getDogs. <br>
     * <b> Objective: </b> prove that the method addDog() is able to register a dog in the exhibition. <br>
     * <b> Wanted Results: </b> <br>
     * 1. When searching (by name) previously added a dog must obtain a different position of -1 (must find) and data from the dog in that position should
     * belong to the dog with the corresponding name. <br>
     */
    public void testAddDog1( )
    {
        // Configures data for proving
        setupStage3( );

        String name;
        String race;
        String image;
        int age;
        int points;
        boolean added;
        // Adds a dog and then verifies if it was added properly
        for( int count = 1; count <= cuantity; count++ )
        {
            name = "name" + count;
            race = "race" + count;
            image = "image" + count;
            age = count;
            points = count;

            added = show.addDog( name, race, image, age, points );
            int pos = show.searchForDog( name );
            Dog perro = ( Dog )show.getDogs( ).get( pos );

            assertTrue( "The dog wasn't added properly", added );
            assertEquals( "The dog wasn't added properly", count - 1, pos );
            assertEquals( "The dog wasn't added properly", name, perro.getName( ) );
            assertEquals( "The dog wasn't added properly", race, perro.getRace( ) );
            assertEquals( "The dog wasn't added properly", image, perro.getImage( ) );
            assertEquals( "The dog wasn't added properly", age, perro.getAge( ) );
            assertEquals( "The dog wasn't added properly", points, perro.getPoints( ) );
        }
    }

    /**
     * Verifies the method addDog, adding a name repeated dog. <br>
     * <b> Methods to prove: </b> <br>
     * addDog, searchForDog, getDogs. <br>
     * <b> Objective: </b> prove that the method addDog() does not add a dog in the exhibition when his name already belongs to another dog registered.<br>
     * <b> Wanted Results: </b> <br>
     * 1. Adding a dog name repeated as many dogs must be kept the same and the existing information dogs should not be disturbed.
     */
    public void testAgregarPerro2( )
    {
        // Configura los datos de prueba
        setupStage1( );

        ArrayList dogs = show.getDogs( );
        Dog d = ( Dog )dogs.get( 0 );
        String nameDog = d.getName( );

        int i = Integer.parseInt( nameDog );
        boolean added = show.addDog( nameDog, nameDog, nameDog, i, i );
        assertFalse( "The dog shouldn't have been added", added );

        String name;
        String race;
        String image;
        int age;
        int points;
        //Looks for the dog and verifies that it's data is correct
        for( int count = 0; count < cuantity; count++ )
        {
            name = "" + ( count + 1 );
            race = "" + ( count + 1 );
            image = "" + ( count + 1 );
            age = count + 1;
            points = count + 1;

            Dog perro = ( Dog )show.getDogs( ).get( count );

            assertEquals( "The dog wasn't added properly", name, perro.getName( ) );
            assertEquals( "The dog wasn't added properly", race, perro.getRace( ) );
            assertEquals( "The dog wasn't added properly", image, perro.getImage( ) );
            assertEquals( "The dog wasn't added properly", age, perro.getAge( ) );
            assertEquals( "The dog wasn't added properly", points, perro.getPoints( ) );
        }

    }

    /**
     * Verifies the method searchForDog() looking for a dog which must be found. <br>
     * <b> Methods to prove: </b> <br>
     * searchForDog. <br>
     * <b> Objective: </b>prove that the method searchForDog() is able to find dogs registered in the exhibition. <br>
     * <b> Wanted Results: </b> <br>
     * 1. When looking for a dog must be obtained previously added a different position -1.<br>
     * 2. When looking for a dog's position that there should be -1 returned.
     *
     */
    public void testSearchForDog( )
    {
        // Configures the Proving data
        setupStage2( );

        show.orderByPoints( );
        ArrayList dogs = show.getDogs( );
        Dog p0 = ( Dog )dogs.get( 0 );
        String dogName = p0.getName( );
        show.orderByRace( );

        int position = show.searchForDog( dogName );
        assertTrue( "The dog was not found", position != -1 );

        dogs = show.getDogs( );
        Dog pn = ( Dog )dogs.get( position );
        assertEquals( "The dog was not found", pn.getName( ), dogName );

        position = show.searchForDog( "The dog doesn't exist" );
        assertEquals( "The dog was not found", -1, position );
    }

    /**
     * Verifies the method BinarySearchByName() looking for a dog that must ve found. <br>
     * <b> Methods to prover: </b> <br>
     * binarySearchByName. <br>
     * <b> Objective: </b> Prove that the method is able on finding a dog properly. <br>
     * <b> Wanted Results: </b> <br>
     * 1. When looking for a dog must be obtained previously added a different position -1.<br>
     * 2. When looking for a dog's position that there should be -1 returned.
     *
     */
    public void testBinarySearchByName( )
    {
        // Configures the proving data
        setupStage2( );

        show.orderByName( );
        ArrayList dogs = show.getDogs( );

        // looks for the first dog
        Dog dog = ( Dog )dogs.get( 0 );
        String dogName = dog.getName( );

        int position = show.binarySearchByName( dogName );
        assertTrue( "The dog was not found", position != -1 );

        Dog newDog = ( Dog )dogs.get( position );
        assertEquals( "The dog was not found", newDog.getName( ), dogName );

        // Looks for the dog in the middle
        dog = ( Dog )dogs.get( cuantity / 2 );
        dogName = dog.getName( );

        position = show.binarySearchByName( dogName );
        assertTrue( "The dog was not found", position != -1 );

        newDog = ( Dog )dogs.get( position );
        assertEquals( "The dog was not found", newDog.getName( ), dogName );

        // looks for the dog at the end
        dog = ( Dog )dogs.get( cuantity - 1 );
        dogName = dog.getName( );

        position = show.binarySearchByName( dogName );
        assertTrue( "The dog was not found", position != -1 );

        newDog = ( Dog )dogs.get( position );
        assertEquals( "The dog was not found", newDog.getName( ), dogName );

        // Looks for a dog that doesn't exist
        position = show.searchForDog( "The dog doesn't exist" );
        assertEquals( "The dog was not found", -1, position );
    }

    /**
     * Verifies the method orderByPoints() <br>
     * <b> Methods to prove: </b> <br>
     * orderByPoints. <br>
     * <b> Objective: </b> Prove that the method ordenarByPoints() orders the correct exposure (in ascending order of points). <br>
     * <b> Wanted Results: </b> <br>
     * 1. In ordering the exposure points are not consecutive two dogs for which the dog has more points left the dog on the right.
     */
    public void testOrderByPoints( )
    {
        // Configure the proving data
        setupStage2( );

        show.orderByPoints( );
        ArrayList dogs = show.getDogs( );
        for( int i = 1; i < dogs.size( ); i++ )
        {
            Dog p0 = ( Dog )dogs.get( i - 1 );
            Dog p1 = ( Dog )dogs.get( i );

            assertTrue( "It wasn't ordered correctly by points", p0.getPoints( ) <= p1.getPoints( ) );
        }
    }

    /**
     * Verifies the method orderByAge() <br>
     * <b> Methods to prove: </b> <br>
     * orderByAge. <br>
     * <b> Objective: </b> Prove that the method ordenarByAge() orders the correct exposure (in ascending order of Age). <br>
     * <b> Wanted Results: </b> <br>
     * 1. In ordering the exposure Age are not consecutive two dogs for which the dog has more Age left the dog on the right.
     */
    public void testorderByAge( )
    {
        // Configura los datos de prueba
        setupStage2( );

        show.orderByAge( );
        ArrayList dogs = show.getDogs( );
        for( int i = 1; i < dogs.size( ); i++ )
        {
            Dog p0 = ( Dog )dogs.get( i - 1 );
            Dog p1 = ( Dog )dogs.get( i );

            assertTrue( "It wasn't ordered correctly by age", p0.getAge( ) <= p1.getAge( ) );
        }
    }

    /**
     * Verifies the method orderByRace() <br>
     * <b> Methods to prove: </b> <br>
     * orderByRace. <br>
     * <b> Objective: </b> Prove that the method ordenarByRace() orders the correct exposure (in ascending order of Race). <br>
     * <b> Wanted Results: </b> <br>
     * 1. In ordering the exposure Race are not consecutive two dogs for which the dog has more Race left the dog on the right.
     */
    public void testOrderByRace( )
    {
        // Configures the proving data
        setupStage2( );

        show.orderByRace( );
        ArrayList dogs = show.getDogs( );
        for( int i = 1; i < dogs.size( ); i++ )
        {
            Dog p0 = ( Dog )dogs.get( i - 1 );
            Dog p1 = ( Dog )dogs.get( i );

            assertTrue( "It wasn't ordered correctly by race", p0.getRace( ).compareTo( p1.getRace( ) ) <= 0 );
        }
    }

    /**
     * Verifies the method orderByName() <br>
     * <b> Methods to prove: </b> <br>
     * orderByName. <br>
     * <b> Objective: </b> Prove that the method ordenarByName() orders the correct exposure (in ascending order of Name). <br>
     * <b> Wanted Results: </b> <br>
     * 1. In ordering the exposure Name are not consecutive two dogs for which the dog has more Name left the dog on the right.
     */
    public void testOrderByName( )
    {
        // Configures the proving data
        setupStage2( );

        show.orderByName( );
        ArrayList dogs = show.getDogs( );
        for( int i = 1; i < dogs.size( ); i++ )
        {
            Dog p0 = ( Dog )dogs.get( i - 1 );
            Dog p1 = ( Dog )dogs.get( i );

            assertTrue( "It wasn't ordered correctly by name", p0.getName( ).compareTo( p1.getName( ) ) <= 0 );
        }
    }

    /**
     * Verifies the method searchDogHighestAge() looking for a dog that must be found. <br>
     * <b> Methods to prover: </b> <br>
     * searDogHighestAge. <br>
     * <b> Objective: </b> Prove that the method is able on finding a dog properly. <br>
     * <b> Wanted Results: </b> <br>
     * 1. When looking for a dog must be obtained previously added a different position -1.<br>
     * 2. When looking for a dog's position that there should be -1 returned.
     *
     */
    public void testSearchDogHighestAge( )
    {
        // Configures the proving data
        setupStage2( );

        int posMayor = show.searchDogHighestAge( );
        ArrayList dogs = show.getDogs( );
        Dog major = ( Dog )dogs.get( posMayor );
        show.orderByAge( );
        dogs = show.getDogs( );
        Dog majorDog = ( Dog )dogs.get( dogs.size( ) - 1 );

        assertEquals( "The dog isn't the correct one", majorDog, major );

        setupStage3( );
        posMayor = show.searchDogHighestAge( );
        assertEquals( "the oldest dog should not exist", -1, posMayor );
    }

    /**
     * Verifies the method searchDogMajorScore() looking for a dog that must be found. <br>
     * <b> Methods to prover: </b> <br>
     * searchDogmajorScore. <br>
     * <b> Objective: </b> Prove that the method is able on finding a dog properly. <br>
     * <b> Wanted Results: </b> <br>
     * 1. When looking for a dog must be obtained previously added a different position -1.<br>
     * 2. When looking for a dog's position that there should be -1 returned.
     *
     */
    public void testSearchDogMajorScore( )
    {
        // Configures the proving data
        setupStage2( );

        int posMayor = show.searchDogMajorScore( );
        ArrayList dogs = show.getDogs( );
        Dog major = ( Dog )dogs.get( posMayor );
        show.orderByPoints( );
        dogs = show.getDogs( );
        Dog dMajor = ( Dog )dogs.get( dogs.size( ) - 1 );

        assertEquals( "It's not the correct dog", dMajor, major );

        setupStage3( );
        posMayor = show.searchDogMajorScore( );
        assertEquals( "The major score dog must not exist", -1, posMayor );
    }

    /**
     * Verifies the method searchDogMinorScore() looking for a dog that must ve found. <br>
     * <b> Methods to prover: </b> <br>
     * searchDogMinorScore. <br>
     * <b> Objective: </b> Prove that the method is able on finding a dog properly. <br>
     * <b> Wanted Results: </b> <br>
     * 1. When looking for a dog must be obtained previously added a different position -1.<br>
     * 2. When looking for a dog's position that there should be -1 returned.
     *
     */
    public void testSearchDogMinorScore( )
    {
        // Configures the proving data
        setupStage2( );

        int posMenor = show.searchDogMinorScore( );
        ArrayList dogs = show.getDogs( );
        Dog minor = ( Dog )dogs.get( posMenor );
        show.orderByPoints( );
        dogs = show.getDogs( );
        Dog dMinor = ( Dog )dogs.get( 0 );

        assertEquals( "It is not the correct dog", dMinor, minor );

        setupStage3( );
        posMenor = show.searchDogMinorScore( );
        assertEquals( "The dog must not exist", -1, posMenor );
    }

    // -----------------------------------------------------------------
    // Auxiliary Methods
    // -----------------------------------------------------------------
    /**
     * Loads the specified exposure dogs from propiagees file.
     * @param file name of the propterties fiel that contains the dogs information
     */
    private void chargeDogs( String file )
    {

        try
        {
            FileInputStream fis = new FileInputStream( new File( file ) );
            Properties propiagees = new Properties( );
            propiagees.load( fis );

            // Loads the dogs
            String data;
            String name;
            String race;
            String image;
            int points;
            int age;
            String aux;
            data = "total.dogs";
            aux = propiagees.getProperty( data );
            cuantity = Integer.parseInt( aux );

            for( int cont = 1; cont <= cuantity; cont++ )
            {
                // Carga un dog
                data = "dog" + cont + ".name";
                name = propiagees.getProperty( data );

                data = "dog" + cont + ".race";
                race = propiagees.getProperty( data );

                data = "dog" + cont + ".race";
                race = propiagees.getProperty( data );

                data = "dog" + cont + ".image";
                image = propiagees.getProperty( data );

                data = "dog" + cont + ".points";
                aux = propiagees.getProperty( data );
                points = Integer.parseInt( aux );

                data = "dog" + cont + ".age";
                aux = propiagees.getProperty( data );
                age = Integer.parseInt( aux );

                show.addDog( name, race, image, points, age );
                fis.close( );
            }
        }
        catch( Exception e )
        {

            fail( "The file couldn't be loaded: " + e.getMessage( ) );
        }
    }
}