#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Mar 21 13:37:19 2023

@author: akshaypatade
"""

import random
class Random_Walk_2D:
    
    #Declaring a variable to store the initial state
    def __init__(self, init_state):
        self.current_state = init_state
    
    #Declaring a function to store all the paths
    def generate(self, num_path, num_step):
        
        #Storing all the paths
        paths = [];
        for i in range(num_path):
            path = []
            path.append(self.current_state)
            x, y = self.current_state
            for j in range(1, num_step):
                
                """Generating a random number for x coordinate 1 is declared as
                head and 0 is declared as tails"""
                
                random_number = random.randint(0, 1)
                if(random_number == 1):
                    x = x + 1
                else:
                    x = x - 1
                
                """Generating a random number for y coordinate 1 is declared as
                head and 0 is declared as tails"""
                random_number = random.randint(0, 1)
                if(random_number == 1):
                    y = y + 1
                
                else:
                    y = y - 1
                
                #Creating new variables to store the data as a tuple
                new_data = (x, y)
                
                #Appending the data to list
                path.append(new_data)
            
            #Appending the data to the list
            paths.append(path)
        
        #Returning the paths
        return paths
                