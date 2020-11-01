This project is the implementation of a client for the FTX exchange. Api - https://docs.ftx.com/.

Project based on spring boot, spring websocket and spring integration frameworks.

When starting the program, enter the command to subscribe to the channel. 
For example:
subscribe ticker BTC-PERP
Where "subscribe" - is the operation, "ticker" - is the channel and "BTC-PERP" is the market.
Or you can enter just "test" it will be equivalent to: 
subscribe ticker BTC/USD
