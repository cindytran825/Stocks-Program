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

The design of the command-line went through a bit of restructuring. If the user wants
to make any changes to a portfolio, they will have to call port-manage in the main menu,
and it will bring the user to a manage portfolio menu. In there, all the methods that can
manage the portfolio will be in there (buy / sell / balance). We thought that if we had
implemented where all the methods are separate in the main menu, it would be inconvenient
when you are trying to make several changes at once or have to check it's values separately
each time and have to type in the specified file over and over. We minimized that repetition
by grouping all the file editing methods together and the file evaluation methods
together.

For the same reason above, port-view in the main menu contains all the methods that evaluates
a portfolio (value, composition, distribution, bar-chart).

For the same reason above, stock-view in the main menu cantains all the methods that evaluates
a stock (value/net growth, moving avg, crossover, bar-chart)

The Dataframe class is used to access the files. It reads the information from the CSV files.
The Alphavantage class was given to us and we used to save the stock files.

[from previous assignment] We had the portfolio class read from csv files because it doesn't
make that much sense to us for the user having to recreate a new portfolio every time they
run the program. It eventually worked against us because the new assignment required us to do the
same thing with a different string format.

The DataChart class is used to create the chart when a user wants a representation of the value
of their portfolio through a timespan. It's in charge of building the charting, scaling it and
formatting  it correctly. We have a BarChartWithImp class that implements the
 BarChart interface that extends the DataChart interface.
By implementing this design, we hope that it'll facilitate the incorporation of any other
graphical charts in future implementations.

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
had to change to accommodate the new directions.
- since the format changed, the getValue method had to change just a little bit with the
introduction of a logging system. It originally used the current (latest) transaction available
with the idea that all the portfolio was being evaluated at a single given date. With the new
addition of a logging system and evaluation of different timestamps, the getValue will now call
getComposition to receive the stock count at a certain time period instead.
- as we did not implement a "buy" or a "sell" method buy combined them in the "editPortfolio",
not that we have been given directions to implement the new methods, we've made the edit portfolio
method private and broke it up into buy and sell. The controller and model had to accommodate this
change. I do think that this change is justified as there were no directions saying that changing
the portfolio had to incorporate a buying and selling method. It is possible to infer that
it could've been refactored into one method based on the given directions in part I.

- getMonthLength() in the MyDateWithImpl was a private method and I changed that to public because
I needed to call that method in the portfolio class. I thought this decision was reasonable because
I needed to get the length of the month to get the value or each month in the bar chart (needed to
call the method multiple times) and it didn't make sense for me to have duplicate code when I could
just call a method that was already made.
