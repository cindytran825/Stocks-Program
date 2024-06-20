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
- changed methods in the PortfolioWithImpl class to all throw IllegalArgumentException upon
receiving any invalid points. We received deductions from assignment 5 for not throwing the
exceptions in the PortfolioWithImpl class when we did invalid-input-checking in the controller
and main model.
- We didn't change anything else except for the fact that we now have a main class and that
we added a new view and a new controller that implements the controller class. Nothing fundamentally
changed in our program :)