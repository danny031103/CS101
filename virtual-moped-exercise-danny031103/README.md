# Virtual Moped Exercise

Virtual object-oriented mopeds roaming the streets of New York.

## Requirements

The goal is to create a program that allows a user to drive a virtual moped around the virtual streets of Manhattan, albeit on the Java command line.

The program accepts the following commands from the user:

- "`go left`"
- "`go right`"
- "`straight on`"
- "`back up`"
- "`how we doin'?`"
- "`fill it up`"
- "`park`"
- "`go to Xi'an Famous Foods`"
- "`help`"

See below for details.

### Driving

The user simply instructs the Moped to "`go left`", "`go right`", "`straight on`", or "`back up`", and moped moves one block in the chosen direction.

- The Moped always starts out its life at 10th St. and 5th Ave., facing South.
- The program must announce the Moped's current location and orientation (which cardinal direction it is facing), when it starts, and with every move.
- If the user commands the Moped to "`park`", then the program outputs a message, "We have parked", indicating the moped has been parked on the sidewalk, and quits.
- Turning left while going forwards is a different thing from turning left while in reverse, hence _your moped must keep track of its orientation_.

![Virtual moped movement](./images/Virtual_moped_movement_diagram.png)

### Gas

- The moped's gas tank stores up to 1 gallon. It comes pre-filled.
- Driving the moped burns 1/20th of gallon per city block.
- If the user enters the command, "`how we doin'?`", the program outputs the current level of gasoline in the tank, as a percentage.
- If the Moped runs out of gas, the user is notified that the Moped has run out of gas ("We have run out of gas. Bye bye!") and no longer drives, and the program should quit.
- To refill the gas tank, the user simply instructs the program to "`fill it up`".

### Homing

The Moped has a special "`go to Xi'an Famous Foods`" command that automatically drives the Moped from wherever it happens to be to [https://www.xianfoods.com/ Xi'an Famous Foods] restaurant at 8th Ave. and 15th St.

- the Moped should auto-drive itself one block at a time to the address of Xi'an Famous Foods, outputting its location with each block.
- the Moped must use the same methods to move block-by-block and same amount of gas when homing as when being manually driven. If it needs to fill up gas while homing, it should do so automatically and then continue. The only difference between homing and manual driving is that the user is not being asked what the Moped should do, the Moped is deciding for itself.

Any driving, including driving while "homing", should consume gas, and potentially lead to running out of gas while _en route_.

### Location-based advertising

Our Moped is paid for by advertising, and should output ads when it reaches the locations of our current clients:

- at 79th St. and 8th Ave., the Moped should output an ad for the [American Museum of Natural History](http://www.amnh.org/).
- at 74th St. and 1st Ave., the Moped should output an ad for [Memorial Sloan Kettering](http://www.mskcc.org/).
- at 56th St. and 3rd Ave., the Moped should output an ad for [Tina's Cuban Cuisine](https://tinascubancuisine.com/) restaurant.
- at 12th St. and 4th Ave., the Moped should output an ad for [The Strand](http://www.strandbooks.com/) book shop.

### Help

If the user enters the command, "`help`", the program should display a list of commands that the program understands.

### Assumptions

Our program should follow a few key assumptions:

Geography:

- Assume that Manhattan is just a simple 200x10 grid of numbered city streets, thus ignoring real-world irregularities in geography and street names.
- Users must not be allowed to drive their Moped off the grid... sorry :( . If a user tries to do so, the cardinal orientation of the moped should change, if it has been commanded to `go left` or `go right`, but its location should remain the same.

Street naming:

- Assume that Streets are named 1st St, 2nd St, 3rd St, and so on up to 200th St.
- Assume that Avenues are named 1st Ave, 2nd Ave, 3rd Ave, 4th Ave and so on up to 10th Ave.

Street directions:

- Assume that Street numbers increase as you go North.
- Assume that Avenue numbers increase as you go West.

### Details

Your assignment must adhere to the following requirements:

- include a class named `Moped` that encapsulates all of the attributes and methods that our moped model needs in order to perform its functions.
- include a class named `TestDrive` which includes a `main()` method that starts up the program and facilitates the interaction between the user and the Moped.
- adhere to the object-oriented design principles of encapsulation and abstraction; make all data fields private; provide 'getter' and 'setter' methods as needed to control the behavior of your objects.

### Example session

This is an example session. User input is displayed with the `>` prompt for readability, even though that character should not show up in the output.

```
Thanks for jumping on the moped.  We're currently parked outside Dr. Rossinsky DDS's office at 10th St. and 5th Ave, facing South.  May I say your teeth look very clean.
What would you like to do?  At any time, say "help" for assistance.
> back up
Now at 11th St. and 5th Ave, facing South.
> back up
Now at 12th St. and 5th Ave, facing South.
> go left
Now at 12th St. and 4th Ave, facing West.  Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?
> how we doin'?
The gas tank is currently 85% full.
> go to Xi'an Famous Foods
Now at 12th St. and 5th Ave, facing West.
Now at 12th St. and 6th Ave, facing West.
Now at 12th St. and 7th Ave, facing West.
Now at 12th St. and 8th Ave, facing West.
Now at 13th St. and 8th Ave, facing North.
Now at 14th St. and 8th Ave, facing North.
Now at 15th St. and 8th Ave, facing North.  We have reached Xi'an Famous Foods.  Enjoy your noodles.
> how we doin'?
The gas tank is currently 50% full.
> go left
...etc...
```

### Extra credit

Create two more "drunk driver" mopeds that drive themselves around erratically one block at a time.

- Drunk driver mopeds must be instantiated from the same class as user-driven mopeds. Your job is to modify the code in this class to allow for both kinds of mopeds.
- These robotic self-driving mopeds should automatically follow a random path, or any path you design for them, as they move one block at a time around Manhattan.
- Drunk driver mopeds perform all the same behaviors as user-driven mopeds, except they do so without human intervention.
- Drunk driver mopeds use gas like the user's moped, but they never run out of gas - they automatically fill up their gas when they are nearly out of gas.
- Drunk driver mopeds never crash into each other. They only crash into the user's moped.
- If the user's moped and either of the robotic mopeds are at the same location at the same time, they should crash into each other and the user should be notified that their moped has crashed, is totally unusable, and the program should quit.
- Like the human-driven moped, each drunk driver moped must announce its location and orientation after every move. All output in this version must clearly identify which moped is at which position.

## Folder structure

This project has several important directories:

- `src` - contains the Java source code for the project (i.e. `.java` files)
- `test` - contains code that will help us determine whether the code you have written works correctly. Do not touch this directory!
- `bin` - contains the compiled code (i.e. `.class` files)
- `lib` - contains any dependencies (other libraries of code that the project depends upon to work)

If your project has no dependencies and has not been compiled, you may not see the `lib` or `bin` directories.

## How to test your work

Automated tests that can help determine whether you have done the work correctly have been included within the `test` directory. Use them as follows. A [video tutorial](https://www.youtube.com/watch?v=Af6Ka0Bmflo) shows how to do this and overcome any problems:

- Click the Run and Debug icon in the Visual Studio Code activity bar, then click the play button to run your code without using the automated tests. Do this prior to running the tests to make sure your program seems to behave correctly yourself.
- Open the relevant test file, located within the `test` directory.
- Click the Test icon (a beaker) in the Visual Studio Code activity bar and click the play button to run those tests. Tests that pass will be marked with a green checkmark, while those that fail will be marked with a red "X".
- Each test that fails will show a message with some indication of what went wrong - these messages may help you pinpoint the source of the error.

If you have trouble running the tests from within Visual Studio Code, you can do them from within a Terminal window, assuming the project directory is the current working directory (change `TestClassName` to the relevant name fo the test class.)

```bash
java -cp "bin:lib/*" org.junit.runner.JUnitCore edu.nyu.cs.TestClassName
```

Windows users should replace the `:` in `"bin:lib/*"` with a semi-colon, `;`, i.e. `"bin;lib/*"`

## How to submit this assignment

Once you have completed the changes to th assignment, you are ready to submit it. Do this from within Visual Studio Code.

1. Click on the `Source Control` icon in the left activity bar in Visual Studio Code.
1. In the Source Control side bar, you will see a field named `Message` - type in a unique message about what you have done, e.g. "_Finished assignment!_" or whatever you want to write as a short note to yourself.
1. Hover over the words `Source Control`. You will see a `...` icon appear - click it to see a menu. In that menu, click `Commit`->`Commit`. This logs the changes you've made to the Git project - remember Git is used to keep track of changes.
1. Go to the same menu and click `Push` to submit your assignment - this uploads your updated files to the copy of your respository on GitHub.

![Push changes to GitHub](./images/how_to_push_changes_to_github_from_vscode.png)

That's it... you're done.

## Double-check your submission

Prove to yourself that you have correctly submitted by viewing your repository on the GitHub website - you should see your completed README.md file there.

## Resubmit as many times as you want

You can re-submit as many times as you want before the deadline. Just make changes to the files on your own computer and repeat the process outlined above to upload them to GitHub.
