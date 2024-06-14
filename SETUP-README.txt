How to run from the jar file:
copy the StocksCindy with the UserPortfolio and CSVFiles in the StocksCindy directory:
1. go to terminal, change current directory to the folder with this assignment (on the same level
as StocksCindy)
2. copy the StocksCindy and its content to the home directory (cp -r StocksCindy ~/StocksCindy)
3. run the jar file from anywhere

there is also a weird bug (I've went to office hours for this and figured out that the problem
was not in the code, but the software), where if you run it, sometimes you get an error saying that
StockApi cannot be found. To fix that (if the error shows up) you need to just delete and recreate
it (full on copy-paste).

To note, as the project is dependent on the csv files available for the stocks, if you want to try
out a stock of your own, you have to DOWNLOAD the stock from the api by running stock-download or
stock-upload. If you're uploading your own file, however, it must be correctly formatted as per the
requirements of the Stocks interface. if you do run stock-download, beware that the API takes a while
to pull all of the information. The controller will say that it's been downloaded (which is true that
the file is created instantly), but you will have to wait until the file actually finishes writing
itself all the data from the API (when it appears on your local folder). Until then, it will just
fail because it can't read the file that hasn't finished writing yet. This time complexity isn't
something we can change because it comes directly from the API and we used the starter code.


How to create a portfolio:
   	- after starting the program, type the command "port-create". It will then ask you to name 	the new portfolio, and you can type in the name of your choosing. A success message should 	pop up and a new portfolio is created.

How to purchase stocks of at least 3 different companies in that portfolio at different dates -
	- after you've created the portfolio, in the main menu, use the command "port-manage". It	will then prompt you for which portfolios you would like to choose from. Enter in one of the
	name that 




and then query the value and cost basis of that portfolio on two specific dates






How to create portfolio with three different stocks:
You would write 'port-create' into the terminal.
it would then ask for the name of the portfolio of your choosing.
you would then add a ticker and the amount of shares. it would then ask you to
add another ticker, you can add as much as you like.
WHen you are done adding the stocks, hit 'j' and that should add the stocks
into the portfolio.
Creating another portfolio:
You can just do 'port-create' again to make another portfolio (preferably with a different name)
and add as many stocks as you like.
'port-view' to see the list of portfolios you have created.

List of Stocks:
GOOG, AMZN, TSLA, APPL

