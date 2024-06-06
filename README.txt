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

   [quit] quits.

   [menu] displays the menu.
