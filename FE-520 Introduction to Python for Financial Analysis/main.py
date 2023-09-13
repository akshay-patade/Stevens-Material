#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Mar 24 01:12:00 2023

@author: akshaypatade
"""

from Random_Walk import Random_Walk_2D

if __name__ == '__main__':
    

    rw = Random_Walk_2D(init_state = [0 ,0])
    print(rw.generate(num_path = 2, num_step = 3))
    print(rw.generate(num_path = 5, num_step = 50))
    