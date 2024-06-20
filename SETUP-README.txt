How to run from the jar file:
move the jar file outside the res folder so that it's on the same level as the StocksCindy directory.
Go to the directory of the assignment (the directory that is holding the StocksCindy directory).
Then, run the jar file from there.

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

	when entering any dates, please keep 0's in front of the units if it's less than 2
	digits for days, 2 digits for month, or 4 digits for years (ex. 4 -> 04)

	- after you've created the portfolio, in the main menu, use the command "port-manage". It	will then prompt you for which portfolios you would like to choose from. Enter in one of the
	name that pops up. Then, type in 'buy' followed by the stock ticker for the transaction. Then a valid
	day, month, and year. repeat this two more times with other stock tickers and other dates.

and then query the value and cost basis of that portfolio on two specific dates
    - once you're done making changes to the portfolio, type 'finish' in the port-manage menu
    and you should be back in the main menu. Type 'port-view' for any analysis features. Select
    the portfolio's name that pops up by typing it out. To
    see the total value of the portfolio, type 'value'. Enter the day, month, year.
    To see the distribution / composition, type 'distribution' or 'composition' and type in the
    day month year. You can do this for any two valid dates


List of Stocks on file:
GOOG 2014-04-15 to 2024-06-03
    three valid dates are:
        2023-05-31
        2023-06-01
        2023-06-02
AMZN 2000-04-11 to 2024-06-03
    three valid dates are:
        2024-05-22
        2024-05-23
        2024-05-24
TSLA 2011-01-05 to 2024-06-03
    three valid dates are:
        2022-12-12
        2022-12-13
        2022-12-14
APPL 2000-05-17 to 2024-06-03
    three valid dates are:
        2023-07-17
        2023-07-18
        2023-07-19

