#!/usr/bin/env python
# coding: utf-8

# ### Name : Akshay Pradeep Patade
# ### CWID : 20009092
# ### email:  apatade@stevens.edu
#  

# ##### **Collaboration Policy.** Homeworks will be done individually: each student must hand in their own answers. Use of partial or entire solutions obtained from others or online is strictly prohibited.

# ### **Late Policy.** Late submission have a penalty of 2\% for each passing hour. 

# ##### **Submission format** :  Complete this jupyter notebook to successfully implement recommender systems on the Movie Lens Dataset. Submit a .py file for this notebook along with the notebook. Go to file -> download as -> python (.py)
# 
# ##### Only submit one .ipynb file and one .py file. The .ipynb file should have answers to all the questions. Do not .zip/.rar files for submission. 

# ##### Download the dataset from the link - https://grouplens.org/datasets/movielens/1m/

# In[1]:


# Import all the required libraries
import numpy as np
import pandas as pd
import operator


# ## Read the Dataset using read_csv function from Pandas
# 

# In[2]:


## Read the dataset from the two files into ratings_data, movies_data and user_data
ratings_data = pd.read_csv('ratings.dat',header = None, sep ='::',engine='python',names=['user_id','movie_id','Ratings','TimeStamp'])
#ratings_data.head()

movies_data = pd.read_csv('movies.dat',header = None, sep="::",engine ='python',names=['movie_id','Title','Genres'],encoding = "ISO-8859-1")
# movies_data

user_data = pd.read_csv('users.dat',header = None, sep ='::',engine='python',names=['user_id','Gender','Age','ID','ZipCode'])
#user_data


# ## Use <b>Numpy</b> to create a ratings data matrix. <b>Print the shape</b> of the matrix

# In[3]:


m = movies_data['movie_id'].max()
n = user_data['user_id'].max()

ratings_data_matrix = np.zeros((m,n),dtype = int)

ratings_data_matrix_1 = ratings_data.to_numpy()

for i in range (0,len(ratings_data_matrix_1)):
    user_id, movie_id, ratings = ratings_data_matrix_1[i,0] - 1, ratings_data_matrix_1[i,1] - 1, ratings_data_matrix_1[i,2]
    ratings_data_matrix[movie_id][user_id] = ratings

print("Shape of the matrix is " +str(ratings_data_matrix.shape))


# ## Normalize the Ratings Data matrix

# In[4]:


mean_array = ratings_data_matrix.mean(axis = 0)
# print(mean_array[0])
standard_deviation_array = ratings_data_matrix.std(axis = 0)
# mean_array.shape
# standard_deviation_array.shape
ratings_data_matrix = (ratings_data_matrix - mean_array) / standard_deviation_array
# print(ratings_data_matrix)
# print(ratings_data_matrix)
     
# # scaler = StandardScaler()
# ratings_data_matrix_2 = preprocessing.normalize(ratings_data_matrix, axis = 0)
# print(ratings_data_matrix_2)


# ## Use the SVD function from numpy to perform SVD on the normalised ratings data matrix. Print the shapes of U, S, V matrices.

# In[5]:


u, s, vtranspose = np.linalg.svd(ratings_data_matrix)
print("Shape of U is "+str(u.shape))
print("Shape of S is "+str(s.shape))
print("Shape of V is "+str(vtranspose.shape))
# print(u)
# print(s)
# print(vtranspose)


# ## Explain the differences between Cosine similarity and Euclidean distance. What are they and what do they do? Which one is better for movie recommendation and why?

# Cosine similarity is a measurement technique which is used to measure the angles betweeen the two vectors. This technique is used to check the similarity between the two vectors. Two vectors will be similar if the angle between the two vectors is minimal. For example if the angle between the two vectors is 90 degress therefore its cosine similarity will be zero indicating that the two vectors are perpendicular to each other and they are not similar. Formula for cosine similarity is cos(theta) = a . b / ||a|| ||b|| where a and b are the two vectors in the multidimensional space.
# 
# Euclidean distance is used to measure the distance between the two vectors which are present in the euclidean space.It is given by the formula square root of (x1-x2) square + (y1-y2) square where x1 & x2 are the coordinates of the two vectors in x coordinate and y1-y2 are the coordinates of the two vectors in y vecotrs.
# 
# Difference Between Cosine Similarity & Euclidean distance.
# Cosine similarity measures the angle between two vectors whereas euclidean measure the distance between two vectors.
# 
# Cosine similarity is better for movie recommendation because we have a text data given to us and we won't be able to find any pattern if we apply euclidean distance.The cosine similarity is advantageous because, despite the fact that the two comparable data objects are separated by the Euclidean distance due to their size, they may have a smaller angle between them. The greater the resemblance, the smaller the angle.
# 

# ### Based on cosine similarity and the matrix decomposition from SVD, sort the movies which are most similar and return the top 5 movies that match a given a movie title

# In[6]:


def cosine_similarity(data, movie_id, top_n):
    index = movie_id - 1 #
    movie_row = reduced_data[index, :]
    magnitude = np.sqrt(np.einsum('ij, ij -> i', reduced_data, reduced_data))
    similarity = np.dot(movie_row, reduced_data.T) / (magnitude[index] * magnitude)
    sort_indexes = np.argsort(-similarity)
    return sort_indexes[:top_n]


# In[7]:


def print_similar_movies(movie_data, movie_id, top_indexes):
    print('Recommendations for {0}: \n'.format(
    movie_data[movie_data.movie_id == movie_id].Title.values))
    for id in top_indexes + 1:
        print(movie_data[movie_data.movie_id == id].Title.values)


# In[10]:


k = 100

movie_title=  'American President, The (1995)'
movie = movies_data[movies_data.Title == movie_title]

movie_id = int(movie['movie_id'])


n = 5
reduced_data= vtranspose[:, :k]
indexes = cosine_similarity(reduced_data, movie_id, n)
print_similar_movies(movies_data,movie_id,indexes)


# In[ ]:





# In[ ]:




