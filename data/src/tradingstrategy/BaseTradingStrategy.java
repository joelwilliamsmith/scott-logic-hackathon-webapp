package tradingstrategy;

import game.DailyOutput;
import game.TradingManager;
import dataobjects.DailyInput;
import exceptions.InsufficientFundsException;
import exceptions.InsufficientSharesException;

public abstract class BaseTradingStrategy {

	protected TradingManager tradingManager;

	protected BaseTradingStrategy () {
		tradingManager = new TradingManager();
	}

	public abstract DailyOutput makeDailyTrade(DailyInput input) throws InsufficientFundsException, InsufficientSharesException;
}