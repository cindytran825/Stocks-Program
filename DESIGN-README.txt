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
when a user needs to access it. If the user wishes to delete anything in it, we just assign the
given stock a new current share value (if we had 50 stocks and wanted to subtract 10, we enter
the same name and label the new share count to 40).

The Dataframe class is used to access the files. It reads the information from the CSV files.
The Alphvantage class was given to us and we used to save the stock files.

We had the portfolio class read from csv files because it doesn't make that much sense to us for
the user having to recreate a new portfolio every time they run the program.

==================================================================================
CHANGES:
- change the data type of anything that has to do with shares from int to double. Initially
the directions said that we are not going to allow fractional shares yet, so to ensure that we
worked with integer shares and enforce whole numbers. Now that we can work with fractional shares,
(for balance) we changed all data type integers into data type double
- made changes to the constructor to accommodate for a log. To check value of stocks at given
times, the portfolio will need to implement a log to keep track of transactions, so we had to add
a log to the constructor for initialization as well as a "load" function to read transaction logs
from the file. In doing so, we also changed the format that the file is being read. To accommodate
this new format change, we also had to change the editPortfolio method so that it writes the file
in the proper format. I know this sounds like a lot of change, but it cannot be helped as the format
had to change to accommodate the new directions
