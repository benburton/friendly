# Friendly

Friendly is based on the idea of Zappos' "Face Game" described in Tony Hsieh's book Delivering Happiness. The
application randomly selects a set of 5 employees, displaying an image of one of their faces with a list of their
names. The objective is to correctly guess which employee you're looking at repeatedly so you get to know your
coworkers' names.

## Setting up your data with Friendly

In order to get your employee photograph and name data into Friendly, you should extend the PeopleProvider interface.
For example:

    import java.util.ArrayList;
    import java.util.List;

    import com.friendly.people.Person.Gender;

    public class MyPeopleProvider implements PeopleProvider {
    
        @Override
        public List<Person> getPeople() {
            return new ArrayList<Person>() {
                public static final long serialVersionUID = 1L;
                {
                    add(new Person("Ben", "Burton", Gender.MALE,
                            "http://i.imgur.com/Q6kHo.png"));
                }
            };
        }
    }
    
At present, the only way to obtain the data for this is to write something to inspect your organization's employee
records. I've written an implementation that scrapes our internal wiki and provides the data that way. You might be
able to do something similar if your organization has those types of records available internally.


## Local Mode vs. RESTful Mode

Friendly comes in two default flavors: you can run the Swing application against a local data set, or you can configure
a remote RESTful service to provide data. I recommend running everything locally do to testing, and then moving to the
RESTful solution once you've figured out how to feed Friendly your data. The configuration parameters are contained in
the friendly.properties file.

If you want to run locally, simply set the friendly.properties uselocal variable to true and configure
LocalPersonQuestionFactory to use a PeopleProvider locally (note: this is a bit sloppy at the moment.. could use some
redesign).


### Deploying RESTful Friendly

To get your REST on, you can run a few different commands from Maven. If you'd like to run your RESTful server locally,
embedded Jetty is the way to go. Simply configure your RemotePersonQuestionFactory and use Maven to start up Jetty:

    $ mvn jetty:run
    
Once you're comfortable enough with how things are running, you can deploy a war file and stick it in whatever servlet
container you'd like:

    $ mvn war:war


## Distributing the Friendly client

At some point, you'll want to provide the Friendly client to users within your organization so they can start
identifying their coworkers. To do this, you can package Friendly as an executable Jar using the following Maven
command:

    $ mvn assembly:assembly
    
This will generate a Jar in the target/ subdirectory that you can distribute. It can be run with

    $ java -jar friendly-1.0-SNAPSHOT-jar-with-dependencies.jar

## Friendly TODO List

There's a lot of work to do with regard to making everything more configurable and separately deployable. For example,
I'd like to figure out a convenient way to share the PeopleProvider and Person interfaces so that the RESTful server
and client instance can operate independently.

The Swing interface could use a lot of work. At the moment, it's pretty basic and doesn't look all that great. I had
considered going with JavaFX 2, but I run a Mac and at the moment the current release is Windows-only.

Potentially add a user management and correctness ratio mechanism. It could be neat to allow users to keep track of
how often they answer correctly, although this should be configurable in case this gives users the feel that it's more
of a test as opposed to just a fun way to get to know your coworkers.

I'd also like to add scripts to make the application run locally whenever a computer's screen saver is unlocked. I
believe that this is possible in Linux, but I haven't been able to find resources to do the same thing in Windows or
OS X.