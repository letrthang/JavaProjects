
Some notes:

1. entry point: com.lmei.main.Main

2. There is a queue (instrumentsQueue) in class "InstrumentPresenter.java" to store all instruments from all different sources.

3. Each type of instrument also has a queue to store its instruments respectively. 
For example, class "LMEInstrumentPresenter" has a queue "lmeInstrumentsQueue" to support for LME instrument.

4. The program is designed using MVP pattern (Model-View-Presenter).

5. For simple, no Database is implemented (no DAO).

6. build the program with Maven command as: mvn package,...

7. run the program with command: java -jar LMEInstrument-0.0.1.jar

8. requirement at: /resource/docs.txt