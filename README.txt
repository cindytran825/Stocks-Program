Features of the program that works:
Out user interface is interactive -

   Menu displays the commands that the user can input
   These commands are :
   [port-create] which allows the user to create a new portfolio with the name of the
   ticker and the amount of shares they want to add. They are able to add as many as
   they want and name the portfolio.

   [port-manage] allows the user to see a list of existing portfolios that they have created.
   They are to choose the name of the portfolio they want to change the shares to.
          ex. if user has [{APPL,3},{AMZN,4}] in their portfolio,
          they can add more shares to the same ticker.
          user inputs -> {APPL,10}, then it'll replace {APPL,3} from [{APPL,3},{AMZN,4}] to
          be [{APPL,10},{AMZN,4}].
   They are also able to add other tickers. user inputs -> {RDDT,3} to the portfolio,
   updated portfolio -> [{APPL,10},{AMZN,4},{RDDT,3]. Shares cannot be negative.

   [port-view] user is able to view the existing portfolio(s) that they have created.

   [port-eval] allows the user to get the value of the shares from the existing portfolio(s).

   [stock-eval] gets the gain or loss from the company and dates that the user inputs.

   [stock-avg] gets the x-day moving average.

   [stock-cross] determines which days are x-day crossover from the dates given by user.

   [stock-upload] allows the user to upload a file.

   [stock-download] allows the user to download a file.
   (It takes a while to call from the API so it'll show that the file was made, but it requires
   about 2-3 minutes to fully install all the information. This is an API issue as we reused the
   starter code almost exactly. Run this command to also update the latest stock data into the
   csv file.

   [quit] quits.

   [menu] displays the menu.


this program does do error input checking, so you shouldn't be able to crash it.
Whenever you input in a date, it tries to retrieve the most recent data available. if it's a time
period you're giving, it finds the closest time period that is WITHIN the provided time.
