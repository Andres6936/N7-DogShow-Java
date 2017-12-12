package edu.jabs.dogShow.domain;

/**
 * Class that represents a Dog. <br>
 * <b>inv: </b> <br>
 * points >= 0 <br>
 * age > 0 <br>
 * image != null <br>
 * name != null <br>
 * race != null
 */
public class Dog
{
    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Dog's name
     */
    private String name;

    /**
     * Dog's race
     */
    private String race;

    /**
     * The path to the dog's image
     */
    private String image;

    /**
     * Dog's points in the exposition
     */
    private int points;

    /**
     * Dog's age in moths
     */
    private int age;

    // -----------------------------------------------------------------
    // Builders
    // -----------------------------------------------------------------

    /**
     * Builds a new dog by the given parameters. <br>
     * <b>post: </b> The dog was built with the specified parameters.
     * @param pName is the name of the dog - pName != null
     * @param pRace is the race of the dog - pRace != null
     * @param pImage is the path to the image of the dog - pImage != null
     * @param pPoints are the points of the dog - pPoints >= 0
     * @param pAge is the age of the dog in months - pAge > 0
     */
    public Dog( String pName, String pRace, String pImage, int pPoints, int pAge )
    {
        name = pName;
        race = pRace;
        image = pImage;
        points = pPoints;
        age = pAge;

        InvariantVerification( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the dog's name
     * @return dog's name
     */
    public String getName( )
    {
        return name;
    }

    /**
     * Returns the dog's race.
     * @return dog's race
     */
    public String getRace( )
    {
        return race;
    }

    /**
     * Returns the path to the image of the dog.
     * @return the path to the image of the dog
     */
    public String getImage( )
    {
        return image;
    }

    /**
     * Returns the dog's points in the exposition.
     * @return Los puntos del perro en la exposición
     */
    public int getPoints( )
    {
        return points;
    }

    /**
     * Returns the dog's age in months.
     * @return the dog's age.
     */
    public int getAge( )
    {
        return age;
    }

    /**
     * Compares two dogs by their names. <br>
     * @param p name of the dog to which it will be compared - p !=null
     * @return Returns 0 if both dogs have the same name. <br>
     *         Returns -1 if the p dog has a major value for its name. <br>
     *         Returns 1 if the p dog has a minor value for its name. <br>
     */
    public int compareByName( Dog p )
    {
        int comparisonValue = name.compareToIgnoreCase( p.name );
        if(comparisonValue < 0){
            comparisonValue = -1;
        }else if(comparisonValue == 0){
            comparisonValue = 0;
        }else{
            comparisonValue = 1;
        }
        return comparisonValue;
    }

    /**
     * Compares two dogs by their races. <br>
     * @param p is the dog to which it will be compared - p != null
     * @return Returns 0 if both dogs have the same race. <br>
     *         Returns -1 if the p dog has a major value for its race. <br>
     *         Returns 1 if the p dog has a minor value for its race. <br>
     */
    public int compareByRace( Dog p )
    {
        int comparedValue = race.compareToIgnoreCase( p.race );
        if(comparedValue < 0){
            comparedValue = -1;
        }else if(comparedValue == 0){
            comparedValue = 0;
        }else{
            comparedValue = 1;
        }
        return comparedValue;
    }

    /**
     * Compares two dogs by their points sum. <br>
     * @param p Dog to which it will be compared - p!= null
     * @return Returns 0 if both dogs have the same points. <br>
     *         Returns -1 if p dog has a major value for its points . <br>
     *         Returns 1 if p dog has a minor value for its points. <br>
     */
    public int compareByPoints( Dog p )
    {
        if( points == p.points )
            return 0;
        else if( points > p.points )
            return 1;
        else
            return -1;
    }

    /**
     * Compares two dogs by their ages. <br>
     * @param p dog to which it will be compared - p!=null
     * @return Returns 0 if both dogs have the same ages. <br>
     *         Returns -1 if p dog has a major value for its age. <br>
     *         Returns 1 if p dog has a minor value for its age. <br>
     */
    public int compareByAge( Dog p )
    {
        if( age == p.age )
            return 0;
        else if( age > p.age )
            return 1;
        else
            return -1;
    }

    /**
     * Returns a change with the dog's name
     * @return the dog's representation in String
     */
    public String toString( )
    {
        return name + " (" + race + ")";
    }

    // -----------------------------------------------------------------
    // Invariant
    // -----------------------------------------------------------------

    /**
     * Verifies the invariant of the class. <br>
     * <b>inv: </b> height > 0 y age > 0 y image != null y name != null y race != null
     */
    private void InvariantVerification( )
    {
        assert ( points >= 0 ) : "The points cannot be less than 0";
        assert ( age > 0 ) : "Age cannot be 0";
        assert ( image != null ) : "The image cannot be null";
        assert ( name != null ) : "The name cannot be null";
        assert ( race != null ) : "The race cannot be null";
    }
}