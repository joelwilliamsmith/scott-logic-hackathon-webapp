package game;

import tradingstrategy.BaseTradingStrategy;

import java.util.LinkedList;

import dataobjects.DailyInput;
import dataobjects.GameData;
import dataobjects.GameOutput;
import exceptions.GameFailureException;
import exceptions.InsufficientFundsException;
import exceptions.InsufficientSharesException;

public class Game {
	private BaseTradingStrategy strategy;
	private GameData gameData;
	
	public Game(BaseTradingStrategy strategy, GameData data) {
		this.strategy = strategy;
		this.gameData = data;
	}
	
	public GameOutput getResult() throws GameFailureException {
		LinkedList<DailyOutput> dailyOutputs = new LinkedList<DailyOutput>();
		
		for(DailyInput input : gameData.getInputs()) {
			try {
				dailyOutputs.add(strategy.makeDailyTrade(input));
			} catch (InsufficientFundsException e) {
				throw new GameFailureException("You tried to make a trade but had insufficient funds. This occurred on day " + input.getDay(), e);
			} catch (InsufficientSharesException e) {
				throw new GameFailureException("You tried to make a trade but had insufficient shares. This occurred on day " + input.getDay(), e);
			}
		}
		DailyOutput last = dailyOutputs.getLast();
		int totalFunds = (int)last.getInvestmentAmount() + last.getAvailableFunds();
		
		return new GameOutput(dailyOutputs, totalFunds);
	}
}
