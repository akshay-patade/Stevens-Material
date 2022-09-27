#!/usr/bin/env python
# coding: utf-8

# # Part 1 - NumPy Assignment

# #### Follow the instructions provided and complete the code provided in the code cells to answer each question. Every question must be solved using numpy. No manually hardcoded solutions will be accepted.

# #### Import numpy (no other libraries are necessary to complete this assignment)

# In[1]:


import numpy as np


# #### Question 1
# Create a 2 dimensional array that has the following elements:\
# Row 1: 0 1 3 7\
# Row 2: 4 8 0 2

# Print the array along with it's shape

# In[2]:


firstarray = np.array([[0,1,3,7],[4,8,0,2]])
firstarray_shape = np.shape(firstarray)
print("The array is:\n", firstarray)
print("The shape of the array is:", firstarray_shape)


# #### Question 2
# 
# Perform the following operations and print each result(all operations must be done using the numpy library):
# 1. Find the number of non-zero elements in your array
# 2. Delete the numbers at index 2 and index 4 in your array (HINT: your array will be flattened, convert it into a 2x3 matrix {2D Array})
# 3. Use the same array from Qs 2. and insert the number '5' at index 2 and the number '9' at index 4 in your array (HINT: your array will be flattened, convert it into a 2x4 matrix {2D Array})
# 4. Print the magnitude of vector 1 [0, 1, 3, 7] and vector 2 [4, 8, 0, 2] in your array

# In[3]:


non_zero_elements = np.count_nonzero(firstarray)
print("The number of non zero elements in the array are:", non_zero_elements)


# In[4]:


#Index 0 is considered as the first element
array_after_delete = np.delete(firstarray.reshape(1,8),[2,4]).reshape(2,3)
print("The array after deleting the elements at index 2 and 4 is:\n", array_after_delete)


# In[5]:


#Index 0 is considered as the first element
array_after_insert = np.insert(array_after_delete.reshape(1,6),[2,4],[5,9]).reshape(2,4)
print("The array after inserting 5 and 9 is:\n", array_after_insert)


# In[6]:


mag_of_vector1 = np.linalg.norm(np.array([0,1,3,7]))
print("The magnitude of vector 1 is =", mag_of_vector1)


# In[7]:


mag_of_vector2 = np.linalg.norm(np.array([4,8,0,2]))
print("The magnitude of vector 2 is =", mag_of_vector2)


# #### Question 3
# 
# These questions will focus on the the linear algebra functions of the numpy library.
# 
# 1. Create 2 2x2 matrices : Matrix 1 = [[4, 3], [2, 6]] and Matrix 2 = [[6, 5], [7, 8]]
# 2. Find and print the determinants of both these matrices (Round it off to the nearest whole number)
# 3. Multiply both the matrices and print the result
# 4. Sort the result from the dot product along the first axis and print the resulting matrix
# 5. Find the transpose of both the matrices and print the results

# In[8]:


matrix1 = np.array([[4,3],[2,6]])
matrix2 = np.array([[6,5],[7,8]])

print("The first matrix is:\n", matrix1)
print("\nThe second matrix is:\n", matrix2)


# In[9]:


det_1 = np.linalg.det(matrix1).round()
det_2 = np.linalg.det(matrix2).round()

print("The determinant of matrix 1 is:", det_1)
print("The determinant of matrix 2 is:", det_2)


# In[10]:


dot_product = np.dot(matrix1,matrix2)

print("The dot product of the 2 matrices is:\n", dot_product)


# In[11]:


sorted_dot_product = np.sort(dot_product,axis = 0)

print("The sorted dot product is:\n", sorted_dot_product)


# In[12]:


transpose_1 = matrix1.transpose()
transpose_2 = matrix2.transpose()

print("The transpose of the first matrix is:\n", transpose_1)
print("\nThe transpose of the second matrix is:\n", transpose_2)


# #### Question 4
# 
# 1. Create a 1-dimensional numpy array with numbers from 0 to 8 (HINT: do not manually type each number, there is an easier way)
# 2. Use numpy to find the 3rd power of each number in the array (cube of each number)
# 3. Create a 2-dimensional numpy array as: [[20, 10, 40], [30, 70, 50]] and print the matrix
# 4. Find the mean of each row and each column of the 2-D array created above

# In[13]:


new_array = np.arange(0,9)

print("The new 1-D array is:\n", new_array)


# In[14]:


cube_array = np.power(new_array,3)

print("The cube of each number of the original array is:\n", cube_array)


# In[15]:


thirdarray = np.array([[20,10,40],[30,70,50]])

print("The array is:\n", thirdarray)


# In[16]:


mean_rows = thirdarray.mean(axis=1)
mean_columns = thirdarray.mean(axis=0)

print("The means of the rows are:\n", mean_rows)
print("\nThe means of the columns are:\n", mean_columns)


# #### Question 5
# 
# 1. Create two 3x3 matrices, as shown below.\
#     Matrix 1 = [[1,2,0],[3,-1,2],[-2,3,-2]]   
#     Matrix 2 = [[[ 5, -6,  6],[ 0,  7,  3],[-1,  8,  1]]\
#     Print the matrices.
# 2. Check if the matrices are invertible
# 2. If they are invertible, find the inverse of the matrices
# 3. Print the first column of the inverted matrices (Use Slicing) 

# In[17]:


mat1 = np.array([[1,2,0],[3,-1,2],[-2,3,-2]])
mat2 = np.array([[ 5, -6, 6],[ 0, 7, 3],[-1, 8, 1]])

print("The first matrix is:\n", mat1)
print("\nThe second matrix is:\n", mat2)


# In[18]:


# Code for part 2
if(np.linalg.det(mat1).round() != 0):
    print("First matrix is invertible")
else:
    print("First matrix is not invertible")
    
if(np.linalg.det(mat2).round() != 0):
    print("Second matrix is invertible")
else:
    print("Second matrix is not invertible")


# In[19]:


#Mat1 is not invertible. Hence commenting the code
#inv1 = 
inv2 = np.linalg.inv(mat2)

#print("Inverse for mat1 is:", inv1)
print("Inverse for mat2 is:", inv2)


# In[20]:


first_col = inv2[:,0]

print("The first column of the inverted matrix is:", first_col)


# #### Question 6
# 
# 1. Create two 2x2 matrices:\
#    m1=[[5, -10],[-4, 9]]\
#    m2=[[-5, -10],[11, 14]], with dtype as float64\
#    Print the matrices
# 2. Multiply both the matrices using numpy (not-element wise)
# 3. Use numpy to find the element wise product of both the matrices
# 4. Find square root for [[43,9],[22,34]] 

# In[21]:


m1 = np.array([[5, -10],[-4, 9]],dtype='float64')
m2 = np.array([[-5, -10],[11, 14]],dtype='float64')

print("The first matrix is:\n", m1)
print("\nThe second matrix is:\n", m2)


# In[22]:


matrix_mul = np.matmul(m1,m2)

print("m1 x m2 = \n", matrix_mul)


# In[23]:


elem_mul = np.multiply(m1,m2)

print("Element wise product = ", elem_mul)


# In[24]:


sqr_root = np.sqrt(np.array([[43,9],[22,34]]))

print("The square root is = ", sqr_root)


# # Part 2 - Build Your Own Linear Algebra Modules

# #### Write your own linear algebra functions . You should complete the following function and you are NOT allowed to use the build-in functions of numpy for this part of the assignment. 

# In[25]:



def reshape(L, m, n):
    # reshape L into a 2d list with m-by-n elements
    new_array=[]
    if(len(L) != m * n):
        print("Elements cannot be reshaped")
    else:
        for i in range(0,m):
            new=[]
            for j in range(0,n):
                new.append(L[i + j])
            new_array.append(new)
    return new_array
    


# In[26]:


def innprod(u, v):
    # inner product of u and v
    product_sum = 0.0
    for i in range(0,min(len(u),len(v))):
        sum += u[i] * v[i]
    return sum;


# In[27]:


def transpose(M):
    # return transpose matrix
    new_matrix = []
    for i in range(0,len(m)):
        new = []
        for j in range(0,len(m[i])):
            new.append(m[j][i])
        new_matrix.append(new)
    return new_matrix


# In[28]:


def mult(A, B):
    # product of matrices
    
    #Code for matrix multiplication
    
    no_of_rows_a = len(a)
    no_of_cols_a = len(a[0])
    
    no_of_rows_b = len(b)
    no_of_cols_b = len(b[0])
    
    matrix = []
    for i in range(0,no_of_rows_a):
        for j in range(0,no_of_cols_b):
            temp_list =[]
            temp = 0
            for k in range(0,no_of_cols_a):
                temp += A[i][k] * B[k][j]
            temp_list.append(temp)
        matrix.append(temp_list)
    return matrix
    
    
    


# In[ ]:




