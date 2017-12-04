entry point: com.lmei.main.Main

Some notes:
1. There is a queue (instrumentsQueue) in class "InstrumentPresenter.java" to store all instruments from all different sources.

2. Each type of instrument also has a queue to store its instruments respectively. 
For example, class "LMEInstrumentPresenter" has a queue "lmeInstrumentsQueue" to support for LME instrument.

3. The program is design using MVP pattern (Model-View-Presenter).

4. For simple, no Database is implemented.

5. build program with Maven command as: mvn package,...