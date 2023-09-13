#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Mar 24 01:39:30 2023

@author: akshaypatade
"""

import heapq

class VolumeLevel:
    
    def __init__(self, volume):
        self.volume = volume
    
    def level(self):
        if self.volume > 100:
            return "high"
        else:
            return "low"


class Stock:
    
    #Initializing the parameters
    def __init__(self, ticker,shares, open_price, close_price, volume):
        self.ticker = ticker
        self.shares = shares
        self.open_price = open_price
        self.close_price = close_price
        self.volume = volume
        

    #Calculating the daily return
    def daily_return(self):
        return (self.close_price - self.open_price) / self.open_price

    #Calculating cost
    def cost(self):
        return self.open_price * self.shares
    
class StockInfo(Stock):
    
    penalty_threshold = 5
    def __init__(self, ticker, shares, open_price, close_price, volume, default_times, years):
        super().__init__(ticker, shares, open_price, close_price, volume)
        self.default_times = default_times
        self.years = years
        self.volume_level = VolumeLevel(self.volume)
        
    def update_years(self):
        self.years = self.years + 1
    
    def daily_return(self):
        
        daily_profit  = super().daily_return()
        if(self.default_times > self.penalty_threshold):
            return daily_profit * 0.9
        
        return daily_profit
    
    def __repr__(self):
        return f"{self.ticker}-{self.open_price}-{self.shares}"
                
portfolio = [
    ('IBM', 100, 91.1, 103, 100),
    ('AAPL', 50, 543.22, 653.1, 200),
    ('FB', 200, 21.09, 25.2, 150),
    ('HPQ', 35, 31.75, 43.89, 120),
    ('YHOO', 45, 16.35, 17.23, 87),
    ('ACME', 75, 115.65, 120.3, 86),
]

#Storing the data as an array of stocks object
stocks = [Stock(ticker, shares, open_price, close_price, volume) for ticker, shares, open_price, close_price, volume in portfolio]


#Calculating the total number of shares
total_shares = sum(stock.shares for stock in stocks)
print("Total number of shares")
print(total_shares)

#Calculating the total cost of shares
total_cost = sum(stock.cost() for stock in stocks)
print("Total cost of shares")
print(total_cost)

#Creating a ticker dictionary
ticker_price_list = [{"ticker": stock.ticker, "open price": stock.open_price} for stock in stocks]
print("Ticker Dictionary")
print(ticker_price_list)

#Sorting the ticker dictionary based on the open price
sorted_ticker_price_list = sorted(ticker_price_list, key=lambda x: x["open price"])
print("Sorted ticker list")
print(sorted_ticker_price_list)

#Get the maximum open price stock
max_open_price_stock = max(ticker_price_list, key=lambda x: x["open price"])
print("Maximum open price stock")
print(max_open_price_stock)

#Find the highest 3 stocks in terms of open price in the ticker price list.
sorted_desc_ticker_price_list = sorted(ticker_price_list, key=lambda x: x['open price'], reverse=True)
top_3_open_prices = heapq.nlargest(3, sorted_desc_ticker_price_list, key=lambda x: x['open price'])
print("Top 3 stocks")
print(top_3_open_prices)

#printing instance of StockInfo
stock_info = StockInfo('YHOO', 200, 18, 19, 1000, 3, 1)
print(stock_info)

