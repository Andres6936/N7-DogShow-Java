package edu.jabs.dogShow.test;

import junit.framework.TestCase;

import edu.jabs.dogShow.domain.Dog;

/**
 * This is the class used to verify the methods of the class Dog
 */
public class DogTest extends TestCase
{
    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Dog used for the testing cases
     */
    private Dog dog1;

    /**
     * Dog used for the testing cases
     */
    private Dog dog2;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Builds dogs 1 and 2
     */
    private void setUpStage1( )
    {
        dog1 = new Dog( "name1", "race1", "image1", 1, 1 );
        dog2 = new Dog( "name2", "race2", "image2", 2, 2 );
    }

    /**
     * Verifies the builder. <br>
     * <b> Methods to prove: </b> <br>
     * Dog (Builder). <br>
     * <b> Objective: </b> prove that the builder creates a dog correctly. <br>
     * <b> Wanted results: </b> <br>
     * 1. When you create a dog, the object's attributes should stay with the correct values.
     */
    public void testDog( )
    {
        setUpStage1( );

        assertEquals( "The name of the dog is wrong", "name1", dog1.getName( ) );
        assertEquals( "The race of the dog is wrong", "race1", dog1.getRace( ) );
        assertEquals( "The image of the dog is wrong", "image1", dog1.getImage( ) );
        assertEquals( "The age of the dog is wrong", 1, dog1.getAge( ) );
        assertEquals( "The height of the dog is wrong", 1, dog1.getPoints( ) );
    }

    /**
     * Verifies the method compareByPoints. <br>
     * <b> Method to prove: </b> <br>
     * compareByPoints. <br>
     * <b> objective: </b> Prove that the method makes the comparison of two dogs correctly <br>
     * <b> Wanted results: </b> <br>
     * 1. By comparing a dog whose points are lower than those of another the result should be -1. <br>
     * 2. By comparing a dog whose points are equal than those of another the result should be 0. <br>
     * 3. By comparing a dog whose points are higher than those of another the result should be 1.
     */
    public void testCompareByPoints( )
    {
        setUpStage1( );

        assertEquals( "Dog 1 should be minor", -1, dog1.compareByPoints( dog2 ) );
        assertEquals( "Dog 1 and dog 2 should be equal", 0, dog1.compareByPoints( dog1 ) );
        assertEquals( "Dog 2 should be major", 1, dog2.compareByPoints( dog1 ) );
    }

    /**
     * Verifies the method compareByAge. <br>
     * <b> Method to prove: </b> <br>
     * compareByAge. <br>
     * <b> objective: </b> Prove that the method makes the comparison of two dogs correctly <br>
     * <b> Wanted results: </b> <br>
     * 1. By comparing a dog whose age is lower than those of another the result should be -1. <br>
     * 2. By comparing a dog whose age is equal than those of another the result should be 0. <br>
     * 3. By comparing a dog whose age is higher than those of another the result should be 1.
     */
    public void testCompareByAge( )
    {
        setUpStage1( );

        assertEquals( "Dog 1 should be minor", -1, dog1.compareByAge( dog2 ) );
        assertEquals( "Dog 1 and dog 2 should be equal", 0, dog1.compareByAge( dog1 ) );
        assertEquals( "Dog 2 should be major", 1, dog2.compareByAge( dog1 ) );
    }

    /**
     * Verifies the method compareByName. <br>
     * <b> Method to prove: </b> <br>
     * compareByName. <br>
     * <b> objective: </b> Prove that the method makes the comparison of two dogs correctly <br>
     * <b> Wanted results: </b> <br>
     * 1. By comparing a dog whose name is lower than those of another the result should be -1. <br>
     * 2. By comparing a dog whose name is equal than those of another the result should be 0. <br>
     * 3. By comparing a dog whose name is higher than those of another the result should be 1.
     */
    public void testCompararPorname( )
    {
        setUpStage1( );

        assertEquals( "Dog 1 should be minor", -1, dog1.compareByName( dog2 ) );
        assertEquals( "Dog 1 and dog 2 should be equal", 0, dog1.compareByName( dog1 ) );
        assertEquals( "Dog 2 should be major", 1, dog2.compareByName( dog1 ) );
    }

    /**
     * Verifies the method compareByRace. <br>
     * <b> Method to prove: </b> <br>
     * compareByRace. <br>
     * <b> objective: </b> Prove that the method makes the comparison of two dogs correctly <br>
     * <b> Wanted results: </b> <br>
     * 1. By comparing a dog whose race is lower than those of another the result should be -1. <br>
     * 2. By comparing a dog whose race is equal than those of another the result should be 0. <br>
     * 3. By comparing a dog whose race is higher than those of another the result should be 1.
     */
    public void testCompararPorrace( )
    {
        setUpStage1( );

        assertEquals( "El perro 1 debería ser menor", -1, dog1.compareByRace( dog2 ) );
        assertEquals( "El perro 1 y el perro 1 deberían ser iguales", 0, dog1.compareByRace( dog1 ) );
        assertEquals( "El perro 2 debería ser mayor", 1, dog2.compareByRace( dog1 ) );
    }
}