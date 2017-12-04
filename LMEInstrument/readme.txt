
Some notes:

1. The program is designed using MVP pattern (Model-View-Presenter).

2. entry point: com.lmei.main.Main

3. There is a queue (instrumentsQueue) in class "InstrumentPresenter.java" to store all instruments from all different sources.
A thread in this class works as a dispatcher to dispatch all incoming instruments to respective presenters.

4. Also each presenter which takes care for each type of instrument also has a queue to store its instruments respectively. 
For example, class "LMEInstrumentPresenter" has a queue "lmeInstrumentsQueue" to process LME instruments.

5. For simple, no Database is implemented (no DAO).

6. build the program with Maven command as: mvn package,...

7. run the program with command: java -jar LMEInstrument-0.0.1.jar

8. requirement at: /resource/docs.txt

===============

What we have to do to add a new instrument type with new rule:

1. Create a new presenter class for new instrument
2. Create a new model and a new rule class for new instrument.
3. Create a new View for new instrument.
