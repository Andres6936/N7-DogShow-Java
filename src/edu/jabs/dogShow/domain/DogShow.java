package edu.jabs.dogShow.domain;

import java.util.*;

/**
 * Is the class that is responsible for managing, organizing, loading and saving dogs. <br>
 * <b>inv: </b> <br>
 * dogs != null <br>
 * there are not two dogs with the same name
 */
public class DogShow
{
    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Vector which contains all the dogs
     */
    private ArrayList dogs;

    // -----------------------------------------------------------------
    // Builders
    // -----------------------------------------------------------------

    /**
     * Constructs a new dogs manager, and lets it empty.
     */
    public DogShow( )
    {
        dogs = new ArrayList( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns a Dog's list. The list that is returned is not the same as the one stored in this class, but if you have the same order.
     * @return List of Dogs.
     */
    public ArrayList getDogs( )
    {
        ArrayList listCopy = new ArrayList( dogs );
        return listCopy;
    }

    /**
     * Organize the list of dogs by breed using the bubble algorithm. <br>
     * <b>post: </b>The Dog's list is organized by race (ascendent order).
     */
    public void orderByRace( )
    {
        for( int i = dogs.size( ); i > 0; i-- )
        {
            for( int j = 0; j < i - 1; j++ )
            {
                Dog d1 = ( Dog )dogs.get( j );
                Dog d2 = ( Dog )dogs.get( j + 1 );

                // If necessary, they should share p1 and p2
                if( d1.compareByRace( d2 ) > 0 )
                {
                    dogs.set( j, d2 );
                    dogs.set( j + 1, d1 );
                }
            }
        }
        invariantVerification( );
    }

    /**
     * Organize the list of dogs by name using the bubble algorithm. <br>
     * <b>post: </b>The dog's list is ordered by name (ascendant order).
     */
    public void orderByName( )
    {
        for( int i = dogs.size( ); i > 0; i-- )
        {
            for( int j = 0; j < i - 1; j++ )
            {
                Dog d1 = ( Dog )dogs.get( j );
                Dog d2 = ( Dog )dogs.get( j + 1 );

                // If necessary, they should share p1 and p2
                if( d1.compareByName( d2 ) > 0 )
                {
                    dogs.set( j, d2 );
                    dogs.set( j + 1, d1 );
                }
            }
        }
        invariantVerification( );
    }
    /**
     * Organize the list of dogs for points using the insertion algorithm. <br>
     * <b>post: </b>The dog's list is ordered by points (ascendant order).
     */
    public void orderByPoints( )
    {
        for( int i = 1; i < dogs.size( ); i++ )
        {
            Dog toInsert = ( Dog )dogs.get( i );
            boolean ended = false;
            for( int j = i; j > 0 && !ended; j-- )
            {
                Dog actual = ( Dog )dogs.get( j - 1 );
                if( actual.compareByPoints( toInsert ) > 0 )
                {
                    dogs.set( j, actual );
                    dogs.set( j - 1, toInsert );
                }
                else
                    ended = true;
            }
        }
        invariantVerification( );
    }

    /**
     * Organize the list of dogs by age using the selection algorithm. <br>
     * <b>post: </b>The dog's list is ordered by age (ascendant order).
     */
    public void orderByAge( )
    {
        int initial;

        // In each iteration we know that:
        // 1. All values before dogs [initial] are sorted by age
        // 2. There is no value after dogs [initial-1] is less than dogs [initial-1]
        // In each iteration, we look for the least among dogs [initial] and dogs [end] and is located in dogs [initial]

        for( initial = 0; initial < dogs.size( ); initial++ )
        {
            int minorPosition = initial;
            Dog minorDog = ( Dog )dogs.get( initial );

            // Search the younger dog between initial and final
            for( int i = initial + 1; i < dogs.size( ); i++ )
            {
                Dog dogPosition = ( Dog )dogs.get( i );

                // The dog's current position is smaller than the smallest found so far
                if( dogPosition.compareByAge( minorDog ) < 0 )
                {
                    minorDog = dogPosition;
                    minorPosition = i;
                }
            }

            if( minorPosition != initial )
            {
                Dog temp = ( Dog )dogs.get( initial );
                dogs.set( initial, minorDog );
                dogs.set( minorPosition, temp );
            }

        }
        invariantVerification( );
    }

    /**
     * Finds a dog by its name and returns the position where it is.
     * @param pName is the name of the dog wanted - pName! = Null
     * @return Returns the position where there is a dog with the given name. If there is any dog with that name returns -1
     */
    public int searchForDog( String pName )
    {
        int position = -1;
        boolean finished = false;

        for( int i = 0; i < dogs.size( ) && !finished; i++ )
        {
            Dog dogPosition = ( Dog )dogs.get( i );
            String dogName = dogPosition.getName( );

            // Both names are the same
            if( dogName.equalsIgnoreCase( pName ) )
            {
                position = i;
                finished = true;
            }
        }

        return position;
    }

    /**
     * Finds a dog by using a binary search. <br>
     * <b>pre: </ b> The list of dogs is ordered by name.
     * @param pName is the name of the dog to search - pName! = Null
     * @return The position of the dog with the given name. If the dog does not exist it returns -1.
     */
    public int binarySearchByName( String pName )
    {
        int position = -1;
        int beginning = 0;
        int end = dogs.size( ) - 1;
        Dog toSearch = new Dog( pName, "", "", 1, 1 );
        while( beginning <= end && position == -1 )
        {
            int middle = ( beginning + end ) / 2;
            Dog half = ( Dog )dogs.get( middle );
            if( half.compareByName( toSearch ) == 0 )
            {
                position = middle;
            }
            else if( half.compareByName( toSearch ) > 0 )
            {
                end = middle - 1;
            }
            else
            {
                beginning = middle + 1;
            }
        }
        return position;
    }

    /**
     * Adds a new dog to the exposition. <br>
     * <b>post: </b> The dog was added to the exposition if there is not another dog with the same name.
     * @param pName name of the dog- pName != null
     * @param pRace race of the dog - pRace != null
     * @param pImage path to the image of the dog - pImage != null
     * @param pPoints points of the dog in the show - pPoints >= 0
     * @param pAge dog's age in months - pAge >= 0
     * @return True if the dog was added, false otherwise
     */
    public boolean addDog( String pName, String pRace, String pImage, int pPoints, int pAge )
    {
        int searchedDog = searchForDog( pName );
        boolean added = false;
        if( searchedDog == -1 )
        {
            Dog newDog = new Dog( pName, pRace, pImage, pPoints, pAge );
            dogs.add( newDog );
            added = true;
        }

        invariantVerification( );

        return added;
    }

    /**
     * Looks for the dog which has the major score in the show.
     * @return Returns the position where the dog with the highest score. If there are no dogs in the exhibition returns -1
     */
    public int searchDogMajorScore( )
    {
        int position = -1;

        if( dogs.size( ) > 0 )
        {
            Dog dMajorScore = ( Dog )dogs.get( 0 );
            position = 0;
            for( int i = 1; i < dogs.size( ); i++ )
            {
                Dog dogPosition = ( Dog )dogs.get( i );

                if( dMajorScore.compareByPoints( dogPosition ) == -1 )
                {
                    position = i;
                    dMajorScore = dogPosition;
                }
            }
        }

        return position;
    }

    /**
     * Looks for the dog which has the minor score in the show.
     * @return Returns the position where the dog with the lowest score. If there are no dogs in the exhibition returns -1
     */
    public int searchDogMinorScore( )
    {
        int position = -1;

        if( dogs.size( ) > 0 )
        {
            Dog dMinorScore = ( Dog )dogs.get( 0 );
            position = 0;
            for( int i = 1; i < dogs.size( ); i++ )
            {
                Dog dogPosition = ( Dog )dogs.get( i );

                if( dMinorScore.compareByPoints( dogPosition ) == 1 )
                {
                    position = i;
                    dMinorScore = dogPosition;
                }
            }
        }

        return position;
    }

    /**
     * Looks for the dog which has the highest age
     * @return Returns the position where the dog with age. If there are no dogs in the exhibition returns -1
     */
    public int searchDogHighestAge( )
    {
        int position = -1;

        if( dogs.size( ) > 0 )
        {
            Dog dHighestAge = ( Dog )dogs.get( 0 );
            position = 0;
            for( int i = 1; i < dogs.size( ); i++ )
            {
                Dog dogPosition = ( Dog )dogs.get( i );


                if( dHighestAge.compareByAge( dogPosition ) == -1 )
                {
                    position = i;
                    dHighestAge = dogPosition;
                }
            }
        }

        return position;
    }

    // -----------------------------------------------------------------
    // Invariant
    // -----------------------------------------------------------------

    /**
     * Verifies the class invariant. <br>
     * <b>inv </b> dogs != null & there are not two dogs with the same name
     */
    private void invariantVerification( )
    {
        assert ( dogs != null ) : "The dog's list mustn't be null";
        assert ( !lookForRepeatedDogsByName( ) ) : "HThere are two dogs with the same name";
    }

    /**
     * Verifies if there're two dogs with the same name.
     * @return Returns true if two dogs with the same name, returns false otherwise
     */
    private boolean lookForRepeatedDogsByName( )
    {
        for( int i = 0; i < dogs.size( ); i++ )
        {
            Dog dogI = ( Dog )dogs.get( i );
            for( int j = 0; j < dogs.size( ); j++ )
            {
                if( i != j )
                {
                    Dog dogJ = ( Dog )dogs.get( j );
                    if( dogJ.getName( ).equals( dogI.getName( ) ) )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Extension Points
    // -----------------------------------------------------------------

    /**
     * Extension Method 1.
     * @return Answer 1
     */
    public String metodo1( )
    {
        return "Answer 1";
    }

    /**
     * Extension Method 2.
     * @return Answer 2
     */
    public String metodo2( )
    {
        return "Answer 2";
    }
}