Features of the program that works:

--------- TEXT BASED WINDOW (TERMINAL) -------------
Out user interface is interactive -

   Menu displays the commands that the user can input
   These commands are :
   [port-create] which allows the user to create a new portfolio with the name of the
   ticker and the amount of shares they want to add. They are able to add as many as
   they want and name the portfolio.

   [port-manage] opens up the port-manage menu, displaying all the methods the user can do
   that CHANGES the specified portfolio
            [buy] buys a specified stock and logs the transaction down
            [sell] sells a specified stock and logs the transaction
            [balance] balances the portfolio with the given point decimals to each stock
            [finish] return to the main menu

   [port-view] opens up the port-view menu, displaying all the methods the user can do that
   EVALUATES the specified portfolio.
            [composition] gets the portfolio's stock composition on a valid date
            [distribution] gets the portfolio's stock value distribution on a valid date
            [value] gets the total value of the portfolio on a valid date
            [bar-chart] gets the bar chart representation of the portfolio's value over time
            [finish] return to the main menu

   [port-list] user is able to view the existing portfolio(s) that they have created.

   [stock-list] allows the user to see which stocks are available on file

   [stock-view] opens the stock-view menu, displaying all the methods the user can do that
   EVALUATES the specified stock.
            [value] gets the stock value's net growth over a time period
            [moving-avg] gets the stock's moving average
            [crossover] gets the stock's crossover dates
            [bar-chart] views the bar chart of the stock's value over time
            [finish] return to the main menu

   [stock-upload] allows the user to upload a file.

   [stock-download] allows the user to download a file.
   (It takes a while to call from the API so it'll show that the file was made, but it requires
   about 2-3 minutes to fully install all the information. This is an API issue as we reused the
   starter code almost exactly. Run this command to also update the latest stock data into the
   csv file.

   [quit] quits.

   [menu] displays the menu.

------------------------------------------------------------------------------

