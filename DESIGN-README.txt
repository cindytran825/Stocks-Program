Design -
Our design utilizes the Model, View, Controller design.
We have the Controller interface that has the goControl() method
signature which the StocksController class implements. The controller helps keeps the
view and the model separate.

The Model interface is implemented by the StocksModel class. The methods in this class is
called in the controller. They care called after the user inputs the commands.

The Stocks interface represents all data associated with a stock. It is used to access the
data of the stocks from the data from the API. The Stock class has methods that gets
the data from the stock API and does some of the calculating.

The View interface has the method signatures for the messages that are displayed
for the user. the StockProgramView implements that interface.

The MyDate class is in charge of making sure that the date is valid.

Everytime a user wants a create a portfolio or add to it,
we made a new file to store the data that the user inputs. The Portfolio class
does all the creating and editing the portfolio which is called in the model
when a user needs to access it.

The Dataframe class is used to access the files. It reads the information from the CSV files.
The Alphvantage class was given to us and we used to save the stock files.

