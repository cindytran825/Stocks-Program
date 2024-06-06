Design -
Our design utilizes the Model, View, Controller design.
We have the Controller interface that has the goControl() method
signature which the StocksController class implements. The controller helps keeps the
view and the model separate.
The Model interface is implemented by the StocksModel class. The methods in this class is
called in the controller. They care called after the user inputs the commands.
The Stocks interface represents all data associated with a stock. It is used to access the
data of the stocks from the data from the API. The Stock
