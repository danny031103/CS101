// DO NOT TOUCH THIS FILE!
package edu.nyu.cs;

// import junit 4 testing framework
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.contrib.java.lang.system.SystemOutRule; // system rules lib - useful for capturing system output
// import static org.mockito.Mockito.*; // for mocking, stubbing, and spying
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import java.util.Arrays;

import org.junit.After;

public class MopedTest {

    Moped m;
    int[][] mockLocations = {
        {10, 5},
        {125, 10},
        {2, 1},
        {200, 2}
    };

    @ClassRule
    public static final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule();

    @Before
    public void init() {
        // System.out.println("Before...");
        // any pre-test setup here
        try {
            m = new Moped();
        }
        catch (Exception e) {
            assertEquals("Expected to be able to instantiate a new Moped object using a no-args constructor", "In fact, we are unable to instantiate a Moped object using a no-args constructor.");
        }
    }

    @After
    public void cleanup() {
        // any post-test knockdown here
        m = null;
    }
    
    public void implementMovement(Moped m, int[] mockLocation, String mockMovementType) {
        // System.out.println(String.format("Trying moped move %s from original point %s facing %s", mockMovementType, Arrays.toString(m.getLocation()), m.getOrientation()));
        try {
            switch (mockMovementType) {
                case "left":
                    m.goLeft();
                    break;
                case "right":
                    m.goRight();
                    break;
                case "straight":
                    m.goStraight();
                    break;
                case "backwards":
                    m.goBackwards();
                    break;
                case "go to Xi'an Famous Foods":
                    m.goToXianFamousFoods();
                    break;
                default:
                    throw new Exception("No such movement type:" + mockMovementType);
            }
        }
        catch (Exception e) {
            assertEquals(
                String.format("Expected program not to crash when trying to move %s.", mockMovementType),
                String.format("Program crashed when moving %s to %s: %s", mockMovementType, Arrays.toString(mockLocation), e)
            );
        }
    }

    @Test
    public void testInitialLocation() {
        int[] expectedLocation = {10, 5}; // 10th st and 5th ave
        int[] actualLocation = m.getLocation();
        if (!Arrays.equals(actualLocation, expectedLocation)) {
            assertEquals(
                String.format("Expected the moped's initial location to be %s.", Arrays.toString(expectedLocation)), 
                String.format("In fact, the initial location of your moped is %s.  FYI, this is the value returned by the getLocation() method.", Arrays.toString(actualLocation))
            );
        }
    }

    @Test
    public void testInitialOrientation() {
        String expectedOrientation = "south"; // lowercase
        String actualOrientation = m.getOrientation().toLowerCase();
        if (!expectedOrientation.equals(actualOrientation)) {
            assertEquals(
                String.format("Expected the moped's initial orientation to be '%s'.", expectedOrientation), 
                String.format("In fact, the initial orientation of your moped is '%s'.  FYI, this is value is the value returned by the getOrientation() method.", actualOrientation)
            );
        }
    }

    /**
     * Test any kind of movement
     * @param mockOrientation The current moped orientation (i.e. "north", "south", "east", "west")
     * @param movements A String array indicating a series of movements to perform (i.e. "straight", "backwards", "left", "right")
     * @param mockLocations A two-dimensional array of coordinates from which to start.
     * @param expectedLocations A two-dimensional array of coordinates where we expect the moped to end up.
     */
    public TestOutcome testMovement(String mockOrientation, String[] mockMovementSequence, int[][] mockLocations, int[][] expectedLocations, String[] expectedOrientations) {
        TestOutcome o = new TestOutcome(true); // assume passing tests until we find otherwise
        for (int i=0; i<mockLocations.length; i++) {
            int[] mockLocation = mockLocations[i]; // set initial location
            int[] expectedLocation = expectedLocations[i]; // where we expect moped to end up
            // not all tests have a specific expectation of orientation.. .leave blank if none
            String expectedOrientation = "any orientation";  // will hold expected orientation at end, if any has been specified
            if (expectedOrientations.length > 0) expectedOrientation = expectedOrientations[i];
            // System.out.printf("start location: %s; orientation: %s; moves: %s;\n", Arrays.toString(mockLocation), mockOrientation, Arrays.toString(mockMovementSequence));

            try {
                m = new Moped(); // initialize a new moped
                m.setOrientation(mockOrientation); // set initial orientation
                m.setLocation(mockLocation); // move moped to starting location
            }
            catch (Exception e) {
                // failure
                String expectedMsg = "Expected program not to crash when instantiating a new moped and setting its location and orientation.";
                String actualMsg = String.format("Program crashed when instantiating or when setLocation or setOrientation were called location to put moped at %s facing %s: %s", Arrays.toString(mockLocation), mockOrientation, e);
                o = new TestOutcome(false, expectedMsg, actualMsg);
            }
            // implement the movements
            for (String mockMovementType : mockMovementSequence) {
                implementMovement(m, mockLocation, mockMovementType); // do the movement
            }

            // test that the updated location matches expectations
            try {
                int[] actualLocation = m.getLocation();
                String actualOrientation = m.getOrientation();
                boolean isSameLocation = Arrays.equals(expectedLocation, actualLocation);
                boolean isSameOrientation = expectedOrientation.equals(actualOrientation) || expectedOrientation.equals("any orientation"); // check for expected orientation, if specified.  If "any" allowed, ignore.
                // System.out.printf("end location: %s; orientation: %s; expectedLocation: %s; expectedOrientation: %s\n", Arrays.toString(actualLocation), actualOrientation, Arrays.toString(expectedLocation), expectedOrientation);
                if (!isSameLocation || !isSameOrientation) {
                    // failure
                    String expectedMsg = String.format("Expected a moped facing '%s' going %s from %s to end up at %s facing '%s'.", mockOrientation, Arrays.toString(mockMovementSequence), Arrays.toString(mockLocation), Arrays.toString(expectedLocation), expectedOrientation);
                    String actualMsg = String.format("In fact, the moped ended up at %s facing '%s'.  FYI, these incorrect location and/or orientation values were returned by the moped's getLocation() and getOrientation() methods, respectively.", Arrays.toString(actualLocation), actualOrientation);
                    o = new TestOutcome(false, expectedMsg, actualMsg);
                }
                else {
                    // success
                    o = new TestOutcome(true);
                }
            }
            catch (Exception e) {
                // failure
                String expectedMsg = "Expected program not to crash when getting location and orientation.";
                String actualMsg = String.format("Program crashed when moving calling getLocation and/or getOrientation: ", e);
                o = new TestOutcome(false, expectedMsg, actualMsg);
            }
        }
        return o;
    }

    @Test
    public void testGoingStraight() {
        // get outcomes of all related tests
        TestOutcome[] outcomes = {
            testGoStraightFacingNorthward(),
            // testGoStraightFacingSouthward(),
            // testGoStraightFacingEastward(),
            // testGoStraightFacingWestward()
        };
        for (TestOutcome o : outcomes) {
            // if any failed, fail assertion
            if (!o.passed) {
                assertEquals(o.expected, o.actual);
            }
        }
    }
    
    public TestOutcome testGoStraightFacingNorthward() {
        System.out.println("testGoStraightFacingNorthward");
        String mockOrientation = "north";
        int[][] expectedLocations = {
            {11, 5},
            {126, 10},
            {3, 1},
            {200, 2}
        };
        String[] expectedOrientations = {
            "north",
            "north",
            "north",
            "north"
        };
        String[] mockMovements = {"straight"};
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    public TestOutcome testGoStraightFacingSouthward() {
        String mockOrientation = "south";
        String[] mockMovements = {"straight"};
        int[][] expectedLocations = {
            {9, 5},
            {124, 10},
            {1, 1},
            {199, 2}
        };
        String[] expectedOrientations = {
            "south",
            "south",
            "south",
            "south"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    public TestOutcome testGoStraightFacingEastward() {
        String mockOrientation = "east";
        String[] mockMovements = {"straight"};
        int[][] expectedLocations = {
            {10, 4},
            {125, 9},
            {2, 1},
            {200, 1}
        };
        String[] expectedOrientations = {
            "east",
            "east",
            "east",
            "east"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }
        
    public TestOutcome testGoStraightFacingWestward() {
        String mockOrientation = "west";
        String[] mockMovements = {"straight"};
        int[][] expectedLocations = {
            {10, 6},
            {125, 10},
            {2, 2},
            {200, 3}
        };
        String[] expectedOrientations = {
            "west",
            "west",
            "west",
            "west"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }
        
    @Test
    public void testGoingBackwards() {
        // get outcomes of all related tests
        TestOutcome[] outcomes = {
            testGoBackwardsFacingNorthward(),
            testGoBackwardsFacingSouthward(),
            testGoBackwardsFacingEastward(),
            testGoBackwardsFacingWestward()
        };
        for (TestOutcome o : outcomes) {
            // if any failed, fail assertion
            if (!o.passed) {
                assertEquals(o.expected, o.actual);
            }
        }
    }
    
    public TestOutcome testGoBackwardsFacingNorthward() {
        String mockOrientation = "north";
        String[] mockMovements = {"backwards"};
        int[][] expectedLocations = {
            {9, 5},
            {124, 10},
            {1, 1},
            {199, 2}
        };
        String[] expectedOrientations = {
            "north",
            "north",
            "north",
            "north"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    public TestOutcome testGoBackwardsFacingSouthward() {
        String mockOrientation = "south";
        String[] mockMovements = {"backwards"};
        int[][] expectedLocations = {
            {11, 5},
            {126, 10},
            {3, 1},
            {200, 2}
        };
        String[] expectedOrientations = {
            "south",
            "south",
            "south",
            "south"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    public TestOutcome testGoBackwardsFacingEastward() {
        String mockOrientation = "east";
        String[] mockMovements = {"backwards"};
        int[][] expectedLocations = {
            {10, 6},
            {125, 10},
            {2, 2},
            {200, 3}
        };
        String[] expectedOrientations = {
            "east",
            "east",
            "east",
            "east"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }
        
    public TestOutcome testGoBackwardsFacingWestward() {
        String mockOrientation = "west";
        String[] mockMovements = {"backwards"};
        int[][] expectedLocations = {
            {10, 4},
            {125, 9},
            {2, 1},
            {200, 1}
        };
        String[] expectedOrientations = {
            "west",
            "west",
            "west",
            "west"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    @Test
    public void testTurningLeft() {
        // get outcomes of all related tests
        TestOutcome[] outcomes = {
            testGoLeftFacingNorthward(),
            testGoLeftFacingSouthward(),
            testGoLeftFacingEastward(),
            testGoLeftFacingWestward()
        };
        for (TestOutcome o : outcomes) {
            // if any failed, fail assertion
            if (!o.passed) {
                assertEquals(o.expected, o.actual);
            }
        }   
    }
    
    public TestOutcome testGoLeftFacingNorthward() {
        String mockOrientation = "north";
        String[] mockMovements = {"left"};
        int[][] expectedLocations = {
            {10, 6},
            {125, 10},
            {2, 2},
            {200, 3}
        };
        String[] expectedOrientations = {
            "west",
            "west",
            "west",
            "west"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    public TestOutcome testGoLeftFacingSouthward() {
        String mockOrientation = "south";
        String[] mockMovements = {"left"};
        int[][] expectedLocations = {
            {10, 4},
            {125, 9},
            {2, 1},
            {200, 1}
        };
        String[] expectedOrientations = {
            "east",
            "east",
            "east",
            "east"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    public TestOutcome testGoLeftFacingEastward() {
        String mockOrientation = "east";
        String[] mockMovements = {"left"};
        int[][] expectedLocations = {
            {11, 5},
            {126, 10},
            {3, 1},
            {200, 2}
        };
        String[] expectedOrientations = {
            "north",
            "north",
            "north",
            "north"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }
        
    public TestOutcome testGoLeftFacingWestward() {
        String mockOrientation = "west";
        String[] mockMovements = {"left"};
        int[][] expectedLocations = {
            {9, 5},
            {124, 10},
            {1, 1},
            {199, 2}
        };
        String[] expectedOrientations = {
            "south",
            "south",
            "south",
            "south"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    @Test
    public void testTurningRight() {
        // get outcomes of all related tests
        TestOutcome[] outcomes = {
            testGoRightFacingNorthward(),
            testGoRightFacingSouthward(),
            testGoRightFacingEastward(),
            testGoRightFacingWestward()
        };
        for (TestOutcome o : outcomes) {
            // if any failed, fail assertion
            if (!o.passed) {
                assertEquals(o.expected, o.actual);
            }
        }   
    }

    public TestOutcome testGoRightFacingNorthward() {
        String mockOrientation = "north";
        String[] mockMovements = {"right"};
        int[][] expectedLocations = {
            {10, 4},
            {125, 9},
            {2, 1},
            {200, 1}
        };
        String[] expectedOrientations = {
            "east",
            "east",
            "east",
            "east"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    public TestOutcome testGoRightFacingSouthward() {
        String mockOrientation = "south";
        String[] mockMovements = {"right"};
        int[][] expectedLocations = {
            {10, 6},
            {125, 10},
            {2, 2},
            {200, 3}
        };
        String[] expectedOrientations = {
            "west",
            "west",
            "west",
            "west"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    public TestOutcome testGoRightFacingEastward() {
        String mockOrientation = "east";
        String[] mockMovements = {"right"};
        int[][] expectedLocations = {
            {9, 5},
            {124, 10},
            {1, 1},
            {199, 2}
        };
        String[] expectedOrientations = {
            "south",
            "south",
            "south",
            "south"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }
        
    public TestOutcome testGoRightFacingWestward() {
        String mockOrientation = "west";
        String[] mockMovements = {"right"};
        int[][] expectedLocations = {
            {11, 5},
            {126, 10},
            {3, 1},
            {200, 2}
        };
        String[] expectedOrientations = {
            "north",
            "north",
            "north",
            "north"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    @Test
    public void testMultiStepMovements() {
        // get outcomes of all related tests
        TestOutcome[] outcomes = {
            testMultiStepMovementsNorthwards(),
            testMultiStepMovementsSouthwards(),
            testMultiStepMovementsEastwards(),
            testMultiStepMovementsWestwards()
        };
        for (TestOutcome o : outcomes) {
            // if any failed, fail assertion
            if (!o.passed) {
                assertEquals(o.expected, o.actual);
            }
        }   
    }

    public TestOutcome testMultiStepMovementsNorthwards() {
        String mockOrientation = "north";
        String[] mockMovements = {"right", "left", "left", "right", "left"};
        int[][] expectedLocations = {
            {12, 6},    // {10, 5},
            {127, 10},  // {125, 10},
            {4, 3},     // {2, 1},
            {200, 3}    // {200, 2}
        };
        String[] expectedOrientations = {
            "west",
            "west",
            "west",
            "west"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    public TestOutcome testMultiStepMovementsSouthwards() {
        String mockOrientation = "south";
        String[] mockMovements = {"right", "left", "left", "right", "left"};
        int[][] expectedLocations = {
            {8, 4},    // {10, 5},     
            {123, 8},  // {125, 10},   
            {1, 1},     // {2, 1},      
            {198, 1}    // {200, 2}     
        };
        String[] expectedOrientations = {
            "east",
            "east",
            "east",
            "east"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    public TestOutcome testMultiStepMovementsEastwards() {
        String mockOrientation = "east";
        String[] mockMovements = {"right", "left", "left", "right", "left"};
        int[][] expectedLocations = {
            {11, 3},    // {10, 5},     
            {126, 8},  // {125, 10},   
            {3, 1},     // {2, 1},      
            {200, 1}    // {200, 2}     
        };
        String[] expectedOrientations = {
            "north",
            "north",
            "north",
            "north"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    public TestOutcome testMultiStepMovementsWestwards() {
        String mockOrientation = "west";
        String[] mockMovements = {"right", "left", "left", "right", "left"};
        int[][] expectedLocations = {
            {9, 7},    // {10, 5},     
            {124, 10},  // {125, 10},   
            {1, 3},     // {2, 1},      
            {198, 4}    // {200, 2}     
        };
        String[] expectedOrientations = {
            "south",
            "south",
            "south",
            "south"
        };
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        return o;
    }

    @Test
    public void testHoming() {
        String mockOrientation = "north";
        String[] mockMovements = {"go to Xi'an Famous Foods"};
        int[][] expectedLocations = {
            {15, 8},    // {10, 5},     
            {15, 8},  // {125, 10},   
            {15, 8},     // {2, 1},      
            {15, 8}    // {200, 2}     
        };
        String[] expectedOrientations = {};
        TestOutcome o = testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
        // if failed, fail assertion
        if (!o.passed) {
            assertEquals(o.expected, o.actual);
        }
    }
    
    @Test
    public void testGasUsage() {
        String[] mockOrientations = {
            "north",
            "south",
            "east",
            "west"
        };
        String[][] mockMovements = {
            {"straight", "left"},                                          // {10, 5}, 
            {"right", "left", "straight"},                                 // {125, 10}, 
            {"left", "left", "left", "right", "straight"},                 // {2, 1}, 
            {"straight", "backwards", "straight", "straight", "left", "right"}   // {200, 2} 
        };
        int[] expectedGasLevels = {
            90,
            90,
            75,
            70
        };
        for (int i=0; i<mockMovements.length; i++) {
            int[] mockLocation = mockLocations[i];
            String mockOrientation = mockOrientations[i];
            String[] mockMovement = mockMovements[i];
            int expectedGasLevel = expectedGasLevels[i];
            try {
                m = new Moped();
                // System.out.println(String.format("setting location to %s", Arrays.toString(mockLocation)));
                m.setLocation(mockLocation); // initial location
                m.setOrientation(mockOrientation); // initial orientation
                for (String movement : mockMovement) {
                    implementMovement(m, mockLocation, movement); // do the movement
                }
                int actualGasLevel = m.getGasLevel();
                boolean isSame = (expectedGasLevel == actualGasLevel);
                if (!isSame) {
                    assertEquals(
                        String.format("Expected gas levels to be %d%% after starting a moped at %s facing '%s', and then moving it %s.", expectedGasLevel, Arrays.toString(mockLocation), mockOrientation, Arrays.toString(mockMovement)),
                        String.format("In fact, the moped ended up with gas level %d%%.  FYI, this value was returned by the getGasLevel() method.", actualGasLevel)
                    );
                }
                else {
                    assert(true); // all good
                }
            m.printLocation();

            }
            catch (Exception e) {
                assertEquals(
                    "Expected program not to crash when instantiating a moped, moving it and checking gas levels.",
                    String.format("Program crashed when instantiating an object, starting it facing %s at %s, moving %s and then checking gas levels: %s", mockOrientations, Arrays.toString(mockLocation), Arrays.toString(mockMovement), e)
                );
            }
        }
    }


}

class TestOutcome {
    boolean passed;
    String expected;
    String actual;
    // constructor for failed tests
    public TestOutcome(boolean passed, String expected, String actual) {
        this.passed = passed;
        this.expected = expected;
        this.actual = actual;
    }
    // constructor for passed tests
    public TestOutcome(boolean passed) {
        this.passed = passed;
    }
}